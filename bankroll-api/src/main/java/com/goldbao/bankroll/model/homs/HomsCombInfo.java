package com.goldbao.bankroll.model.homs;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.goldbao.bankroll.model.Model;

/**
 * @author shuyu.fang
 */
@Entity
@Table(name = "bk_homs_comb_info")
@AttributeOverride(name = "id", column = @Column(name = "combinfo_id"))
public class HomsCombInfo extends Model implements Serializable {

	private static final long serialVersionUID = 8258644037325439517L;
	@Column(name = "fund_account")
    private String fundAccount;
    @Column(name = "client_name")
    private String clientName;
    @Column(name = "asset_unit_name")
    private String assetunitName;
    @Column(name = "combine_no")
    private String combineNo;
    @Column(name = "combine_id")
    private String combineId;
    @Column(name = "combine_name")
    private String combineName;
    @Column(name = "asset_id")
    private String assetId;
//    @Column(name = "operator_no")
//    private String operatorNo;

    @Column(name = "stop_value")
    private String stopValue;

    @Column(name = "warning_value")
    private String warningValue;
    @Column(name = "begin_asset")
    private String beginAsset;
    @Column(name = "update_time")
    private Date updateTime;

    public String getFundAccount() {
        return fundAccount;
    }

    public void setFundAccount(String fundAccount) {
        this.fundAccount = fundAccount;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getAssetunitName() {
        return assetunitName;
    }

    public void setAssetunitName(String assetunitName) {
        this.assetunitName = assetunitName;
    }

    public String getCombineNo() {
        return combineNo;
    }

    public void setCombineNo(String combineNo) {
        this.combineNo = combineNo;
    }

    public String getCombineId() {
        return combineId;
    }

    public void setCombineId(String combineId) {
        this.combineId = combineId;
    }

    public String getCombineName() {
        return combineName;
    }

    public void setCombineName(String combineName) {
        this.combineName = combineName;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

//    public String getOperatorNo() {
//        return operatorNo;
//    }
//
//    public void setOperatorNo(String operatorNo) {
//        this.operatorNo = operatorNo;
//    }


    public String getStopValue() {
        return stopValue;
    }

    public void setStopValue(String stopValue) {
        this.stopValue = stopValue;
    }

    public String getWarningValue() {
        return warningValue;
    }

    public void setWarningValue(String warningValue) {
        this.warningValue = warningValue;
    }

    public String getBeginAsset() {
        return beginAsset;
    }

    public void setBeginAsset(String beginAsset) {
        this.beginAsset = beginAsset;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
