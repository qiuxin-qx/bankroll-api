package com.goldbao.bankroll.dao.trade.impl;

import com.goldbao.bankroll.dao.GenericDaoSupport;
import com.goldbao.bankroll.dao.trade.TradeRecordDao;
import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.PageableList;
import com.goldbao.bankroll.model.enums.EnumDr;
import com.goldbao.bankroll.model.enums.EnumTradeStatus;
import com.goldbao.bankroll.model.enums.EnumTradeType;
import com.goldbao.bankroll.model.trade.Cash;
import com.goldbao.bankroll.model.trade.TradeRecord;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author shuyu.fang
 */
public class TradeRecordDaoHibernateImpl extends GenericDaoSupport<TradeRecord> implements TradeRecordDao {
    @Override
    public Long addTradeRecord(TradeRecord record) throws ServiceException {
        record.setTradeStatus(EnumTradeStatus.INIT);
        Long id =  this.save(record);
        return id;
    }

    @Override
    public boolean updateTradeRecordToDeal(TradeRecord record) {
        String hql = "update TradeRecord r set r.tradeStatus=?, r.dealTime=? where r.serialNo=?";
        int c = this.update(hql, EnumTradeStatus.SUCCESS, new Date(), record.getSerialNo());
        return c > 1;
    }

    @Override
    public TradeRecord getTradeRecordBySerialNo(String orderNo) {
        String hql = "from TradeRecord r where r.serialNo=?";
        TradeRecord record = this.get(hql, orderNo);
        return record;
    }

    @Override
    public PageableList<TradeRecord> getTradeRecordList(int index, int size, Long userId, EnumTradeType tradeType) {
        String hql = "from TradeRecord r where r.tradeType=?";

        List<Object> params = new ArrayList<Object>();
        params.add(tradeType);

        if (userId != null) {
            hql += " and r.creator.id=?";
            params.add(userId);
        }
        hql += " order by addTime desc";
        return this.pages(hql, index, size, params);
    }

    @Override
    public void addCash(Cash cash) throws ServiceException {
        this.save(cash, Cash.class);
    }

    @Override
    public PageableList<Cash> getApplyCashList(EnumTradeStatus tradeStatus, int index, int size) {
        String hql = "from Cash c where c.dr=?";
        List<Object> params = new ArrayList<Object>();
        params.add(EnumDr.NORMAL);
        if (tradeStatus != null) {
            hql += " and c.status=?";
            params.add(tradeStatus);
        }
        return this.pages(Cash.class, hql, index, size, params);
    }

    @Override
    public Cash getApplyCashById(long cashId) {
        String hql = "from Cash c wehre c.id=?";
        return this.get(Cash.class, hql, cashId);
    }

    @Override
    public void updateCash(Cash cash) {
        cash.setDealTime(new Date());
        this.getHibernateTemplate().update(cash);
    }
}
