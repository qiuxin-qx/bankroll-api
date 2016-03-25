package com.goldbao.bankroll.dao.manage.user;

import com.goldbao.bankroll.model.PageableList;
import com.goldbao.bankroll.model.enums.EnumVerified;
import com.goldbao.bankroll.model.user.User;
import com.goldbao.bankroll.model.user.UserApplyReal;

import java.util.Map;

/**
 * @author shuyu.fang
 */
public interface ManageUserDao {
    PageableList<User> getUserList(Map<String, Object> options, int index, int size);

    User getUserById(Long id);

    PageableList<UserApplyReal> getApplyUserRealList(EnumVerified status, int index, int size);

    UserApplyReal getApplyUserRealById(long id);

    boolean updateUser(User user);
}
