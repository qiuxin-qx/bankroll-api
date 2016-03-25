package com.goldbao.bankroll.model.bankroll;

import com.goldbao.bankroll.model.Model;
import com.goldbao.bankroll.model.enums.EnumBankrollBillStatus;
import com.goldbao.bankroll.model.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 配资账单
 * @author shuyu.fang
 */
@Entity
@Table(name = "bk_bankroll_bill")
@AttributeOverride(name = "id", column = @Column(name = "bill_id"))
public class BankrollBill extends Model implements Serializable {
	private static final long serialVersionUID = 3877993770862054226L;

	@ManyToOne
    @JoinColumn(name = "biller_id")
    private User biller; // 账单人

    @ManyToOne
    @JoinColumn(name = "record_id")
    private BankrollRecord record; // 账单对应的配资记录

    private BigDecimal amount; // 账单金额
    @Column(name = "real_amount")
    private BigDecimal realAmount; // 账单实付金额
    @Column(name = "should_fk")
    private BigDecimal shouldFk; // 应收罚款
    @Column(name = "real_fk")
    private BigDecimal realFk;   // 实收罚款

    private int num; // 付款期次
    @Column(name = "should_time")
    private Date shouldTime; // 应付时间
    @Column(name = "real_time")
    private Date realTime; // 实付时间

    private EnumBankrollBillStatus status; // 账单状态

    public User getBiller() {
        return biller;
    }

    public void setBiller(User biller) {
        this.biller = biller;
    }

    public BankrollRecord getRecord() {
        return record;
    }

    public void setRecord(BankrollRecord record) {
        this.record = record;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public BigDecimal getShouldFk() {
        return shouldFk;
    }

    public void setShouldFk(BigDecimal shouldFk) {
        this.shouldFk = shouldFk;
    }

    public BigDecimal getRealFk() {
        return realFk;
    }

    public void setRealFk(BigDecimal realFk) {
        this.realFk = realFk;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Date getShouldTime() {
        return shouldTime;
    }

    public void setShouldTime(Date shouldTime) {
        this.shouldTime = shouldTime;
    }

    public Date getRealTime() {
        return realTime;
    }

    public void setRealTime(Date realTime) {
        this.realTime = realTime;
    }

    public EnumBankrollBillStatus getStatus() {
        return status;
    }

    public void setStatus(EnumBankrollBillStatus status) {
        this.status = status;
    }
}
