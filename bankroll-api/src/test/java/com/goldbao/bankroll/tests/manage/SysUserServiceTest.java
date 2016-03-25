package com.goldbao.bankroll.tests.manage;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.manage.sysuser.SysUser;
import com.goldbao.bankroll.model.manage.sysuser.SysUserToken;
import com.goldbao.bankroll.service.manage.sysuser.SysUserService;

/**
 * 后台账户管理
 * @author shuyu.fang
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:bankroll-api-service.xml" })
public class SysUserServiceTest {

    private SysUserService sysUserService;

    @Test
    public void testAddSysUser() {
        SysUser user = new SysUser();
        user.setUsername("aaa");
        user.setPassword("111111");
        user.setRealname("小明");

        try {
            sysUserService.addSysUser(user);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSysLogin() {
        String username = "aaa";
        String password = "111111";
        try {
            SysUser user = sysUserService.login(username, password);

            SysUserToken token = sysUserService.generateToken(user);

        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetSysUserToken() {
        String token = "32d16d146ea45596dcd37a1d071f1553";
        SysUserToken userToken = sysUserService.getToken(token);
        Assert.assertNotNull(userToken);
        Assert.assertNotNull(userToken.getUser());
    }

    @Autowired
    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }
}
