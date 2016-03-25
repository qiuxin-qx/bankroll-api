package com.goldbao.bankroll.dao.bankroll.impl;

import com.goldbao.bankroll.dao.GenericDaoSupport;
import com.goldbao.bankroll.dao.bankroll.BankrollDao;
import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.Model;
import com.goldbao.bankroll.model.PageableList;
import com.goldbao.bankroll.model.bankroll.BankrollApply;
import com.goldbao.bankroll.model.bankroll.BankrollApplyDTO;
import com.goldbao.bankroll.model.bankroll.BankrollApplyStatDTO;
import com.goldbao.bankroll.model.bankroll.BankrollRecord;
import com.goldbao.bankroll.model.enums.EnumBankrollApplyStatus;
import com.goldbao.bankroll.model.enums.EnumCycleUnit;
import com.goldbao.bankroll.model.enums.EnumDr;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author shuyu.fang
 */
public class BankrollDaoHibernateImpl extends GenericDaoSupport<Model> implements BankrollDao {
    @Override
    public Long addApplyBankroll(BankrollApply apply) throws ServiceException {
        return this.save(apply, BankrollApply.class);
    }

    @Override
    public PageableList<BankrollApply> getApplyList(Map<String, Object> options, int index, int size) {
        String hql = "from BankrollApply a where a.dr=?";
        String hql2 = "select count(*) from BankrollApply a where a.dr=?";
        List<Object> params = new ArrayList<Object>();
        params.add(EnumDr.NORMAL);

        if (options.get("applicantId") != null) {
            hql += " and a.applicant.id=?";
            hql2 += " and a.applicant.id=?";
            params.add(options.get("applicantId"));
        }

        if (options.get("unit") != null) {
            hql += " and a.cycleUnit=?";
            hql2 += " and a.cycleUnit=?";
            params.add(options.get("unit"));
        }
        if (options.get("status") != null) {
            hql += " and a.status=?";
            hql2 += " and a.status=?";
            params.add(options.get("status"));
        }

        hql += " order by a.addTime desc";

        return this.pages(BankrollApply.class, hql, hql2, index, size, params);
    }

    @Override
    public PageableList<BankrollApplyDTO> getApplyDTOList(Long applicantId, EnumCycleUnit unit, EnumBankrollApplyStatus status, int fundStat, int index, int size) {
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
            " where a.applicant_id=?";

        String sql2 = "select count(*) " +
            " from bk_bankroll_apply a" +
            " inner join bk_user_fund f on f.user_id=a.applicant_id" +
            " where a.applicant_id=?";

        List<Object> params = new ArrayList<Object>();

        params.add(applicantId);

        if (unit != null) {
            sql1 += " and a.cycle_unit=?";
            sql2 += " and a.cycle_unit=?";
            params.add(unit.ordinal());
        }
        if (status != null) {
            sql1 += " and a.status=?";
            sql2 += " and a.status=?";
            params.add(status.ordinal());
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
    public BankrollApply getApplyById(long applyId) {
        String hql = "from BankrollApply b where b.dr=? and b.id=?";
        return this.get(BankrollApply.class,hql, EnumDr.NORMAL, applyId);
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

        if (options.get("applicantId") != null) {
            hql1 += " and a.applicant.id=?";
            hql2 += " and a.applicant.id=?";
            params.add(options.get("applicantId"));
        }

        PageableList<BankrollRecord> r = this.pages(BankrollRecord.class, hql1, hql2, index, size, params);
        return r;
    }

    @Override
    public BankrollRecord getBankrollRecordById(long recordId) {
        String hql = "from BankrollRecord b where b.dr=? and b.id=?";
        return this.get(BankrollRecord.class,hql, EnumDr.NORMAL, recordId);
    }

    @Override
    public BankrollApplyStatDTO getBankrollApplyStat() {
        String sql = "select count(*) as applyCount, sum(money) as totalMoney, sum(deposit) as totalDeposit from bk_bankroll_apply";
        return this.getDTO(BankrollApplyStatDTO.class, sql, null);
    }
}
