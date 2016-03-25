package com.goldbao.bankroll.dao.user.impl;

import com.goldbao.bankroll.dao.user.PhoneValidateCodeDao;
import com.goldbao.bankroll.model.user.PhoneValidateCode;
import com.goldbao.bankroll.model.enums.EnumDr;
import com.goldbao.bankroll.model.enums.EnumValidateCodePurpose;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @author shuyu.fang
 */
public class PhoneValidateCodeDaoHibernateImpl extends HibernateDaoSupport implements PhoneValidateCodeDao {
    @SuppressWarnings("unchecked")
	@Override
    public PhoneValidateCode getLastValidateCodeByPhone(String phone, EnumValidateCodePurpose purpose) {
        DetachedCriteria criteria = DetachedCriteria.forClass(PhoneValidateCode.class);
        criteria.add(Restrictions.eq("phone", phone));
        criteria.add(Restrictions.eq("purpose", purpose));
        criteria.addOrder(Order.desc("addTime"));
        // 只取最新一条
        List<PhoneValidateCode> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 1);

        if (list == null || list.size() == 0) return null;
        return list.get(0);
    }

    @Override
    public PhoneValidateCode addValidateCode(PhoneValidateCode validateCode) {
        validateCode.setDr(EnumDr.NORMAL);
        validateCode.setAddTime(new Date());
        validateCode.setUsed(0);
        this.getHibernateTemplate().save(validateCode);
        return validateCode;
    }

    @Override
    public Integer updateToUsed(Long id) {
        final Long codeId = id;
        return this.getHibernateTemplate().execute(new HibernateCallback<Integer>() {
            @Override
            public Integer doInHibernate(Session session) throws HibernateException, SQLException {
                String hql = "update PhoneValidateCode c set c.used=:used, c.usedTime=:usedTime where c.id=:id";
                Query query = session.createQuery(hql);
                query.setInteger("used", 1);
                query.setDate("usedTime", new Date());
                query.setLong("id", codeId);
                return query.executeUpdate();
            }
        });
    }

    @Override
    public PhoneValidateCode getValidateCodeById(Long validateCodeId) {
        return this.getHibernateTemplate().get(PhoneValidateCode.class, validateCodeId);
    }
}
