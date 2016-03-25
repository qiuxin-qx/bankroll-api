package com.goldbao.bankroll.model.bankroll;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.goldbao.bankroll.model.Model;
import com.goldbao.bankroll.model.enums.EnumIsLimitPosition;

/**
 * 配资资金规则
 */
@Entity
@Table(name = "bk_bankroll_rule")
@AttributeOverride(name = "id", column = @Column(name = "rule_id"))
public class BankrollRule extends Model implements Serializable {

	private static final long serialVersionUID = 1281095252854504418L;

	@ManyToOne
    @JoinColumn(name = "solution_id")
    private BankrollSolution solution;

    @Column(name = "limit_position")
    private EnumIsLimitPosition limitPosition;
    @Column(name = "warning_line")
    private BigDecimal warningLine;
    @Column(name = "open_line")
    private BigDecimal openLine;
    @Column(name = "instantly_transac")
    private Integer instantlyTransac;
    @Column(name = "min_money")
    private BigDecimal minMoney;
    @Column(name = "max_money")
    private BigDecimal maxMoney;

    @OneToMany(mappedBy = "rule")
    private List<BankrollInterest> interests;

    /**
     * 所属解决方案
     */
    public BankrollSolution getSolution() {
        return solution;
    }
    /**
     * 所属解决方案
     */
    public void setSolution(BankrollSolution solution) {
        this.solution = solution;
    }
    /**
     * 是否限制仓位
     */
    public EnumIsLimitPosition getLimitPosition() {
        return limitPosition;
    }
    /**
     * 是否限制仓位
     */
    public void setLimitPosition(EnumIsLimitPosition limitPosition) {
        this.limitPosition = limitPosition;
    }

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
     * 利率
     */
    public List<BankrollInterest> getInterests() {
        return interests;
    }
    /**
     * 利率
     */
    public void setInterests(List<BankrollInterest> interests) {
        this.interests = interests;
    }
}
