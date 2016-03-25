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
@Table(name = "bk_homs_combo_stock")
@AttributeOverride(name = "id", column = @Column(name = "combostock_id"))
public class HomsComboStock extends Model implements Serializable {
	private static final long serialVersionUID = 7654902664431303855L;
	@Column(name = "init_date")
    private String initDate;
    @Column(name = "fund_account")
    private String fundAccount;
    @Column(name = "combine_id")
    private String combineId;
    @Column(name = "stock_account")
    private String stockAccount;
    @Column(name = "seat_no")
    private String seatNo;
    @Column(name = "exchange_type")
    private String exchangeType;
    @Column(name = "stock_code")
    private String stockCode;
    @Column(name = "invest_way")
    private String investWay;
    @Column(name = "pupil_flag")
    private String pupilFlag;
    @Column(name = "begin_amount")
    private String beginAmount;
    @Column(name = "current_amount")
    private String currentAmount;
    @Column(name = "enable_amount")
    private String enableAmount;
    @Column(name = "cost_balance")
    private String costBalance;
    @Column(name = "market_value")
    private String marketValue;
    @Column(name = "buy_amount")
    private String buyAmount;
    @Column(name = "sell_amount")
    private String sellAmount;
    @Column(name = "asset_id")
    private String assetId;

    public String getInitDate() {
        return initDate;
    }

    public void setInitDate(String initDate) {
        this.initDate = initDate;
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

    public String getInvestWay() {
        return investWay;
    }

    public void setInvestWay(String investWay) {
        this.investWay = investWay;
    }

    public String getPupilFlag() {
        return pupilFlag;
    }

    public void setPupilFlag(String pupilFlag) {
        this.pupilFlag = pupilFlag;
    }

    public String getBeginAmount() {
        return beginAmount;
    }

    public void setBeginAmount(String beginAmount) {
        this.beginAmount = beginAmount;
    }

    public String getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(String currentAmount) {
        this.currentAmount = currentAmount;
    }

    public String getEnableAmount() {
        return enableAmount;
    }

    public void setEnableAmount(String enableAmount) {
        this.enableAmount = enableAmount;
    }

    public String getCostBalance() {
        return costBalance;
    }

    public void setCostBalance(String costBalance) {
        this.costBalance = costBalance;
    }

    public String getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(String marketValue) {
        this.marketValue = marketValue;
    }

    public String getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(String buyAmount) {
        this.buyAmount = buyAmount;
    }

    public String getSellAmount() {
        return sellAmount;
    }

    public void setSellAmount(String sellAmount) {
        this.sellAmount = sellAmount;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }
}
