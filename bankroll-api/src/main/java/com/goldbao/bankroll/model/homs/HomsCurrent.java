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
@Table(name = "bk_homs_current")
@AttributeOverride(name = "id", column = @Column(name = "current_id"))
public class HomsCurrent extends Model implements Serializable {

	private static final long serialVersionUID = -5514543766577352599L;
	@Column(name = "fund_account")
    private String fundAccount;
    @Column(name = "combine_id")
    private String combineId;
    
    private String date;
    @Column(name = "occur_balance")
    private String occurBalance;
    @Column(name = "exchange_type")
    private String exchangeType;
    @Column(name = "stock_code")
    private String stockCode;
    @Column(name = "busin_op_flag")
    private String businOpflag;
    @Column(name = "busin_caption")
    private String businCaption;
    @Column(name = "post_balance")
    private String postBalance;
    @Column(name = "business_price")
    private String businessPrice;
    @Column(name = "business_amount")
    private String businessAmount;
    @Column(name = "total_fare")
    private String totalFare;
    @Column(name = "serial_no")
    private String serialNo;
    @Column(name = "entrust_dir_name")
    private String entrustdirName;
    @Column(name = "entrust_price")
    private String entrustPrice;
    @Column(name = "currency_name")
    private String currencyName;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOccurBalance() {
        return occurBalance;
    }

    public void setOccurBalance(String occurBalance) {
        this.occurBalance = occurBalance;
    }

    public String getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getBusinOpflag() {
        return businOpflag;
    }

    public void setBusinOpflag(String businOpflag) {
        this.businOpflag = businOpflag;
    }

    public String getBusinCaption() {
        return businCaption;
    }

    public void setBusinCaption(String businCaption) {
        this.businCaption = businCaption;
    }

    public String getPostBalance() {
        return postBalance;
    }

    public void setPostBalance(String postBalance) {
        this.postBalance = postBalance;
    }

    public String getBusinessPrice() {
        return businessPrice;
    }

    public void setBusinessPrice(String businessPrice) {
        this.businessPrice = businessPrice;
    }

    public String getBusinessAmount() {
        return businessAmount;
    }

    public void setBusinessAmount(String businessAmount) {
        this.businessAmount = businessAmount;
    }

    public String getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(String totalFare) {
        this.totalFare = totalFare;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getEntrustdirName() {
        return entrustdirName;
    }

    public void setEntrustdirName(String entrustdirName) {
        this.entrustdirName = entrustdirName;
    }

    public String getEntrustPrice() {
        return entrustPrice;
    }

    public void setEntrustPrice(String entrustPrice) {
        this.entrustPrice = entrustPrice;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }
}
