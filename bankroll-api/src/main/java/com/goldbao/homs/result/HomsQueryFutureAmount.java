package com.goldbao.homs.result;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 组合层期货可用数量查询
 */
public class HomsQueryFutureAmount {

    @JsonProperty("long_current_amount")
    private String longCurrentAmount;
    @JsonProperty("short_current_amount")
    private String shortCurrentAmount;
    @JsonProperty("long_amount")
    private String longAmount;
    @JsonProperty("short_amount")
    private String shortAmount;
    @JsonProperty("yes_long_amount")
    private String yesLongAmount;
    @JsonProperty("yes_short_amount")
    private String yesShortAmount;
    @JsonProperty("error_no")
    private String errorNo;
    @JsonProperty("error_info")
    private String errorInfo;
    @JsonProperty("exchange_type")
    private String exchangeType;
    @JsonProperty("stock_code")
    private String stockCode;
    @JsonProperty("invest_way")
    private String investWay;

    /**
     * 当前多仓数量
     */
    public String getLongCurrentAmount() {
        return longCurrentAmount;
    }

    public void setLongCurrentAmount(String longCurrentAmount) {
        this.longCurrentAmount = longCurrentAmount;
    }
    /**
     * 当前空仓数量
     */
    public String getShortCurrentAmount() {
        return shortCurrentAmount;
    }

    public void setShortCurrentAmount(String shortCurrentAmount) {
        this.shortCurrentAmount = shortCurrentAmount;
    }
    /**
     * 总的当前多仓可用数量
     */
    public String getLongAmount() {
        return longAmount;
    }

    public void setLongAmount(String longAmount) {
        this.longAmount = longAmount;
    }
    /**
     * 总的当前空仓可用数量
     */
    public String getShortAmount() {
        return shortAmount;
    }

    public void setShortAmount(String shortAmount) {
        this.shortAmount = shortAmount;
    }
    /**
     * 当前多仓可用昨持仓数量（仅上期所代码使用有效）
     */
    public String getYesLongAmount() {
        return yesLongAmount;
    }

    public void setYesLongAmount(String yesLongAmount) {
        this.yesLongAmount = yesLongAmount;
    }
    /**
     * 当前空仓可用昨持仓数量（仅上期所代码使用有效）
     */
    public String getYesShortAmount() {
        return yesShortAmount;
    }

    public void setYesShortAmount(String yesShortAmount) {
        this.yesShortAmount = yesShortAmount;
    }

    public String getErrorNo() {
        return errorNo;
    }

    public void setErrorNo(String errorNo) {
        this.errorNo = errorNo;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
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
     * 投资类型
     */
    public String getInvestWay() {
        return investWay;
    }

    public void setInvestWay(String investWay) {
        this.investWay = investWay;
    }
}
