package com.goldbao.bankroll.service.user;

import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.PageableList;
import com.goldbao.bankroll.model.enums.EnumTradeType;
import com.goldbao.bankroll.model.enums.EnumValidateCodePurpose;
import com.goldbao.bankroll.model.user.PhoneValidateCode;
import com.goldbao.bankroll.model.user.User;
import com.goldbao.bankroll.model.user.UserApplyReal;
import com.goldbao.bankroll.model.user.UserFund;
import com.goldbao.bankroll.model.user.UserFundLog;
import com.goldbao.bankroll.model.user.UserToken;

public interface UserService {
    /**
     * 登录
     * @param username
     * @param password
     * @return
     * @throws ServiceException
     */
    User login(String username, String password) throws ServiceException;

    /**
     * 注册
     * @param user
     * @return
     * @throws ServiceException
     */
    User register(User user) throws ServiceException;

    /**
     * 生成接口调用token
     * @param user
     * @return
     */
    UserToken generateToken(User user);

    /**
     * 生成手机校验码
     * @param phone
     * @return
     */
    PhoneValidateCode generatePhoneValidateCode(String phone, EnumValidateCodePurpose purpose) throws ServiceException;

    /**
     * 获取手机校验码
     * @param phone
     * @param purpose
     * @return
     */
    PhoneValidateCode getLastValidateCode(String phone, EnumValidateCodePurpose purpose);

    /**
     * 将某条校验码置为已使用
     * @param id
     * @return
     */
    boolean updateValidateCode(Long id);

    boolean checkUserExist(String username);

    UserToken getUserToken(String token);

    UserFund getUserFundByUserId(Long userId);

    PageableList<UserFundLog> getUserFundLogs(Long userId, EnumTradeType tradeType, int index, int size);

    boolean checkUserPhoneExist(String phone);

    boolean addApplyUserReal(User user, String realName, String cardId, String cardIdPhoto1, String cardIdPhoto2, String cardIdPhoto3) throws ServiceException;

    UserApplyReal getUserApplyReal(Long id);

    boolean updateApplyUserReal(UserApplyReal applyReal);
}
