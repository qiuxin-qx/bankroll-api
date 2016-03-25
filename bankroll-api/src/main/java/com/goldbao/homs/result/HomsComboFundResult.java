package com.goldbao.homs.result;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 组合层现货可用资金查询
 */
public class HomsComboFundResult {

    @JsonProperty("fund_account")
    private String fundAccount;
    @JsonProperty("combine_id")
    private String combineId;
    @JsonProperty("enable_balance")
    private String enableBalance;
    @JsonProperty("enable_balance_t1")
    private String enableBalanceT1;
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
     * 可用资金（T+0）
     */
    public String getEnableBalance() {
        return enableBalance;
    }

    public void setEnableBalance(String enableBalance) {
        this.enableBalance = enableBalance;
    }
    /**
     * 可用资金（T+1）
     */
    public String getEnableBalanceT1() {
        return enableBalanceT1;
    }

    public void setEnableBalanceT1(String enableBalanceT1) {
        this.enableBalanceT1 = enableBalanceT1;
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
