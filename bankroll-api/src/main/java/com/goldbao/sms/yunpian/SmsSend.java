package com.goldbao.sms.yunpian;

import java.util.HashMap;
import java.util.Map;

import com.goldbao.utils.HttpUtil;
import com.goldbao.utils.StringUtil;

public class SmsSend {
	
	public String tplSend(String url, String key, String tplId, String phone, String message) {
		return "";
	}

	public String send(String url, String apikey, String mobile, String text, String extend, String uid) {
		Map<String, String> p = new HashMap<String, String>();
		p.put("apikey", apikey);
		p.put("mobile", mobile);
		p.put("text", text);
		if (!StringUtil.isEmpty(extend)) p.put("extend", extend);
		if (!StringUtil.isEmpty(uid)) p.put("uid", uid);

		String result = HttpUtil.post(url, p);

		return result;
	}

	public String send(String url, String apikey, String mobile, String text) {
		return send(url, apikey, mobile, text, "", "");
	}

}
