package com.goldbao.utils;

import java.util.Calendar;
import java.util.Date;

public class CalendarUtil {
	
	public static Date nextDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1);
		return calendar.getTime();
	}

	public static Date nextDay(Date now) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.DATE, 1);
		return calendar.getTime();
	}

    public static Date nextHour(int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, hours);
        return calendar.getTime();
    }

	public static Date nextMonthDay(Date startDate) {
		return nextMonthDay(startDate, 1);
	}

	public static Date nextMonthDay(Date startDate, int months) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.add(Calendar.MONTH, months);
		return calendar.getTime();
	}
}
