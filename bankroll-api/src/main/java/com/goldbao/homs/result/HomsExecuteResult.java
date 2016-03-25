package com.goldbao.homs.result;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * homs 执行修改操作默认返回
 */
public class HomsExecuteResult {

    @JsonProperty("error_no")
    private String status;

    @JsonProperty("error_info")
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
