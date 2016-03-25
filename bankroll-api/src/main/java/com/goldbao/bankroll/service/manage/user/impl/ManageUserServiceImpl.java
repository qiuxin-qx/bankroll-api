package com.goldbao.bankroll.service.manage.user.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.goldbao.bankroll.dao.manage.user.ManageUserDao;
import com.goldbao.bankroll.model.PageableList;
import com.goldbao.bankroll.model.enums.EnumVerified;
import com.goldbao.bankroll.model.user.User;
import com.goldbao.bankroll.model.user.UserApplyReal;
import com.goldbao.bankroll.service.manage.user.ManageUserService;

/**
 * @author shuyu.fang
 */
public class ManageUserServiceImpl implements ManageUserService {
    private ManageUserDao manageUserDao;

    @Override
    public PageableList<User> getUserList(String username, int index, int size) {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("username", username);

        return manageUserDao.getUserList(options, index, size);
    }

    @Override
    public User getUserById(Long id) {
        return manageUserDao.getUserById(id);
    }

    @Override
    public PageableList<UserApplyReal> getApplyUserRealList(EnumVerified status, int index, int size) {

        return this.manageUserDao.getApplyUserRealList(status, index, size);
    }

    @Override
    public UserApplyReal getApplyUserRealById(long id) {
        return manageUserDao.getApplyUserRealById(id);
    }

    @Override
    public boolean updateUserApplyRealToPass(UserApplyReal real) {
        User user = real.getUser();

        user.setCardId(real.getCardId());
        user.setCardIdPhoto1(real.getCardIdPhoto1());
        user.setCardIdPhoto2(real.getCardIdPhoto2());
        user.setCardIdPhoto3(real.getCardIdPhoto3());
        user.setVerifiedCard(EnumVerified.VERIFIED);
        manageUserDao.updateUser(user);
        return false;
    }

    @Autowired
    public void setManageUserDao(ManageUserDao manageUserDao) {
        this.manageUserDao = manageUserDao;
    }
}
