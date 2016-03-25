package com.goldbao.bankroll.service.manage.bankroll;

import com.goldbao.bankroll.model.bankroll.RangeTradingDayDTO;
import com.goldbao.bankroll.model.bankroll.TradingDay;

import java.util.Date;
import java.util.List;

/**
 * 交易日管理
 * @author shuyu.fang
 */
public interface ManageTradingDayService {

    /**
     * 生成一个范围的交易日期
     * @param tradingDays
     * @return
     */
    boolean generateTradingDay(List<TradingDay> tradingDays);

    List<TradingDay> getTradingDayList(Date startDate, int days);

    boolean isTradingDay(Date date);

    RangeTradingDayDTO getRangeTradingDay(Date startDate, Integer days);
}
