package com.goldbao.bankroll.service.manage.bankroll.impl;

import com.goldbao.bankroll.dao.manage.bankroll.ManageTradingDayDao;
import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.bankroll.RangeTradingDayDTO;
import com.goldbao.bankroll.model.bankroll.TradingDay;
import com.goldbao.bankroll.model.enums.EnumTradingDayType;
import com.goldbao.bankroll.service.manage.bankroll.ManageTradingDayService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author shuyu.fang
 */
public class ManageTradingDayServiceImpl implements ManageTradingDayService {
    private ManageTradingDayDao manageTradingDayDao;

    @Override
    public boolean generateTradingDay(List<TradingDay> tradingDays) {
        if (tradingDays != null && tradingDays.size() > 0) {
            for (TradingDay day: tradingDays) {
                try {
                    manageTradingDayDao.addTradingDay(day);
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
            }
        }

        return true;
    }

    @Override
    public List<TradingDay> getTradingDayList(Date startDate, int days) {
        return this.manageTradingDayDao.getTradingDayList(startDate, days);
    }

    @Override
    public boolean isTradingDay(Date date) {
        TradingDay day = this.manageTradingDayDao.getTradingDayByDate(date);
        if (day == null || day.getDayType() == EnumTradingDayType.CLOSE) {
            return false;
        }

        return true;
    }

    @Override
    public RangeTradingDayDTO getRangeTradingDay(Date startDate, Integer days) {
        return manageTradingDayDao.getRangeTradingDay(startDate, days);
    }

    @Autowired
    public void setManageTradingDayDao(ManageTradingDayDao manageTradingDayDao) {
        this.manageTradingDayDao = manageTradingDayDao;
    }
}
