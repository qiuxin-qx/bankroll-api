package com.goldbao.bankroll.model.bankroll;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.goldbao.bankroll.model.Model;

/**
 * @author shuyu.fang
 */
@Entity
@Table(name = "bk_bankroll_homs_info_log")
@AttributeOverride(name = "id", column = @Column(name = "log_id"))
public class BankrollHomsInfoLog extends Model implements Serializable {
	private static final long serialVersionUID = 4542158480988769329L;
	@ManyToOne
    @JoinColumn(name = "record_id")
    private BankrollRecord record; // 关联的配资记录
    @Column(name = "operator_no")
    private String operatorNo;    // 操作员账号
    @Column(name = "asset_id")
    private String assetId;       // 单元编号
    @Column(name = "asset_name")
    private String assetName;     // 单元名称
    @Column(name = "combine_id")
    private String combineId;      // 组合编号
    @Column(name = "combine_name")
    private String combineName;    // 组合名称
    @Column(name = "warning_value", scale = 3)
    private BigDecimal warningValue;  // 警告金额
    @Column(name = "open_value", scale = 3)
    private BigDecimal openValue;      // 平仓金额
    @Column(name = "cur_loan", scale = 3)
    private BigDecimal curLoan;         // 借款金额
    @Column(name = "fund_account")
    private String fundAccount;    // 账户编号
    @Column(name = "asset_total_value", scale = 3)
    private BigDecimal assetTotalValue; // 单元总资产（净值+余额）
    @Column(name = "asset_value", scale = 3)
    private BigDecimal assetValue;       // 单元净值（股票资产+基金资产+期货+回购+债权。。。）
    @Column(name = "current_cash", scale = 3)
    private BigDecimal currentCash;      // 现金余额
    @Column(name = "stock_asset", scale = 3)
    private BigDecimal stockAsset;       // 股票资产
    @Column(name = "fund_asset", scale = 3)
    private BigDecimal fundAsset;        // 基金资产
    @Column(name = "bond_asset", scale = 3)
    private BigDecimal bondAsset;        // 债权资产
    @Column(name = "hg_asset", scale = 3)
    private BigDecimal hgAsset;           // 回购资产
    @Column(name = "future_asset", scale = 3)
    private BigDecimal futureAsset;      // 期货资产

    public BankrollRecord getRecord() {
        return record;
    }

    public void setRecord(BankrollRecord record) {
        this.record = record;
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

    public BigDecimal getWarningValue() {
        return warningValue;
    }

    public void setWarningValue(BigDecimal warningValue) {
        this.warningValue = warningValue;
    }

    public BigDecimal getOpenValue() {
        return openValue;
    }

    public void setOpenValue(BigDecimal openValue) {
        this.openValue = openValue;
    }

    public BigDecimal getCurLoan() {
        return curLoan;
    }

    public void setCurLoan(BigDecimal curLoan) {
        this.curLoan = curLoan;
    }

    public String getFundAccount() {
        return fundAccount;
    }

    public void setFundAccount(String fundAccount) {
        this.fundAccount = fundAccount;
    }

    public BigDecimal getAssetTotalValue() {
        return assetTotalValue;
    }

    public void setAssetTotalValue(BigDecimal assetTotalValue) {
        this.assetTotalValue = assetTotalValue;
    }

    public BigDecimal getAssetValue() {
        return assetValue;
    }

    public void setAssetValue(BigDecimal assetValue) {
        this.assetValue = assetValue;
    }

    public BigDecimal getCurrentCash() {
        return currentCash;
    }

    public void setCurrentCash(BigDecimal currentCash) {
        this.currentCash = currentCash;
    }

    public BigDecimal getStockAsset() {
        return stockAsset;
    }

    public void setStockAsset(BigDecimal stockAsset) {
        this.stockAsset = stockAsset;
    }

    public BigDecimal getFundAsset() {
        return fundAsset;
    }

    public void setFundAsset(BigDecimal fundAsset) {
        this.fundAsset = fundAsset;
    }

    public BigDecimal getBondAsset() {
        return bondAsset;
    }

    public void setBondAsset(BigDecimal bondAsset) {
        this.bondAsset = bondAsset;
    }

    public BigDecimal getHgAsset() {
        return hgAsset;
    }

    public void setHgAsset(BigDecimal hgAsset) {
        this.hgAsset = hgAsset;
    }

    public BigDecimal getFutureAsset() {
        return futureAsset;
    }

    public void setFutureAsset(BigDecimal futureAsset) {
        this.futureAsset = futureAsset;
    }
}
