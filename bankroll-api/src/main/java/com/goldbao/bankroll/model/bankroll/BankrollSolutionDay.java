package com.goldbao.bankroll.model.bankroll;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.goldbao.bankroll.model.Model;
import com.goldbao.bankroll.model.Organize;

/**
 * 配资方案 - 按天
 */
@Entity
@Table(name = "bk_bankroll_solution_day")
@AttributeOverride(name = "id", column = @Column(name = "solution_id"))
public class BankrollSolutionDay extends Model implements Serializable {

	private static final long serialVersionUID = 3028781780196305014L;
    @Column(name = "min_lever")
	private Integer minLever;
    @Column(name = "max_lever")
    private Integer maxLever;
    @Column(name = "min_money")
    private BigDecimal minMoney;
    @Column(name = "max_money")
    private BigDecimal maxMoney;
    @Column(name = "max_instantly_transac_money")
    private BigDecimal maxinstantlyTransacMoney;
    @Column(name = "max_limit_position_money")
    private BigDecimal maxLimitPositionMoney;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "org_id")
    private Organize organize;

    @OneToMany(mappedBy = "solution")
    private List<BankrollRuleDay> rules;

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

    public List<BankrollRuleDay> getRules() {
        return rules;
    }

    public void setRules(List<BankrollRuleDay> rules) {
        this.rules = rules;
    }
	public Organize getOrganize() {
		return organize;
	}
	public void setOrganize(Organize organize) {
		this.organize = organize;
	}
}
