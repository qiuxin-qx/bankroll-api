package com.goldbao.bankroll.vo;

import java.math.BigDecimal;

/**
 * @author shuyu.fang
 */
public class TradeRecordResult {

    private String orderNo;

    private String serialNo;

    private Long payer; // 付款人

    private Long payee; // 收款人

//    private Long creator;// 创建人

    private BigDecimal amount;

    private BigDecimal fee;
    private int tradeType;
    private int tradeStatus;

    private String dealTime;

    private String addTime;

    private String remark;

    /**
     * 平台订单号
     */
    public String getOrderNo() {
        return orderNo;
    }
    /**
     * 平台订单号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    /**
     * 平台流水号
     */
    public String getSerialNo() {
        return serialNo;
    }
    /**
     * 平台流水号
     */
    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }
    /**
     * 付款人
     */
    public Long getPayer() {
        return payer;
    }
    /**
     * 付款人
     */
    public void setPayer(Long payer) {
        this.payer = payer;
    }
    /**
     * 收款人
     */
    public Long getPayee() {
        return payee;
    }
    /**
     * 收款人
     */
    public void setPayee(Long payee) {
        this.payee = payee;
    }
    /**
     * 交易金额
     */
    public BigDecimal getAmount() {
        return amount;
    }
    /**
     * 交易金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    /**
     * 手续费
     */
    public BigDecimal getFee() {
        return fee;
    }
    /**
     * 手续费
     */
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    /**
     * 交易类型 - 枚举
     * @see com.goldbao.bankroll.model.enums.EnumTradeStatus
     */
    public int getTradeType() {
        return tradeType;
    }
    /**
     * 交易类型 - 枚举
     * @see com.goldbao.bankroll.model.enums.EnumTradeStatus
     */
    public void setTradeType(int tradeType) {
        this.tradeType = tradeType;
    }
    /**
     * 交易状态 - 枚举
     * @see com.goldbao.bankroll.model.enums.EnumTradeType
     */
    public int getTradeStatus() {
        return tradeStatus;
    }
    /**
     * 交易状态 - 枚举
     * @see com.goldbao.bankroll.model.enums.EnumTradeType
     */
    public void setTradeStatus(int tradeStatus) {
        this.tradeStatus = tradeStatus;
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
     * 添加时间
     */
    public String getAddTime() {
        return addTime;
    }
    /**
     * 添加时间
     */
    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    /**
     * 备注
     */
    public String getRemark() {
        return remark;
    }
    /**
     * 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
