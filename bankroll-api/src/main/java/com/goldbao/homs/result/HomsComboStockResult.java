package com.goldbao.homs.result;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 组合持仓查询
 */
public class HomsComboStockResult {

    @JsonProperty("init_date")
    private String initDate;
    @JsonProperty("fund_account")
    private String fundAccount;
    @JsonProperty("combine_id")
    private String combineId;
    @JsonProperty("stock_account")
    private String stockAccount;
    @JsonProperty("seat_no")
    private String seatNo;
    @JsonProperty("exchange_type")
    private String exchangeType;
    @JsonProperty("stock_code")
    private String stockCode;
    @JsonProperty("invest_way")
    private String investWay;
    @JsonProperty("pupil_flag")
    private String pupilFlag;
    @JsonProperty("begin_amount")
    private String beginAmount;
    @JsonProperty("current_amount")
    private String currentAmount;
    @JsonProperty("enable_amount")
    private String enableAmount;
    @JsonProperty("cost_balance")
    private String costBalance;
    @JsonProperty("market_value")
    private String marketValue;
    @JsonProperty("buy_amount")
    private String buyAmount;
    @JsonProperty("sell_amount")
    private String sellAmount;

    @JsonProperty("asset_id")
    private String assetId;

    /**
     * 日期
     */
    public String getInitDate() {
        return initDate;
    }

    public void setInitDate(String initDate) {
        this.initDate = initDate;
    }
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
     * 股东代码
     */
    public String getStockAccount() {
        return stockAccount;
    }

    public void setStockAccount(String stockAccount) {
        this.stockAccount = stockAccount;
    }
    /**
     * 托管代码
     */
    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }
    /**
     * 市场类别
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
     * 投资类型
     */
    public String getInvestWay() {
        return investWay;
    }

    public void setInvestWay(String investWay) {
        this.investWay = investWay;
    }
    /**
     * 多空标识
     */
    public String getPupilFlag() {
        return pupilFlag;
    }

    public void setPupilFlag(String pupilFlag) {
        this.pupilFlag = pupilFlag;
    }
    /**
     * 期初数量
     */
    public String getBeginAmount() {
        return beginAmount;
    }

    public void setBeginAmount(String beginAmount) {
        this.beginAmount = beginAmount;
    }
    /**
     * 当前数量
     */
    public String getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(String currentAmount) {
        this.currentAmount = currentAmount;
    }
    /**
     * 可用数量
     */
    public String getEnableAmount() {
        return enableAmount;
    }

    public void setEnableAmount(String enableAmount) {
        this.enableAmount = enableAmount;
    }
    /**
     * 当前成本
     */
    public String getCostBalance() {
        return costBalance;
    }

    public void setCostBalance(String costBalance) {
        this.costBalance = costBalance;
    }
    /**
     * 当前市值
     */
    public String getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(String marketValue) {
        this.marketValue = marketValue;
    }
    /**
     * 当日买入数量
     */
    public String getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(String buyAmount) {
        this.buyAmount = buyAmount;
    }
    /**
     * 当日卖出数量
     */
    public String getSellAmount() {
        return sellAmount;
    }

    public void setSellAmount(String sellAmount) {
        this.sellAmount = sellAmount;
    }

    /**
     * 单元编号
     */
    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }
}
