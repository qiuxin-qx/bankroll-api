package com.goldbao.bankroll.model.bankroll;

import java.util.Date;

/**
 * 交易日范围（BO）
 * @author shuyu.fang
 */
public class RangeTradingDayDTO {

    private Date startDate;

    private Date endDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
