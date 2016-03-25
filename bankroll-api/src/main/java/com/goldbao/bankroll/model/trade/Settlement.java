package com.goldbao.bankroll.model.trade;

import com.goldbao.bankroll.model.Model;
import com.goldbao.bankroll.model.enums.EnumSettlementStatus;
import com.goldbao.bankroll.model.trade.TradeRecord;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 结算记录
 * @author shuyu.fang
 */
@Entity
@Table(name = "bk_settlement")
@AttributeOverride(name = "id", column = @Column(name = "settlement_id"))
public class Settlement extends Model implements Serializable {

    @Column(name = "order_no")
    private String orderNo;
    @Column(name = "serial_no")
    private String serialNo;

    private BigDecimal amount;

    @OneToMany(mappedBy = "settlement")
    private List<TradeRecord> tradeRecords;

    private Date dealTime;

    private EnumSettlementStatus status;

    // 后续还要添加结算银行卡信息，作为结算依据

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public List<TradeRecord> getTradeRecords() {
        return tradeRecords;
    }

    public void setTradeRecords(List<TradeRecord> tradeRecords) {
        this.tradeRecords = tradeRecords;
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    public EnumSettlementStatus getStatus() {
        return status;
    }

    public void setStatus(EnumSettlementStatus status) {
        this.status = status;
    }
}
