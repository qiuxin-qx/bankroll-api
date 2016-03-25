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
@Table(name = "bk_homs_operator_info")
@AttributeOverride(name = "id", column = @Column(name = "operator_id"))
public class HomsOperatorInfo extends Model implements Serializable {

	private static final long serialVersionUID = -2896338000357672843L;
	@Column(name = "operator_no")
    private String operatorNo;
    @Column(name = "operator_name")
    private String operatorName;
    @Column(name = "operator_status")
    private String operatorStatus;
    @Column(name = "expire_days")
    private String expireDays;
    @Column(name = "force_chg_password")
    private String forceChgPassword;
    @Column(name = "stock_op_right")
    private String stockOpright;
    @Column(name = "future_op_right")
    private String futureOpright;

    public String getOperatorNo() {
        return operatorNo;
    }

    public void setOperatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOperatorStatus() {
        return operatorStatus;
    }

    public void setOperatorStatus(String operatorStatus) {
        this.operatorStatus = operatorStatus;
    }

    public String getExpireDays() {
        return expireDays;
    }

    public void setExpireDays(String expireDays) {
        this.expireDays = expireDays;
    }

    public String getForceChgPassword() {
        return forceChgPassword;
    }

    public void setForceChgPassword(String forceChgPassword) {
        this.forceChgPassword = forceChgPassword;
    }

    public String getStockOpright() {
        return stockOpright;
    }

    public void setStockOpright(String stockOpright) {
        this.stockOpright = stockOpright;
    }

    public String getFutureOpright() {
        return futureOpright;
    }

    public void setFutureOpright(String futureOpright) {
        this.futureOpright = futureOpright;
    }
}
