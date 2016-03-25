package com.goldbao.bankroll.model.bankroll;

import com.goldbao.bankroll.model.Model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 配资日志表
 * @author shuyu.fang
 */
@Entity
@Table(name = "bk_bankroll_record_log")
@AttributeOverride(name = "id", column = @Column(name = "record_log_id"))
public class BankrollRecordLog extends Model implements Serializable {

	private static final long serialVersionUID = 7759084450052866105L;

	@ManyToOne
    @JoinColumn(name = "record_id")
    private BankrollRecord record;

    private String message;

    private String creatorName;

    /**
     * 对应的配资订单记录
     */
    public BankrollRecord getRecord() {
        return record;
    }
    /**
     * 对应的配资订单记录
     */
    public void setRecord(BankrollRecord record) {
        this.record = record;
    }
    /**
     * 消息内容
     */
    public String getMessage() {
        return message;
    }
    /**
     * 消息内容
     */
    public void setMessage(String message) {
        this.message = message;
    }
    /**
     * 消息创建人
     */
    public String getCreatorName() {
        return creatorName;
    }
    /**
     * 消息创建人
     */
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
}
