package com.goldbao.bankroll.dao.bankroll.impl;

import com.goldbao.bankroll.dao.GenericDaoSupport;
import com.goldbao.bankroll.dao.bankroll.BankrollHomsDao;
import com.goldbao.bankroll.model.bankroll.BankrollHomsInfo;

public class BankrollHomsDaoHibernateImpl extends GenericDaoSupport<BankrollHomsInfo> implements BankrollHomsDao {

	@Override
	public BankrollHomsInfo getBankrollHomsInfo(long recordId) {
		String hql = "from BankrollHomsInfo i where i.record.id=?";
        return this.get(hql, recordId);
	}

}
