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
@Table(name = "bk_homs_realdeal_record")
@AttributeOverride(name = "id", column = @Column(name = "operator_id"))
public class HomsRealdealRecord extends Model implements Serializable {

	private static final long serialVersionUID = 472131280833815682L;
	@Column(name = "position_str")
    private String positionStr;
    @Column(name = "business_no")
    private String businessNo;
    @Column(name = "batch_no")
    private String batchNo;
    @Column(name = "entrust_no")
    private String entrustNo;
    @Column(name = "fund_account")
    private String fundAccount;
    @Column(name = "combine_id")
    private String combineId;
    @Column(name = "stock_code")
    private String stockCode;
    @Column(name = "exchange_type")
    private String exchangeType;
    @Column(name = "stock_account")
    private String stockAccount;
    @Column(name = "seat_no")
    private String seatNo;
    @Column(name = "entrust_direction")
    private String entrustDirection;
    @Column(name = "business_time")
    private String businessTime;
    @Column(name = "business_amount")
    private String businessAmount;
    @Column(name = "business_price")
    private String businessPrice;
    @Column(name = "business_balance")
    private String businessBalance;
    @Column(name = "ampay_off_type")
    private String ampayoffType;
    @Column(name = "total_fare")
    private String totalFare;
    @Column(name = "yj_fare")
    private String yjFare;
    @Column(name = "gh_fare")
    private String ghFare;
    @Column(name = "yh_fare")
    private String yhFare;
    @Column(name = "jy_fare")
    private String jyFare;
    @Column(name = "asset_id")
    private String assetId;

    public String getPositionStr() {
        return positionStr;
    }

    public void setPositionStr(String positionStr) {
        this.positionStr = positionStr;
    }

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }

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

    public String getEntrustDirection() {
        return entrustDirection;
    }

    public void setEntrustDirection(String entrustDirection) {
        this.entrustDirection = entrustDirection;
    }

    public String getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(String businessTime) {
        this.businessTime = businessTime;
    }

    public String getBusinessAmount() {
        return businessAmount;
    }

    public void setBusinessAmount(String businessAmount) {
        this.businessAmount = businessAmount;
    }

    public String getBusinessPrice() {
        return businessPrice;
    }

    public void setBusinessPrice(String businessPrice) {
        this.businessPrice = businessPrice;
    }

    public String getBusinessBalance() {
        return businessBalance;
    }

    public void setBusinessBalance(String businessBalance) {
        this.businessBalance = businessBalance;
    }

    public String getAmpayoffType() {
        return ampayoffType;
    }

    public void setAmpayoffType(String ampayoffType) {
        this.ampayoffType = ampayoffType;
    }

    public String getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(String totalFare) {
        this.totalFare = totalFare;
    }

    public String getYjFare() {
        return yjFare;
    }

    public void setYjFare(String yjFare) {
        this.yjFare = yjFare;
    }

    public String getGhFare() {
        return ghFare;
    }

    public void setGhFare(String ghFare) {
        this.ghFare = ghFare;
    }

    public String getYhFare() {
        return yhFare;
    }

    public void setYhFare(String yhFare) {
        this.yhFare = yhFare;
    }

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
