package com.goldbao.homs.result;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 组合层现货可用资金查询
 */
public class HomsFutuComboFundResult {

    @JsonProperty("fund_account")
    private String fundAccount;
    @JsonProperty("combine_id")
    private String combineId;
    @JsonProperty("futu_enable_balance")
    private String futuEnableBalance;
    @JsonProperty("date")
    private String date;
    @JsonProperty("currency_no")
    private String currencyNo;

    /**
     * 账户编号
     */
    public String getFundAccount() {
        return fundAccount;
    }

    public void setFundAccount(String fundAccount) {
        this.fundAccount = fundAccount;
    }
    /**
     * 组合编号
     */
    public String getCombineId() {
        return combineId;
    }

    public void setCombineId(String combineId) {
        this.combineId = combineId;
    }
    /**
     * 期货可用保证金
     */
    public String getFutuEnableBalance() {
        return futuEnableBalance;
    }

    public void setFutuEnableBalance(String futuEnableBalance) {
        this.futuEnableBalance = futuEnableBalance;
    }
    /**
     * 日期
     */
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    /**
     * 币种
     */
    public String getCurrencyNo() {
        return currencyNo;
    }

    public void setCurrencyNo(String currencyNo) {
        this.currencyNo = currencyNo;
    }
}
