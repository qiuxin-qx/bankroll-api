package com.goldbao.homs.result;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 资金划转
 */
public class MoveAssetResult {

    private String iBusinNo;
    @JsonProperty("error_no")
    private String errorNo;
    @JsonProperty("error_info")
    private String errorInfo;

    /**
     * 业务序号
     */
    public String getiBusinNo() {
        return iBusinNo;
    }

    public void setiBusinNo(String iBusinNo) {
        this.iBusinNo = iBusinNo;
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
