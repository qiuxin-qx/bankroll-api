package com.goldbao.homs.result;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 成交查询
 */
public class HomsRealdealResult {

    @JsonProperty("position_str")
    private String positionStr;
    @JsonProperty("business_no")
    private String businessNo;
    @JsonProperty("batch_no")
    private String batchNo;
    @JsonProperty("entrust_no")
    private String entrustNo;
    @JsonProperty("fund_account")
    private String fundAccount;
    @JsonProperty("combine_id")
    private String combineId;
    @JsonProperty("stock_code")
    private String stockCode;
    @JsonProperty("exchange_type")
    private String exchangeType;
    @JsonProperty("stock_account")
    private String stockAccount;
    @JsonProperty("seat_no")
    private String seatNo;
    @JsonProperty("entrust_direction")
    private String entrustDirection;
    @JsonProperty("business_time")
    private String businessTime;
    @JsonProperty("business_amount")
    private String businessAmount;
    @JsonProperty("business_price")
    private String businessPrice;
    @JsonProperty("business_balance")
    private String businessBalance;
    @JsonProperty("ampayoff_type")
    private String ampayoffType;
    @JsonProperty("total_fare")
    private String totalFare;
    @JsonProperty("yj_fare")
    private String yjFare;
    @JsonProperty("gh_fare")
    private String ghFare;
    @JsonProperty("yh_fare")
    private String yhFare;
    @JsonProperty("jy_fare")
    private String jyFare;

    @JsonProperty("asset_id")
    private String assetId;

    /**
     * 定位序号
     */
    public String getPositionStr() {
        return positionStr;
    }

    public void setPositionStr(String positionStr) {
        this.positionStr = positionStr;
    }
    /**
     * 成交序号
     */
    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }
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
     * 委托序号
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
     * 席位代码
     */
    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }
    /**
     * 委托类型
     */
    public String getEntrustDirection() {
        return entrustDirection;
    }

    public void setEntrustDirection(String entrustDirection) {
        this.entrustDirection = entrustDirection;
    }
    /**
     * 成交时间
     */
    public String getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(String businessTime) {
        this.businessTime = businessTime;
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
     * 成交价格
     */
    public String getBusinessPrice() {
        return businessPrice;
    }

    public void setBusinessPrice(String businessPrice) {
        this.businessPrice = businessPrice;
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
     * 平仓类型
     */
    public String getAmpayoffType() {
        return ampayoffType;
    }

    public void setAmpayoffType(String ampayoffType) {
        this.ampayoffType = ampayoffType;
    }
    /**
     * 总费用
     */
    public String getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(String totalFare) {
        this.totalFare = totalFare;
    }
    /**
     * 佣金
     */
    public String getYjFare() {
        return yjFare;
    }

    public void setYjFare(String yjFare) {
        this.yjFare = yjFare;
    }
    /**
     * 过户费
     */
    public String getGhFare() {
        return ghFare;
    }

    public void setGhFare(String ghFare) {
        this.ghFare = ghFare;
    }
    /**
     * 印花税
     */
    public String getYhFare() {
        return yhFare;
    }

    public void setYhFare(String yhFare) {
        this.yhFare = yhFare;
    }
    /**
     * 交易费
     */
    public String getJyFare() {
        return jyFare;
    }

    public void setJyFare(String jyFare) {
        this.jyFare = jyFare;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }
}
