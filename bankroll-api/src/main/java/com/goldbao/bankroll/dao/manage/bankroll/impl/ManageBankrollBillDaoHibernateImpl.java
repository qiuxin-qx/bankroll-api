package com.goldbao.bankroll.dao.manage.bankroll.impl;

import java.util.Date;
import java.util.List;

import com.goldbao.bankroll.dao.GenericDaoSupport;
import com.goldbao.bankroll.dao.manage.bankroll.ManageBankrollBillDao;
import com.goldbao.bankroll.model.bankroll.BankrollBill;
import com.goldbao.bankroll.model.enums.EnumBankrollBillStatus;

/**
 * @author shuyu.fang
 */
public class ManageBankrollBillDaoHibernateImpl extends GenericDaoSupport<BankrollBill> implements ManageBankrollBillDao {
    @Override
    public List<BankrollBill> getBankrollBillListByMonth(Date startDate) {
        String hql = "from BankrollBill b where (b.status=? or b.status=?) and b.shouldTime<?";
        return this.list(BankrollBill.class, hql, EnumBankrollBillStatus.NOT_REPAYMENT, EnumBankrollBillStatus.OVERDUE_NOT_REPAYMENT, startDate);
    }

    @Override
    public boolean updateBill(BankrollBill bill) {
         this.getHibernateTemplate().update(bill);
        return true;
    }

    @Override
    public List<BankrollBill> getBankrollBillListByRecordId(Long recordId) {
        String hql = "from BankrollBill b where b.status=? and b.record.id=?";
        return this.list(BankrollBill.class, hql, EnumBankrollBillStatus.NOT_REPAYMENT, recordId);
    }
}
