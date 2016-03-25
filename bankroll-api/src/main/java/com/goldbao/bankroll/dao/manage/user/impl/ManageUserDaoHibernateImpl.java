package com.goldbao.bankroll.dao.manage.user.impl;

import com.goldbao.bankroll.dao.GenericDaoSupport;
import com.goldbao.bankroll.dao.manage.user.ManageUserDao;
import com.goldbao.bankroll.model.PageableList;
import com.goldbao.bankroll.model.enums.EnumDr;
import com.goldbao.bankroll.model.enums.EnumVerified;
import com.goldbao.bankroll.model.user.User;
import com.goldbao.bankroll.model.user.UserApplyReal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author shuyu.fang
 */
public class ManageUserDaoHibernateImpl extends GenericDaoSupport<User> implements ManageUserDao {
    @Override
    public PageableList<User> getUserList(Map<String, Object> options, int index, int size) {
        String hql = "from User u where u.dr=?";
        List<Object> params = new ArrayList<Object>();
        params.add(EnumDr.NORMAL);
        for (String key: options.keySet()) {
            if (options.get(key) != null) {
                hql += " and u."+key+" like ?";
                params.add("%"+options.get(key)+"%");
            }
        }
        return this.pages(User.class, hql, index, size, params);
    }

    @Override
    public User getUserById(Long id) {
        String hql = "from User u where u.id=? and u.dr=?";
        return this.get(hql, id, EnumDr.NORMAL);
    }

    @Override
    public PageableList<UserApplyReal> getApplyUserRealList(EnumVerified status, int index, int size) {
        String hql = "from UserApplyReal r where r.dr=?";

        List<Object> params = new ArrayList<Object>();
        params.add(EnumDr.NORMAL);
        if (status != null) {
            hql += " and r.status=?";
            params.add(status);
        }
        return this.pages(UserApplyReal.class, hql, index, size, params);
    }

    @Override
    public UserApplyReal getApplyUserRealById(long id) {
        String hql = "from UserApplyReal r where r.dr=? and r.id=?";

        return this.get(UserApplyReal.class, hql, EnumDr.NORMAL, id);
    }

    @Override
    public boolean updateUser(User user) {
        this.getHibernateTemplate().update(user);
        return true;
    }
}
