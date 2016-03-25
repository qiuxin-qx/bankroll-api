package com.goldbao.bankroll.vo;

import java.math.BigDecimal;

/**
 * 利率【按月】
 */
public class BankrollInterestResult {

    private Integer months;

    private BigDecimal interest;

    /**
     * 配资使用时间【单位为月】
     */
    public Integer getMonths() {
        return months;
    }
    /**
     * 配资使用时间【单位为月】
     */
    public void setMonths(Integer months) {
        this.months = months;
    }
    /**
     * 对应利率
     */
    public BigDecimal getInterest() {
        return interest;
    }
    /**
     * 对应利率
     */
    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }
}
