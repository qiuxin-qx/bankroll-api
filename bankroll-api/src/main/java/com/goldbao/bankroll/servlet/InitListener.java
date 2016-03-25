package com.goldbao.bankroll.servlet;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import payment.api.system.PaymentEnvironment;

/**
 * 系统初始化配置项
 */
public class InitListener implements ServletContextListener {


    private final static Logger logger = LoggerFactory.getLogger(InitListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        // 获取classes目录
        String realPath = this.getClass().getClassLoader().getResource("/").getPath();

        logger.debug(realPath);

        logger.debug("中金支付初始化");
        try {
            String cfcaConfigPath = "config"+ File.separatorChar+"payment";
            cfcaConfigPath = realPath + cfcaConfigPath;

            PaymentEnvironment.initialize(cfcaConfigPath);
        } catch (Exception e) {
            logger.error("中金支付初始化失败：\n{}", e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
