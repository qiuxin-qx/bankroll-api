package com.goldbao.bankroll.model.bankroll;


/**
 * @author shuyu.fang
 */
public class BankrollHomsInfoDTO {

    private String operatorNo;    // 操作员账号

    private String assetId;       // 单元编号

    private String assetName;     // 单元名称

    private String combineId;      // 组合编号

    private String combineName;    // 组合名称

    private String warningValue;  // 警告金额

    private String openValue;      // 平仓金额

    private String curLoan;         // 借款金额

    private String fundAccount;    // 账户编号

    private String assetTotalValue; // 单元总资产（净值+余额）

    private String assetValue;       // 单元净值（股票资产+基金资产+期货+回购+债权。。。）

    private String currentCash;      // 现金余额

    private String stockAsset;       // 股票资产

    private String fundAsset;        // 基金资产

    private String bondAsset;        // 债权资产

    private String hgAsset;           // 回购资产

    private String futureAsset;      // 期货资产

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

    public String getWarningValue() {
        return warningValue;
    }

    public void setWarningValue(String warningValue) {
        this.warningValue = warningValue;
    }

    public String getOpenValue() {
        return openValue;
    }

    public void setOpenValue(String openValue) {
        this.openValue = openValue;
    }

    public String getCurLoan() {
        return curLoan;
    }

    public void setCurLoan(String curLoan) {
        this.curLoan = curLoan;
    }

    public String getFundAccount() {
        return fundAccount;
    }

    public void setFundAccount(String fundAccount) {
        this.fundAccount = fundAccount;
    }

    public String getAssetTotalValue() {
        return assetTotalValue;
    }

    public void setAssetTotalValue(String assetTotalValue) {
        this.assetTotalValue = assetTotalValue;
    }

    public String getAssetValue() {
        return assetValue;
    }

    public void setAssetValue(String assetValue) {
        this.assetValue = assetValue;
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

    public String getFutureAsset() {
        return futureAsset;
    }

    public void setFutureAsset(String futureAsset) {
        this.futureAsset = futureAsset;
    }
}
