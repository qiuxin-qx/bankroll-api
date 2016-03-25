package com.goldbao.bankroll.dao.manage.bankroll.impl;

import com.goldbao.bankroll.dao.GenericDaoSupport;
import com.goldbao.bankroll.dao.manage.bankroll.ManageTradingDayDao;
import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.PageableList;
import com.goldbao.bankroll.model.bankroll.RangeTradingDayDTO;
import com.goldbao.bankroll.model.bankroll.TradingDay;
import com.goldbao.bankroll.model.enums.EnumTradingDayType;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import java.util.Date;
import java.util.List;

/**
 * @author shuyu.fang
 */
public class ManageTradingDaoHibernateImpl extends GenericDaoSupport<TradingDay> implements ManageTradingDayDao {
    @Override
    public Long addTradingDay(TradingDay day) throws ServiceException {

        return this.save(day);
    }

    @Override
    public List<TradingDay> getTradingDayList(Date startDate, int days) {
        String hql = "from TradingDay d where d.date>=? and d.dayType=?";
        PageableList<TradingDay> r = this.pages(hql, 1, days, startDate, EnumTradingDayType.OPEN);
        return r.getList();
    }

    @Override
    public TradingDay getTradingDayByDate(Date date) {
        String hql = "from TradingDay d where d.date=?";
        return this.get(hql, date);
    }

    @Override
    public RangeTradingDayDTO getRangeTradingDay(Date startDate, Integer days) {
        String sql = "select min(t.date_str) as startDate, max(t.date_str) as endDate from " +
            "(select date_str from bk_trading_day where date_str >=:date_str and day_type=0  order by trading_day_id limit :days) t";
        Session session = this.getSessionFactory().getCurrentSession();

        Query query = session.createSQLQuery(sql);
        query.setDate("date_str", startDate);
        query.setInteger("days", days);
        query.setResultTransformer(Transformers.aliasToBean(RangeTradingDayDTO.class));

        @SuppressWarnings("unchecked")
		List<RangeTradingDayDTO> list = query.list();
        if (list != null && list.size() > 0) return list.get(0);
        return null;
    }

    @Override
    public TradingDay getNextTradingDay() {
        Date now = new Date();
        String hql = "from TradingDay d where d.dayType=? and d.date>? order by d.date asc";
        PageableList<TradingDay> days = this.pages(hql, 1, 1, EnumTradingDayType.OPEN, now);
        if (days.getList() != null && days.getList().size() > 0) {
            return days.getList().get(0);
        }
        return null;
    }
}
