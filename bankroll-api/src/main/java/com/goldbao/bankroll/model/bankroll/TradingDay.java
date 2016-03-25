package com.goldbao.bankroll.model.bankroll;

import com.goldbao.bankroll.model.Model;
import com.goldbao.bankroll.model.enums.EnumTradingDayType;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 交易日记录表
 * @author shuyu.fang
 */
@Entity
@Table(name = "bk_trading_day")
@AttributeOverride(name = "id", column = @Column(name = "trading_day_id"))
public class TradingDay extends Model implements Serializable {
	private static final long serialVersionUID = 2002596542472987989L;

	@Column(name = "date_str", unique = true, columnDefinition = "date")
    private Date date;  // 日期

    @Column(name = "week_num")
    private Integer weekNum; // 对应的星期数字

    private String holiday;  // 假日

    @Column(name = "day_type")
    private EnumTradingDayType dayType; // 是否交易日

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * 星期对应的数字
     */
    public Integer getWeekNum() {
        return weekNum;
    }
    /**
     * 星期对应的数字
     */
    public void setWeekNum(Integer weekNum) {
        this.weekNum = weekNum;
    }
    /**
     * 假日描述，为空表示非节假日
     */
    public String getHoliday() {
        return holiday;
    }
    /**
     * 假日描述，为空表示非节假日
     */
    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }
    /**
     * 是否交易日
     */
    public EnumTradingDayType getDayType() {
        return dayType;
    }
    /**
     * 是否交易日
     */
    public void setDayType(EnumTradingDayType dayType) {
        this.dayType = dayType;
    }
}
