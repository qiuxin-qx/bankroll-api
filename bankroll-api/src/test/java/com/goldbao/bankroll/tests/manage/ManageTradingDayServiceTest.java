package com.goldbao.bankroll.tests.manage;

import com.goldbao.bankroll.model.bankroll.RangeTradingDayDTO;
import com.goldbao.bankroll.model.bankroll.TradingDay;
import com.goldbao.bankroll.model.enums.EnumTradingDayType;
import com.goldbao.bankroll.service.manage.bankroll.ManageTradingDayService;
import com.goldbao.utils.CommonUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 股市交易日管理用例
 * @author shuyu.fang
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:bankroll-api-service.xml" })
public class ManageTradingDayServiceTest {

    private ManageTradingDayService manageTradingDayService;

    private final static Logger logger = LoggerFactory.getLogger(ManageTradingDayServiceTest.class);

    @Test
    public void testGenerateTradingDay() {
        List<TradingDay> tradingDays = calendar();

        manageTradingDayService.generateTradingDay(tradingDays);
    }

    @Test
    public void testGetTradingDay() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date startDate = format.parse("2015-01-01 12:12:12");
        int days = 10;

        List<TradingDay> tradingDays = manageTradingDayService.getTradingDayList(startDate, days);
        try {
            logger.debug(new ObjectMapper().writeValueAsString(tradingDays));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    @Test
    public void testGetRangeTradingDay() {
        RangeTradingDayDTO dto = manageTradingDayService.getRangeTradingDay(new Date(), 10);
        Assert.assertNotNull(dto);
    }


    @Test
    public void testIsTradingDay() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date date = format.parse("2015-01-09 12:12:12");

        boolean isTradingDay = manageTradingDayService.isTradingDay(date);

        Assert.assertTrue(isTradingDay);
    }

    private synchronized List<TradingDay> calendar() {
        List<TradingDay> tradingDays = new ArrayList<TradingDay>();
        Calendar startYear = Calendar.getInstance();
        startYear.set(2015, 0, 1);
        Calendar endYear = Calendar.getInstance();
        endYear.set(2015, 11, 31);
        while (startYear.compareTo(endYear) != 1) {
            logger.debug("{}, {}, {}, {}",
                CommonUtil.formatDate(startYear.getTime(), "yyyy-MM-dd"),
                startYear.get(Calendar.DAY_OF_WEEK) - 1,
                startYear.get(Calendar.DAY_OF_WEEK_IN_MONTH),
                startYear.get(Calendar.DATE));
            TradingDay day = new TradingDay();
            day.setDate(startYear.getTime());
            Integer week = startYear.get(Calendar.DAY_OF_WEEK) - 1;
            if (week == 0) week = 7;
            day.setWeekNum(week);
            EnumTradingDayType dayType = EnumTradingDayType.OPEN;
            if (week == 6 || week == 7)  dayType = EnumTradingDayType.CLOSE;
            day.setDayType(dayType);
            tradingDays.add(day);
            startYear.add(Calendar.DATE, 1);
        }
        return tradingDays;
    }


    @Autowired
    public void setManageTradingDayService(ManageTradingDayService manageTradingDayService) {
        this.manageTradingDayService = manageTradingDayService;
    }
}
