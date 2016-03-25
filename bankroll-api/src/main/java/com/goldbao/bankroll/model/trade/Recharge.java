package com.goldbao.bankroll.model.trade;

import com.goldbao.bankroll.model.Model;
import com.goldbao.bankroll.model.enums.EnumPayType;
import com.goldbao.bankroll.model.enums.EnumTradeStatus;
import com.goldbao.bankroll.model.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 充值记录表
 */
@Entity
@Table(name = "bk_recharged")
@AttributeOverride(name = "id", column = @Column(name="recharge_id"))
public class Recharge extends Model implements Serializable {
	
	private static final long serialVersionUID = -3489538435665443618L;

	@Column(name = "order_no")
    private String orderNo;

    @Column(scale = 2)
    private BigDecimal amount;
    @Column(scale = 2)
    private BigDecimal fee;
    private EnumTradeStatus status;
    @Column(name = "deal_time")
    private Date dealTime;

    @ManyToOne
    @JoinColumn(name="creator_id")
    private User creator;
    @Column(name = "pay_type")
    private EnumPayType payType;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 交易金额，单位为元
     */
    public BigDecimal getAmount() {
        return amount;
    }
    /**
     * 交易金额，单位为元
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    /**
     * 手续费金额，单位为元
     */
    public BigDecimal getFee() {
        return fee;
    }
    /**
     * 手续费金额，单位为元
     */
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }
    /**
     * 交易状态
     */
    public EnumTradeStatus getStatus() {
        return status;
    }
    /**
     * 交易状态
     */
    public void setStatus(EnumTradeStatus status) {
        this.status = status;
    }
    /**
     * 创建者
     */
    public User getCreator() {
        return creator;
    }
    /**
     * 创建者
     */
    public void setCreator(User creator) {
        this.creator = creator;
    }

    /**
     * 成交时间
     */
    public Date getDealTime() {
        return dealTime;
    }
    /**
     * 成交时间
     */
    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    public EnumPayType getPayType() {
        return payType;
    }

    public void setPayType(EnumPayType payType) {
        this.payType = payType;
    }
}
