package com.goldbao.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class HttpUtil {

	private final static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	public static String post(String url, Map<String, String> p) {
		return post(url, p, "utf-8");
	}

	public static String post(String url, Map<String, String> p, String encoding) {
		HttpClient client = new HttpClient();

		PostMethod post = new PostMethod(url);

		if (p != null && p.size() > 0) {
			org.apache.commons.httpclient.NameValuePair[] pairs = new org.apache.commons.httpclient.NameValuePair[p.size()];
			int i = 0;
			for (String key: p.keySet()) {
				pairs[i++] = new org.apache.commons.httpclient.NameValuePair(key, p.get(key));
			}
			post.setRequestBody(pairs);
		}

		HttpMethodParams params = post.getParams();
		params.setContentCharset(encoding);
		try {
			client.executeMethod(post);

			return respToString(post);
		} catch (IOException e) {
			logger.error("http post error: " + e.getMessage());
		}
		return "";
	}

	private static String respToString(HttpMethod method) throws IOException {
		InputStream is = method.getResponseBodyAsStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		int i = 0;
		try {
			while ((line = reader.readLine()) != null) {
				if (i++ > 0) {
					sb.append("\n");
				}
				sb.append(line);
			}
			return sb.toString();
		} finally {
			is.close();
		}
	}
}
