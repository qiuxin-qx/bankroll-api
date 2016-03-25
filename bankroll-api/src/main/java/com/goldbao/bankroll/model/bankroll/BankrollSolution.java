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
 * 配资方案
 * @author shuyu.fang
 */
@Entity
@Table(name = "bk_bankroll_solution")
@AttributeOverride(name = "id", column = @Column(name = "solution_id"))
public class BankrollSolution extends Model implements Serializable {

	private static final long serialVersionUID = -1512057437443767149L;

    @Column(name = "min_lever")
    private Integer minLever;
    @Column(name = "max_lever")
    private Integer maxLever;
    @Column(name = "min_principal")
    private BigDecimal minPrincipal;
    @Column(name = "max_principal")
    private BigDecimal maxPrincipal;

//    private EnumCycleUnit cycleUnit;

    @OneToMany(mappedBy = "solution")
    private List<BankrollRule> rules;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "org_id")
    private Organize organize;


    /**
     * 方案所属机构
     */
    public Organize getOrganize() {
        return organize;
    }

    /**
     * 方案所属机构
     */
    public void setOrganize(Organize organize) {
        this.organize = organize;
    }

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

    public List<BankrollRule> getRules() {
        return rules;
    }

    public void setRules(List<BankrollRule> rules) {
        this.rules = rules;
    }
}
