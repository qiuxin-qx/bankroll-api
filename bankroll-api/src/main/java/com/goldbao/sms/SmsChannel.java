package com.goldbao.sms;

public interface SmsChannel {
	
	String send(String phone, String message);

}
