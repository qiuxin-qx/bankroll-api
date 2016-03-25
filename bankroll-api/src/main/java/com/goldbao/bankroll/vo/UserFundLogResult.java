package com.goldbao.bankroll.vo;

import java.math.BigDecimal;

/**
 * @author shuyu.fang
 */
public class UserFundLogResult {

    private int tradeType;

    private long payerId; // 付款人

    private long payeeId; // 收款人

    private BigDecimal amount; // 变动金额

    private BigDecimal balance; // 当前账户余额

    private int tradeDirection; // 交易方向（0收入，1支出）

    private String remark;

    private String addTime;

    public int getTradeType() {
        return tradeType;
    }

    public void setTradeType(int tradeType) {
        this.tradeType = tradeType;
    }

    public long getPayerId() {
        return payerId;
    }

    public void setPayerId(long payerId) {
        this.payerId = payerId;
    }

    public long getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(long payeeId) {
        this.payeeId = payeeId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getTradeDirection() {
        return tradeDirection;
    }

    public void setTradeDirection(int tradeDirection) {
        this.tradeDirection = tradeDirection;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }
}
