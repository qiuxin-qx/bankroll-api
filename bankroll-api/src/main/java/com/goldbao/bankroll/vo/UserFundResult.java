package com.goldbao.bankroll.vo;

import java.math.BigDecimal;

/**
 * @author shuyu.fang
 */
public class UserFundResult {

    private BigDecimal balance;

    private BigDecimal freeze;

    private BigDecimal principal;

    private BigDecimal bankroll;

    private BigDecimal profitAndLoss;

    private String userId;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getFreeze() {
        return freeze;
    }

    public void setFreeze(BigDecimal freeze) {
        this.freeze = freeze;
    }

    public BigDecimal getPrincipal() {
        return principal;
    }

    public void setPrincipal(BigDecimal principal) {
        this.principal = principal;
    }

    public BigDecimal getBankroll() {
        return bankroll;
    }

    public void setBankroll(BigDecimal bankroll) {
        this.bankroll = bankroll;
    }

    public BigDecimal getProfitAndLoss() {
        return profitAndLoss;
    }

    public void setProfitAndLoss(BigDecimal profitAndLoss) {
        this.profitAndLoss = profitAndLoss;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
