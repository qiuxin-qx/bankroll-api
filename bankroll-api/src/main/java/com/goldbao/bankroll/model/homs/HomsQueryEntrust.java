package com.goldbao.bankroll.model.homs;

import com.goldbao.bankroll.model.Model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author shuyu.fang
 */
@Entity
@Table(name = "bk_homs_query_entrust")
@AttributeOverride(name = "id", column = @Column(name = "operator_id"))
public class HomsQueryEntrust extends Model implements Serializable {

	private static final long serialVersionUID = 6948095801108951658L;
	@Column(name = "batch_no")
    private String batchNo;
    @Column(name = "entrust_no")
    private String entrustNo;
    @Column(name = "fund_account")
    private String fundAccount;
    @Column(name = "combine_id")
    private String combineId;
    @Column(name = "instruct_no")
    private String instructNo;
    @Column(name = "instruct_mod_no")
    private String instructmodNo;
    @Column(name = "stock_code")
    private String stockCode;
    @Column(name = "exchange_type")
    private String exchangeType;
    @Column(name = "stock_account")
    private String stockAccount;
    @Column(name = "seat_no")
    private String seatNo;
    @Column(name = "invest_way")
    private String investWay;
    @Column(name = "entrust_direction")
    private String entrustDirection;
    @Column(name = "entrust_price")
    private String entrustPrice;
    @Column(name = "entrust_amount")
    private String entrustAmount;
    @Column(name = "entrust_time")
    private String entrustTime;
    @Column(name = "report_time")
    private String reportTime;
    @Column(name = "business_amount")
    private String businessAmount;
    @Column(name = "business_balance")
    private String businessBalance;
    @Column(name = "withdraw_amount")
    private String withdrawAmount;
    @Column(name = "amentrust_status")
    private String amentrustStatus;
    @Column(name = "cancel_info")
    private String cancelInfo;

    @Column(name = "asset_id")
    private String assetId;
    @Column(name = "position_str")
    private String positionStr;

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getEntrustNo() {
        return entrustNo;
    }

    public void setEntrustNo(String entrustNo) {
        this.entrustNo = entrustNo;
    }

    public String getFundAccount() {
        return fundAccount;
    }

    public void setFundAccount(String fundAccount) {
        this.fundAccount = fundAccount;
    }

    public String getCombineId() {
        return combineId;
    }

    public void setCombineId(String combineId) {
        this.combineId = combineId;
    }

    public String getInstructNo() {
        return instructNo;
    }

    public void setInstructNo(String instructNo) {
        this.instructNo = instructNo;
    }

    public String getInstructmodNo() {
        return instructmodNo;
    }

    public void setInstructmodNo(String instructmodNo) {
        this.instructmodNo = instructmodNo;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
    }

    public String getStockAccount() {
        return stockAccount;
    }

    public void setStockAccount(String stockAccount) {
        this.stockAccount = stockAccount;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public String getInvestWay() {
        return investWay;
    }

    public void setInvestWay(String investWay) {
        this.investWay = investWay;
    }

    public String getEntrustDirection() {
        return entrustDirection;
    }

    public void setEntrustDirection(String entrustDirection) {
        this.entrustDirection = entrustDirection;
    }

    public String getEntrustPrice() {
        return entrustPrice;
    }

    public void setEntrustPrice(String entrustPrice) {
        this.entrustPrice = entrustPrice;
    }

    public String getEntrustAmount() {
        return entrustAmount;
    }

    public void setEntrustAmount(String entrustAmount) {
        this.entrustAmount = entrustAmount;
    }

    public String getEntrustTime() {
        return entrustTime;
    }

    public void setEntrustTime(String entrustTime) {
        this.entrustTime = entrustTime;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public String getBusinessAmount() {
        return businessAmount;
    }

    public void setBusinessAmount(String businessAmount) {
        this.businessAmount = businessAmount;
    }

    public String getBusinessBalance() {
        return businessBalance;
    }

    public void setBusinessBalance(String businessBalance) {
        this.businessBalance = businessBalance;
    }

    public String getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(String withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }

    public String getAmentrustStatus() {
        return amentrustStatus;
    }

    public void setAmentrustStatus(String amentrustStatus) {
        this.amentrustStatus = amentrustStatus;
    }

    public String getCancelInfo() {
        return cancelInfo;
    }

    public void setCancelInfo(String cancelInfo) {
        this.cancelInfo = cancelInfo;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getPositionStr() {
        return positionStr;
    }

    public void setPositionStr(String positionStr) {
        this.positionStr = positionStr;
    }
}
