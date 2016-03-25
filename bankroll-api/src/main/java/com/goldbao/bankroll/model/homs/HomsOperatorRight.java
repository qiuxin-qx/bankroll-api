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
@Table(name = "bk_homs_operator_right")
@AttributeOverride(name = "id", column = @Column(name = "right_id"))
public class HomsOperatorRight extends Model implements Serializable {

	private static final long serialVersionUID = 3218804998514165669L;
	@Column(name = "fund_account")
    private String fundAccount;
    @Column(name = "combine_id")
    private String combineId;
    @Column(name = "asset_id")
    private String assetId;
    @Column(name = "asset_name")
    private String assetName;
    @Column(name = "rights")
    private String rights;
    @Column(name = "fund_status")
    private String fundStatus;
    @Column(name = "fund_name")
    private String fundName;

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

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public String getFundStatus() {
        return fundStatus;
    }

    public void setFundStatus(String fundStatus) {
        this.fundStatus = fundStatus;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }
}
