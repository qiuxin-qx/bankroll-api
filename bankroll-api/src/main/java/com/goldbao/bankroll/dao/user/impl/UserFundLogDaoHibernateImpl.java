package com.goldbao.bankroll.dao.user.impl;

import com.goldbao.bankroll.dao.GenericDaoSupport;
import com.goldbao.bankroll.dao.user.UserFundLogDao;
import com.goldbao.bankroll.model.PageableList;
import com.goldbao.bankroll.model.enums.EnumTradeType;
import com.goldbao.bankroll.model.user.UserFundLog;

/**
 * @author shuyu.fang
 */
public class UserFundLogDaoHibernateImpl extends GenericDaoSupport<UserFundLog> implements UserFundLogDao {
    @Override
    public PageableList<UserFundLog> getUserFunds(Long userId, EnumTradeType tradeType, int index, int size) {
        String hql = "from UserFundLog l where l.payer.id=?";
        if (tradeType != null) {
            hql += " and l.tradeType=?";
            return this.pages(hql, index, size, userId, tradeType);
        }
        return this.pages(hql, index, size, userId);
    }
}
