package com.goldbao.sms;


import com.goldbao.bankroll.exception.ServiceException;

public interface SmsManager {
	
	String send(String phone, String message);

	String send(String phone, String message, EnumSmsType type) throws ServiceException;
}
