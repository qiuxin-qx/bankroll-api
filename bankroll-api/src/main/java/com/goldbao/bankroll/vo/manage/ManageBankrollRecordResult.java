package com.goldbao.bankroll.vo.manage;

import java.math.BigDecimal;

import com.goldbao.bankroll.vo.BankrollApplicantResult;

/**
 * 配资记录
 * @author shuyu.fang
 */
public class ManageBankrollRecordResult {

    private Long id;

    private String bankrollNo; // @see BankrollApply.bankrollNo

    private BigDecimal money; // 申请配资金额

    private BigDecimal deposit; // 保证金

    private BigDecimal managementFee; // 单位（月，天）服务费（利息）

    private BigDecimal openLineMoney; // 平仓金额

    private BigDecimal warningLineMoney; // 警告金额

    private Integer cycle; // 配资周期（天、月）

    private int cycleUnit; // 配资周期单位

    private int status; // 状态


    private String homsOperatorNo; // homs帐号
    private String homsOperatorPwd; // homs密码
    private String startDate;    // 配资开始时间
    private String endDate;    // 配资结束时间

    private Integer renewNumber; // 续约次数
    private BankrollApplicantResult applicant; // 申请人;

    private ManageBankrollCreatorResult creator; // 创建人（后台管理人员）

    private String addTime;

    private String updateTime; // 修改时间

    private BigDecimal settleMoney; // 结算金额

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
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

    public int getCycleUnit() {
        return cycleUnit;
    }

    public void setCycleUnit(int cycleUnit) {
        this.cycleUnit = cycleUnit;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getHomsOperatorNo() {
        return homsOperatorNo;
    }

    public void setHomsOperatorNo(String homsOperatorNo) {
        this.homsOperatorNo = homsOperatorNo;
    }

    public String getHomsOperatorPwd() {
        return homsOperatorPwd;
    }

    public void setHomsOperatorPwd(String homsOperatorPwd) {
        this.homsOperatorPwd = homsOperatorPwd;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getRenewNumber() {
        return renewNumber;
    }

    public void setRenewNumber(Integer renewNumber) {
        this.renewNumber = renewNumber;
    }

    public BankrollApplicantResult getApplicant() {
        return applicant;
    }

    public void setApplicant(BankrollApplicantResult applicant) {
        this.applicant = applicant;
    }

    public ManageBankrollCreatorResult getCreator() {
        return creator;
    }

    public void setCreator(ManageBankrollCreatorResult creator) {
        this.creator = creator;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public BigDecimal getSettleMoney() {
        return settleMoney;
    }

    public void setSettleMoney(BigDecimal settleMoney) {
        this.settleMoney = settleMoney;
    }
}
