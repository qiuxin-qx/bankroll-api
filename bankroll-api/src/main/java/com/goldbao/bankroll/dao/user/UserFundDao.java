package com.goldbao.bankroll.dao.user;

import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.trade.UserBank;
import com.goldbao.bankroll.model.user.UserFund;
import com.goldbao.bankroll.model.user.UserFundLog;

/**
 * 用户账户管理
 * @author shuyu.fang
 */
public interface UserFundDao {

    Long addUserFund(UserFund userFund) throws ServiceException;

    boolean updateUserFund(UserFund userFund);

    UserFund getUserFundByUserId(Long userId);

    void addUserFundLog(UserFundLog log) throws ServiceException;

    void addUserBank(UserBank userBank) throws ServiceException;
}
