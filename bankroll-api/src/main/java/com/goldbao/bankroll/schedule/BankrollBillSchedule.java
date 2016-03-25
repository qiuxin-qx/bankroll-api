package com.goldbao.bankroll.schedule;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.goldbao.bankroll.model.bankroll.BankrollBill;
import com.goldbao.bankroll.model.bankroll.BankrollRecord;
import com.goldbao.bankroll.service.manage.bankroll.ManageBankrollService;
import com.goldbao.utils.CommonUtil;

/**
 * 配资账单自动还款
 *
 * resources/bankroll-api-manage-job.xml
 * @author shuyu.fang
 */
public class BankrollBillSchedule {

    private ManageBankrollService manageBankrollService;

    private final static Logger logger = LoggerFactory.getLogger(BankrollBillSchedule.class);

    private final static String DATE_FORMAT = "yyyy-MM-dd";

    // TODO 今天应该收明天的钱，二不因该是昨天的，这个地方（按月，按天）需要改掉。。

    /**
     * 账单按月还款
     */
    public void billRepaymentByMonth() {
        try {
            logger.debug("账单按月还款--start");
            Date today = CommonUtil.parseDate(CommonUtil.formatDate(new Date(), DATE_FORMAT), DATE_FORMAT);
            List<BankrollBill> bills = manageBankrollService.getNotRepaymentBillListByMonth(today);

            if (bills != null && bills.size() > 0) {
                for (BankrollBill bill: bills) {
                    manageBankrollService.updateBillRepaymentOfMonth(bill);
                }
            }
            logger.debug("账单按月还款--end");
        } catch (Exception ex) {
            logger.debug("账单按月还款 报错--"+ex.getMessage());
            logger.error(ex.getMessage());
        }

    }

    /**
     * 账单按天还款（自动续约）
     */
    public void billRepaymentByDay() {
        try {
            logger.debug("账单按天还款（自动续约）-- start");
            Date today = CommonUtil.parseDate(CommonUtil.formatDate(new Date(), DATE_FORMAT), DATE_FORMAT);

            List<BankrollRecord> list = manageBankrollService.getBankrollRecordListByDate(today);

            if (list != null) {

                for (BankrollRecord record: list) {
                    manageBankrollService.updateBillRepaymentOfDay(record);
                }

            }
            logger.debug("账单按天还款（自动续约）-- end");
            // 1. 获取配资将要将要到期的记录（差一天）
            // 2. 从账户扣除一天的服务费
            // 3. 配资记录延期一天
            // 4. 添加一条续约申请（系统）记录
            // 5. 审核通过并延期配资
        } catch (Exception ex) {
            logger.debug("账单按天还款（自动续约） 异常-- start");
            logger.error(ex.getMessage());
        }

    }


    @Autowired
    public void setManageBankrollService(ManageBankrollService manageBankrollService) {
        this.manageBankrollService = manageBankrollService;
    }
}
