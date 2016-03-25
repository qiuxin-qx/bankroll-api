package com.goldbao.sms;

import com.goldbao.bankroll.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SmsManagerImpl implements SmsManager {

    @Autowired
    private SmsChannel yunpianSmsChannel;

    private Logger logger = LoggerFactory.getLogger(SmsManagerImpl.class);

    private final static String SMS_FLAG = "【股神配资注册】";

    @Override
    public String send(String phone, String message) {
        SmsChannel smsManager = yunpianSmsChannel;
        String r = smsManager.send(phone, filterMessage(message));
        logger.debug(r);
        return r;
    }

    @Override
    public String send(String phone, String message, EnumSmsType type) throws ServiceException {
        if(type == EnumSmsType.PHONE_VALIDATE_CODE)
            return send(phone, getPhoneValidateCodeMessage(message));
        throw new ServiceException("不支持的短信发送");
    }

    private String filterMessage(String message) {
        if (message.indexOf(SMS_FLAG) != 0) {
            message = SMS_FLAG + message;
        }
        return message;
    }

    private String getPhoneValidateCodeMessage(String code) {
        return SMS_FLAG + "您的注册校验码为："+code+"，此验证码30分钟后失效。请勿泄漏给他人！";
    }

}
