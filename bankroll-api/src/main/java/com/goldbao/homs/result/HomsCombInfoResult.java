package com.goldbao.homs.result;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 组合信息查询
 */
public class HomsCombInfoResult {

    @JsonProperty("fund_account")
    private String fundAccount;
    @JsonProperty("client_name")
    private String clientName;
    @JsonProperty("assetunit_name")
    private String assetunitName;
    @JsonProperty("combine_no")
    private String combineNo;
    @JsonProperty("combine_id")
    private String combineId;
    @JsonProperty("combine_name")
    private String combineName;
    @JsonProperty("asset_id")
    private String assetId;

    @JsonProperty("stop_value")
    private String stopValue;

    @JsonProperty("warning_value")
    private String warningValue;
    @JsonProperty("begin_asset")
    private String beginAsset;

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
     * 账户名称
     */
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
    /**
     * 获取资产单元名称
     */
    public String getAssetunitName() {
        return assetunitName;
    }
    public void setAssetunitName(String assetunitName) {
        this.assetunitName = assetunitName;
    }
    /**
     * 获取组合序号
     */
    public String getCombineNo() {
        return combineNo;
    }

    public void setCombineNo(String combineNo) {
        this.combineNo = combineNo;
    }
    /**
     * 获取<i>组合编号，重要的字段</i>
     */
    public String getCombineId() {
        return combineId;
    }

    public void setCombineId(String combineId) {
        this.combineId = combineId;
    }
    /**
     * 获取组合名称
     */
    public String getCombineName() {
        return combineName;
    }

    public void setCombineName(String combineName) {
        this.combineName = combineName;
    }
    /**
     * 获取单元编号
     */
    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getStopValue() {
        return stopValue;
    }

    public void setStopValue(String stopValue) {
        this.stopValue = stopValue;
    }

    public String getWarningValue() {
        return warningValue;
    }

    public void setWarningValue(String warningValue) {
        this.warningValue = warningValue;
    }

    public String getBeginAsset() {
        return beginAsset;
    }

    public void setBeginAsset(String beginAsset) {
        this.beginAsset = beginAsset;
    }
}
