package com.goldbao.homs.result;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 组合资产查询
 */
public class HomsQueryCombAssetResult {

    @JsonProperty("error_no")
    private String errorNo;
    @JsonProperty("error_info")
    private String errorInfo;
    @JsonProperty("fund_account")
    private String fundAccount;
    @JsonProperty("asset_id")
    private String assetId;
    @JsonProperty("combine_id")
    private String combineId;
    @JsonProperty("asset_value")
    private String assetValue;
    @JsonProperty("asset_total_value")
    private String assetTotalValue;
    @JsonProperty("current_cash")
    private String currentCash;
    @JsonProperty("stock_asset")
    private String stockAsset;
    @JsonProperty("fund_asset")
    private String fundAsset;
    @JsonProperty("bond_asset")
    private String bondAsset;
    @JsonProperty("hg_asset")
    private String hgAsset;
    @JsonProperty("futures_asset")
    private String futuresAsset;

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
     * 账户编号
     */
    public String getFundAccount() {
        return fundAccount;
    }

    public void setFundAccount(String fundAccount) {
        this.fundAccount = fundAccount;
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
     * 组合编号
     */
    public String getCombineId() {
        return combineId;
    }

    public void setCombineId(String combineId) {
        this.combineId = combineId;
    }
    /**
     * 单元净值
     */
    public String getAssetValue() {
        return assetValue;
    }

    public void setAssetValue(String assetValue) {
        this.assetValue = assetValue;
    }
    /**
     * 单元总资产
     */
    public String getAssetTotalValue() {
        return assetTotalValue;
    }

    public void setAssetTotalValue(String assetTotalValue) {
        this.assetTotalValue = assetTotalValue;
    }
    /**
     * 单元现金余额
     */
    public String getCurrentCash() {
        return currentCash;
    }

    public void setCurrentCash(String currentCash) {
        this.currentCash = currentCash;
    }
    /**
     * 单元股票资产
     */
    public String getStockAsset() {
        return stockAsset;
    }
    public void setStockAsset(String stockAsset) {
        this.stockAsset = stockAsset;
    }
    /**
     * 单元基金资产
     */
    public String getFundAsset() {
        return fundAsset;
    }

    public void setFundAsset(String fundAsset) {
        this.fundAsset = fundAsset;
    }
    /**
     * 单元债券资产
     */
    public String getBondAsset() {
        return bondAsset;
    }

    public void setBondAsset(String bondAsset) {
        this.bondAsset = bondAsset;
    }
    /**
     * 单元回购资产
     */
    public String getHgAsset() {
        return hgAsset;
    }

    public void setHgAsset(String hgAsset) {
        this.hgAsset = hgAsset;
    }
    /**
     * 单元期货资产
     */
    public String getFuturesAsset() {
        return futuresAsset;
    }

    public void setFuturesAsset(String futuresAsset) {
        this.futuresAsset = futuresAsset;
    }
}
