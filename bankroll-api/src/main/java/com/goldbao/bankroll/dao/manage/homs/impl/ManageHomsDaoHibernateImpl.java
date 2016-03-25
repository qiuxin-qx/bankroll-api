package com.goldbao.bankroll.dao.manage.homs.impl;

import com.goldbao.bankroll.dao.GenericDaoSupport;
import com.goldbao.bankroll.dao.manage.homs.ManageHomsDao;
import com.goldbao.bankroll.model.Model;
import com.goldbao.bankroll.model.PageableList;
import com.goldbao.bankroll.model.enums.EnumDr;
import com.goldbao.bankroll.model.homs.HomsOperatorInfo;
import com.goldbao.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author shuyu.fang
 */
public class ManageHomsDaoHibernateImpl extends GenericDaoSupport<Model> implements ManageHomsDao {
    @Override
    public PageableList<HomsOperatorInfo> getHomsOperatorList(Map<String, Object> options, int index, int size) {
        String hql = "from HomsOperatorInfo i where i.dr=? ";
        List<Object> params = new ArrayList<Object>();
        params.add(EnumDr.NORMAL);
        if (options.containsKey("operatorNo") &&  options.get("operatorNo") != null
            && !StringUtil.isEmpty(options.get("operatorNo").toString())) {
            hql += " and i.operatorNo like ?";
            params.add("%"+options.get("operatorNo")+"%");
        }
        return this.pages(HomsOperatorInfo.class, hql, index, size, params);
    }
}
