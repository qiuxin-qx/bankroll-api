package com.goldbao.bankroll.model.company;

import com.goldbao.bankroll.model.Model;
import com.goldbao.bankroll.model.enums.EnumCompanyFundType;
import com.goldbao.bankroll.model.enums.EnumTradeDirection;
import com.goldbao.bankroll.model.enums.EnumTradeType;
import com.goldbao.bankroll.model.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 公司资产变更日志
 * @author shuyu.fang
 */
@Entity
@Table(name = "bk_company_fund_log")
@AttributeOverride(name = "id", column = @Column(name = "log_id"))
public class CompanyFundLog extends Model implements Serializable {
	private static final long serialVersionUID = 7204472235586206325L;

	@Column(name = "fund_type")
    private EnumCompanyFundType fundType;

    @OneToOne
    @JoinColumn(name = "payee_id")
    private User payee;
    @Column(name = "trade_type")
    private EnumTradeType tradeType;
    @Column(name = "trade_direction")
    private EnumTradeDirection tradeDirection;

    private BigDecimal amount;

    private BigDecimal balance;

    private String remark;

    /**
     * 公司账户
     */
    public EnumCompanyFundType getFundType() {
        return fundType;
    }
    /**
     * 公司账户
     */
    public void setFundType(EnumCompanyFundType fundType) {
        this.fundType = fundType;
    }
    /**
     * 个人账户
     */
    public User getPayee() {
        return payee;
    }
    /**
     * 个人账户
     */
    public void setPayee(User payee) {
        this.payee = payee;
    }
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
     * 交易方向（0入账，1支出）
     */
    public EnumTradeDirection getTradeDirection() {
        return tradeDirection;
    }
    /**
     * 交易方向（0入账，1支出）
     */
    public void setTradeDirection(EnumTradeDirection tradeDirection) {
        this.tradeDirection = tradeDirection;
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
     * 账户余额
     */
    public BigDecimal getBalance() {
        return balance;
    }
    /**
     * 账户余额
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
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
