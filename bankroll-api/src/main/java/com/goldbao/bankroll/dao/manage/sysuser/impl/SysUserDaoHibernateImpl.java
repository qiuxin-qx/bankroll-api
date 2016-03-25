package com.goldbao.bankroll.dao.manage.sysuser.impl;

import com.goldbao.bankroll.dao.GenericDaoSupport;
import com.goldbao.bankroll.dao.manage.sysuser.SysUserDao;
import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.enums.EnumDr;
import com.goldbao.bankroll.model.enums.EnumUserStatus;
import com.goldbao.bankroll.model.manage.sysuser.SysUser;
import com.goldbao.bankroll.model.manage.sysuser.SysUserToken;
import com.goldbao.utils.CalendarUtil;
import com.goldbao.utils.CommonUtil;

/**
 * @author shuyu.fang
 */
public class SysUserDaoHibernateImpl extends GenericDaoSupport<SysUser> implements SysUserDao {

    @Override
    public Long addSysUser(SysUser user) throws ServiceException {
        user.setStatus(EnumUserStatus.NORMAL);
        return this.save(user);
    }

    @Override
    public SysUser getUserByName(String username) {
        String hql = "from SysUser su where su.dr=? and su.status=? and su.username=?";

        return this.get(hql, EnumDr.NORMAL, EnumUserStatus.NORMAL, username);
    }

    @Override
    public SysUserToken addSysUserToken(SysUser user) throws ServiceException {
        SysUserToken token = new SysUserToken();
        token.setExpireTime(CalendarUtil.nextDay());
        token.setToken(CommonUtil.randomToken());
        token.setUser(user);
        this.save(token, SysUserToken.class);
        return token;
    }

    @Override
    public Long getUserCountByName(String username) {
        String hql = "select count(su) from SysUser su where su.username=?";

        return this.get(Long.class, hql, username);
    }

    @Override
    public SysUserToken getToken(String token) {
        String hql = "from SysUserToken t where t.token=?";

        return this.get(SysUserToken.class, hql, token);
    }
}
