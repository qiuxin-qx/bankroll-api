package com.goldbao.bankroll.manage.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.manage.sysuser.SysUser;
import com.goldbao.bankroll.model.manage.sysuser.SysUserToken;
import com.goldbao.bankroll.service.manage.sysuser.SysUserService;
import com.goldbao.bankroll.vo.ModelTemplate;
import com.goldbao.bankroll.vo.manage.SysLoginResult;

/**
 * 后台用户管理
 * @author shuyu.fang
 */
@Controller
@RequestMapping("/admin/user")
public class SysUserController {

    private SysUserService sysUserService;

    @RequestMapping("/login")
    public @ResponseBody ModelTemplate<SysLoginResult> login(String username, String password) {
        ModelTemplate<SysLoginResult> r = null;
        try {
            SysUser user = sysUserService.login(username, password);

            SysUserToken token = sysUserService.generateToken(user);

            SysLoginResult result = new SysLoginResult();
            BeanUtils.copyProperties(user, result);

            result.setStatus(user.getStatus().ordinal());
            result.setToken(token.getToken());
            r = new ModelTemplate<SysLoginResult>(result);
        } catch (ServiceException e) {
            r = new ModelTemplate<SysLoginResult>(e);
        }

        return r;
    }

    @Autowired
    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }
}
