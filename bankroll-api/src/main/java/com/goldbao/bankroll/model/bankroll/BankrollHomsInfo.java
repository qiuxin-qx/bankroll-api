package com.goldbao.bankroll.model.bankroll;

import com.goldbao.bankroll.model.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 配资homs一般信息
 * @author shuyu.fang
 */
@Entity
@Table(name = "bk_bankroll_homs_info")
@AttributeOverride(name = "id", column = @Column(name = "homs_id"))
public class BankrollHomsInfo extends Model implements Serializable {
	private static final long serialVersionUID = 7152588922094595915L;
	@OneToOne
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

    @Column(name = "udpate_time")
    private Date updateTime;

    /**
     * 关联的配资记录
     */
    public BankrollRecord getRecord() {
        return record;
    }
    /**
     * 关联的配资记录
     */
    public void setRecord(BankrollRecord record) {
        this.record = record;
    }
    /**
     * 操作员账号
     */
    public String getOperatorNo() {
        return operatorNo;
    }
    /**
     * 操作员账号
     */
    public void setOperatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
    }
    /**
     * 单元编号
     */
    public String getAssetId() {
        return assetId;
    }
    /**
     * 单元编号
     */
    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }
    /**
     * 单元名称
     */
    public String getAssetName() {
        return assetName;
    }
    /**
     * 单元名称
     */
    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }
    /**
     * 组合编号
     */
    public String getCombineId() {
        return combineId;
    }
    /**
     * 组合编号
     */
    public void setCombineId(String combineId) {
        this.combineId = combineId;
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
    /**
     * 借款金额
     */
    public BigDecimal getCurLoan() {
        return curLoan;
    }
    /**
     * 借款金额
     */
    public void setCurLoan(BigDecimal curLoan) {
        this.curLoan = curLoan;
    }

    /**
     * 单元名称
     */
    public String getCombineName() {
        return combineName;
    }
    /**
     * 单元名称
     */
    public void setCombineName(String combineName) {
        this.combineName = combineName;
    }
    /**
     * 账户编号
     */
    public String getFundAccount() {
        return fundAccount;
    }
    /**
     * 账户编号
     */
    public void setFundAccount(String fundAccount) {
        this.fundAccount = fundAccount;
    }
    /**
     * 单元总资产
     */
    public BigDecimal getAssetTotalValue() {
        return assetTotalValue;
    }
    /**
     * 单元总资产
     */
    public void setAssetTotalValue(BigDecimal assetTotalValue) {
        this.assetTotalValue = assetTotalValue;
    }
    /**
     * 单元净值
     */
    public BigDecimal getAssetValue() {
        return assetValue;
    }
    /**
     * 单元净值
     */
    public void setAssetValue(BigDecimal assetValue) {
        this.assetValue = assetValue;
    }
    /**
     * 现金余额
     */
    public BigDecimal getCurrentCash() {
        return currentCash;
    }
    /**
     * 现金余额
     */
    public void setCurrentCash(BigDecimal currentCash) {
        this.currentCash = currentCash;
    }
    /**
     * 股票资产
     */
    public BigDecimal getStockAsset() {
        return stockAsset;
    }
    /**
     * 股票资产
     */
    public void setStockAsset(BigDecimal stockAsset) {
        this.stockAsset = stockAsset;
    }
    /**
     * 基金资产
     */
    public BigDecimal getFundAsset() {
        return fundAsset;
    }
    /**
     * 基金资产
     */
    public void setFundAsset(BigDecimal fundAsset) {
        this.fundAsset = fundAsset;
    }
    /**
     * 债券资产
     */
    public BigDecimal getBondAsset() {
        return bondAsset;
    }
    /**
     * 债券资产
     */
    public void setBondAsset(BigDecimal bondAsset) {
        this.bondAsset = bondAsset;
    }
    /**
     * 回购资产
     */
    public BigDecimal getHgAsset() {
        return hgAsset;
    }
    /**
     * 回购资产
     */
    public void setHgAsset(BigDecimal hgAsset) {
        this.hgAsset = hgAsset;
    }
    /**
     * 期货资产
     */
    public BigDecimal getFutureAsset() {
        return futureAsset;
    }
    /**
     * 期货资产
     */
    public void setFutureAsset(BigDecimal futureAsset) {
        this.futureAsset = futureAsset;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
