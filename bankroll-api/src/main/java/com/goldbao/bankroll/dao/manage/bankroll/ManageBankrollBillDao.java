package com.goldbao.bankroll.dao.manage.bankroll;

import java.util.Date;
import java.util.List;

import com.goldbao.bankroll.model.bankroll.BankrollBill;

/**
 * @author shuyu.fang
 */
public interface ManageBankrollBillDao {

    List<BankrollBill> getBankrollBillListByMonth(Date startDate);

    boolean updateBill(BankrollBill bill);

    List<BankrollBill> getBankrollBillListByRecordId(Long recordId);
}
