package com.goldbao.bankroll.tests.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.goldbao.bankroll.model.bankroll.BankrollHomsInfo;
import com.goldbao.bankroll.model.bankroll.BankrollRecord;
import com.goldbao.bankroll.model.homs.HomsUser;
import com.goldbao.bankroll.service.homs.HomsService;
import com.goldbao.bankroll.service.manage.bankroll.ManageBankrollService;
import com.goldbao.homs.HomsEvent;
import com.goldbao.homs.result.HomsLoginResult;
import com.goldbao.homs.result.HomsQueryFutureLoanResult;
import com.goldbao.utils.MathUtil;

/**
 * @author shuyu.fang
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:bankroll-api-service.xml", "classpath:bankroll-api-homs.xml"})
public class BankrollHomsScheduleTest {

    private ManageBankrollService manageBankrollService;
    private HomsService homsService;

    private HomsEvent homsEvent;

    private String operatorNo = "62519001";
    private String operatorPwd = "032776";

    private final static Logger logger = LoggerFactory.getLogger(BankrollHomsScheduleTest.class);


    @Test
    public void testSyncHomsJob() {
        String token = loginHoms();
        // 获取homs的借贷记录，以和我们平台的账号绑定
        List<HomsQueryFutureLoanResult> futureLoans = homsEvent.queryFutureLoan(token);
        if (futureLoans != null && futureLoans.size() > 0) {
            for (HomsQueryFutureLoanResult futureLoan : futureLoans) {
                syncHomsRecord(futureLoan);
            }
        }
    }

    private void syncHomsRecord(HomsQueryFutureLoanResult futureLoan) {
        String operatorNo = futureLoan.getOperatorNo();
        try {
            BankrollRecord record = manageBankrollService.getBankrollRecordByOperatorNo(operatorNo);
            if (record != null) {
                String combineId = futureLoan.getCombineId();
                String assetId = futureLoan.getAssetId();

                BankrollHomsInfo homsInfo = manageBankrollService.getBankrollHomsInfo(record.getId());
                if(homsInfo == null) {
                    homsInfo = new BankrollHomsInfo();
                    BeanUtils.copyProperties(futureLoan, homsInfo);
                    homsInfo.setOpenValue(MathUtil.format(futureLoan.getStopValue()));
                    homsInfo.setUpdateTime(new Date());
                    boolean flag = manageBankrollService.addBankrollHomsInfo(homsInfo);
                } else {
                    BeanUtils.copyProperties(futureLoan, homsInfo);
                    homsInfo.setOpenValue(MathUtil.format(futureLoan.getStopValue()));
                    homsInfo.setUpdateTime(new Date());
                    boolean flag = manageBankrollService.updateBankrollHomsInfo(homsInfo);
                }
            }
        } catch (Exception ex) {
            logger.error("同步配资账号{}, 失败原因{}", operatorNo, ex.getMessage());
            ex.printStackTrace();
        }
    }


    private String loginHoms() {
        HomsLoginResult result = homsEvent.login(operatorNo, operatorPwd);
        HomsUser homsUser = homsService.login(operatorNo, operatorPwd, result.getUserToken());
        return homsUser.getUserToken();
    }

    @Autowired
    public void setManageBankrollService(ManageBankrollService manageBankrollService) {
        this.manageBankrollService = manageBankrollService;
    }
    @Autowired
    public void setHomsService(HomsService homsService) {
        this.homsService = homsService;
    }

    @Autowired
    public void setHomsEvent(HomsEvent homsEvent) {
        this.homsEvent = homsEvent;
    }
}
