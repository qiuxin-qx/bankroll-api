package com.goldbao.bankroll.model.user;

import com.goldbao.bankroll.model.Model;
import com.goldbao.bankroll.model.enums.EnumTradeDirection;
import com.goldbao.bankroll.model.enums.EnumTradeType;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用户资金流动日志表
 * @author shuyu.fang
 */
@Entity
@Table(name = "bk_user_fund_log")
@AttributeOverride(name = "id", column = @Column(name = "log_id"))
public class UserFundLog extends Model implements Serializable {

	private static final long serialVersionUID = 6289090620464761470L;

	@Column(name = "trade_type")
    private EnumTradeType tradeType;

    @ManyToOne
    @JoinColumn(name = "payer_id")
    private User payer; // 付款人
    @ManyToOne
    @JoinColumn(name = "payee_id")
    private User payee; // 收款人

    private BigDecimal amount; // 变动金额

    private BigDecimal balance; // 当前账户余额
    @Column(name = "trade_direction")
    private EnumTradeDirection tradeDirection; // 交易方向（0收入，1支出）

    private String remark;

    /**
     * 交易类型
     */
    public EnumTradeType getTradeType() {
        return tradeType;
    }
    /**
     * 交易类型
     */
    public void setTradeType(EnumTradeType tradeType) {
        this.tradeType = tradeType;
    }
    /**
     * 资金变动会员
     */
    public User getPayer() {
        return payer;
    }
    /**
     * 资金变动会员
     */
    public void setPayer(User payer) {
        this.payer = payer;
    }
    /**
     * 收款人
     */
    public User getPayee() {
        return payee;
    }
    /**
     * 收款人
     */
    public void setPayee(User payee) {
        this.payee = payee;
    }
    /**
     * 交易金额
     */
    public BigDecimal getAmount() {
        return amount;
    }
    /**
     * 交易金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    /**
     * 资金变动会员余额
     */
    public BigDecimal getBalance() {
        return balance;
    }
    /**
     * 资金变动会员余额
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    /**
     * 交易方向（0收入，1支出）
     */
    public EnumTradeDirection getTradeDirection() {
        return tradeDirection;
    }
    /**
     * 交易方向（0收入，1支出）
     */
    public void setTradeDirection(EnumTradeDirection tradeDirection) {
        this.tradeDirection = tradeDirection;
    }

    /**
     * 备注
     */
    public String getRemark() {
        return remark;
    }
    /**
     * 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
