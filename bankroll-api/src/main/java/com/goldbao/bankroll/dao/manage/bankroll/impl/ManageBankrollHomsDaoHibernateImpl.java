package com.goldbao.bankroll.dao.manage.bankroll.impl;

import com.goldbao.bankroll.dao.GenericDaoSupport;
import com.goldbao.bankroll.dao.manage.bankroll.ManageBankrollHomsDao;
import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.bankroll.BankrollHomsInfo;
import com.goldbao.bankroll.model.bankroll.BankrollHomsInfoLog;

import java.util.List;

/**
 * @author shuyu.fang
 */
public class ManageBankrollHomsDaoHibernateImpl extends GenericDaoSupport<BankrollHomsInfo> implements ManageBankrollHomsDao {

    @Override
    public BankrollHomsInfo getBankrollHomsInfo(Long recordId) {
        String hql = "from BankrollHomsInfo i where i.record.id=?";
        return this.get(hql, recordId);
    }

    @Override
    public Long addBankrollHomsInfo(BankrollHomsInfo homsInfo) throws ServiceException {
        return this.save(homsInfo);
    }

    @Override
    public boolean updateBankrollHomsInfo(BankrollHomsInfo homsInfo) {
        this.getHibernateTemplate().update(homsInfo);
        return true;
    }

    @Override
    public boolean addBankrollHomsInfoLog(BankrollHomsInfoLog homsInfoLog) throws ServiceException {
        this.save(homsInfoLog, BankrollHomsInfoLog.class);
        return false;
    }

    @Override
    public List<BankrollHomsInfoLog> getBankrollHomsInfoLog(long recordId) {
        String hql = "from BankrollHomsInfoLog l where l.record.id=?";
        return this.list(BankrollHomsInfoLog.class, hql, recordId);
    }
}
