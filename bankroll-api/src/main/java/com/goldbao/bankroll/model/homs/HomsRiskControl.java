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
@Table(name = "bk_homs_risk_control")
@AttributeOverride(name = "id", column = @Column(name = "control_id"))
public class HomsRiskControl extends Model implements Serializable {

	private static final long serialVersionUID = 5959867209811759196L;

	@Column(name = "serial_no")
    private String serialNo;

    private String date;
    @Column(name = "risk_type")
    private String riskType;
    @Column(name = "sub_risk_type")
    private String subRiskType;
    @Column(name = "control_layer")
    private String controlLayer;
    @Column(name = "fund_ids")
    private String fundIds;
    @Column(name = "layer_ids")
    private String layerIds;
    @Column(name = "valve_type")
    private String valveType;
    @Column(name = "control_type")
    private String controlType;
    @Column(name = "fund_collect")
    private String fundCollect;
    @Column(name = "stock_collect")
    private String stockCollect;
    @Column(name = "risk_level")
    private String riskLevel;
    @Column(name = "effect_range")
    private String effectRange;
    @Column(name = "compare_direction")
    private String compareDirection;
    @Column(name = "en_value1")
    private String enValue1;
    @Column(name = "value_opertion1")
    private String valueOpertion1;
    @Column(name = "en_value2")
    private String enValue2;
    @Column(name = "value_opertion2")
    private String valueOpertion2;
    @Column(name = "en_value3")
    private String enValue3;
    @Column(name = "value_opertion3")
    private String valueOpertion3;
    @Column(name = "control_switch")
    private String controlSwitch;
    @Column(name = "modify_date")
    private String modifyDate;
    @Column(name = "modify_time")
    private String modifyTime;
    @Column(name = "modify_operator")
    private String modifyOperator;
    @Column(name = "control_summary")
    private String controlSummary;
    @Column(name = "message_hint")
    private String messageHint;

    private String remark;
    @Column(name = "company_no")
    private String companyNo;

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRiskType() {
        return riskType;
    }

    public void setRiskType(String riskType) {
        this.riskType = riskType;
    }

    public String getSubRiskType() {
        return subRiskType;
    }

    public void setSubRiskType(String subRiskType) {
        this.subRiskType = subRiskType;
    }

    public String getControlLayer() {
        return controlLayer;
    }

    public void setControlLayer(String controlLayer) {
        this.controlLayer = controlLayer;
    }

    public String getFundIds() {
        return fundIds;
    }

    public void setFundIds(String fundIds) {
        this.fundIds = fundIds;
    }

    public String getLayerIds() {
        return layerIds;
    }

    public void setLayerIds(String layerIds) {
        this.layerIds = layerIds;
    }

    public String getValveType() {
        return valveType;
    }

    public void setValveType(String valveType) {
        this.valveType = valveType;
    }

    public String getControlType() {
        return controlType;
    }

    public void setControlType(String controlType) {
        this.controlType = controlType;
    }

    public String getFundCollect() {
        return fundCollect;
    }

    public void setFundCollect(String fundCollect) {
        this.fundCollect = fundCollect;
    }

    public String getStockCollect() {
        return stockCollect;
    }

    public void setStockCollect(String stockCollect) {
        this.stockCollect = stockCollect;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getEffectRange() {
        return effectRange;
    }

    public void setEffectRange(String effectRange) {
        this.effectRange = effectRange;
    }

    public String getCompareDirection() {
        return compareDirection;
    }

    public void setCompareDirection(String compareDirection) {
        this.compareDirection = compareDirection;
    }

    public String getEnValue1() {
        return enValue1;
    }

    public void setEnValue1(String enValue1) {
        this.enValue1 = enValue1;
    }

    public String getValueOpertion1() {
        return valueOpertion1;
    }

    public void setValueOpertion1(String valueOpertion1) {
        this.valueOpertion1 = valueOpertion1;
    }

    public String getEnValue2() {
        return enValue2;
    }

    public void setEnValue2(String enValue2) {
        this.enValue2 = enValue2;
    }

    public String getValueOpertion2() {
        return valueOpertion2;
    }

    public void setValueOpertion2(String valueOpertion2) {
        this.valueOpertion2 = valueOpertion2;
    }

    public String getEnValue3() {
        return enValue3;
    }

    public void setEnValue3(String enValue3) {
        this.enValue3 = enValue3;
    }

    public String getValueOpertion3() {
        return valueOpertion3;
    }

    public void setValueOpertion3(String valueOpertion3) {
        this.valueOpertion3 = valueOpertion3;
    }

    public String getControlSwitch() {
        return controlSwitch;
    }

    public void setControlSwitch(String controlSwitch) {
        this.controlSwitch = controlSwitch;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyOperator() {
        return modifyOperator;
    }

    public void setModifyOperator(String modifyOperator) {
        this.modifyOperator = modifyOperator;
    }

    public String getControlSummary() {
        return controlSummary;
    }

    public void setControlSummary(String controlSummary) {
        this.controlSummary = controlSummary;
    }

    public String getMessageHint() {
        return messageHint;
    }

    public void setMessageHint(String messageHint) {
        this.messageHint = messageHint;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }
}
