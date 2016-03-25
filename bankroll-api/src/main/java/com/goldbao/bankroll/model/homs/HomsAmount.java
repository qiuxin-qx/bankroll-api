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
@Table(name = "bk_homs_amount")
@AttributeOverride(name = "id", column = @Column(name = "amount_id"))
public class HomsAmount extends Model implements Serializable {

	private static final long serialVersionUID = 7634162434808451658L;
	@Column(name = "enable_amount")
    private String enableAmount;
    @Column(name = "error_no")
    private String errorNo;
    @Column(name = "error_info")
    private String errorInfo;
    @Column(name = "t1_amount")
    private String t1Amount;
    @Column(name = "stock_code")
    private String stockCode;
    @Column(name = "exchange_type")
    private String exchangeType;
    @Column(name = "combine_id")
    private String combineId;
//    @Column(name = "fund_account")
//    private String fundAccount;

    public String getEnableAmount() {
        return enableAmount;
    }

    public void setEnableAmount(String enableAmount) {
        this.enableAmount = enableAmount;
    }

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

    public String getT1Amount() {
        return t1Amount;
    }

    public void setT1Amount(String t1Amount) {
        this.t1Amount = t1Amount;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
    }

    public String getCombineId() {
        return combineId;
    }

    public void setCombineId(String combineId) {
        this.combineId = combineId;
    }

//    public String getFundAccount() {
//        return fundAccount;
//    }
//
//    public void setFundAccount(String fundAccount) {
//        this.fundAccount = fundAccount;
//    }
}
