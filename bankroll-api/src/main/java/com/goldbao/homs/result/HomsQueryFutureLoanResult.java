package com.goldbao.homs.result;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 借贷信息查询
 */
public class HomsQueryFutureLoanResult {

    @JsonProperty("borrower_name")
    private String borrowerName;
    @JsonProperty("begin_asset")
    private String beginAsset;
    @JsonProperty("operator_no")
    private String operatorNo;
    @JsonProperty("asset_id")
    private String assetId;
    @JsonProperty("asset_name")
    private String assetName;
    @JsonProperty("future_opright")
    private String futureOpright;
    @JsonProperty("warning_value")
    private String warningValue;
    @JsonProperty("stop_value")
    private String stopValue;
    @JsonProperty("cur_loan")
    private String curLoan;
    @JsonProperty("risk_ratio")
    private String riskRatio;
    @JsonProperty("combie_id")
    private String combineId;

    /**
     * 借款人
     */
    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }
    /**
     * 期初资产
     */
    public String getBeginAsset() {
        return beginAsset;
    }

    public void setBeginAsset(String beginAsset) {
        this.beginAsset = beginAsset;
    }
    /**
     * 操作员
     */
    public String getOperatorNo() {
        return operatorNo;
    }

    public void setOperatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
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
     * 期货操作权限
     */
    public String getFutureOpright() {
        return futureOpright;
    }

    public void setFutureOpright(String futureOpright) {
        this.futureOpright = futureOpright;
    }
    /**
     * 预警值
     */
    public String getWarningValue() {
        return warningValue;
    }

    public void setWarningValue(String warningValue) {
        this.warningValue = warningValue;
    }
    /**
     * 止损值
     */
    public String getStopValue() {
        return stopValue;
    }

    public void setStopValue(String stopValue) {
        this.stopValue = stopValue;
    }
    /**
     * 已借金额
     */
    public String getCurLoan() {
        return curLoan;
    }

    public void setCurLoan(String curLoan) {
        this.curLoan = curLoan;
    }
    /**
     * 风险度
     */
    public String getRiskRatio() {
        return riskRatio;
    }

    public void setRiskRatio(String riskRatio) {
        this.riskRatio = riskRatio;
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
}
