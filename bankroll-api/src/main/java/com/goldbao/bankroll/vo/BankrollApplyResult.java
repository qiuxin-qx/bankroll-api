package com.goldbao.bankroll.vo;

import java.math.BigDecimal;

/**
 * 申请配资结果
 */
public class BankrollApplyResult {

    private Long id;

    private BankrollApplicantResult applicant; // 申请人

    private BigDecimal money; // 申请配资金额

    private Integer lever; // 杠杆

    private BigDecimal deposit; // 保证金

    private BigDecimal prepDeposit; // 预付保证金

    private BigDecimal managementFee; // 管理费（总额）

    private BigDecimal openLineMoney; // 平仓金额

    private BigDecimal warningLineMoney; // 警告金额

    private Integer cycle; // 配资周期（天、月）

    private Integer cycleUnit; // 配资周期单位

    private Integer status; // 状态
    
    private String addTime; // 申请时间

    private Long bankrollRecordId;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public BankrollApplicantResult getApplicant() {
		return applicant;
	}

	public void setApplicant(BankrollApplicantResult applicant) {
		this.applicant = applicant;
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

    public Integer getCycleUnit() {
        return cycleUnit;
    }

    public void setCycleUnit(Integer cycleUnit) {
        this.cycleUnit = cycleUnit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

    public Long getBankrollRecordId() {
        return bankrollRecordId;
    }

    public void setBankrollRecordId(Long bankrollRecordId) {
        this.bankrollRecordId = bankrollRecordId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
