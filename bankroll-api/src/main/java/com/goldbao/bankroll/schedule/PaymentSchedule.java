package com.goldbao.bankroll.schedule;

import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.enums.EnumPayType;
import com.goldbao.bankroll.model.trade.Settlement;
import com.goldbao.bankroll.model.trade.TradeRecord;
import com.goldbao.bankroll.service.manage.trade.ManageTradeRecordService;
import com.goldbao.bankroll.service.trade.RechargeService;
import com.goldbao.pay.*;
import com.goldbao.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 *
 * @author shuyu.fang
 */
public class PaymentSchedule {

    private ManageTradeRecordService manageTradeRecordService;

    private RechargeService rechargeService;

    private PayApi cfcaPayApi;

    // 1. 保证支付的业务通畅，需要一个定期查询支付状态的功能
    // 2. 查询状态的查询需要一些策略保证，
    //    比如过期时间设定
    //


    // 支付状态同步
    public void syncPaymentStatus() {

        /**
         *
         * 获取支付状态为未支付的订单
         * 去支付方获取当前支付状态
         * 如果为已支付，则修改将该笔订单处理
         * 订单超时时间，暂定24小时
         */

        Date date = new Date();
        // cfca
        EnumPayType payType = EnumPayType.CFCA;
        List<TradeRecord> tradeRecordList = manageTradeRecordService.getTradeRecordOfWaitSync(date, payType);

        if (tradeRecordList != null) {

            for (TradeRecord tradeRecord: tradeRecordList) {
                try {
                    QueryPayParameter param = new QueryPayParameter();
                    param.setSerialNo(tradeRecord.getSerialNo());
                    QueryPayResult result = cfcaPayApi.queryPay(param);
                    if (result != null && result.getStatus().equals("20")) {

                        if (rechargeService.updateDealRecharge(CommonUtil.CFCA_ORDER_NO, tradeRecord.getSerialNo(), EnumPayType.CFCA)) {

                        }
                    }
                } catch (PayException ex) {

                } catch (ServiceException ex) {

                }
            }

        }

        // other...

    }

    // 同步 - 结算操作[这里只是充值的批量结算，取现在代扣成功后]
    public void syncSettlement() {
        // 结算操作是T+1，提起结算申请是不能立即到账的，所以这里先将
        // 结算的记录放到结算表里并表明是申请状态
        // 然后定期发起结算状态查询，当状态（status）为40时，可认为是成功了


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

        if (settlement != null) {
            try {
                SettlementParameter param = new SettlementParameter();
                param.setOrderNo(CommonUtil.CFCA_ORDER_NO);
                param.setSerialNumber(settlement.getSerialNo());
                param.setAmount(settlement.getAmount());
                BankAccount2 bankAccount = new BankAccount2();
//                bankAccount.setBankID("700");
//                bankAccount.setAccountName("aa");
//                bankAccount.setAccountNumber("1111");
//                bankAccount.setBranchName("ccs");
//                bankAccount.setProvince("sssss");
//                bankAccount.setCity("aaaaa");
                bankAccount.setBankID("308");
                bankAccount.setAccountName("杨飞");
                bankAccount.setAccountNumber("6226090216683728");
                bankAccount.setBranchName("");
                bankAccount.setProvince("");
                bankAccount.setCity("");
                param.setBankAccount(bankAccount);
                SettlementResult result = cfcaPayApi.settlement(param);
                if (result.getStatus()!=null && result.getStatus().equals("20")) {
                    // 这地方不能这么做
//                    manageTradeRecordService.updateSettlementToFinish(settlement.getId());

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
