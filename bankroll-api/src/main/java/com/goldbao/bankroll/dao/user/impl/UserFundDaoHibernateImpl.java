package com.goldbao.bankroll.dao.user.impl;

import java.math.BigDecimal;

import com.goldbao.bankroll.dao.GenericDaoSupport;
import com.goldbao.bankroll.dao.user.UserFundDao;
import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.trade.UserBank;
import com.goldbao.bankroll.model.user.UserFund;
import com.goldbao.bankroll.model.user.UserFundLog;
import com.goldbao.utils.MathUtil;

/**
 *
 * @author shuyu.fang
 */
public class UserFundDaoHibernateImpl extends GenericDaoSupport<UserFund> implements UserFundDao {
    @Override
    public Long addUserFund(UserFund userFund) throws ServiceException {
        BigDecimal zero = MathUtil.format("0");
        userFund.setProfitAndLoss(zero);
        userFund.setBalance(zero);
        userFund.setBankroll(zero);
        userFund.setFreeze(zero);
        userFund.setPrincipal(zero);
        return this.save(userFund);
    }

    @Override
    public boolean updateUserFund(UserFund userFund) {
        this.getHibernateTemplate().update(userFund);
        return true;
    }

    @Override
    public UserFund getUserFundByUserId(Long userId) {
        String hql = "from UserFund f where f.user.id=?";
        return this.get(hql, userId);
    }

    @Override
    public void addUserFundLog(UserFundLog log) throws ServiceException {
        this.save(log, UserFundLog.class);
    }

    @Override
    public void addUserBank(UserBank userBank) throws ServiceException {
        this.save(userBank, UserBank.class);
    }
}
