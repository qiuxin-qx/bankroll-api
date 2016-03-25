package com.goldbao.bankroll.tests;

import com.goldbao.sms.SmsManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:bankroll-api-service.xml", "classpath:bankroll-api-sms.xml" })
public class SmsManagerTest {

    Logger logger = LoggerFactory.getLogger(SmsManagerTest.class);

    SmsManager smsManager;

    @Test
    public void testYunpianSmsChannel() {
        String r = smsManager.send("13916297656", "aaaaaa");
        logger.debug(r);
    }

    @Autowired
    public void setSmsManager(SmsManager smsManager) {
        this.smsManager = smsManager;
    }
}
