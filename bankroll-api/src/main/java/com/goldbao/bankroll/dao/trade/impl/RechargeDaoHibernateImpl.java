package com.goldbao.bankroll.dao.trade.impl;

import com.goldbao.bankroll.dao.GenericDaoSupport;
import com.goldbao.bankroll.dao.trade.RechargeDao;
import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.enums.EnumTradeStatus;
import com.goldbao.bankroll.model.trade.Recharge;

/**
 * @author shuyu.fang
 */
public class RechargeDaoHibernateImpl extends GenericDaoSupport<Recharge> implements RechargeDao {
    @Override
    public Long addRecharge(Recharge recharge) throws ServiceException {
        recharge.setStatus(EnumTradeStatus.SUCCESS);
        return this.save(recharge);
    }

    @Override
    public Recharge getRechargeByOrderNo(String orderNo) {
        return null;
    }
}
