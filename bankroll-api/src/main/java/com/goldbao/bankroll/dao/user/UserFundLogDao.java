package com.goldbao.bankroll.dao.user;

import com.goldbao.bankroll.model.PageableList;
import com.goldbao.bankroll.model.enums.EnumTradeType;
import com.goldbao.bankroll.model.user.UserFundLog;

/**
 * @author shuyu.fang
 */
public interface UserFundLogDao {

    PageableList<UserFundLog> getUserFunds(Long userId, EnumTradeType tradeType, int index, int size);
}
