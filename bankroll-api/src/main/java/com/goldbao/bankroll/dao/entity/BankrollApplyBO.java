package com.goldbao.bankroll.dao.entity;

import com.goldbao.bankroll.model.enums.EnumBankrollApplyStatus;
import com.goldbao.bankroll.model.enums.EnumCycleUnit;

import java.math.BigDecimal;

/**
 *
 * @author shuyu.fang
 */
public class BankrollApplyBO {

    private String bankrollNo; // 配资标识，用于标识一个配资并订单展示给前台

    private BigDecimal money; // 申请配资金额

    private Integer lever; // 杠杆

    private BigDecimal deposit; // 保证金
    private BigDecimal prepDeposit; // 预付保证金
    private BigDecimal managementFee; // 单位（月，天）服务费（利息）
    private BigDecimal openLineMoney; // 平仓金额
    private BigDecimal warningLineMoney; // 警告金额

    private Integer cycle; // 配资周期（天、月）
    private EnumCycleUnit cycleUnit; // 配资周期单位

    private EnumBankrollApplyStatus status; // 状态
    
    private BankrollApplicantBo applicant;

    

    public BankrollApplyBO(String bankrollNo, BigDecimal money, Integer lever,
			BigDecimal deposit, BigDecimal prepDeposit,
			BigDecimal managementFee, BigDecimal openLineMoney,
			BigDecimal warningLineMoney, Integer cycle,
			EnumCycleUnit cycleUnit, EnumBankrollApplyStatus status, BankrollApplicantBo applicant, BigDecimal balance) {
		this.bankrollNo = bankrollNo;
		this.money = money;
		this.lever = lever;
		this.deposit = deposit;
		this.prepDeposit = prepDeposit;
		this.managementFee = managementFee;
		this.openLineMoney = openLineMoney;
		this.warningLineMoney = warningLineMoney;
		this.cycle = cycle;
		this.cycleUnit = cycleUnit;
		this.status = status;
		
		this.applicant = applicant;
		
		this.applicant.setBalance(balance);
	}

	public String getBankrollNo() {
        return bankrollNo;
    }

    public void setBankrollNo(String bankrollNo) {
        this.bankrollNo = bankrollNo;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getLever() {
        return lever;
    }

    public void setLever(Integer lever) {
        this.lever = lever;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public BigDecimal getPrepDeposit() {
        return prepDeposit;
    }

    public void setPrepDeposit(BigDecimal prepDeposit) {
        this.prepDeposit = prepDeposit;
    }

    public BigDecimal getManagementFee() {
        return managementFee;
    }

    public void setManagementFee(BigDecimal managementFee) {
        this.managementFee = managementFee;
    }

    public BigDecimal getOpenLineMoney() {
        return openLineMoney;
    }

    public void setOpenLineMoney(BigDecimal openLineMoney) {
        this.openLineMoney = openLineMoney;
    }

    public BigDecimal getWarningLineMoney() {
        return warningLineMoney;
    }

    public void setWarningLineMoney(BigDecimal warningLineMoney) {
        this.warningLineMoney = warningLineMoney;
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public EnumCycleUnit getCycleUnit() {
        return cycleUnit;
    }

    public void setCycleUnit(EnumCycleUnit cycleUnit) {
        this.cycleUnit = cycleUnit;
    }

    public EnumBankrollApplyStatus getStatus() {
        return status;
    }

    public void setStatus(EnumBankrollApplyStatus status) {
        this.status = status;
    }

	public BankrollApplicantBo getApplicant() {
		return applicant;
	}

	public void setApplicant(BankrollApplicantBo applicant) {
		this.applicant = applicant;
	}
}
