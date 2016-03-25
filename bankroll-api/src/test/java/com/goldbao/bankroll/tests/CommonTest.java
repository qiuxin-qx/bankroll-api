package com.goldbao.bankroll.tests;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.goldbao.utils.CalendarUtil;
import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import payment.tools.util.GUID;

import com.goldbao.utils.CommonUtil;
import com.goldbao.utils.MathUtil;


public class CommonTest {

    private final static Logger logger = LoggerFactory.getLogger(CommonTest.class);

    @Test
    public void testRandomValidateCode() {
        for (int i = 0; i < 10; i++) {
            logger.debug(CommonUtil.randomValidateCode());
        }
    }

    @Test
    public void testGUID() {
        logger.debug("{}", GUID.getTxNo());
    }

    @Test
    public void testCalendar() {
        calendar();
    }


    private synchronized void calendar() {
        Calendar startYear = Calendar.getInstance();
        startYear.set(2015, 0, 1);
        Calendar endYear = Calendar.getInstance();
        endYear.set(2015, 4, 31);
        while (startYear.compareTo(endYear) != 1) {
            logger.debug("{}, {}, {}", // {}, {}, {},
                CommonUtil.formatDate(startYear.getTime(), "yyyy-MM-dd"),
//                startYear.get(Calendar.DAY_OF_WEEK) - 1,
//                startYear.get(Calendar.DAY_OF_WEEK_IN_MONTH),
//                startYear.get(Calendar.DATE),
                startYear.get(Calendar.DAY_OF_MONTH),
                startYear.getActualMaximum(Calendar.DAY_OF_MONTH));
            startYear.add(Calendar.DATE, 1);
        }

    }

    @Test
    public void testalendar2() throws Exception {
        Calendar startYear = Calendar.getInstance();
        startYear.setTime(CommonUtil.parseDate("2015-01-19", "yyyy-MM-dd"));
        startYear.add(Calendar.MONTH, 1);
        logger.debug("{}", CommonUtil.formatDate(startYear.getTime()));
    }

    @Test
    public void testSub() {
        String a = "1000";
        String b = "4";

        System.out.println(MathUtil.format(MathUtil.divide(a, b), 2));
    }

    @Test
    public void testRandom() {
        Set<Long> a = new HashSet<>();
        for (int i = 0; i < 100000000; i++) {
            a.add(new Random().nextLong());
        }

        Assert.assertNotEquals(a.size(), 100000000);
    }

    @Test
    public void testChar() {
        System.out.println(CommonUtil.formatDate(CalendarUtil.nextHour(-1)));
    }

    @Test
    public void test1() {
        System.out.println(CommonUtil.base64Encode("<?xml version=\"1.0\" encoding=\"UTF-8\"?><Response version=\"2.0\"><Head><Code>2000</Code><Message>OK.</Message></Head></Response>"));
    }
}
