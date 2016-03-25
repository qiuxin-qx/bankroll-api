package com.goldbao.bankroll.dao.organize.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.goldbao.bankroll.dao.organize.OrganizeDao;
import com.goldbao.bankroll.model.Organize;
import com.goldbao.bankroll.model.enums.EnumDr;

public class OrganizeDaoHibernateImpl extends HibernateDaoSupport implements OrganizeDao {

	@Override
	public Organize addOrganize(Organize organize) {
		this.getHibernateTemplate().save(organize);
		return organize;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Organize getOrganizeById(Long id) {
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Organize.class);
		criteria.add(Restrictions.eq("id", id));
		criteria.add(Restrictions.eq("dr", EnumDr.NORMAL));
		List<Organize> orgs = this.getHibernateTemplate().findByCriteria(criteria);
		
		if (orgs != null && orgs.size() > 0) return orgs.get(0);
		return null;
		
	}

	@Override
	public Organize updateOrganize(Organize organize) {
		this.getHibernateTemplate().saveOrUpdate(organize);
		return organize;
	}

}
