package com.goldbao.homs.result;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 股票资产查询
 */
public class HomsQueryStockCurrentResult {

    @JsonProperty("fund_account")
    private String fundAccount;
    @JsonProperty("combine_id")
    private String combineId;
    @JsonProperty("asset_id")
    private String assetId;
    @JsonProperty("current_cash")
    private String currentCash;
    @JsonProperty("market_value")
    private String marketValue;

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
     * 单元编号
     */
    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    /**
     * 现金资产
     */
    public String getCurrentCash() {
        return currentCash;
    }

    public void setCurrentCash(String currentCash) {
        this.currentCash = currentCash;
    }
    /**
     * 股票市值
     */
    public String getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(String marketValue) {
        this.marketValue = marketValue;
    }
}
