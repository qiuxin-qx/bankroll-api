package com.goldbao.bankroll.tests.manage;

import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.PageableList;
import com.goldbao.bankroll.model.bankroll.BankrollHomsInfo;
import com.goldbao.bankroll.model.bankroll.BankrollHomsInfoDTO;
import com.goldbao.bankroll.model.bankroll.BankrollHomsInfoLog;
import com.goldbao.bankroll.model.bankroll.BankrollRecord;
import com.goldbao.bankroll.model.enums.EnumBankrollRecordStatus;
import com.goldbao.bankroll.model.homs.HomsOperatorInfo;
import com.goldbao.bankroll.service.homs.HomsService;
import com.goldbao.bankroll.service.manage.bankroll.ManageBankrollService;
import com.goldbao.bankroll.service.manage.homs.ManageHomsService;
import com.goldbao.utils.MathUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * @author shuyu.fang
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:bankroll-api-service.xml"})
public class ManageHomsServiceTest {

    private ManageHomsService manageHomsService;

    private HomsService homsService;
    private ManageBankrollService manageBankrollService;

    // 获取homs operator列表
    @Test
    public void testGetHomsOperatorList() {
        String operatorNo = "";
        PageableList<HomsOperatorInfo> operatorList = manageHomsService.getHomsOperatorList(operatorNo, 1, 10);

    }

    @Test
    public void testSyncHomsBankrollRecordJob() {
        List<BankrollRecord> records = manageBankrollService.getBankrollRecordBySatus(EnumBankrollRecordStatus.ALLOCATIONED_HOMS, 1L);
        if (records != null) {
            for (BankrollRecord record: records) {
                BankrollHomsInfoDTO dto = homsService.getHomsInfoDTO(record.getHomsOperatorNo());
                if (dto != null) {
                    BankrollHomsInfo homsInfo = manageBankrollService.getBankrollHomsInfo(record.getId());
                    try {
                        if (homsInfo == null) {
                            homsInfo = new BankrollHomsInfo();
                            BeanUtils.copyProperties(dto, homsInfo);
                            homsInfo.setRecord(record);
                            homsInfo.setAssetTotalValue(MathUtil.format(dto.getAssetTotalValue(), 3));
                            homsInfo.setAssetValue(MathUtil.format(dto.getAssetValue(), 3));
                            homsInfo.setCurLoan(MathUtil.format(dto.getCurLoan(), 3));
                            homsInfo.setCurrentCash(MathUtil.format(dto.getCurrentCash(), 3));
                            homsInfo.setOpenValue(MathUtil.format(dto.getOpenValue(), 3));
                            homsInfo.setWarningValue(MathUtil.format(dto.getWarningValue(), 3));
                            homsInfo.setBondAsset(MathUtil.format(dto.getBondAsset(), 3));
                            homsInfo.setStockAsset(MathUtil.format(dto.getStockAsset(), 3));
                            homsInfo.setFundAsset(MathUtil.format(dto.getFundAsset(), 3));
                            homsInfo.setHgAsset(MathUtil.format(dto.getHgAsset(), 3));
                            homsInfo.setFutureAsset(MathUtil.format(dto.getFutureAsset(), 3));
                            homsInfo.setUpdateTime(new Date());
                            manageBankrollService.addBankrollHomsInfo(homsInfo);
                        } else {
                            BeanUtils.copyProperties(dto, homsInfo);
                            homsInfo.setAssetTotalValue(MathUtil.format(dto.getAssetTotalValue(), 3));
                            homsInfo.setAssetValue(MathUtil.format(dto.getAssetValue(), 3));
                            homsInfo.setCurLoan(MathUtil.format(dto.getCurLoan(), 3));
                            homsInfo.setCurrentCash(MathUtil.format(dto.getCurrentCash(), 3));
                            homsInfo.setOpenValue(MathUtil.format(dto.getOpenValue(), 3));
                            homsInfo.setWarningValue(MathUtil.format(dto.getWarningValue(), 3));
                            homsInfo.setBondAsset(MathUtil.format(dto.getBondAsset(), 3));
                            homsInfo.setStockAsset(MathUtil.format(dto.getStockAsset(), 3));
                            homsInfo.setFundAsset(MathUtil.format(dto.getFundAsset(), 3));
                            homsInfo.setHgAsset(MathUtil.format(dto.getHgAsset(), 3));
                            homsInfo.setFutureAsset(MathUtil.format(dto.getFutureAsset(), 3));
                            homsInfo.setUpdateTime(new Date());
                            manageBankrollService.updateBankrollHomsInfo(homsInfo);
                        }

                        BankrollHomsInfoLog homsInfoLog = new BankrollHomsInfoLog();
                        BeanUtils.copyProperties(homsInfo, homsInfoLog, "addTime", "ver", "dr");

                        manageBankrollService.addBankrollHomsInfoLog(homsInfoLog);
                    } catch (ServiceException ex) {
                        ex.printStackTrace();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    @Autowired
    public void setManageHomsService(ManageHomsService manageHomsService) {
        this.manageHomsService = manageHomsService;
    }
    @Autowired
    public void setHomsService(HomsService homsService) {
        this.homsService = homsService;
    }
    @Autowired
    public void setManageBankrollService(ManageBankrollService manageBankrollService) {
        this.manageBankrollService = manageBankrollService;
    }
}
