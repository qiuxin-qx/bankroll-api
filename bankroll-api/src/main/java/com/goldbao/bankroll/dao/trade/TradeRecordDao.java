package com.goldbao.bankroll.dao.trade;

import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.PageableList;
import com.goldbao.bankroll.model.enums.EnumTradeStatus;
import com.goldbao.bankroll.model.enums.EnumTradeType;
import com.goldbao.bankroll.model.trade.Cash;
import com.goldbao.bankroll.model.trade.TradeRecord;

/**
 * 交易记录
 * @author shuyu.fang
 */
public interface TradeRecordDao {

    Long addTradeRecord(TradeRecord record) throws ServiceException;

    boolean updateTradeRecordToDeal(TradeRecord record);

    TradeRecord getTradeRecordBySerialNo(String orderNo);

    PageableList<TradeRecord> getTradeRecordList(int index, int size, Long userId, EnumTradeType tradeType);

    void addCash(Cash cash) throws ServiceException;

    PageableList<Cash> getApplyCashList(EnumTradeStatus tradeStatus, int index, int size);

    Cash getApplyCashById(long cashId);

    void updateCash(Cash cash);
}
