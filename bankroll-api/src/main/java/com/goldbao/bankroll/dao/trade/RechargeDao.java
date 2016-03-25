package com.goldbao.bankroll.dao.trade;

import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.trade.Recharge;

/**
 * 充值记录管理
 */
public interface RechargeDao {


    Long addRecharge(Recharge recharge) throws ServiceException;

    Recharge getRechargeByOrderNo(String orderNo);
}
