package com.goldbao.bankroll.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 按天配资方案
 */
public class BankrollSolutionDayResult {

    private Integer minLever;

    private Integer maxLever;

    private BigDecimal minMoney;

    private BigDecimal maxMoney;

    private BigDecimal maxinstantlyTransacMoney;

    private BigDecimal maxLimitPositionMoney;

    private List<BankrollRuleDayResult> rules;


    /**
     * 最小杠杆倍数
     */
    public Integer getMinLever() {
        return minLever;
    }
    /**
     * 最小杠杆倍数
     */
    public void setMinLever(Integer minLever) {
        this.minLever = minLever;
    }
    /**
     * 最大杠杆倍数
     */
    public Integer getMaxLever() {
        return maxLever;
    }
    /**
     * 最大杠杆倍数
     */
    public void setMaxLever(Integer maxLever) {
        this.maxLever = maxLever;
    }
    /**
     * 最小配资金额
     */
    public BigDecimal getMinMoney() {
        return minMoney;
    }
    /**
     * 最小配资金额
     */
    public void setMinMoney(BigDecimal minMoney) {
        this.minMoney = minMoney;
    }
    /**
     * 最大配资金额
     */
    public BigDecimal getMaxMoney() {
        return maxMoney;
    }
    /**
     * 最大配资金额
     */
    public void setMaxMoney(BigDecimal maxMoney) {
        this.maxMoney = maxMoney;
    }
    /**
     * 最大可立即交易的配资金额
     */
    public BigDecimal getMaxinstantlyTransacMoney() {
        return maxinstantlyTransacMoney;
    }
    /**
     * 最大可立即交易的配资金额
     */
    public void setMaxinstantlyTransacMoney(BigDecimal maxinstantlyTransacMoney) {
        this.maxinstantlyTransacMoney = maxinstantlyTransacMoney;
    }
    /**
     * 最大可不限制仓位的配资金额
     */
    public BigDecimal getMaxLimitPositionMoney() {
        return maxLimitPositionMoney;
    }
    /**
     * 最大可不限制仓位的配资金额
     */
    public void setMaxLimitPositionMoney(BigDecimal maxLimitPositionMoney) {
        this.maxLimitPositionMoney = maxLimitPositionMoney;
    }

    /**
     * 规则
     */
    public List<BankrollRuleDayResult> getRules() {
        return rules;
    }

    /**
     * 规则
     */
    public void setRules(List<BankrollRuleDayResult> rules) {
        this.rules = rules;
    }
}
