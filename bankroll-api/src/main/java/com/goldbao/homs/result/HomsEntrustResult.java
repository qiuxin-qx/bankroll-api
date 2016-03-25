package com.goldbao.homs.result;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 普通委托
 */
public class HomsEntrustResult {

    @JsonProperty("entrust_no")
    private String entrustNo;
    @JsonProperty("batch_no")
    private String batchNo;
    @JsonProperty("error_no")
    private String errorNo;
    @JsonProperty("error_info")
    private String errorInfo;

    /**
     * 委托编号
     */
    public String getEntrustNo() {
        return entrustNo;
    }

    public void setEntrustNo(String entrustNo) {
        this.entrustNo = entrustNo;
    }
    /**
     * 委托批号
     */
    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
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
}
