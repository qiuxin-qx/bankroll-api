package com.goldbao.bankroll.model.homs;

import com.goldbao.bankroll.model.Model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author shuyu.fang
 */
@Entity
@Table(name = "bk_homs_comb_asset")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class HomsCombAsset extends Model implements Serializable {
   
	private static final long serialVersionUID = -4344122951810954424L;
	@Column(name = "error_no")
    private String errorNo;
    @Column(name = "error_info")
    private String errorInfo;
    @Column(name = "fund_account")
    private String fundAccount;
    @Column(name = "asset_id")
    private String assetId;
    @Column(name = "combine_id")
    private String combineId;
    @Column(name = "asset_value")
    private String assetValue;
    @Column(name = "asset_total_value")
    private String assetTotalValue;
    @Column(name = "current_cash")
    private String currentCash;
    @Column(name = "stock_asset")
    private String stockAsset;
    @Column(name = "fund_asset")
    private String fundAsset;
    @Column(name = "bond_asset")
    private String bondAsset;
    @Column(name = "hg_asset")
    private String hgAsset;
    @Column(name = "future_asset")
    private String futuresAsset;
    @Column(name = "update_time")
    private Date updateTime;

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

    public String getFundAccount() {
        return fundAccount;
    }

    public void setFundAccount(String fundAccount) {
        this.fundAccount = fundAccount;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getCombineId() {
        return combineId;
    }

    public void setCombineId(String combineId) {
        this.combineId = combineId;
    }

    public String getAssetValue() {
        return assetValue;
    }

    public void setAssetValue(String assetValue) {
        this.assetValue = assetValue;
    }

    public String getAssetTotalValue() {
        return assetTotalValue;
    }

    public void setAssetTotalValue(String assetTotalValue) {
        this.assetTotalValue = assetTotalValue;
    }

    public String getCurrentCash() {
        return currentCash;
    }

    public void setCurrentCash(String currentCash) {
        this.currentCash = currentCash;
    }

    public String getStockAsset() {
        return stockAsset;
    }

    public void setStockAsset(String stockAsset) {
        this.stockAsset = stockAsset;
    }

    public String getFundAsset() {
        return fundAsset;
    }

    public void setFundAsset(String fundAsset) {
        this.fundAsset = fundAsset;
    }

    public String getBondAsset() {
        return bondAsset;
    }

    public void setBondAsset(String bondAsset) {
        this.bondAsset = bondAsset;
    }

    public String getHgAsset() {
        return hgAsset;
    }

    public void setHgAsset(String hgAsset) {
        this.hgAsset = hgAsset;
    }

    public String getFuturesAsset() {
        return futuresAsset;
    }

    public void setFuturesAsset(String futuresAsset) {
        this.futuresAsset = futuresAsset;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
