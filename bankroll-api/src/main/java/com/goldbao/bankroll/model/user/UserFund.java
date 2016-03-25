package com.goldbao.bankroll.model.user;

import com.goldbao.bankroll.model.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用户资金
 */
@Entity
@Table(name = "bk_user_fund")
@AttributeOverride(name = "id", column = @Column(name = "fund_id"))
public class UserFund extends Model implements Serializable {
	private static final long serialVersionUID = -6786549589759681713L;

	private BigDecimal balance;

    private BigDecimal freeze;

    private BigDecimal principal;

    private BigDecimal bankroll;
    @Column(name = "profit_and_loss")
    private BigDecimal profitAndLoss;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * 可用余额
     */
    public BigDecimal getBalance() {
        return balance;
    }
    /**
     * 可用余额
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    /**
     * 冻结金额<br/>
     * 操作冻结时间：<br/>
     * +1. 申请配资之后，配资审核通过之前，需先把保证金冻结<br/>
     * +2.
     */
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

    /**
     * 关联用户
     */
    public User getUser() {
        return user;
    }
    /**
     * 关联用户
     */
    public void setUser(User user) {
        this.user = user;
    }
}
