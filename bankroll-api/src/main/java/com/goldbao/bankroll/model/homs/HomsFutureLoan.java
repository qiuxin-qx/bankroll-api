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
@Table(name = "bk_homs_future_loan")
@AttributeOverride(name = "id", column = @Column(name = "loan_id"))
public class HomsFutureLoan extends Model implements Serializable {

	private static final long serialVersionUID = 3217012955141328956L;
	@Column(name = "borrower_name")
    private String borrowerName;
    @Column(name = "begin_asset")
    private String beginAsset;
    @Column(name = "operator_no")
    private String operatorNo;
    @Column(name = "asset_id")
    private String assetId;
    @Column(name = "asset_name")
    private String assetName;
    @Column(name = "future_op_right")
    private String futureOpright;
    @Column(name = "warning_value")
    private String warningValue;
    @Column(name = "stop_value")
    private String stopValue;
    @Column(name = "cur_loan")
    private String curLoan;
    @Column(name = "risk_ratio")
    private String riskRatio;
    @Column(name = "combine_id")
    private String combineId;
    @Column(name = "update_time")
    private Date updateTime;

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public String getBeginAsset() {
        return beginAsset;
    }

    public void setBeginAsset(String beginAsset) {
        this.beginAsset = beginAsset;
    }

    public String getOperatorNo() {
        return operatorNo;
    }

    public void setOperatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
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

    public String getFutureOpright() {
        return futureOpright;
    }

    public void setFutureOpright(String futureOpright) {
        this.futureOpright = futureOpright;
    }

    public String getWarningValue() {
        return warningValue;
    }

    public void setWarningValue(String warningValue) {
        this.warningValue = warningValue;
    }

    public String getStopValue() {
        return stopValue;
    }

    public void setStopValue(String stopValue) {
        this.stopValue = stopValue;
    }

    public String getCurLoan() {
        return curLoan;
    }

    public void setCurLoan(String curLoan) {
        this.curLoan = curLoan;
    }

    public String getRiskRatio() {
        return riskRatio;
    }

    public void setRiskRatio(String riskRatio) {
        this.riskRatio = riskRatio;
    }

    public String getCombineId() {
        return combineId;
    }

    public void setCombineId(String combineId) {
        this.combineId = combineId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
