package com.goldbao.bankroll.dao.manage.bankroll;

import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.bankroll.RangeTradingDayDTO;
import com.goldbao.bankroll.model.bankroll.TradingDay;

import java.util.Date;
import java.util.List;

/**
 * @author shuyu.fang
 */
public interface ManageTradingDayDao {
    Long addTradingDay(TradingDay day) throws ServiceException;

    List<TradingDay> getTradingDayList(Date startDate, int days);

    TradingDay getTradingDayByDate(Date date);

    RangeTradingDayDTO getRangeTradingDay(Date startDate, Integer days);

    TradingDay getNextTradingDay();
}
