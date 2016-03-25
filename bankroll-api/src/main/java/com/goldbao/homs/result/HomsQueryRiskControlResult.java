package com.goldbao.homs.result;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 公司层已设置风控信息查询
 */
public class HomsQueryRiskControlResult {

    @JsonProperty("serial_no")
    private String serialNo;
    @JsonProperty("date")
    private String date;
    @JsonProperty("risk_type")
    private String riskType;
    @JsonProperty("sub_risk_type")
    private String subRiskType;
    @JsonProperty("control_layer")
    private String controlLayer;
    @JsonProperty("fund_ids")
    private String fundIds;
    @JsonProperty("layer_ids")
    private String layerIds;
    @JsonProperty("valve_type")
    private String valveType;
    @JsonProperty("control_type")
    private String controlType;
    @JsonProperty("fund_collect")
    private String fundCollect;
    @JsonProperty("stock_collect")
    private String stockCollect;
    @JsonProperty("risk_level")
    private String riskLevel;
    @JsonProperty("effect_range")
    private String effectRange;
    @JsonProperty("compare_direction")
    private String compareDirection;
    @JsonProperty("en_value1")
    private String enValue1;
    @JsonProperty("value_opertion1")
    private String valueOpertion1;
    @JsonProperty("en_value2")
    private String enValue2;
    @JsonProperty("value_opertion2")
    private String valueOpertion2;
    @JsonProperty("en_value3")
    private String enValue3;
    @JsonProperty("value_opertion3")
    private String valueOpertion3;
    @JsonProperty("control_switch")
    private String controlSwitch;
    @JsonProperty("modify_date")
    private String modifyDate;
    @JsonProperty("modify_time")
    private String modifyTime;
    @JsonProperty("modify_operator")
    private String modifyOperator;
    @JsonProperty("control_summary")
    private String controlSummary;
    @JsonProperty("message_hint")
    private String messageHint;
    @JsonProperty("remark")
    private String remark;
    @JsonProperty("company_no")
    private String companyNo;

    /**
     * 序号
     */
    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }
    /**
     * 日期
     */
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    /**
     * 风控类型
     */
    public String getRiskType() {
        return riskType;
    }

    public void setRiskType(String riskType) {
        this.riskType = riskType;
    }
    /**
     * 风控子类
     */
    public String getSubRiskType() {
        return subRiskType;
    }

    public void setSubRiskType(String subRiskType) {
        this.subRiskType = subRiskType;
    }
    /**
     * 控制层次
     */
    public String getControlLayer() {
        return controlLayer;
    }

    public void setControlLayer(String controlLayer) {
        this.controlLayer = controlLayer;
    }
    /**
     * 账户范围
     */
    public String getFundIds() {
        return fundIds;
    }

    public void setFundIds(String fundIds) {
        this.fundIds = fundIds;
    }
    /**
     * 单元范围
     */
    public String getLayerIds() {
        return layerIds;
    }

    public void setLayerIds(String layerIds) {
        this.layerIds = layerIds;
    }
    /**
     * 阀值类型
     */
    public String getValveType() {
        return valveType;
    }

    public void setValveType(String valveType) {
        this.valveType = valveType;
    }
    /**
     * 控制方式
     */
    public String getControlType() {
        return controlType;
    }

    public void setControlType(String controlType) {
        this.controlType = controlType;
    }
    /**
     * 账户汇总方式
     */
    public String getFundCollect() {
        return fundCollect;
    }

    public void setFundCollect(String fundCollect) {
        this.fundCollect = fundCollect;
    }
    /**
     * 证券汇总方式
     */
    public String getStockCollect() {
        return stockCollect;
    }

    public void setStockCollect(String stockCollect) {
        this.stockCollect = stockCollect;
    }
    /**
     * 优先级别
     */
    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }
    /**
     * 作用范围
     */
    public String getEffectRange() {
        return effectRange;
    }

    public void setEffectRange(String effectRange) {
        this.effectRange = effectRange;
    }
    /**
     * 比较方向
     */
    public String getCompareDirection() {
        return compareDirection;
    }

    public void setCompareDirection(String compareDirection) {
        this.compareDirection = compareDirection;
    }
    /**
     * 阀值1
     */
    public String getEnValue1() {
        return enValue1;
    }

    public void setEnValue1(String enValue1) {
        this.enValue1 = enValue1;
    }
    /**
     * 阀值1操作
     */
    public String getValueOpertion1() {
        return valueOpertion1;
    }

    public void setValueOpertion1(String valueOpertion1) {
        this.valueOpertion1 = valueOpertion1;
    }
    /**
     * 阀值2
     */
    public String getEnValue2() {
        return enValue2;
    }

    public void setEnValue2(String enValue2) {
        this.enValue2 = enValue2;
    }
    /**
     * 阀值2操作
     */
    public String getValueOpertion2() {
        return valueOpertion2;
    }

    public void setValueOpertion2(String valueOpertion2) {
        this.valueOpertion2 = valueOpertion2;
    }
    /**
     * 阀值3
     */
    public String getEnValue3() {
        return enValue3;
    }

    public void setEnValue3(String enValue3) {
        this.enValue3 = enValue3;
    }
    /**
     * 阀值3操作
     */
    public String getValueOpertion3() {
        return valueOpertion3;
    }

    public void setValueOpertion3(String valueOpertion3) {
        this.valueOpertion3 = valueOpertion3;
    }
    /**
     * 启用标志
     */
    public String getControlSwitch() {
        return controlSwitch;
    }

    public void setControlSwitch(String controlSwitch) {
        this.controlSwitch = controlSwitch;
    }
    /**
     * 修改日期
     */
    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }
    /**
     * 修改时间
     */
    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
    /**
     * 修改操作员
     */
    public String getModifyOperator() {
        return modifyOperator;
    }

    public void setModifyOperator(String modifyOperator) {
        this.modifyOperator = modifyOperator;
    }
    /**
     * 风控说明
     */
    public String getControlSummary() {
        return controlSummary;
    }

    public void setControlSummary(String controlSummary) {
        this.controlSummary = controlSummary;
    }
    /**
     * 消息提醒
     */
    public String getMessageHint() {
        return messageHint;
    }

    public void setMessageHint(String messageHint) {
        this.messageHint = messageHint;
    }
    /**
     * 备注
     */
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    /**
     * 公司编号
     */
    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }
}
