package com.goldbao.bankroll.dao.manage.trade.impl;

import com.goldbao.bankroll.dao.GenericDaoSupport;
import com.goldbao.bankroll.dao.manage.trade.ManageTradeRecordDao;
import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.enums.EnumPayType;
import com.goldbao.bankroll.model.enums.EnumTradeStatus;
import com.goldbao.bankroll.model.enums.EnumTradeType;
import com.goldbao.bankroll.model.trade.Recharge;
import com.goldbao.bankroll.model.trade.Settlement;
import com.goldbao.bankroll.model.trade.TradeRecord;
import com.goldbao.utils.CalendarUtil;
import com.goldbao.utils.CommonUtil;

import java.util.Date;
import java.util.List;

/**
 * @author shuyu.fang
 */
public class ManageTradeRecordDaoImpl extends GenericDaoSupport<TradeRecord> implements ManageTradeRecordDao {

    @Override
    public List<TradeRecord> getTradeRecordOfWaitSync(Date date, EnumPayType payType) {
        Date yestday = CalendarUtil.nextHour(-24);
        if (payType == EnumPayType.CFCA) {
            String hql = "from TradeRecord r where r.addTime<=? and r.addTime>=? and r.orderNo=? and r.tradeStatus=? and r.tradeType=?";
            List<TradeRecord> tradeRecordList = this.list(TradeRecord.class, hql, date, yestday, CommonUtil.CFCA_ORDER_NO, EnumTradeStatus.INIT, EnumTradeType.RECHARGE);
            return tradeRecordList;
        } else {
            // TODO 其他支付方式时，这里需要重构
            return null;
        }
    }

    @Override
    public Settlement getSettlementById(Long id) {
        return this.getHibernateTemplate().get(Settlement.class, id);
    }

    @Override
    public List<TradeRecord> getTradeRecordOfSuccess(Date date, EnumPayType payType) {
        if (payType == EnumPayType.CFCA) {
            String hql = "from TradeRecord r where r.addTime<=? and r.orderNo=? and r.tradeStatus=? and r.tradeType=?";
            List<TradeRecord> tradeRecordList = this.list(TradeRecord.class, hql, date, CommonUtil.CFCA_ORDER_NO, EnumTradeStatus.SUCCESS, EnumTradeType.RECHARGE);
            return tradeRecordList;
        } else {
            // TODO 其他支付方式时，这里需要重构
            return null;
        }
    }

    @Override
    public void updateTradeRecordToWaitSettlement(TradeRecord tradeRecord) {
        tradeRecord.setTradeStatus(EnumTradeStatus.WAIT_SETTLEMENT);
        this.getHibernateTemplate().update(tradeRecord);
    }

    @Override
    public void addSettlement(Settlement settlement) throws ServiceException {
        this.save(settlement, Settlement.class);

    }

    @Override
    public void updateSettlement(Settlement settlement) {
        this.getHibernateTemplate().update(settlement);
    }

    @Override
    public List<TradeRecord> getTradeRecordBySettlementId(Long id) {
        String hql = "from TradeRecord r where r.settlement.id=?";
        return this.list(TradeRecord.class, hql, id);
    }

    @Override
    public void updateTradeRecordToSettlement(TradeRecord tradeRecord) {
        tradeRecord.setTradeStatus(EnumTradeStatus.SETTLEMENT);
        this.getHibernateTemplate().update(tradeRecord);
    }
}
