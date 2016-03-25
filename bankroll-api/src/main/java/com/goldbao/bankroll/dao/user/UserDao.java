package com.goldbao.bankroll.dao.user;

import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.user.User;
import com.goldbao.bankroll.model.user.UserApplyReal;
import com.goldbao.bankroll.model.user.UserToken;

public interface UserDao {

	/**
	 * 根据用户名(或者手机号码)获取用户信息
	 * @throws ServiceException
	 */
    User getUserByNameOrPhone(String username) throws ServiceException;

    /**
     * 添加一个用户
     * @throws ServiceException
     */
    User addUser(User user) throws ServiceException;
    
    /**
     * 添加一个授权
     */
    UserToken addUserToken(User user);
    
    /**
     * 修改一个授权
     */
    UserToken updateUserToken(UserToken token);
    
    /**
     * 根据用户ID获取授权信息
     */
    UserToken getUserTokenByUserId(Long userid);
    
    /**
     * 根据token获取授权信息
     */
    UserToken getUserTokenByToken(String token);

    /**
     * 获取名为username的user数量
     * @param username
     * @return
     */
    Long getUserNumByName(String username);

    Long getUserNumByPhone(String phone);

    boolean addApplyUserReal(UserApplyReal applyReal) throws ServiceException;

    UserApplyReal getUserApplyReal(Long id);

    boolean updateApplyUserReal(UserApplyReal applyReal);

    boolean updateUserLoginTime(User user);
}
