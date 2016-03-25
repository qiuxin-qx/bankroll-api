package com.goldbao.bankroll.manage.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldbao.bankroll.model.bankroll.BankrollHomsInfo;
import com.goldbao.bankroll.model.bankroll.BankrollRecord;
import com.goldbao.bankroll.model.homs.HomsCombAsset;
import com.goldbao.bankroll.model.homs.HomsCombInfo;
import com.goldbao.bankroll.model.homs.HomsUser;
import com.goldbao.bankroll.service.homs.HomsService;
import com.goldbao.bankroll.service.manage.bankroll.ManageBankrollService;
import com.goldbao.homs.HomsEvent;
import com.goldbao.homs.HomsException;
import com.goldbao.homs.result.HomsCombInfoResult;
import com.goldbao.homs.result.HomsLoginResult;
import com.goldbao.homs.result.HomsQueryCombAssetResult;
import com.goldbao.homs.result.HomsQueryFutureLoanResult;
import com.goldbao.utils.MathUtil;

/**
 * homs操作
 * @author shuyu.fang
 */
@Controller
@RequestMapping("/admin/homs")
public class ManageHomsController {

    private HomsService homsService;

    private HomsEvent homsEvent;

    private final static String operatorNo = "62519002";
    private final static String operatorPwd = "032776";

    private final static Logger logger = LoggerFactory.getLogger(ManageHomsController.class);
    private ManageBankrollService manageBankrollService;

    @RequestMapping("/futureLoan")
    public @ResponseBody  List<HomsQueryFutureLoanResult> futureLoan() {

        // 1. 从数据库获取管理员用户的token
        // 2. 获取数据，如果获取不到，则重新登录homs并获取到新的token
        List<HomsQueryFutureLoanResult> futureLoans = null;
        try {
            String homsToken = getHomsToken();
            // 1. 先获取配资在homs端的信息（借款信息）
            futureLoans = homsEvent.queryFutureLoan(homsToken);
        } catch (HomsException ex) {
            ex.printStackTrace();
            String homsToken = loginHoms();
            // 1. 先获取配资在homs端的信息（借款信息）
            futureLoans = homsEvent.queryFutureLoan(homsToken);
        }

        return futureLoans;
    }

    @RequestMapping("/combInfo")
    public @ResponseBody  List<HomsCombInfoResult> combInfo() {
        // 1. 从数据库获取管理员用户的token
        // 2. 获取数据，如果获取不到，则重新登录homs并获取到新的token
        List<HomsCombInfoResult> combInfos = null;
        try {
            String homsToken = getHomsToken();
            // 1. 先获取配资在homs端的信息（借款信息）
            combInfos = homsEvent.queryCombInfo(homsToken, "", "");
        } catch (HomsException ex) {
            ex.printStackTrace();
            String homsToken = loginHoms();
            combInfos = homsEvent.queryCombInfo(homsToken, "", "");
        }

        if (combInfos.size() > 0) {
            List<HomsCombInfo> list = new ArrayList<HomsCombInfo>();
            for (HomsCombInfoResult item: combInfos) {
                HomsCombInfo combInfo = new HomsCombInfo();
                BeanUtils.copyProperties(item, combInfo);
                list.add(combInfo);
            }
            homsService.addCombInfos(list);
        }

        return combInfos;
    }

    @RequestMapping("/combAsset")
    public @ResponseBody String combAsset() {
        String homsToken = getHomsToken();
        List<HomsCombInfo> list = homsService.getCombInfoList();
        if (list != null) {
            for (HomsCombInfo combInfo: list) {
                HomsQueryCombAssetResult combAsset = homsEvent.queryCombAsset(homsToken, combInfo.getFundAccount(), combInfo.getCombineId());
                if (combAsset != null) {
                    HomsCombAsset asset = new HomsCombAsset();
                    BeanUtils.copyProperties(combAsset, asset);
                    homsService.addOrUpdateCombAsset(asset);
                }
            }
        }
        return "000";
    }

    @RequestMapping("/syncHomsJob")
    public @ResponseBody String syncHomsJob() {
        String token = loginHoms();
        // 获取homs的借贷记录，以和我们平台的账号绑定
        List<HomsQueryFutureLoanResult> futureLoans = homsEvent.queryFutureLoan(token);
        if (futureLoans != null && futureLoans.size() > 0) {
            for (HomsQueryFutureLoanResult futureLoan : futureLoans) {
                syncHomsRecord(futureLoan, token);
            }
        }
        return "000";
    }

    private void syncHomsRecord(HomsQueryFutureLoanResult futureLoan, String homstoken) {
        String operatorNo = futureLoan.getOperatorNo();
        try {
            BankrollRecord record = manageBankrollService.getBankrollRecordByOperatorNo(operatorNo);
            if (record != null) {
//                String combineId = futureLoan.getCombineId();
//                String assetId = futureLoan.getAssetId();

                BankrollHomsInfo homsInfo = manageBankrollService.getBankrollHomsInfo(record.getId());
                if(homsInfo == null) {
                    homsInfo = new BankrollHomsInfo();
                    BeanUtils.copyProperties(futureLoan, homsInfo);
                    homsInfo.setOpenValue(MathUtil.format(futureLoan.getStopValue()));
                    homsInfo.setUpdateTime(new Date());
                    homsInfo.setRecord(record);
                    manageBankrollService.addBankrollHomsInfo(homsInfo);
                } else {
                    BeanUtils.copyProperties(futureLoan, homsInfo);
                    homsInfo.setOpenValue(MathUtil.format(futureLoan.getStopValue()));
                    homsInfo.setUpdateTime(new Date());
                    manageBankrollService.updateBankrollHomsInfo(homsInfo);
                }

                if (homsInfo != null) {
//                    homsEvent.queryComboFund(homstoken, );
                }

            }
        } catch (Exception ex) {
            logger.error("同步配资账号{}, 失败原因{}", operatorNo, ex.getMessage());
            ex.printStackTrace();
        }
    }



    public String getHomsToken() {
        HomsUser homsUser = homsService.getHomsUser(operatorNo);
        String homsToken = "";
        if (homsUser == null) {
            homsToken = loginHoms();
        } else {
            homsToken = homsUser.getUserToken();
        }
        return homsToken;
    }

    public String loginHoms() {
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
