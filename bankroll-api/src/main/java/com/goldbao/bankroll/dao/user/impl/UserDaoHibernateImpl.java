package com.goldbao.bankroll.dao.user.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.goldbao.bankroll.dao.GenericDaoSupport;
import com.goldbao.bankroll.dao.user.UserDao;
import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.enums.EnumDr;
import com.goldbao.bankroll.model.user.User;
import com.goldbao.bankroll.model.user.UserApplyReal;
import com.goldbao.bankroll.model.user.UserToken;
import com.goldbao.utils.CalendarUtil;
import com.goldbao.utils.CommonUtil;

public class UserDaoHibernateImpl extends GenericDaoSupport<User> implements UserDao {

	@Override
	public User getUserByNameOrPhone(String username) throws ServiceException {

		String hql = "from User u where u.dr=? and (u.username=? or u.mobilephone=?)";
		List<User> r = this.list(User.class, hql, EnumDr.NORMAL, username, username);
		if (r.size() > 0) return r.get(0);
		return null;
	}

	@Override
	public User addUser(User user) throws ServiceException {
		this.save(user);
		return user;
	}

	@Override
	public UserToken addUserToken(User user) {
		UserToken token = new UserToken();
		token.setExpireTime(CalendarUtil.nextDay());
		token.setDr(EnumDr.NORMAL);
		token.setUser(user);
		token.setToken(CommonUtil.randomToken());
		this.getHibernateTemplate().save(token);
		return token;
	}

	@Override
	public UserToken updateUserToken(UserToken token) {
		token.setToken(CommonUtil.randomToken());
		token.setExpireTime(CalendarUtil.nextDay());
		this.getHibernateTemplate().update(token);
		return token;
	}

    @Override
    public boolean updateUserLoginTime(User user) {
        user.setLastLoginTime(new Date());
        this.getHibernateTemplate().update(user);
        return true;
    }

    @SuppressWarnings("unchecked")
	@Override
	public UserToken getUserTokenByUserId(Long userid) {
		DetachedCriteria criteria = DetachedCriteria.forClass(UserToken.class);
		criteria.add(Restrictions.eq("user.id", userid));
		
		List<UserToken> r = this.getHibernateTemplate().findByCriteria(criteria);
		if (r.size() > 0) return r.get(0);
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserToken getUserTokenByToken(String token) {
		DetachedCriteria criteria = DetachedCriteria.forClass(UserToken.class);
		criteria.add(Restrictions.eq("token", token));

		List<UserToken> r = this.getHibernateTemplate().findByCriteria(criteria);
		if (r.size() > 0) return r.get(0);
		return null;
	}

	@Override
	public Long getUserNumByName(String username) {

		final String name = username;
		return this.getHibernateTemplate().execute(new HibernateCallback<Long>() {
			@Override
			public Long doInHibernate(Session session) throws HibernateException, SQLException {
				String hql = "select count(user) from User user where user.username=:username";
				Query query = session.createQuery(hql);
				query.setString("username", name);
				Long num = (Long) query.uniqueResult();
				return num;
			}
		});
	}

	@Override
	public Long getUserNumByPhone(String phone) {
		String hql = "select count(user) from User user where user.mobilephone=?";
		return this.get(Long.class, hql, phone);
	}

	@Override
	public boolean addApplyUserReal(UserApplyReal applyReal) throws ServiceException {
		long count = this.save(applyReal, UserApplyReal.class);
		return count == 1;
	}

	@Override
	public UserApplyReal getUserApplyReal(Long id) {
		String hql = "from UserApplyReal r where r.user.id=?";
		return this.get(UserApplyReal.class, hql, id);
	}

	@Override
	public boolean updateApplyUserReal(UserApplyReal applyReal) {
		this.getHibernateTemplate().update(applyReal);
		return true;
	}
}
