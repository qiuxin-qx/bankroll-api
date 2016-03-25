package com.goldbao.bankroll.model.bankroll;

import com.goldbao.bankroll.model.Model;
import com.goldbao.bankroll.model.enums.EnumBankrollApplyStatus;
import com.goldbao.bankroll.model.enums.EnumCycleUnit;
import com.goldbao.bankroll.model.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 申请配资记录表
 * @author shuyu.fang
 */
@Entity
@Table(name = "bk_bankroll_apply")
@AttributeOverride(name = "id", column = @Column(name = "apply_id"))
public class BankrollApply extends Model implements Serializable {

	private static final long serialVersionUID = -2748651895742907265L;

	@ManyToOne
    @JoinColumn(name = "applicant_id")
    private User applicant; // 申请人

    @Column(name = "bankroll_no", unique = true)
    private String bankrollNo; // 配资标识，用于标识一个配资并订单展示给前台

    private BigDecimal money; // 申请配资金额

    private Integer lever; // 杠杆

    private BigDecimal deposit; // 保证金
    @Column(name = "prep_deposit")
    private BigDecimal prepDeposit; // 预付保证金
    @Column(name = "management_fee")
    private BigDecimal managementFee; // 单位（月，天）服务费（利息）
    @Column(name = "open_line_money")
    private BigDecimal openLineMoney; // 平仓金额
    @Column(name = "warning_line_money")
    private BigDecimal warningLineMoney; // 警告金额

    private Integer cycle; // 配资周期（天、月）
    @Column(name = "cycle_unit")
    private EnumCycleUnit cycleUnit; // 配资周期单位

    private EnumBankrollApplyStatus status; // 状态

    @OneToOne(mappedBy = "apply")
    private BankrollRecord bankrollRecord;

    @Column(length = 500)
    private String remark;

    /**
     * 申请人
     */
    public User getApplicant() {
        return applicant;
    }
    /**
     * 申请人
     */
    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }
    /**
     * 配资标识，用于标识一个配资并订单展示给前台
     */
    public String getBankrollNo() {
        return bankrollNo;
    }
    /**
     * 配资标识，用于标识一个配资并订单展示给前台
     */
    public void setBankrollNo(String bankrollNo) {
        this.bankrollNo = bankrollNo;
    }

    /**
     * 申请配资金额
     */
    public BigDecimal getMoney() {
        return money;
    }
    /**
     * 申请配资金额
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    /**
     * 杠杆
     */
    public Integer getLever() {
        return lever;
    }
    /**
     * 杠杆
     */
    public void setLever(Integer lever) {
        this.lever = lever;
    }
    /**
     * 保证金
     */
    public BigDecimal getDeposit() {
        return deposit;
    }
    /**
     * 保证金
     */
    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }
    /**
     * 预付保证金
     */
    public BigDecimal getPrepDeposit() {
        return prepDeposit;
    }
    /**
     * 预付保证金
     */
    public void setPrepDeposit(BigDecimal prepDeposit) {
        this.prepDeposit = prepDeposit;
    }
    /**
     * 单位（月，天）服务费（利息）
     */
    public BigDecimal getManagementFee() {
        return managementFee;
    }
    /**
     * 单位（月，天）服务费（利息）
     */
    public void setManagementFee(BigDecimal managementFee) {
        this.managementFee = managementFee;
    }
    /**
     * 平仓金额
     */
    public BigDecimal getOpenLineMoney() {
        return openLineMoney;
    }
    /**
     * 平仓金额
     */
    public void setOpenLineMoney(BigDecimal openLineMoney) {
        this.openLineMoney = openLineMoney;
    }
    /**
     * 警告金额
     */
    public BigDecimal getWarningLineMoney() {
        return warningLineMoney;
    }
    /**
     * 警告金额
     */
    public void setWarningLineMoney(BigDecimal warningLineMoney) {
        this.warningLineMoney = warningLineMoney;
    }
    /**
     * 周期（天，月）
     */
    public Integer getCycle() {
        return cycle;
    }
    /**
     * 周期（天，月）
     */
    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }
    /**
     * 周期单位（天，月）
     */
    public EnumCycleUnit getCycleUnit() {
        return cycleUnit;
    }
    /**
     * 周期单位（天，月）
     */
    public void setCycleUnit(EnumCycleUnit cycleUnit) {
        this.cycleUnit = cycleUnit;
    }
    /**
     * 状态
     */
    public EnumBankrollApplyStatus getStatus() {
        return status;
    }
    /**
     * 状态
     */
    public void setStatus(EnumBankrollApplyStatus status) {
        this.status = status;
    }

    public BankrollRecord getBankrollRecord() {
        return bankrollRecord;
    }

    public void setBankrollRecord(BankrollRecord bankrollRecord) {
        this.bankrollRecord = bankrollRecord;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
