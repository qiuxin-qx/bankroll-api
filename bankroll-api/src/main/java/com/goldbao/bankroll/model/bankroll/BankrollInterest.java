package com.goldbao.bankroll.model.bankroll;

import com.goldbao.bankroll.model.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 规则 - 利率控制
 */
@Entity
@Table(name = "bk_bankroll_interest")
@AttributeOverride(name = "id", column = @Column(name = "interest_id"))
public class BankrollInterest extends Model implements Serializable {

	private static final long serialVersionUID = 211619526036078494L;

	private Integer months;

    private BigDecimal interest;

    @ManyToOne
    @JoinColumn(name = "rule_id")
    private BankrollRule rule;

    /**
     * 资金使用月数
     */
    public Integer getMonths() {
        return months;
    }
    /**
     * 资金使用月数
     */
    public void setMonths(Integer months) {
        this.months = months;
    }

    /**
     * 利率
     */
    public BigDecimal getInterest() {
        return interest;
    }
    /**
     * 利率
     */
    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public BankrollRule getRule() {
        return rule;
    }

    public void setRule(BankrollRule rule) {
        this.rule = rule;
    }
}
