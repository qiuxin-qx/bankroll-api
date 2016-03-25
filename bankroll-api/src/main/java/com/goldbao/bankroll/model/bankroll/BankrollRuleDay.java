package com.goldbao.bankroll.model.bankroll;

import com.goldbao.bankroll.model.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 配资规则 - 按天
 */
@Entity
@Table(name = "bk_bankroll_rule_day")
@AttributeOverride(name = "id", column = @Column(name = "rule_id"))
public class BankrollRuleDay extends Model implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7767558094351830445L;
	private Integer lever;
    @Column(name = "warning_line")
    private BigDecimal warningLine;
    @Column(name = "open_line")
    private BigDecimal openLine;
    @Column(name = "min_use_days")
    private Integer minUseDays;
    @Column(name = "max_use_days")
    private Integer maxUseDays;
    @Column(name = "manage_fee_rate")
    private BigDecimal manageFeeRate;
    @Column(name = "max_money")
    private BigDecimal maxMoney;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "solution_id")
    private BankrollSolutionDay solution;

    /**
     * 杠杆倍率
     */
    public Integer getLever() {
        return lever;
    }
    /**
     * 杠杆倍率
     */
    public void setLever(Integer lever) {
        this.lever = lever;
    }
    /**
     * 警告线，这里返回比例值，比如配资金额为1000元，警告线比例1.10
     * 则警告线为1000*1.10 = 1100<br/>
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
     * 最少使用时间【天】
     */
    public Integer getMinUseDays() {
        return minUseDays;
    }
    /**
     * 最少使用时间【天】
     */
    public void setMinUseDays(Integer minUseDays) {
        this.minUseDays = minUseDays;
    }
    /**
     * 最多使用时间【天】
     */
    public Integer getMaxUseDays() {
        return maxUseDays;
    }
    /**
     * 最多使用时间【天】
     */
    public void setMaxUseDays(Integer maxUseDays) {
        this.maxUseDays = maxUseDays;
    }
    /**
     * 管理费率
     */
    public BigDecimal getManageFeeRate() {
        return manageFeeRate;
    }
    /**
     * 管理费率
     */
    public void setManageFeeRate(BigDecimal manageFeeRate) {
        this.manageFeeRate = manageFeeRate;
    }

    /**
     * 允许最大配资金额
     */
    public BigDecimal getMaxMoney() {
        return maxMoney;
    }

    /**
     * 允许最大配资金额
     */
    public void setMaxMoney(BigDecimal maxMoney) {
        this.maxMoney = maxMoney;
    }

    public BankrollSolutionDay getSolution() {
        return solution;
    }

    public void setSolution(BankrollSolutionDay solution) {
        this.solution = solution;
    }
}
