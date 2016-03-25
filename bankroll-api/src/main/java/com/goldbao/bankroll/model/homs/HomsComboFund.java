package com.goldbao.bankroll.model.homs;

import com.goldbao.bankroll.model.Model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author shuyu.fang
 */
@Entity
@Table(name = "bk_homs_combo_fund")
@AttributeOverride(name = "id", column = @Column(name = "combofund_id"))
public class HomsComboFund extends Model implements Serializable {
	private static final long serialVersionUID = 4644140481267389794L;
	@Column(name = "fund_account")
    private String fundAccount;
    @Column(name = "combine_id")
    private String combineId;
    @Column(name = "enable_balance")
    private String enableBalance;
    @Column(name = "enable_balance_t1")
    private String enableBalanceT1;

    private String date;
    @Column(name = "currency_no")
    private String currencyNo;

    public String getFundAccount() {
        return fundAccount;
    }

    public void setFundAccount(String fundAccount) {
        this.fundAccount = fundAccount;
    }

    public String getCombineId() {
        return combineId;
    }

    public void setCombineId(String combineId) {
        this.combineId = combineId;
    }

    public String getEnableBalance() {
        return enableBalance;
    }

    public void setEnableBalance(String enableBalance) {
        this.enableBalance = enableBalance;
    }

    public String getEnableBalanceT1() {
        return enableBalanceT1;
    }

    public void setEnableBalanceT1(String enableBalanceT1) {
        this.enableBalanceT1 = enableBalanceT1;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCurrencyNo() {
        return currencyNo;
    }

    public void setCurrencyNo(String currencyNo) {
        this.currencyNo = currencyNo;
    }
}
