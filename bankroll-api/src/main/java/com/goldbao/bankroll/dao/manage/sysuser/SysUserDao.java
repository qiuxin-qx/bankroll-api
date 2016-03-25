package com.goldbao.bankroll.dao.manage.sysuser;

import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.manage.sysuser.SysUser;
import com.goldbao.bankroll.model.manage.sysuser.SysUserToken;

/**
 * @author shuyu.fang
 */
public interface SysUserDao {
    Long addSysUser(SysUser user) throws ServiceException;

    SysUser getUserByName(String username);

    SysUserToken addSysUserToken(SysUser user) throws ServiceException;

    Long getUserCountByName(String username);

    SysUserToken getToken(String token);
}
