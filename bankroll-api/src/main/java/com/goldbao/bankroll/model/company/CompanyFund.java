package com.goldbao.bankroll.model.company;

import com.goldbao.bankroll.model.Model;
import com.goldbao.bankroll.model.enums.EnumCompanyFundType;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 公司资金表
 * 目前公司资金划分为以下几类：
 * 1. 用户充值【属冻结资金】
 * 2. 管理费（月息）【属公司收入】
 * 3. 配资资金【同样属冻结资金】
 * 4. 融资【终端用户配资来源】
 *
 * @author shuyu.fang
 */
@Entity
@Table(name = "bk_company_fund")
@AttributeOverride(name = "id", column = @Column(name = "fund_id"))
public class CompanyFund extends Model implements Serializable {
	private static final long serialVersionUID = -2083797345405469580L;
	@Column(name = "fund_code")
    private String fundCode;
    @Column(name = "fund_name")
    private String fundName;

    private BigDecimal amount;
    @Column(name = "last_amount")
    private BigDecimal lastAmount;
    @Column(name = "last_income")
    private BigDecimal lastIncome;
    @Column(name = "last_do_time")
    private Date lastDoTime;

    /**
     * 账户类型，唯一，账户首选筛选字段
     */
    @Column(name = "fund_type", unique = true)
    private EnumCompanyFundType fundType;

    /**
     * 账户对外标识，唯一
     */
    public String getFundCode() {
        return fundCode;
    }
    /**
     * 账户对外标识，唯一
     */
    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }
    /**
     * 账户标识描述
     */
    public String getFundName() {
        return fundName;
    }
    /**
     * 账户标识描述
     */
    public void setFundName(String fundName) {
        this.fundName = fundName;
    }
    /**
     * 账户金额
     */
    public BigDecimal getAmount() {
        return amount;
    }
    /**
     * 账户金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    /**
     * 账户上次金额
     */
    public BigDecimal getLastAmount() {
        return lastAmount;
    }
    /**
     * 账户上次金额
     */
    public void setLastAmount(BigDecimal lastAmount) {
        this.lastAmount = lastAmount;
    }
    /**
     * 账户上次收入（负代表支出）
     */
    public BigDecimal getLastIncome() {
        return lastIncome;
    }
    /**
     * 账户上次收入（负代表支出）
     */
    public void setLastIncome(BigDecimal lastIncome) {
        this.lastIncome = lastIncome;
    }
    /**
     * 最后操作时间
     */
    public Date getLastDoTime() {
        return lastDoTime;
    }
    /**
     * 最后操作时间
     */
    public void setLastDoTime(Date lastDoTime) {
        this.lastDoTime = lastDoTime;
    }
    /**
     * 账户类型，唯一
     */
    public EnumCompanyFundType getFundType() {
        return fundType;
    }
    /**
     * 账户类型，唯一
     */
    public void setFundType(EnumCompanyFundType fundType) {
        this.fundType = fundType;
    }
}
