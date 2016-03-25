package com.goldbao.homs.result;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 委托查询
 */
public class HomsQueryEntrustResult {

    @JsonProperty("batch_no")
    private String batchNo;
    @JsonProperty("entrust_no")
    private String entrustNo;
    @JsonProperty("fund_account")
    private String fundAccount;
    @JsonProperty("combine_id")
    private String combineId;
    @JsonProperty("instruct_no")
    private String instructNo;
    @JsonProperty("instructmod_no")
    private String instructmodNo;
    @JsonProperty("stock_code")
    private String stockCode;
    @JsonProperty("exchange_type")
    private String exchangeType;
    @JsonProperty("stock_account")
    private String stockAccount;
    @JsonProperty("seat_no")
    private String seatNo;
    @JsonProperty("invest_way")
    private String investWay;
    @JsonProperty("entrust_direction")
    private String entrustDirection;
    @JsonProperty("entrust_price")
    private String entrustPrice;
    @JsonProperty("entrust_amount")
    private String entrustAmount;
    @JsonProperty("entrust_time")
    private String entrustTime;
    @JsonProperty("report_time")
    private String reportTime;
    @JsonProperty("business_amount")
    private String businessAmount;
    @JsonProperty("business_balance")
    private String businessBalance;
    @JsonProperty("withdraw_amount")
    private String withdrawAmount;
    @JsonProperty("amentrust_status")
    private String amentrustStatus;
    @JsonProperty("cancel_info")
    private String cancelInfo;

    @JsonProperty("asset_id")
    private String assetId;
    @JsonProperty("position_str")
    private String positionStr;

    /**
     * 委托批号
     */
    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }
    /**
     * 委托编号
     */
    public String getEntrustNo() {
        return entrustNo;
    }

    public void setEntrustNo(String entrustNo) {
        this.entrustNo = entrustNo;
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
     * 指令序号
     */
    public String getInstructNo() {
        return instructNo;
    }

    public void setInstructNo(String instructNo) {
        this.instructNo = instructNo;
    }
    /**
     * 指令修改序号
     */
    public String getInstructmodNo() {
        return instructmodNo;
    }

    public void setInstructmodNo(String instructmodNo) {
        this.instructmodNo = instructmodNo;
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
     * 交易市场
     */
    public String getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
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
     * 申报席位
     */
    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
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
     * 委托方向
     */
    public String getEntrustDirection() {
        return entrustDirection;
    }

    public void setEntrustDirection(String entrustDirection) {
        this.entrustDirection = entrustDirection;
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
    /**
     * 委托数量
     */
    public String getEntrustAmount() {
        return entrustAmount;
    }

    public void setEntrustAmount(String entrustAmount) {
        this.entrustAmount = entrustAmount;
    }
    /**
     * 委托时间
     */
    public String getEntrustTime() {
        return entrustTime;
    }

    public void setEntrustTime(String entrustTime) {
        this.entrustTime = entrustTime;
    }
    /**
     * 申报时间
     */
    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
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
     * 成交金额
     */
    public String getBusinessBalance() {
        return businessBalance;
    }

    public void setBusinessBalance(String businessBalance) {
        this.businessBalance = businessBalance;
    }
    /**
     * 已撤数量
     */
    public String getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(String withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }
    /**
     * 委托状态
     */
    public String getAmentrustStatus() {
        return amentrustStatus;
    }

    public void setAmentrustStatus(String amentrustStatus) {
        this.amentrustStatus = amentrustStatus;
    }
    /**
     * 废单原因
     */
    public String getCancelInfo() {
        return cancelInfo;
    }

    public void setCancelInfo(String cancelInfo) {
        this.cancelInfo = cancelInfo;
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
     * 定位序号，homs反应这个字段没用，可以忽略
     */
    public String getPositionStr() {
        return positionStr;
    }

    public void setPositionStr(String positionStr) {
        this.positionStr = positionStr;
    }
}
