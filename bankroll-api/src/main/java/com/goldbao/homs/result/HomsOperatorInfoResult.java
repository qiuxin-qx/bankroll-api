package com.goldbao.homs.result;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 操作员信息查询
 */
public class HomsOperatorInfoResult {

    @JsonProperty("operator_no")
    private String operatorNo;
    @JsonProperty("operator_name")
    private String operatorName;
    @JsonProperty("operator_status")
    private String operatorStatus;
    @JsonProperty("expire_days")
    private String expireDays;
    @JsonProperty("force_chg_password")
    private String forceChgPassword;
    @JsonProperty("stock_opright")
    private String stockOpright;
    @JsonProperty("future_opright")
    private String futureOpright;

    /**
     * 操作员编号
     */
    public String getOperatorNo() {
        return operatorNo;
    }

    public void setOperatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
    }
    /**
     * 操作员名称
     */
    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
    /**
     * 操作员状态
     */
    public String getOperatorStatus() {
        return operatorStatus;
    }

    public void setOperatorStatus(String operatorStatus) {
        this.operatorStatus = operatorStatus;
    }
    /**
     * 密码有效天数
     */
    public String getExpireDays() {
        return expireDays;
    }

    public void setExpireDays(String expireDays) {
        this.expireDays = expireDays;
    }
    /**
     * 登录后是否需要修改密码
     */
    public String getForceChgPassword() {
        return forceChgPassword;
    }

    public void setForceChgPassword(String forceChgPassword) {
        this.forceChgPassword = forceChgPassword;
    }
    /**
     * 股票操作权限
     */
    public String getStockOpright() {
        return stockOpright;
    }

    public void setStockOpright(String stockOpright) {
        this.stockOpright = stockOpright;
    }
    /**
     * 期货操作权限
     */
    public String getFutureOpright() {
        return futureOpright;
    }

    public void setFutureOpright(String futureOpright) {
        this.futureOpright = futureOpright;
    }
}
