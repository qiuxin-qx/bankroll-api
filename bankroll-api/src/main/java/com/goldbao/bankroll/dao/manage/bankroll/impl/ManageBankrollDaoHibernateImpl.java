package com.goldbao.bankroll.dao.manage.bankroll.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.goldbao.bankroll.dao.GenericDaoSupport;
import com.goldbao.bankroll.dao.manage.bankroll.ManageBankrollDao;
import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.Model;
import com.goldbao.bankroll.model.PageableList;
import com.goldbao.bankroll.model.bankroll.*;
import com.goldbao.bankroll.model.enums.EnumBankrollApplyStatus;
import com.goldbao.bankroll.model.enums.EnumBankrollRecordStatus;
import com.goldbao.bankroll.model.enums.EnumCycleUnit;
import com.goldbao.bankroll.model.enums.EnumDr;

/**
 * @author shuyu.fang
 */
public class ManageBankrollDaoHibernateImpl extends GenericDaoSupport<Model> implements ManageBankrollDao {
    @Override
    public PageableList<BankrollApply> getApplyList(Map<String, Object> options, int index, int size) {
        
        String hql1 = "from BankrollApply a where a.dr=?";
        String hql2 = "select count(a) from BankrollApply a where a.dr=?";
        List<Object> params = new ArrayList<Object>();
        params.add(EnumDr.NORMAL);

        if (options.get("unit") != null) {
            hql1 += " and a.cycleUnit=?";
            hql2 += " and a.cycleUnit=?";
            params.add(options.get("unit"));
        }
        if (options.get("status") != null) {
            hql1 += " and a.status=?";
            hql2 += " and a.status=?";
            params.add(options.get("status"));
        }

        hql1 += " order by a.id desc";
        
        PageableList<BankrollApply> r = this.pages(BankrollApply.class, hql1, hql2, index, size, params);
        return r;
    }

    @Override
    public BankrollApply getApplyById(Long id) {

        String hql = "from BankrollApply a where a.id=? and a.dr=?";

        BankrollApply apply = this.get(BankrollApply.class, hql, id, EnumDr.NORMAL);
        return  apply;
    }

    @Override
    public Long addBankrollRecord(BankrollRecord bankrollRecord) throws ServiceException {
        return this.save(bankrollRecord, BankrollRecord.class);
    }

    @Override
    public void addBankrollRecordLog(BankrollRecordLog recordLog) throws ServiceException {
        this.save(recordLog, BankrollRecordLog.class);
    }

    @Override
    public boolean updateBankrollApplyToDeal(BankrollApply apply) {
        String hql = "update BankrollApply a set a.status=? where a.id=?";
        int num = this.update(hql, EnumBankrollApplyStatus.PASS, apply.getId());
        return num > 0;
    }

    @Override
    public BankrollRecord getBankrollRecordById(long recordId) {
        String hql = "from BankrollRecord r where r.id=? and r.dr=?";

        return this.get(BankrollRecord.class, hql, recordId, EnumDr.NORMAL);
    }

    @Override
    public PageableList<BankrollRecord> getBankrollRecordList(Map<String, Object> options, int index, int size) {
        String hql1 = "from BankrollRecord a where a.dr=?";
        String hql2 = "select count(a) from BankrollRecord a where a.dr=?";
        List<Object> params = new ArrayList<Object>();
        params.add(EnumDr.NORMAL);

        if (options.get("unit") != null) {
            hql1 += " and a.cycleUnit=?";
            hql2 += " and a.cycleUnit=?";
            params.add(options.get("unit"));
        }
        if (options.get("status") != null) {
            hql1 += " and a.status=?";
            hql2 += " and a.status=?";
            params.add(options.get("status"));
        }

        hql1 += " order by a.id desc";

        PageableList<BankrollRecord> r = this.pages(BankrollRecord.class, hql1, hql2, index, size, params);
        return r;
    }

    @Override
    public boolean updateBankrollRecordToAllocateHoms(BankrollRecord record) {
        String hql = "update BankrollRecord r set r.homsOperatorNo=?, r.homsOperatorPwd=?, " +
            "r.startDate=?, r.endDate=?, r.status=?, r.renewNumber=?, r.updateTime=? where r.id=?";

        int num = this.update(hql, record.getHomsOperatorNo(), record.getHomsOperatorPwd(),
            record.getStartDate(), record.getEndDate(), record.getStatus(), record.getRenewNumber(), record.getUpdateTime(), record.getId());

        return num == 1;
    }

    @Override
    public Long addBankrollBill(BankrollBill bill) throws ServiceException {
        return this.save(bill, BankrollBill.class);
    }

    @Override
    public PageableList<BankrollApplyDTO> getApplyDTOList(EnumCycleUnit eUnit, EnumBankrollApplyStatus eStatus, int fundStat, int index, int size) {
        String sql1 = "select a.apply_id id, a.add_time addTime, a.cycle cycle, a.cycle_unit cycleUnit," +
            " a.deposit deposit, a.prep_deposit prepDeposit, a.open_line_money openLineMoney, a.money money," +
            " a.warning_line_money warningLineMoney, a.management_fee managementFee, a.status status," +
            " a.remark remark, a.lever lever," +
            " u.user_id applicantId, u.username username, u.mobilephone mobilephone," +
            " f.balance balance," +
            " r.record_id bankrollRecordId" +
            " from bk_bankroll_apply a" +
            " inner join bk_user u on a.applicant_id=u.user_id" +
            " inner join bk_user_fund f on f.user_id=u.user_id" +
            " left join bk_bankroll_record r on r.apply_id=a.apply_id" +
            " where 1=1";

        String sql2 = "select count(*) " +
            " from bk_bankroll_apply a" +
            " inner join bk_user_fund f on f.user_id=a.applicant_id" +
            " where 1=1";

        List<Object> params = new ArrayList<Object>();

        if (eUnit != null) {
            sql1 += " and a.cycle_unit=?";
            sql2 += " and a.cycle_unit=?";
            params.add(eUnit.ordinal());
        }
        if (eStatus != null) {
            sql1 += " and a.status=?";
            sql2 += " and a.status=?";
            params.add(eStatus.ordinal());
        }


        if (fundStat == 1) {
            sql1 += " and if(a.cycle_unit=0, a.deposit+a.management_fee*a.cycle, a.deposit+a.management_fee)<=f.balance";
            sql2 += " and if(a.cycle_unit=0, a.deposit+a.management_fee*a.cycle, a.deposit+a.management_fee)<=f.balance";
        }
        if (fundStat == 0) {
            sql1 += " and if(a.cycle_unit=0, a.deposit+a.management_fee*a.cycle, a.deposit+a.management_fee)>f.balance";
            sql2 += " and if(a.cycle_unit=0, a.deposit+a.management_fee*a.cycle, a.deposit+a.management_fee)>f.balance";
        }

        sql1 += " order by a.apply_id desc ";


        return this.pagesDTO(BankrollApplyDTO.class, sql1, sql2, index, size, params);
    }

    @Override
    public List<BankrollRecord> getBankrollRecordListByDate(Date date) {
        String hql = "from BankrollRecord r where r.cycleUnit=? and r.status=? and r.endDate<=?";

        return this.list(BankrollRecord.class, hql, EnumCycleUnit.DAY, EnumBankrollRecordStatus.ALLOCATIONED_HOMS, date);
    }

    @Override
    public boolean updateBankrollRecordToRenewByDay(BankrollRecord record) {
        this.getHibernateTemplate().update(record);
        return true;
    }

    @Override
    public List<BankrollRecord> getBankrollRecordListByStatus(EnumBankrollRecordStatus status, Long orgId) {
        String hql = "from BankrollRecord r where r.status=? and r.organize.id=? order by r.addTime";
        return this.list(BankrollRecord.class, hql, status, orgId);
    }

    @Override
    public BankrollRecord getBankrollRecordByOperatorNo(String operatorNo) {
        String hql = "from BankrollRecord r where r.status=? and r.homsOperatorNo=?";
        BankrollRecord record = this.get(BankrollRecord.class, hql, EnumBankrollRecordStatus.ALLOCATIONED_HOMS, operatorNo);
        return record;
    }

    @Override
    public boolean updateBankrollRecord(BankrollRecord record) {
        this.getHibernateTemplate().update(record);
        return true;
    }
}
