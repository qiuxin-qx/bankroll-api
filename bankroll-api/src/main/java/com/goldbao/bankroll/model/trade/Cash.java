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
 * 提现记录表
 * @author shuyu.fang
 */
@Entity
@Table(name = "bk_cash")
@AttributeOverride(name = "id", column = @Column(name="recharge_id"))
public class Cash extends Model implements Serializable {

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

    @ManyToOne
    @JoinColumn(name = "user_bank_id")
    private UserBank userBank;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public EnumTradeStatus getStatus() {
        return status;
    }

    public void setStatus(EnumTradeStatus status) {
        this.status = status;
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public EnumPayType getPayType() {
        return payType;
    }

    public void setPayType(EnumPayType payType) {
        this.payType = payType;
    }

    public UserBank getUserBank() {
        return userBank;
    }

    public void setUserBank(UserBank userBank) {
        this.userBank = userBank;
    }
}
