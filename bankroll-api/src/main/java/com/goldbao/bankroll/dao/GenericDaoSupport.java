package com.goldbao.bankroll.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.Model;
import com.goldbao.bankroll.model.PageableList;
import com.goldbao.bankroll.model.enums.EnumDr;

/**
 * 泛型支持的dao操作模板
 * @author shuyu.fang
 */
public abstract class GenericDaoSupport<T extends Model> extends HibernateDaoSupport {

    private Class<T> entityClass;


    @SuppressWarnings("unchecked")
	public GenericDaoSupport() {
       entityClass = (Class<T>)((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    /**
     * 添加一条主对象
     * @param item
     * @return
     * @throws ServiceException
     */
    protected Long save(T item) throws ServiceException {
        try {
            item.setDr(EnumDr.NORMAL);
            item.setAddTime(new Date());
            return (Long) this.getHibernateTemplate().save(item);
        } catch (Exception ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    /**
     * 添加一条从对象
     * @param item
     * @param clazz
     * @param <S>
     * @return
     * @throws ServiceException
     */
    protected <S extends Model> Long save(S item, Class<S> clazz) throws ServiceException {
        try {
            item.setDr(EnumDr.NORMAL);
            item.setAddTime(new Date());
            return (Long) this.getHibernateTemplate().save(item);
        } catch (Exception ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    protected T get(final String hql, final Object... values) {
        return this.getHibernateTemplate().executeWithNativeSession(new HibernateCallback<T>() {
            @SuppressWarnings("unchecked")
			@Override
            public T doInHibernate(Session session) throws HibernateException, SQLException {
                Query q = session.createQuery(hql);
                if (values != null) {
                    for (int i = 0; i < values.length; i++) {
                        q.setParameter(i, values[i]);
                    }
                }
                return (T) q.uniqueResult();
            }
        });
    }

    protected T get(String hql, Object value) {
        return get(hql, new Object[] {value});
    }

    protected T get(String hql) {
        return get(hql, (Object[])null);
    }

    protected <S extends Serializable> S get(Class<S> clazz, final String hql, final Object... values) {
        return this.getHibernateTemplate().executeWithNativeSession(new HibernateCallback<S>() {
            @SuppressWarnings("unchecked")
			@Override
            public S doInHibernate(Session session) throws HibernateException, SQLException {
                Query q = session.createQuery(hql);
                if (values != null) {
                    for (int i = 0; i < values.length; i++) {
                        q.setParameter(i, values[i]);
                    }
                }
                return (S) q.uniqueResult();
            }
        });
    }

    protected <S> S getDTO(final Class<S> clazz, final String sql1, final List<Object> values) {

        S item = this.getHibernateTemplate().executeWithNativeSession(new HibernateCallback<S>() {
            @SuppressWarnings("unchecked")
            @Override
            public S doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createSQLQuery(sql1);
                if (values != null) {
                    for (int i = 0; i < values.size(); i++) {
                        query.setParameter(i, values.get(i));
                    }
                }
                query.setResultTransformer(Transformers.aliasToBean(clazz));
                S item = (S) query.uniqueResult();
                return item;
            }
        });
        return item;
    }

    protected <S extends Serializable> S get(Class<S> clazz, String hql, Object value) {
        return get(clazz, hql, new Object[] {value});
    }

    protected <S extends Serializable> S get(Class<S> clazz, String hql) {
        return get(clazz, hql, (Object[])null);
    }
    
    protected <S extends Serializable> List<S> list(Class<S> clazz, final String hql, final Object...values) {
    	return this.getHibernateTemplate().executeWithNativeSession(new HibernateCallback<List<S>>() {
            @SuppressWarnings("unchecked")
			@Override
            public List<S> doInHibernate(Session session) throws HibernateException, SQLException {
                Query q = session.createQuery(hql);
                if (values != null) {
                    for (int i = 0; i < values.length; i++) {
                        q.setParameter(i, values[i]);
                    }
                }
                return q.list();
            }
        });
    }
    
    protected <S extends Serializable> List<S> list(Class<S> clazz, String hql, Object value) {
        return list(clazz, hql, new Object[] {value});
    }

    protected <S> List<S> listDTO(final Class<S> clazz, final String sql1, final List<Object> values) {

        List<S> list = this.getHibernateTemplate().executeWithNativeSession(new HibernateCallback<List<S>>() {
            @SuppressWarnings("unchecked")
            @Override
            public List<S> doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createSQLQuery(sql1);
                if (values != null) {
                    for (int i = 0; i < values.size(); i++) {
                        query.setParameter(i, values.get(i));
                    }
                }
                query.setResultTransformer(Transformers.aliasToBean(clazz));
                List<S> list =  query.list();
                return list;
            }
        });
        return list;
    }

    protected <S extends Serializable> List<S> list(Class<S> clazz, String hql) {
        return list(clazz, hql, (Object[])null);
    }

    protected int update(final String hql, final Object... values) {

        return this.getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Integer>() {
            @Override
            public Integer doInHibernate(Session session) throws HibernateException, SQLException {
                Query q =  session.createQuery(hql);
                if (values != null) {
                    for (int i = 0; i < values.length; i++) {
                        q.setParameter(i, values[i]);
                    }
                }
                return q.executeUpdate();
            }
        });
    }

    protected int update(String hql, Object value) {
        return update(hql, new Object[] {value});
    }

    protected int update(String hql) {
        return update(hql, (Object[])null);
    }

    /**
     * 分页获取某对象
     * @param hql
     * @param index 从1开始的分页索引
     * @param size 每页数据条数
     * @param values
     * @return
     */
    protected PageableList<T> pages(String hql, int index, int size, Object... values) {
        return pages(entityClass, hql, index, size, values);
    }


    /**
     * 分页获取某对象
     * @param clazz
     * @param hql
     * @param index 从1开始的分页索引
     * @param size 每页数据条数
     * @param values
     * @param <S>
     * @return
     */
    protected <S extends Model> PageableList<S> pages(Class<S> clazz, final String hql, final int index, final int size, final Object... values) {

        int i = (index < 1 ? 1:index);
        final int s = (size < 1 ? 10:size);
        final int startIndex = (i-1) * s;

        List<S> list = this.getHibernateTemplate().executeWithNativeSession(new HibernateCallback<List<S>>() {
            @SuppressWarnings("unchecked")
			@Override
            public List<S> doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(hql);
                if (values != null) {
                    for (int i = 0; i < values.length; i++) {
                        if (values[i] != null) query.setParameter(i, values[i]);
                    }
                }

                query.setFirstResult(startIndex);
                query.setMaxResults(s);
                List<S> list =  query.list();
                return list;
            }
        });

        Long count = this.getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Long>() {
            @Override
            public Long doInHibernate(Session session) throws HibernateException, SQLException {
                String countHql = "select count(*) " + hql;
                Query query = session.createQuery(countHql);
                if (values != null) {
                    for (int i = 0; i < values.length; i++) {
                        if (values[i] != null) query.setParameter(i, values[i]);
                    }
                }
                return (Long) query.uniqueResult();
            }
        });

        PageableList<S> r = new PageableList<S>(i, s);

        r.setList(list);
        r.setIndex(i);
        r.setSize(s);
        r.setCount(count);
        return r;
    }


    protected PageableList<T> pages(String hql, int index, int size, List<Object> values) {
        return pages(entityClass, hql, index, size, values);
    }
    protected <S extends Model> PageableList<S> pages(final Class<S> clazz, final String hql, final int index, final int size, final List<Object> values) {
        int i = (index < 1 ? 1:index);
        final int s = (size < 1 ? 10:size);
        final int startIndex = (i-1) * s;

        List<S> list = this.getHibernateTemplate().executeWithNativeSession(new HibernateCallback<List<S>>() {
            @SuppressWarnings("unchecked")
			@Override
            public List<S> doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(hql);
                if (values != null) {
                    for (int i = 0; i < values.size(); i++) {
                        query.setParameter(i, values.get(i));
                    }
                }

                query.setFirstResult(startIndex);
                query.setMaxResults(s);

                List<S> list =  query.list();
                return list;
            }
        });

        Long count = this.getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Long>() {
            @Override
            public Long doInHibernate(Session session) throws HibernateException, SQLException {
                String countHql = "select count(*) " + hql;
                Query query = session.createQuery(countHql);
                if (values != null) {
                    for (int i = 0; i < values.size(); i++) {
                        query.setParameter(i, values.get(i));
                    }
                }
                return (Long) query.uniqueResult();
            }
        });

        PageableList<S> r = new PageableList<S>(i, s);

        r.setList(list);
        r.setIndex(i);
        r.setSize(s);
        r.setCount(count);
        return r;
    }


    /**
     * 更加通用的分页
     * @param clazz 返回类
     * @param hql1  数据sql
     * @param hql2  统计sql
     * @param index
     * @param size
     * @param values  参数
     * @param <S>
     * @return
     */
    protected <S> PageableList<S> pages(Class<S> clazz, final String hql1, final String hql2, final int index, final int size, final List<Object> values) {
        int i = (index < 1 ? 1:index);
        final int s = (size < 1 ? 10:size);
        final int startIndex = (i-1) * s;

        List<S> list = this.getHibernateTemplate().executeWithNativeSession(new HibernateCallback<List<S>>() {
            @SuppressWarnings("unchecked")
			@Override
            public List<S> doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(hql1);
                if (values != null) {
                    for (int i = 0; i < values.size(); i++) {
                        query.setParameter(i, values.get(i));
                    }
                }

                query.setFirstResult(startIndex);
                query.setMaxResults(s);
                List<S> list =  query.list();
                return list;
            }
        });

        Long count = this.getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Long>() {
            @Override
            public Long doInHibernate(Session session) throws HibernateException, SQLException {
                String countHql = hql2;
                Query query = session.createQuery(countHql);
                if (values != null) {
                    for (int i = 0; i < values.size(); i++) {
                        query.setParameter(i, values.get(i));
                    }
                }
                return (Long) query.uniqueResult();
            }
        });

        PageableList<S> r = new PageableList<S>(i, s);

        r.setList(list);
        r.setIndex(i);
        r.setSize(s);
        r.setCount(count);
        return r;
    }

    protected <S> PageableList<S> pagesDTO(final Class<S> clazz, final String sql1, final String sql2, final int index, final int size, final List<Object> values) {
        int i = (index < 1 ? 1:index);
        final int s = (size < 1 ? 10:size);
        final int startIndex = (i-1) * s;

        List<S> list = this.getHibernateTemplate().executeWithNativeSession(new HibernateCallback<List<S>>() {
            @SuppressWarnings("unchecked")
            @Override
            public List<S> doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createSQLQuery(sql1);
                if (values != null) {
                    for (int i = 0; i < values.size(); i++) {
                        query.setParameter(i, values.get(i));
                    }
                }
                query.setResultTransformer(Transformers.aliasToBean(clazz));
                query.setFirstResult(startIndex);
                query.setMaxResults(s);
                List<S> list =  query.list();
                return list;
            }
        });

        Long count = this.getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Long>() {
            @Override
            public Long doInHibernate(Session session) throws HibernateException, SQLException {
                String countHql = sql2;
                Query query = session.createSQLQuery(countHql);
                if (values != null) {
                    for (int i = 0; i < values.size(); i++) {
                        query.setParameter(i, values.get(i));
                    }
                }
                BigInteger count = (BigInteger) query.uniqueResult();
                return count.longValue();
            }
        });

        PageableList<S> r = new PageableList<S>(i, s);

        r.setList(list);
        r.setIndex(i);
        r.setSize(s);
        r.setCount(count);
        return r;
    }
}
