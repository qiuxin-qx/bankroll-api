package com.goldbao.bankroll.model.bankroll;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @author shuyu.fang
 */
public class BankrollApplyDTO {

    private BigInteger id;

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

    private Timestamp addTime; // 申请时间

    private BigInteger bankrollRecordId;

    private String remark;

    private BigInteger applicantId;

    private String username;

    private String mobilephone;

    private BigDecimal balance;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
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

    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }

    public BigInteger getBankrollRecordId() {
        return bankrollRecordId;
    }

    public void setBankrollRecordId(BigInteger bankrollRecordId) {
        this.bankrollRecordId = bankrollRecordId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigInteger getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(BigInteger applicantId) {
        this.applicantId = applicantId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
