package com.goldbao.bankroll.service.manage.sysuser.impl;

import com.goldbao.bankroll.dao.manage.sysuser.SysUserDao;
import com.goldbao.bankroll.exception.EnumServiceMessage;
import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.manage.sysuser.SysUser;
import com.goldbao.bankroll.model.manage.sysuser.SysUserToken;
import com.goldbao.bankroll.service.manage.sysuser.SysUserService;
import com.goldbao.utils.CommonUtil;
import com.goldbao.utils.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author shuyu.fang
 */
public class SysUserServiceImpl implements SysUserService {
    private SysUserDao sysUserDao;

    @Override
    public Long addSysUser(SysUser user) throws ServiceException {
        Long count = sysUserDao.getUserCountByName(user.getUsername());
        if (count > 0)
            throw new ServiceException(EnumServiceMessage.SYS_USER_USERNAME_EXIST);

        // 加密
        String password = user.getPassword();
        String salt = CommonUtil.randomValidateCode();
        String pwd = EncryptUtil.md5(password + salt);
        user.setPassword(pwd);
        user.setSalt(salt);

        return sysUserDao.addSysUser(user);
    }

    @Override
    public SysUser login(String username, String password) throws ServiceException {
        SysUser user = sysUserDao.getUserByName(username);
        if (user == null)
            throw new ServiceException(EnumServiceMessage.SYS_USER_NOT_EXIST);

        String salt = user.getSalt();
        String pwd = EncryptUtil.md5(password + salt);

        if (!user.getPassword().equalsIgnoreCase(pwd)) {
            throw new ServiceException(EnumServiceMessage.SYS_USER_ERROR_PASSWORD);
        }

        return user;
    }

    @Override
    public SysUserToken generateToken(SysUser user) throws ServiceException {
        return sysUserDao.addSysUserToken(user);
    }

    @Override
    public SysUserToken getToken(String token) {
        return sysUserDao.getToken(token);
    }

    @Autowired
    public void setSysUserDao(SysUserDao sysUserDao) {
        this.sysUserDao = sysUserDao;
    }
}
