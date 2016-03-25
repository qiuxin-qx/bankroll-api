package com.goldbao.homs.result;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 组合流水查询
 */
public class HomsQueryCurrentResult {

    @JsonProperty("fund_account")
    private String fundAccount;
    @JsonProperty("combine_id")
    private String combineId;
    @JsonProperty("date")
    private String date;
    @JsonProperty("occur_balance")
    private String occurBalance;
    @JsonProperty("exchange_type")
    private String exchangeType;
    @JsonProperty("stock_code")
    private String stockCode;
    @JsonProperty("busin_opflag")
    private String businOpflag;
    @JsonProperty("busin_caption")
    private String businCaption;
    @JsonProperty("post_balance")
    private String postBalance;
    @JsonProperty("business_price")
    private String businessPrice;
    @JsonProperty("business_amount")
    private String businessAmount;
    @JsonProperty("total_fare")
    private String totalFare;
    @JsonProperty("serial_no")
    private String serialNo;
    @JsonProperty("entrustdir_name")
    private String entrustdirName;

    @JsonProperty("entrust_price")
    private String entrustPrice;
    @JsonProperty("currency_name")
    private String currencyName;

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
     * 日期
     */
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    /**
     * 发生金额
     */
    public String getOccurBalance() {
        return occurBalance;
    }

    public void setOccurBalance(String occurBalance) {
        this.occurBalance = occurBalance;
    }
    /**
     * 交易市场
     */
    public String getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
    }
    /**
     * 证券代码
     */
    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }
    /**
     * 业务类别
     */
    public String getBusinOpflag() {
        return businOpflag;
    }

    public void setBusinOpflag(String businOpflag) {
        this.businOpflag = businOpflag;
    }
    /**
     * 业务名称
     */
    public String getBusinCaption() {
        return businCaption;
    }

    public void setBusinCaption(String businCaption) {
        this.businCaption = businCaption;
    }
    /**
     * 发生后金额
     */
    public String getPostBalance() {
        return postBalance;
    }

    public void setPostBalance(String postBalance) {
        this.postBalance = postBalance;
    }
    /**
     * 成交价格
     */
    public String getBusinessPrice() {
        return businessPrice;
    }

    public void setBusinessPrice(String businessPrice) {
        this.businessPrice = businessPrice;
    }
    /**
     * 成交数量
     */
    public String getBusinessAmount() {
        return businessAmount;
    }

    public void setBusinessAmount(String businessAmount) {
        this.businessAmount = businessAmount;
    }
    /**
     * 成交费用
     */
    public String getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(String totalFare) {
        this.totalFare = totalFare;
    }
    /**
     * 流水号
     */
    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }
    /**
     * 委托方向名称
     */
    public String getEntrustdirName() {
        return entrustdirName;
    }

    public void setEntrustdirName(String entrustdirName) {
        this.entrustdirName = entrustdirName;
    }

    /**
     * 委托价格
     */
    public String getEntrustPrice() {
        return entrustPrice;
    }

    public void setEntrustPrice(String entrustPrice) {
        this.entrustPrice = entrustPrice;
    }
}
