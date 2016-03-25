package com.goldbao.bankroll.manage.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldbao.bankroll.exception.EnumServiceMessage;
import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.PageableList;
import com.goldbao.bankroll.model.enums.EnumVerified;
import com.goldbao.bankroll.model.manage.sysuser.SysUserToken;
import com.goldbao.bankroll.model.user.User;
import com.goldbao.bankroll.model.user.UserApplyReal;
import com.goldbao.bankroll.service.manage.sysuser.SysUserService;
import com.goldbao.bankroll.service.manage.user.ManageUserService;
import com.goldbao.bankroll.vo.ModelTemplate;
import com.goldbao.bankroll.vo.Pages;
import com.goldbao.bankroll.vo.manage.UserApplyRealResult;
import com.goldbao.bankroll.vo.manage.UserResult;
import com.goldbao.utils.CommonUtil;

/**
 * 网站用户管理
 * @author shuyu.fang
 */
@Controller
@RequestMapping("/admin/user")
public class ManageUserController {

    private ManageUserService manageUserService;

    private SysUserService sysUserService;

    private final static Logger logger = LoggerFactory.getLogger(ManageUserController.class);

    @RequestMapping("/getUserList")
    public @ResponseBody ModelTemplate<Pages<UserResult>> getUserList(String token, String username, int index, int size) {
        ModelTemplate<Pages<UserResult>> r;
        try {
            SysUserToken userToken = sysUserService.getToken(token);

            if (userToken == null) {
                throw new ServiceException(EnumServiceMessage.SYS_USER_TOKEN_NOT_EXIST);
            }

            PageableList<User> userList = manageUserService.getUserList(username, index, size);

            Pages<UserResult> p = new Pages<UserResult>();

            BeanUtils.copyProperties(userList, p, "list");
            List<UserResult> list = new ArrayList<UserResult>();
            for (User user: userList.getList()) {
                UserResult u = new UserResult();
                BeanUtils.copyProperties(user.getUserFund(), u);
                BeanUtils.copyProperties(user, u);
                u.setUserStatus(user.getUserStatus().ordinal());
                u.setLastLoginTime(CommonUtil.formatDate(user.getLastLoginTime()));
                u.setRegisterTime(CommonUtil.formatDate(user.getAddTime()));
                list.add(u);
            }
            p.setList(list);
            p.setPageCount(userList.getPageCount());
            r = new ModelTemplate<Pages<UserResult>>(p);
        } catch (Exception ex) {
            r = new ModelTemplate<Pages<UserResult>>(ex);
            logger.error(ex.getMessage());
            ex.printStackTrace();
        }
        return r;
    }

    @RequestMapping("/getUserById")
    public @ResponseBody ModelTemplate<UserResult> getUserById(String token, long userId) {
        ModelTemplate<UserResult> r;
        try {
            SysUserToken userToken = sysUserService.getToken(token);

            if (userToken == null) {
                throw new ServiceException(EnumServiceMessage.SYS_USER_TOKEN_NOT_EXIST);
            }

            User user = manageUserService.getUserById(userId);
            UserResult u = new UserResult();
            BeanUtils.copyProperties(user.getUserFund(), u);
            BeanUtils.copyProperties(user, u);
            u.setUserStatus(user.getUserStatus().ordinal());
            u.setLastLoginTime(CommonUtil.formatDate(user.getLastLoginTime()));
            u.setRegisterTime(CommonUtil.formatDate(user.getAddTime()));

            r = new ModelTemplate<UserResult>(u);

        } catch (ServiceException ex) {
            r = new ModelTemplate<UserResult>(ex);
            logger.error(ex.getMessage());
        } catch (Exception ex) {
            r = new ModelTemplate<UserResult>(ex);
            logger.error(ex.getMessage());
        }
        return r;

    }

    @RequestMapping("/getUserRealApplyList")
    public @ResponseBody ModelTemplate<Pages<UserApplyRealResult>> getUserRealApplyList(String token, int status, int index, int size) {
        ModelTemplate<Pages<UserApplyRealResult>> r;
        try {
            SysUserToken userToken = sysUserService.getToken(token);

            if (userToken == null) {
                throw new ServiceException(EnumServiceMessage.SYS_USER_TOKEN_NOT_EXIST);
            }

            EnumVerified mStatus = EnumVerified.parse(status);
            PageableList<UserApplyReal> list = manageUserService.getApplyUserRealList(mStatus, index, size);

            Pages<UserApplyRealResult> p = new Pages<UserApplyRealResult>();

            BeanUtils.copyProperties(list, p, "list");

            List<UserApplyRealResult> l = new ArrayList<UserApplyRealResult>();

            if (list.getList() != null) {
                for (UserApplyReal item: list.getList()) {
                    UserApplyRealResult result = new UserApplyRealResult();
                    BeanUtils.copyProperties(item.getUser(), result);
                    BeanUtils.copyProperties(item, result);
                    result.setStatus(item.getStatus().ordinal());
                    result.setAddTime(CommonUtil.formatDate(item.getAddTime()));
                    l.add(result);
                }
            }
            p.setList(l);
            p.setPageCount(list.getPageCount());
            r = new ModelTemplate<Pages<UserApplyRealResult>>(p);

        } catch (ServiceException ex) {
            r = new ModelTemplate<Pages<UserApplyRealResult>>(ex);
            logger.error(ex.getMessage());
        } catch (Exception ex) {
            r = new ModelTemplate<Pages<UserApplyRealResult>>(ex);
            logger.error(ex.getMessage());
        }
        return r;
    }

    @RequestMapping("/getUserRealApplyById")
    public @ResponseBody ModelTemplate<UserApplyRealResult> getUserRealApplyById(String token, long id) {
        ModelTemplate<UserApplyRealResult> r;
        try {
            SysUserToken userToken = sysUserService.getToken(token);

            if (userToken == null) {
                throw new ServiceException(EnumServiceMessage.SYS_USER_TOKEN_NOT_EXIST);
            }
            UserApplyReal item = manageUserService.getApplyUserRealById(id);

            UserApplyRealResult result = new UserApplyRealResult();
            BeanUtils.copyProperties(item.getUser(), result);
            BeanUtils.copyProperties(item, result);
            result.setStatus(item.getStatus().ordinal());
            result.setAddTime(CommonUtil.formatDate(item.getAddTime()));
            r = new ModelTemplate<UserApplyRealResult>(result);

        } catch (ServiceException ex) {
            r = new ModelTemplate<UserApplyRealResult>(ex);
            logger.error(ex.getMessage());
        } catch (Exception ex) {
            r = new ModelTemplate<UserApplyRealResult>(ex);
            logger.error(ex.getMessage());
        }
        return r;
    }
    @Autowired
    public void setManageUserService(ManageUserService manageUserService) {
        this.manageUserService = manageUserService;
    }
    @Autowired
    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }
}
