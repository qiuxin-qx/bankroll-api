package com.goldbao.bankroll.tests.manage;

import com.goldbao.bankroll.model.bankroll.BankrollBill;
import com.goldbao.bankroll.model.bankroll.BankrollRecord;
import com.goldbao.bankroll.service.manage.bankroll.ManageBankrollService;
import com.goldbao.utils.CommonUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * @author shuyu.fang
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:bankroll-api-service.xml" })
public class BankrollBillScheduleTest {

    private ManageBankrollService manageBankrollService;

    private final static Logger logger = LoggerFactory.getLogger(BankrollBillScheduleTest.class);

    @Test
    public void testBillByMonth() {
        try {
            String format = "yyyy-MM-dd";
            Date today = CommonUtil.parseDate(CommonUtil.formatDate(new Date(), format), format);
            List<BankrollBill> bills = manageBankrollService.getNotRepaymentBillListByMonth(today);

            if (bills != null && bills.size() > 0) {
                for (BankrollBill bill: bills) {
                    manageBankrollService.updateBillRepaymentOfMonth(bill);
                }
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    @Test
    public void testBillByDay() {
        try {
            Date today = CommonUtil.parseDate(CommonUtil.formatDate(new Date(), "yyyy-MM-dd"), "yyyy-MM-dd");
            List<BankrollRecord> list = manageBankrollService.getBankrollRecordListByDate(today);


            if (list != null) {

                for (BankrollRecord record: list) {
                    manageBankrollService.updateBillRepaymentOfDay(record);
                }

            }
            Assert.assertNotNull(list);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Autowired
    public void setManageBankrollService(ManageBankrollService manageBankrollService) {
        this.manageBankrollService = manageBankrollService;
    }
}
