package com.goldbao.bankroll.service.manage.trade;

import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.enums.EnumPayType;
import com.goldbao.bankroll.model.manage.sysuser.SysUser;
import com.goldbao.bankroll.model.trade.Recharge;
import com.goldbao.bankroll.model.trade.Settlement;
import com.goldbao.bankroll.model.trade.TradeRecord;
import com.goldbao.bankroll.model.user.User;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author shuyu.fang
 */
public interface ManageTradeRecordService {
    boolean transfer(SysUser user, User user1, BigDecimal transferMoney);

    /**
     * 获取date时间以来的状态为未支付的订单列表
     * @param date
     * @param payType 具体支付方式
     * @return
     */
    List<TradeRecord> getTradeRecordOfWaitSync(Date date, EnumPayType payType);

    /**
     * 获取date时间以来状态为已支付但未结算的支付记录列表
     * @param date
     * @param payType
     * @return
     */
    List<TradeRecord> getTradeRecordOfSuccess(Date date, EnumPayType payType);

    /**
     * 添加一条结算记录，并执行相关操作
     * @param tradeRecordList
     * @return
     */
    Settlement addSettlement(List<TradeRecord> tradeRecordList) throws ServiceException;

    void updateSettlementToFinish(Long id);
}
