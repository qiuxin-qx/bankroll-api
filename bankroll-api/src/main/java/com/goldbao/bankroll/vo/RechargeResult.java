package com.goldbao.bankroll.vo;


/**
 * 充值结果（同步）封装
 */
public class RechargeResult {
    private String orderNo;

    private String dealTime;

    private String resp;

    /**
     * 订单号
     */
    public String getOrderNo() {
        return orderNo;
    }
    /**
     * 订单号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    /**
     * 成交时间
     */
    public String getDealTime() {
        return dealTime;
    }
    /**
     * 成交时间
     */
    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }

    /**
     * 支付回馈结果
     */
    public String getResp() {
        return resp;
    }
    /**
     * 支付回馈结果
     */
    public void setResp(String resp) {
        this.resp = resp;
    }
}
