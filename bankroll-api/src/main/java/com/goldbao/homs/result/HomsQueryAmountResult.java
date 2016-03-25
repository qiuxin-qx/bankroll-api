package com.goldbao.homs.result;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 组合层现货可用数量查询
 */
public class HomsQueryAmountResult {

    @JsonProperty("enable_amount")
    private String enableAmount;

    @JsonProperty("error_no")
    private String errorNo;
    @JsonProperty("error_info")
    private String errorInfo;
    @JsonProperty("t1_amount")
    private String t1Amount;

    /**
     * 可用数量
     */
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
    /**
     * 可用数量（T+1）
     */
    public String getT1Amount() {
        return t1Amount;
    }

    public void setT1Amount(String t1Amount) {
        this.t1Amount = t1Amount;
    }
}
