package com.goldbao.bankroll.service.user.impl;

import com.goldbao.bankroll.dao.user.PhoneValidateCodeDao;
import com.goldbao.bankroll.dao.user.UserFundDao;
import com.goldbao.bankroll.dao.user.UserFundLogDao;
import com.goldbao.bankroll.model.PageableList;
import com.goldbao.bankroll.model.enums.EnumTradeType;
import com.goldbao.bankroll.model.enums.EnumVerified;
import com.goldbao.bankroll.model.user.*;
import com.goldbao.bankroll.model.enums.EnumUserStatus;
import com.goldbao.bankroll.model.enums.EnumValidateCodePurpose;
import com.goldbao.utils.CommonUtil;
import com.goldbao.utils.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;

import com.goldbao.bankroll.dao.user.UserDao;
import com.goldbao.bankroll.exception.EnumServiceMessage;
import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.service.user.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    private PhoneValidateCodeDao phoneValidateCodeDao;
    private UserFundDao userFundDao;
    private UserFundLogDao userFundLogDao;

    @Override
    public User login(String username, String password) throws ServiceException {
        User user = userDao.getUserByNameOrPhone(username);
        if (user == null)
        	throw new ServiceException(EnumServiceMessage.USER_NOT_EXIST);
        
        String salt = user.getSalt();
        String pwd = EncryptUtil.md5(password + salt);
        
        if (!user.getPassword().equalsIgnoreCase(pwd)) {
        	throw new ServiceException(EnumServiceMessage.USER_ERROR_PASSWORD);
        }
        userDao.updateUserLoginTime(user);
        
        return user;
    }

    @Override
    public User register(User user) throws ServiceException {
    	Long num = userDao.getUserNumByName(user.getUsername());
        if (num > 0)
        	throw new ServiceException(EnumServiceMessage.USER_REGISTER_USERNAME_EXIST);
        String password = user.getPassword();
        String salt = CommonUtil.randomValidateCode();
        String pwd = EncryptUtil.md5(password + salt);
        user.setPassword(pwd);
        user.setUserStatus(EnumUserStatus.NORMAL);
        user.setSalt(salt);
        user.setVerifiedCard(EnumVerified.NOT_VERIFIED);
        user.setVerifiedEmail(EnumVerified.NOT_VERIFIED);
        user.setVerifiedMobilephone(EnumVerified.VERIFIED); // 手机号默认就认证过
        User registerUser = userDao.addUser(user);
        UserFund userFund = new UserFund();
        userFund.setUser(registerUser);
        userFundDao.addUserFund(userFund);

        return registerUser;
    }

	@Override
	public UserToken generateToken(User user) {

        return userDao.addUserToken(user);
	}

    @Override
    public PhoneValidateCode generatePhoneValidateCode(String phone, EnumValidateCodePurpose purpose) throws ServiceException {

        PhoneValidateCode validateCode = phoneValidateCodeDao.getLastValidateCodeByPhone(phone, purpose);
        if (validateCode != null) {
            // TODO 校验是否可以添加新的验证码
        }
        PhoneValidateCode validateCode1 = new PhoneValidateCode();
        validateCode1.setCode(CommonUtil.randomValidateCode());
        validateCode1.setPhone(phone);
        validateCode1.setPurpose(purpose);

        validateCode1 = phoneValidateCodeDao.addValidateCode(validateCode1);

        return validateCode1;
    }

    @Override
    public PhoneValidateCode getLastValidateCode(String phone, EnumValidateCodePurpose purpose) {
        return phoneValidateCodeDao.getLastValidateCodeByPhone(phone, purpose);
    }

    @Override
    public boolean updateValidateCode(Long id) {
        phoneValidateCodeDao.updateToUsed(id);
        return false;
    }

    @Override
    public boolean checkUserExist(String username) {
        Long num =  userDao.getUserNumByName(username);
        return num > 0;
    }

    @Override
    public boolean checkUserPhoneExist(String phone) {
        Long num =  userDao.getUserNumByPhone(phone);
        return num > 0;
    }

    @Override
    public UserToken getUserToken(String token) {
        return userDao.getUserTokenByToken(token);
    }

    @Override
    public UserFund getUserFundByUserId(Long userId) {
        return userFundDao.getUserFundByUserId(userId);
    }

    @Override
    public PageableList<UserFundLog> getUserFundLogs(Long userId, EnumTradeType tradeType, int index, int size) {
        return userFundLogDao.getUserFunds(userId, tradeType, index, size);
    }

    @Override
    public boolean addApplyUserReal(User user, String realName, String cardId, String cardIdPhoto1, String cardIdPhoto2, String cardIdPhoto3) throws ServiceException {
        UserApplyReal applyReal = new UserApplyReal();
        applyReal.setUser(user);
        applyReal.setCardIdPhoto1(cardIdPhoto1);
        applyReal.setCardIdPhoto2(cardIdPhoto2);
        applyReal.setCardIdPhoto3(cardIdPhoto3);
        applyReal.setStatus(EnumVerified.NOT_VERIFIED);
        applyReal.setRealName(realName);
        applyReal.setCardId(cardId);

        return userDao.addApplyUserReal(applyReal);
    }

    @Override
    public UserApplyReal getUserApplyReal(Long id) {
        return userDao.getUserApplyReal(id);
    }

    @Override
    public boolean updateApplyUserReal(UserApplyReal applyReal) {
        return userDao.updateApplyUserReal(applyReal);
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setPhoneValidateCodeDao(PhoneValidateCodeDao phoneValidateCodeDao) {
        this.phoneValidateCodeDao = phoneValidateCodeDao;
    }
    @Autowired
    public void setUserFundDao(UserFundDao userFundDao) {
        this.userFundDao = userFundDao;
    }
    @Autowired
    public void setUserFundLogDao(UserFundLogDao userFundLogDao) {
        this.userFundLogDao = userFundLogDao;
    }
}
