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
@Table(name = "bk_homs_current_stock")
@AttributeOverride(name = "id", column = @Column(name = "currentstock_id"))
public class HomsCurrentStock extends Model implements Serializable {

	private static final long serialVersionUID = 5165616325340766194L;
	@Column(name = "fund_account")
    private String fundAccount;
    @Column(name = "combine_id")
    private String combineId;
    @Column(name = "asset_id")
    private String assetId;
    @Column(name = "current_cash")
    private String currentCash;
    @Column(name = "market_value")
    private String marketValue;


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

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getCurrentCash() {
        return currentCash;
    }

    public void setCurrentCash(String currentCash) {
        this.currentCash = currentCash;
    }

    public String getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(String marketValue) {
        this.marketValue = marketValue;
    }
}
