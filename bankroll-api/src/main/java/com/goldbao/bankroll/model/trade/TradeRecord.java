package com.goldbao.bankroll.model.trade;

import com.goldbao.bankroll.model.Model;
import com.goldbao.bankroll.model.enums.EnumTradeStatus;
import com.goldbao.bankroll.model.enums.EnumTradeType;
import com.goldbao.bankroll.model.manage.sysuser.SysUser;
import com.goldbao.bankroll.model.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 交易记录
 */
@Entity
@Table(name = "bk_trade_record")
@AttributeOverride(name = "id", column = @Column(name = "record_id"))
public class TradeRecord extends Model implements Serializable {

	private static final long serialVersionUID = 4528349080212125158L;
	@Column(name = "order_no")
    private String orderNo;
    @Column(name = "serial_no")
    private String serialNo;

    @ManyToOne
    @JoinColumn(name = "payer_id")
    private User payer; // 付款人
    @ManyToOne
    @JoinColumn(name = "payee_id")
    private User payee; // 收款人
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;// 创建人
    @ManyToOne
    @JoinColumn(name = "sys_creator_id")
    public SysUser sysCreator; // 系统创建人

    private BigDecimal amount;

    private BigDecimal fee;

    @Column(name = "trade_type")
    private EnumTradeType tradeType;
    @Column(name = "trade_status")
    private EnumTradeStatus tradeStatus;
    @Column(name = "deal_time")
    private Date dealTime;

    private String remark;

    @ManyToOne
    @JoinColumn(name = "settlement_id")
    private Settlement settlement;

    /**
     * 平台订单号
     */
    public String getOrderNo() {
        return orderNo;
    }
    /**
     * 平台订单号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    /**
     * 平台流水号
     */
    public String getSerialNo() {
        return serialNo;
    }
    /**
     * 平台流水号
     */
    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }
    /**
     * 付款人
     */
    public User getPayer() {
        return payer;
    }
    /**
     * 付款人
     */
    public void setPayer(User payer) {
        this.payer = payer;
    }
    /**
     * 收款人
     */
    public User getPayee() {
        return payee;
    }
    /**
     * 收款人
     */
    public void setPayee(User payee) {
        this.payee = payee;
    }
    /**
     * 创建人
     */
    public User getCreator() {
        return creator;
    }
    /**
     * 创建人
     */
    public void setCreator(User creator) {
        this.creator = creator;
    }
    /**
     * 交易金额
     */
    public BigDecimal getAmount() {
        return amount;
    }
    /**
     * 交易金额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    /**
     * 手续费
     */
    public BigDecimal getFee() {
        return fee;
    }
    /**
     * 手续费
     */
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    /**
     * 交易类型 - 枚举
     */
    public EnumTradeType getTradeType() {
        return tradeType;
    }
    /**
     * 交易类型 - 枚举
     */
    public void setTradeType(EnumTradeType tradeType) {
        this.tradeType = tradeType;
    }
    /**
     * 交易状态 - 枚举
     */
    public EnumTradeStatus getTradeStatus() {
        return tradeStatus;
    }
    /**
     * 交易状态 - 枚举
     */
    public void setTradeStatus(EnumTradeStatus tradeStatus) {
        this.tradeStatus = tradeStatus;
    }
    /**
     * 成交时间
     */
    public Date getDealTime() {
        return dealTime;
    }

    /**
     * 成交时间
     */
    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    /**
     * 备注
     */
    public String getRemark() {
        return remark;
    }
    /**
     * 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public SysUser getSysCreator() {
        return sysCreator;
    }

    public void setSysCreator(SysUser sysCreator) {
        this.sysCreator = sysCreator;
    }

    public Settlement getSettlement() {
        return settlement;
    }

    public void setSettlement(Settlement settlement) {
        this.settlement = settlement;
    }
}
