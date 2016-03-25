package com.goldbao.bankroll.vo.manage;

import java.math.BigDecimal;

/**
 * @author shuyu.fang
 */
public class BankrollHomsInfoLogResult {
    private Long recordId; // 关联的配资记录

    private String operatorNo;    // 操作员账号

    private String assetId;       // 单元编号

    private String assetName;     // 单元名称

    private String combineId;      // 组合编号

    private String combineName;    // 组合名称

    private BigDecimal warningValue;  // 警告金额

    private BigDecimal openValue;      // 平仓金额

    private BigDecimal curLoan;         // 借款金额

    private String fundAccount;    // 账户编号

    private BigDecimal assetTotalValue; // 单元总资产（净值+余额）

    private BigDecimal assetValue;       // 单元净值（股票资产+基金资产+期货+回购+债权。。。）

    private BigDecimal currentCash;      // 现金余额

    private BigDecimal stockAsset;       // 股票资产

    private BigDecimal fundAsset;        // 基金资产

    private BigDecimal bondAsset;        // 债权资产

    private BigDecimal hgAsset;           // 回购资产

    private BigDecimal futureAsset;      // 期货资产

    private String addTime;

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
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

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }
}
