package com.goldbao.bankroll.vo;


import java.math.BigDecimal;
import java.util.List;

/**
 * 按月配资规则
 * @author shuyu.fang
 */
public class BankrollRuleResult {

    private BigDecimal warningLine;

    private BigDecimal openLine;

    private Integer instantlyTransac;

    private BigDecimal minMoney;

    private BigDecimal maxMoney;

    private String limitPosition;

    private List<BankrollInterestResult> interests;

    /**
     * 警告线，这里返回比例值，比如配资金额为1000元，警告线比例1.1
     * 则警告线为1000*1.1 = 1100<br/>
     * 最高不能超过2.00
     */
    public BigDecimal getWarningLine() {
        return warningLine;
    }
    /**
     * 警告线，这里返回比例值，比如配资金额为1000元，警告线比例1.10
     * 则警告线为1000*1.10 = 1100<br/>
     * 最高不能超过2.00
     */
    public void setWarningLine(BigDecimal warningLine) {
        this.warningLine = warningLine;
    }
    /**
     * 平仓线，这里返回比例值，比如配资金额为1000元，平仓线比例1.08<br/>
     * 则平仓线为1000*1.08 = 1080<br/>
     * 最高不能超过2.00
     */
    public BigDecimal getOpenLine() {
        return openLine;
    }
    /**
     * 平仓线，这里返回比例值，比如配资金额为1000元，平仓线比例1.08<br/>
     * 则平仓线为1000*1.08 = 1080<br/>
     * 最高不能超过2.00
     */
    public void setOpenLine(BigDecimal openLine) {
        this.openLine = openLine;
    }
    /**
     * 是否允许立刻交易，0为允许，1为下个交易日方可交易
     */
    public Integer getInstantlyTransac() {
        return instantlyTransac;
    }
    /**
     * 是否允许立刻交易，0为允许，1为下个交易日方可交易
     */
    public void setInstantlyTransac(Integer instantlyTransac) {
        this.instantlyTransac = instantlyTransac;
    }

    /**
     * 该规则支持最低配资金额
     */
    public BigDecimal getMinMoney() {
        return minMoney;
    }
    /**
     * 该规则支持最低配资金额
     */
    public void setMinMoney(BigDecimal minMoney) {
        this.minMoney = minMoney;
    }

    /**
     * 该规则支持最高配资金额
     */
    public BigDecimal getMaxMoney() {
        return maxMoney;
    }

    /**
     * 该规则支持最高配资金额
     */
    public void setMaxMoney(BigDecimal maxMoney) {
        this.maxMoney = maxMoney;
    }

    /**
     * 是否限制仓位
     */
    public String getLimitPosition() {
        return limitPosition;
    }

    /**
     * 是否限制仓位
     */
    public void setLimitPosition(String limitPosition) {
        this.limitPosition = limitPosition;
    }
    /**
     * 利率
     */
    public List<BankrollInterestResult> getInterests() {
        return interests;
    }
    /**
     * 利率
     */
    public void setInterests(List<BankrollInterestResult> interests) {
        this.interests = interests;
    }
}
