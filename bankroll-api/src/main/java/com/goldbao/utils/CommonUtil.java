package com.goldbao.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import payment.tools.util.*;
import payment.tools.util.StringUtil;

public class CommonUtil {

	private final static Logger logger = LoggerFactory.getLogger(CommonUtil.class);

    /**
     * 中金支付固定订单号，用于结算
     */
    public final static String CFCA_ORDER_NO = "2015030511460900";

	/**
	 * 生成32位随机码
	 * @return
	 */
	public static String randomToken() {
		Long random = new Date().getTime();
		String token = EncryptUtil.md5(String.valueOf(random));
		return token;
	}

	/**
	 * 生成32位随机订单号
	 * @return
	 */
	public static String randomOrderNo() {
		String uuid =  UUID.randomUUID().toString();
		uuid = uuid.replaceAll("-", "");
		return uuid;
	}

	/**
	 * 生成6位数字随机码
	 * @return
	 */
	public static String randomValidateCode() {
		Random r = new Random();
		StringBuilder s = new StringBuilder(6);
		for (int i = 0; i < 6; i++) {
			int num = Math.abs(r.nextInt())%10;
			s.append(num);
		}

		return s.toString();
	}

	/**
	 * 序列化对象
	 * @param o
	 * @return
	 */
	public static String serializeJSON(Object o) {
		try {
			return new ObjectMapper().writeValueAsString(o);
		} catch (IOException e) {
			e.printStackTrace();
			return "{}";
		}
	}

	/**
	 * 反序列化复杂对象list
	 * @param clazz
	 * @param json
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> deserializeJSONToList(Class<T> clazz, String json) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			JavaType listType = mapper.getTypeFactory().constructParametricType(List.class, clazz);
			return mapper.readValue(json, listType);
		} catch (IOException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * 反序列化对象
	 * @param clazz
	 * @param json
	 * @param <T>
	 * @return
	 */
	public static <T> T deserializeJSON(Class<T> clazz, String json) {
		try {
			return new ObjectMapper().readValue(json, clazz);
		} catch (IOException e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * 格式化日期
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String formatDate(Date date, String format) {
		if (date == null) return "";
		SimpleDateFormat format1 = new SimpleDateFormat(format);
		return format1.format(date);
	}

	public static Date parseDate(String date) throws Exception {
		return parseDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static Date parseDate(String date, String s) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat(s);
		return format.parse(date);
	}

	/**
	 * 复制list，公用方法
	 * @param o 原始list
	 * @param clazz 目标类型
	 * @param <T>   目标类型
	 * @param <O>   原始类型
	 * @return
	 */
	public <T, O> List<T> copyList(List<O> o, Class<T> clazz) {
		if (o == null || o.size() == 0) return null;
		List<T> t = new ArrayList<T>();
		try {
			for (O item : o) {
				T target = clazz.newInstance();
				BeanUtils.copyProperties(item, target);
				t.add(target);
			}
			return t;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return null;
	}

    public static String base64Encode(String text, String encoding) throws UnsupportedEncodingException {
        return Base64.encode(text, encoding);
    }

    public static String base64Encode(String text) {
        try {
            return base64Encode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {

        }
        return "";
    }
}
