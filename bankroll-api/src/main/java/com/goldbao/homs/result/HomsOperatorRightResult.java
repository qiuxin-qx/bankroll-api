package com.goldbao.homs.result;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 账户操作权限查询
 */
public class HomsOperatorRightResult {

    @JsonProperty("fund_account")
    private String fundAccount;
    @JsonProperty("combine_id")
    private String combineId;
    @JsonProperty("asset_id")
    private String assetId;
    @JsonProperty("asset_name")
    private String assetName;
    @JsonProperty("rights")
    private String rights;
    @JsonProperty("fund_status")
    private String fundStatus;
    @JsonProperty("fund_name")
    private String fundName;

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
     * 单元名称
     */
    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }
    /**
     * 权限
     */
    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }
    /**
     * 账户状态
     */
    public String getFundStatus() {
        return fundStatus;
    }

    public void setFundStatus(String fundStatus) {
        this.fundStatus = fundStatus;
    }
    /**
     * 账户名称
     */
    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }
}
