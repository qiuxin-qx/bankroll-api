package com.goldbao.bankroll.dao.manage.trade;

import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.enums.EnumPayType;
import com.goldbao.bankroll.model.trade.Recharge;
import com.goldbao.bankroll.model.trade.Settlement;
import com.goldbao.bankroll.model.trade.TradeRecord;

import java.util.Date;
import java.util.List;

/**
 * @author shuyu.fang
 */
public interface ManageTradeRecordDao {
    List<TradeRecord> getTradeRecordOfWaitSync(Date date, EnumPayType payType);

    List<TradeRecord> getTradeRecordOfSuccess(Date date, EnumPayType payType);

    void updateTradeRecordToWaitSettlement(TradeRecord tradeRecord);

    void addSettlement(Settlement settlement) throws ServiceException;

    void updateSettlement(Settlement settlement);

    List<TradeRecord> getTradeRecordBySettlementId(Long id);

    void updateTradeRecordToSettlement(TradeRecord tradeRecord);

    Settlement getSettlementById(Long id);
}
