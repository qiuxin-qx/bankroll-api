package com.goldbao.bankroll.dao.homs.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.goldbao.bankroll.dao.GenericDaoSupport;
import com.goldbao.bankroll.dao.homs.HomsDao;
import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.Model;
import com.goldbao.bankroll.model.bankroll.BankrollHomsInfoDTO;
import com.goldbao.bankroll.model.enums.EnumDr;
import com.goldbao.bankroll.model.homs.HomsAmount;
import com.goldbao.bankroll.model.homs.HomsCombAsset;
import com.goldbao.bankroll.model.homs.HomsCombInfo;
import com.goldbao.bankroll.model.homs.HomsComboFund;
import com.goldbao.bankroll.model.homs.HomsComboStock;
import com.goldbao.bankroll.model.homs.HomsCurrent;
import com.goldbao.bankroll.model.homs.HomsCurrentStock;
import com.goldbao.bankroll.model.homs.HomsFutureLoan;
import com.goldbao.bankroll.model.homs.HomsHistoryRealdealRecord;
import com.goldbao.bankroll.model.homs.HomsOperatorInfo;
import com.goldbao.bankroll.model.homs.HomsOperatorRight;
import com.goldbao.bankroll.model.homs.HomsQueryEntrust;
import com.goldbao.bankroll.model.homs.HomsRealdealRecord;
import com.goldbao.bankroll.model.homs.HomsRiskControl;
import com.goldbao.bankroll.model.homs.HomsUser;

/**
 * @author shuyu.fang
 */
public class HomsDaoHibernateImpl extends GenericDaoSupport<Model> implements HomsDao {


    @Override
    public long getUserCountByOperatorNo(String operatorNo) {
        return 0;
    }

    @Override
    public HomsUser updateHomsUserLoginInfo(HomsUser homsUser) {

        Session session = this.getSessionFactory().getCurrentSession();
        String hql = "update HomsUser u set u.lastLoginTime=:lastLoginTime, u.userToken=:userToken where u.operatorNo=:operatorNo";
        Date lastLoginTime = new Date();
        homsUser.setLastLoginTime(lastLoginTime);
        Query query = session.createQuery(hql);
        query.setTimestamp("lastLoginTime", lastLoginTime);
        query.setString("userToken", homsUser.getUserToken());
        query.setString("operatorNo", homsUser.getOperatorNo());
        query.executeUpdate();
        return homsUser;
    }

    @Override
    public HomsUser getUserByOperatorNo(String operatorNo) {
        Session session = this.getSessionFactory().getCurrentSession();
        String hql = "from HomsUser where dr=:dr and operatorNo=:operatorNo";
        Query query = session.createQuery(hql);
        query.setParameter("dr", EnumDr.NORMAL);
        query.setString("operatorNo", operatorNo);
        HomsUser homsUser = (HomsUser) query.uniqueResult();
        return homsUser;
    }

    @Override
    public HomsUser addHomsUser(Long userId, HomsUser homsUser) {
        homsUser.setAddTime(new Date());
        homsUser.setLastLoginTime(new Date());
        homsUser.setDr(EnumDr.NORMAL);
        this.getHibernateTemplate().save(homsUser);
        return homsUser;
    }

    @Override
    public void addCombInfo(HomsCombInfo i) {
        this.getHibernateTemplate().save(i);
    }

    @Override
    public void addComboStock(HomsComboStock i) {
        try {
            this.save(i, HomsComboStock.class);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addOperator(HomsOperatorInfo i) {
        try {
            this.save(i, HomsOperatorInfo.class);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addQUeryEntrust(HomsQueryEntrust i) {
        try {
            this.save(i, HomsQueryEntrust.class);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addRealdeal(HomsRealdealRecord i) {
        try {
            this.save(i, HomsRealdealRecord.class);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addAmount(HomsAmount i) {
        try {
            this.save(i, HomsAmount.class);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addAsset(HomsCombAsset asset) {
        try {
            this.save(asset, HomsCombAsset.class);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addCurrentStock(HomsCurrentStock i) {
        try {
            this.save(i, HomsCurrentStock.class);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public HomsFutureLoan getFutureLoanByCombineId(String combineId) {
        String hql = "from HomsFutureLoan f where f.combineId=?";
        return this.get(HomsFutureLoan.class, hql, combineId);
    }

    @Override
    public void addFutureLoan(HomsFutureLoan i) {
        try {
            this.save(i, HomsFutureLoan.class);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateFutureLoan(HomsFutureLoan futureLoan) {
        this.getHibernateTemplate().update(futureLoan);
    }

    @Override
    public void addComboFunds(HomsComboFund i) {
        try {
            this.save(i, HomsComboFund.class);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addCurrent(HomsCurrent i) {
        try {
            this.save(i, HomsCurrent.class);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addOperatorRight(HomsOperatorRight i) {
        try {
            this.save(i, HomsOperatorRight.class);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addRiskControl(HomsRiskControl i) {
        try {
            this.save(i, HomsRiskControl.class);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addHistoryRealdeal(HomsHistoryRealdealRecord i) {
        this.getHibernateTemplate().save(i);
    }

    @Override
    public HomsCombInfo getCombInfoByCombineId(String combineId) {
        String hql = "from HomsCombInfo c where c.combineId=?";
        return this.get(HomsCombInfo.class, hql, combineId);
    }

    @Override
    public void updateCombInfo(HomsCombInfo combInfo) {
        this.getHibernateTemplate().update(combInfo);
    }

    @Override
    public List<HomsCombInfo> getCombInfoList() {
        String hql = "from HomsCombInfo c";
        return this.list(HomsCombInfo.class, hql);
    }

    @Override
    public HomsCombAsset getCombAssetByCombineId(String combineId) {
        String hql = "from HomsCombAsset c where c.combineId=? ";
        return this.get(HomsCombAsset.class, hql, combineId);
    }

    @Override
    public void updateCombAsset(HomsCombAsset combAsset) {
        this.getHibernateTemplate().update(combAsset);
    }

    @Override
    public BankrollHomsInfoDTO getHomsInfoDTO(String homsOperatorNo) {
        String sql = "select f.asset_id assetId, f.asset_name assetName, f.combine_id combineId, f.cur_loan curLoan," +
            " f.operator_no operatorNo, f.warning_value warningValue, f.stop_value openValue, c.combine_name combineName," +
            " c.fund_account fundAccount, ca.asset_total_value assetTotalValue, ca.asset_value assetValue," +
            " ca.current_cash currentCash, ca.stock_asset stockAsset," +
            " ca.fund_asset fundAsset, ca.bond_asset bondAsset, ca.hg_asset hgAsset, ca.future_asset futureAsset" +
            " from bk_homs_future_loan f" +
            " left join bk_homs_comb_info c on f.combine_id=c.combine_id" +
            " left join bk_homs_comb_asset ca on f.combine_id=ca.combine_id" +
            " where f.operator_no=?";
        List<Object> p = new ArrayList<Object>();
        p.add(homsOperatorNo);
        List<BankrollHomsInfoDTO> l = this.listDTO(BankrollHomsInfoDTO.class, sql, p);
        if (l != null && l.size() > 0) return l.get(0);
        return null;
    }
}
