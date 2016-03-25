package com.goldbao.bankroll.vo;


import java.math.BigDecimal;
import java.util.List;

/**
 * 按月的配资方案
 */
public class BankrollSolutionResult {

    private Integer minLever;

    private Integer maxLever;

    private BigDecimal minPrincipal;

    private BigDecimal maxPrincipal;

    private List<BankrollRuleResult> rules;

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
     * 最小投资本金
     */
    public BigDecimal getMinPrincipal() {
        return minPrincipal;
    }
    /**
     * 最小投资本金
     */
    public void setMinPrincipal(BigDecimal minPrincipal) {
        this.minPrincipal = minPrincipal;
    }
    /**
     * 最大投资本金
     */
    public BigDecimal getMaxPrincipal() {
        return maxPrincipal;
    }
    /**
     * 最大投资本金
     */
    public void setMaxPrincipal(BigDecimal maxPrincipal) {
        this.maxPrincipal = maxPrincipal;
    }
    /**
     * 规则
     */
    public List<BankrollRuleResult> getRules() {
        return rules;
    }

    /**
     * 规则
     */
    public void setRules(List<BankrollRuleResult> rules) {
        this.rules = rules;
    }
}
