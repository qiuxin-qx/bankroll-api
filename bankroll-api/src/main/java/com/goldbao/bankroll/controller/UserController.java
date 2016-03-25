package com.goldbao.bankroll.controller;

import com.goldbao.bankroll.exception.EnumServiceMessage;
import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.enums.EnumValidateCodePurpose;
import com.goldbao.bankroll.model.enums.EnumVerified;
import com.goldbao.bankroll.model.user.PhoneValidateCode;
import com.goldbao.bankroll.model.user.User;
import com.goldbao.bankroll.model.user.UserApplyReal;
import com.goldbao.bankroll.model.user.UserToken;
import com.goldbao.bankroll.service.user.UserService;
import com.goldbao.bankroll.vo.Empty;
import com.goldbao.bankroll.vo.LoginResult;
import com.goldbao.bankroll.vo.ModelTemplate;
import com.goldbao.bankroll.vo.RegisterResult;
import com.goldbao.sms.EnumSmsType;
import com.goldbao.sms.SmsManager;
import com.goldbao.utils.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private SmsManager smsManager;

	// 登录
	@RequestMapping(value = "/login") // , method = RequestMethod.POST
	public @ResponseBody ModelTemplate<LoginResult> login(String username, String password) {
		ModelTemplate<LoginResult> r = null;
		try {
			// 用户名密码规则校验
			if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password)) 
				throw new ServiceException(EnumServiceMessage.USER_LOGIN_PARAMETER_CANT_NULL);
			// 登录
			User user  = userService.login(username, password);
			
			// 封装返回值
			LoginResult result = new LoginResult();
			BeanUtils.copyProperties(user, result);
			result.setStatus(user.getUserStatus().ordinal());
            result.setVerifiedCard(user.getVerifiedCard().ordinal());
            result.setVerifiedEmail(user.getVerifiedEmail().ordinal());
            result.setVerifiedMobilephone(user.getVerifiedMobilephone().ordinal());
			result.setToken(userService.generateToken(user).getToken());

			r = new ModelTemplate<>(result);
		} catch (ServiceException e) {
			r = new ModelTemplate<>(e);
		}
		return r;
	}
	
	// -----------------------------------注册------------------------------------------
	// 获取手机校验码
	@RequestMapping(value = "/getPhoneValidateCode")
	public @ResponseBody ModelTemplate<Empty> getPhoneValidateCode(String phone) {
		ModelTemplate<Empty> r = null;
		try {
			PhoneValidateCode validateCode = userService.generatePhoneValidateCode(phone, EnumValidateCodePurpose.REGISTER);
			smsManager.send(phone, validateCode.getCode(), EnumSmsType.PHONE_VALIDATE_CODE);
			r = new ModelTemplate<Empty>(Empty.empty());
		} catch (ServiceException e) {
			r = new ModelTemplate<Empty>(e);
		}
		return r;
	}
	
	// 校验手机校验码
	@RequestMapping(value = "/validatePhoneCode")
	public @ResponseBody ModelTemplate<Empty> validatePhoneCode(String phone, String validateCode) {
		ModelTemplate<Empty> r = null;
		try {
			PhoneValidateCode code = userService.getLastValidateCode(phone, EnumValidateCodePurpose.REGISTER);
			if (code == null) {
				throw new ServiceException(EnumServiceMessage.USER_VALIDATE_CODE_NOT_EXIST);
			}
			if (!code.getCode().equals(validateCode)) {
				throw new ServiceException(EnumServiceMessage.USER_VALIDATE_ERROR);
			}

			userService.updateValidateCode(code.getId());

			r = new ModelTemplate<Empty>(Empty.empty());
		} catch (ServiceException ex) {
			r = new ModelTemplate<Empty>(ex);
		} catch (Exception ex) {
			r = new ModelTemplate<Empty>(ex);
		}
		return r;
	}
	// 校验用户名
	@RequestMapping(value = "/checkUserName")
	public @ResponseBody ModelTemplate<Empty> checkUserName(String username) {
		ModelTemplate<Empty> r = null;
		try {
			boolean flag = userService.checkUserExist(username);
			if (flag) {
				throw new ServiceException(EnumServiceMessage.USER_REGISTER_USERNAME_EXIST);
			}
			r = new ModelTemplate<Empty>(Empty.empty());
		} catch (ServiceException ex) {
			r = new ModelTemplate<Empty>(ex);
		} catch (Exception ex) {
			r = new ModelTemplate<Empty>(ex);
		}
		return r;
	}
	// 注册
	@RequestMapping(value = "/register")
	public @ResponseBody ModelTemplate<RegisterResult> register(String username, String phone, String password, String repassword, String ip) {
		ModelTemplate<RegisterResult> r = null;
		try {
			if (!password.equals(repassword)) {
				throw new ServiceException(EnumServiceMessage.USER_REGISTER_PASSWORD_NOT_MATCH);
			}

			boolean flag = userService.checkUserExist(username);
			if (flag) {
				throw new ServiceException(EnumServiceMessage.USER_REGISTER_USERNAME_EXIST);
			}

			flag = userService.checkUserPhoneExist(phone);
			if (flag) {
				throw new ServiceException(EnumServiceMessage.USER_REGISTER_USER_PHONE_EXIST);
			}


			// TODO 注册参数需要做严格校验
			User user = new User();
			user.setUsername(username);
			user.setMobilephone(phone);
			user.setPassword(password);
			user.setRegisterIp(ip);
			User u = userService.register(user);
			RegisterResult result = new RegisterResult();
			BeanUtils.copyProperties(u, result);
			result.setStatus(user.getUserStatus().getStatus());
			result.setToken(userService.generateToken(user).getToken());
			r = new ModelTemplate<RegisterResult>(result);
		} catch (ServiceException ex) {
			r = new ModelTemplate<RegisterResult>(ex);
		} catch (Exception ex) {
			r = new ModelTemplate<RegisterResult>(ex);
		}
		return r;
	}

	@RequestMapping("/applyReal")
	public @ResponseBody ModelTemplate<Empty> applyReal(String token, String realName, String cardId, String cardIdPhoto1, String cardIdPhoto2, String cardIdPhoto3) {
		ModelTemplate<Empty> r = null;

		try {
			UserToken userToken = userService.getUserToken(token);
			if (userToken == null) {
				throw new ServiceException(EnumServiceMessage.USER_TOKEN_NOT_EXIST);
			}

			User user = userToken.getUser();

			UserApplyReal applyReal = userService.getUserApplyReal(user.getId());
			if (applyReal == null) {
				userService.addApplyUserReal(user, realName, cardId, cardIdPhoto1, cardIdPhoto2, cardIdPhoto3);
			} else {
				applyReal.setCardIdPhoto1(cardIdPhoto1);
				applyReal.setCardIdPhoto2(cardIdPhoto2);
				applyReal.setCardIdPhoto3(cardIdPhoto3);
				applyReal.setStatus(EnumVerified.NOT_VERIFIED);
				applyReal.setRealName(realName);
				applyReal.setCardId(cardId);
				userService.updateApplyUserReal(applyReal);
			}

			r = new ModelTemplate<Empty>(Empty.empty());
		} catch (ServiceException ex) {
			r = new ModelTemplate<Empty>(ex);
		} catch (Exception ex) {
			r = new ModelTemplate<Empty>(ex);
		}

		return r;
	}
}
