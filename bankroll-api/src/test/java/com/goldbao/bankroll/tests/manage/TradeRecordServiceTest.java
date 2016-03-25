package com.goldbao.bankroll.tests.manage;

import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.enums.EnumPayType;
import com.goldbao.bankroll.model.trade.Settlement;
import com.goldbao.bankroll.model.trade.TradeRecord;
import com.goldbao.bankroll.service.manage.trade.ManageTradeRecordService;
import com.goldbao.bankroll.service.trade.RechargeService;
import com.goldbao.pay.*;
import com.goldbao.utils.CommonUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * @author shuyu.fang
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:bankroll-api-service.xml", "classpath:bankroll-api-pay.xml" })
public class TradeRecordServiceTest {

    private ManageTradeRecordService manageTradeRecordService;

    private RechargeService rechargeService;

    private PayApi cfcaPayApi;

    // 同步支付状态
    @Test
    public void testSyncTradeRecordStatus() {

        Date date = new Date();

        List<TradeRecord> tradeRecordList = manageTradeRecordService.getTradeRecordOfWaitSync(date, EnumPayType.CFCA);

        if (tradeRecordList != null && tradeRecordList.size() > 0) {

            for (TradeRecord tradeRecord: tradeRecordList) {
                QueryPayParameter param = new QueryPayParameter();

                param.setSerialNo(tradeRecord.getSerialNo());

                try {
                    QueryPayResult result = cfcaPayApi.queryPay(param);
                    if (result != null && result.getStatus().equals("20")) {
                        if (rechargeService.updateDealRecharge(result.getOrderNo(), result.getSerialNo(), EnumPayType.CFCA)) {

                        }
                    }

                } catch (PayException ex) {
                   ex.printStackTrace();
                } catch (ServiceException ex) {
                   ex.printStackTrace();
                }

            }

        }

        System.out.println(tradeRecordList);
    }

    // 同步订单为已结算
    @Test
    public void testSyncTradeRecordToSettlement() {

        // 获取截至目前未结算的订单
        // 由于有支付状态同步接口，为了保证效率，这里只同步确定为成功，但是未结算的订单
        // 汇总待结算的金额，并记录订单
        // 进行结算操作
        // 修改状态为已结算，并记录结算

        Date date = new Date();
        // cfca
        EnumPayType payType = EnumPayType.CFCA;
        List<TradeRecord> tradeRecordList = manageTradeRecordService.getTradeRecordOfSuccess(date, payType);

        Settlement settlement = null;
        try {
            settlement = manageTradeRecordService.addSettlement(tradeRecordList);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(settlement);
        if (settlement != null) {
            try {
                SettlementParameter param = new SettlementParameter();
                param.setOrderNo(CommonUtil.CFCA_ORDER_NO);
                param.setSerialNumber(settlement.getSerialNo());
                param.setAmount(settlement.getAmount());
                BankAccount2 bankAccount = new BankAccount2();
                bankAccount.setBankID("700");
                bankAccount.setAccountName("aa");
                bankAccount.setAccountNumber("1111");
                bankAccount.setBranchName("ccs");
                bankAccount.setProvince("sssss");
                bankAccount.setCity("aaaaa");
                param.setBankAccount(bankAccount);
                SettlementResult result = cfcaPayApi.settlement(param);
                if (result.getStatus()!=null && result.getStatus().equals("20")) {
                    manageTradeRecordService.updateSettlementToFinish(settlement.getId());

                }
            } catch (PayException ex) {

            }
        }

        // other...
    }

    @Autowired
    public void setManageTradeRecordService(ManageTradeRecordService manageTradeRecordService) {
        this.manageTradeRecordService = manageTradeRecordService;
    }
    @Autowired
    public void setRechargeService(RechargeService rechargeService) {
        this.rechargeService = rechargeService;
    }
    @Autowired
    public void setCfcaPayApi(PayApi cfcaPayApi) {
        this.cfcaPayApi = cfcaPayApi;
    }
}
