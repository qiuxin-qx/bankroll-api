package com.goldbao.bankroll.service.manage.sysuser;

import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.manage.sysuser.SysUser;
import com.goldbao.bankroll.model.manage.sysuser.SysUserToken;

/**
 * @author shuyu.fang
 */
public interface SysUserService {
    Long addSysUser(SysUser user) throws ServiceException;

    SysUser login(String username, String password) throws ServiceException;

    SysUserToken generateToken(SysUser user) throws ServiceException;

    SysUserToken getToken(String token);
}
