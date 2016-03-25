package com.goldbao.bankroll.model.bankroll;

import com.goldbao.bankroll.model.Model;
import com.goldbao.bankroll.model.Organize;
import com.goldbao.bankroll.model.enums.EnumBankrollRecordStatus;
import com.goldbao.bankroll.model.enums.EnumCycleUnit;
import com.goldbao.bankroll.model.manage.sysuser.SysUser;
import com.goldbao.bankroll.model.user.User;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 配资记录
 * @author shuyu.fang
 */
@Entity
@Table(name = "bk_bankroll_record")
@AttributeOverride(name = "id", column = @Column(name = "record_id"))
public class BankrollRecord extends Model implements Serializable {

	private static final long serialVersionUID = 6415743060749809190L;

	@OneToOne
    @JoinColumn(name = "apply_id")
    private BankrollApply apply; // 对应的申请记录

    @Column(name = "bankroll_no")
    private String bankrollNo; // @see BankrollApply.bankrollNo

    private BigDecimal money; // 申请配资金额

    private BigDecimal deposit; // 保证金

    @Column(name = "management_fee")
    private BigDecimal managementFee; // 单位（月，天）服务费（利息）

    @Column(name = "open_line_money")
    private BigDecimal openLineMoney; // 平仓金额
    @Column(name = "warning_line_money")
    private BigDecimal warningLineMoney; // 警告金额

    private Integer cycle; // 配资周期（天、月）

    @Column(name = "cycle_unit")
    private EnumCycleUnit cycleUnit; // 配资周期单位

    private EnumBankrollRecordStatus status; // 状态

    // TODO homs帐号和配资记录绑定，后期分配homs帐号时，需添加相关属性

    @Column(name = "homs_operator_no")
    private String homsOperatorNo; // homs帐号
    @Column(name = "homs_operator_pwd")
    private String homsOperatorPwd; // homs密码
    @Column(name = "start_date")
    private Date startDate;    // 配资开始时间
    @Column(name = "end_date")
    private Date endDate;    // 配资结束时间
    @Column(name = "real_end_date")
    private Date realEndDate;// 实际配资结束时间

    @Column(name = "renew_number")
    private Integer renewNumber; // 续约次数

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private User applicant; // 申请人
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private SysUser creator; // 创建人（后台管理人员）

    @ManyToOne
    @JoinColumn(name = "org_id")
    private Organize organize;
    @Column(name = "update_time")
    private Date updateTime; // 修改时间

    @Column(name = "settle_money")
    private BigDecimal settleMoney;

    /**
     * 对应的申请记录
     */
    public BankrollApply getApply() {
        return apply;
    }
    /**
     * 对应的申请记录
     */
    public void setApply(BankrollApply apply) {
        this.apply = apply;
    }
    /**
     * @see BankrollApply bankrollNo
     */
    public String getBankrollNo() {
        return bankrollNo;
    }
    /**
     * @see BankrollApply bankrollNo
     */
    public void setBankrollNo(String bankrollNo) {
        this.bankrollNo = bankrollNo;
    }
    /**
     * 配资金额
     */
    public BigDecimal getMoney() {
        return money;
    }
    /**
     * 配资金额
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
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
     * 投资周期（天，月）
     */
    public Integer getCycle() {
        return cycle;
    }
    /**
     * 投资周期（天，月）
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
    public EnumBankrollRecordStatus getStatus() {
        return status;
    }
    /**
     * 状态
     */
    public void setStatus(EnumBankrollRecordStatus status) {
        this.status = status;
    }
    /**
     * homs操作员登录帐号
     */
    public String getHomsOperatorNo() {
        return homsOperatorNo;
    }
    /**
     * homs操作员登录帐号
     */
    public void setHomsOperatorNo(String homsOperatorNo) {
        this.homsOperatorNo = homsOperatorNo;
    }
    /**
     * homs操作员登录密码
     */
    public String getHomsOperatorPwd() {
        return homsOperatorPwd;
    }
    /**
     * homs操作员登录密码
     */
    public void setHomsOperatorPwd(String homsOperatorPwd) {
        this.homsOperatorPwd = homsOperatorPwd;
    }
    /**
     * 计息开始时间
     */
    public Date getStartDate() {
        return startDate;
    }
    /**
     * 计息开始时间
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    /**
     * 计息结束时间
     */
    public Date getEndDate() {
        return endDate;
    }
    /**
     * 计息结束时间
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    /**
     * 实际计息结束时间
     */
    public Date getRealEndDate() {
        return realEndDate;
    }
    /**
     * 实际计息结束时间
     */
    public void setRealEndDate(Date realEndDate) {
        this.realEndDate = realEndDate;
    }

    /**
     * 续约次数
     */
    public Integer getRenewNumber() {
        return renewNumber;
    }
    /**
     * 续约次数
     */
    public void setRenewNumber(Integer renewNumber) {
        this.renewNumber = renewNumber;
    }

    /**
     * 配资人
     */
    public User getApplicant() {
        return applicant;
    }
    /**
     * 配资人
     */
    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }
    /**
     * 创建人（审核人）
     */
    public SysUser getCreator() {
        return creator;
    }
    /**
     * 创建人（审核人）
     */
    public void setCreator(SysUser creator) {
        this.creator = creator;
    }
    /**
     * 提供资金的组织
     */
    public Organize getOrganize() {
		return organize;
	}
    /**
     * 提供资金的组织
     */
	public void setOrganize(Organize organize) {
		this.organize = organize;
	}
	/**
     * 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }
    /**
     * 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 结算金额
     */
    public BigDecimal getSettleMoney() {
        return settleMoney;
    }
    /**
     * 结算金额
     */
    public void setSettleMoney(BigDecimal settleMoney) {
        this.settleMoney = settleMoney;
    }
}
