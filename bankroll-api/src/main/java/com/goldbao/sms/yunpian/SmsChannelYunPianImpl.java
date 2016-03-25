package com.goldbao.sms.yunpian;

import com.goldbao.sms.SmsChannel;

public class SmsChannelYunPianImpl implements SmsChannel {
	private String url;
	private String apikey;

	@Override
	public String send(String phone, String message) {
		return new SmsSend().send(url, apikey, phone, message);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}
}
