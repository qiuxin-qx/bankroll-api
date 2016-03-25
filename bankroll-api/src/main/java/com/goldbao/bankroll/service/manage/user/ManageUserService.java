package com.goldbao.bankroll.service.manage.user;

import com.goldbao.bankroll.model.PageableList;
import com.goldbao.bankroll.model.enums.EnumVerified;
import com.goldbao.bankroll.model.user.User;
import com.goldbao.bankroll.model.user.UserApplyReal;

/**
 * @author shuyu.fang
 */
public interface ManageUserService {

    User getUserById(Long id);
    PageableList<User> getUserList(String username, int index, int size);

    PageableList<UserApplyReal> getApplyUserRealList(EnumVerified status, int index, int size);

    UserApplyReal getApplyUserRealById(long id);

    boolean updateUserApplyRealToPass(UserApplyReal real);
}
