package com.goldbao.bankroll.tests.service;

import java.io.IOException;
import java.util.Date;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.enums.EnumDr;
import com.goldbao.bankroll.model.enums.EnumUserStatus;
import com.goldbao.bankroll.model.enums.EnumValidateCodePurpose;
import com.goldbao.bankroll.model.enums.EnumVerified;
import com.goldbao.bankroll.model.user.PhoneValidateCode;
import com.goldbao.bankroll.model.user.User;
import com.goldbao.bankroll.model.user.UserApplyReal;
import com.goldbao.bankroll.service.user.UserService;
import com.goldbao.bankroll.vo.LoginResult;
import com.goldbao.utils.CommonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:bankroll-api-service.xml" })
public class UserServiceTest {

	Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

	@Autowired
	private UserService userService;

	@Test
	public void testRegister() {
		User user = new User();
		try {
			user.setLastLoginIp("111");
			user.setLastLoginTime(new Date());
			user.setMobilephone("123234234");
			user.setPassword("1111111");
			user.setUsername("aaaa2");
			user.setUserStatus(EnumUserStatus.NORMAL);
			user.setDr(EnumDr.NORMAL);
//			user.setRegisterTime(new Date());
			userService.register(user);
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
	}

	@Test
	public void testLogin() {
		try {
			User user = userService.login("aaaa2", "1111111");
			Assert.assertNotNull(user);
			LoginResult result = new LoginResult();
			BeanUtils.copyProperties(user, result);
			result.setStatus(user.getUserStatus().getStatus());
			result.setToken(userService.generateToken(user).getToken());
			logger.debug(new ObjectMapper().writeValueAsString(result));
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGenerateToken() {
		String token = CommonUtil.randomToken();
		System.out.println(token);
		Assert.assertEquals(token.length(), 32);
	}

	@Test
	public void testGetPhoneValidateCode() throws IOException {
		String phone = "13916297656";
		try {
			PhoneValidateCode validateCode  = userService.generatePhoneValidateCode(phone, EnumValidateCodePurpose.REGISTER);
			logger.debug(new ObjectMapper().writeValueAsString(validateCode));
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
	}

	@Test
	public void testCheckValidateCode() {
		String phone = "13916297656";

		PhoneValidateCode code = userService.getLastValidateCode(phone, EnumValidateCodePurpose.REGISTER);
		userService.updateValidateCode(code.getId());
	}

	public void testUserToken() {
//		userService.getUserToken();
	}

	// 添加一条实名制申请
	@Test
	public void testAddApplyUserReal() {
		try {
			User user = userService.login("aaaa2", "1111111");
			String cardIdPhoto1 = "12";
			String cardIdPhoto2 = "1";
			String cardIdPhoto3 = "1";
			String realName = "1";

			String cardId = "1";
			UserApplyReal applyReal = userService.getUserApplyReal(user.getId());
			if (applyReal == null) {
				boolean flag = userService.addApplyUserReal(user, realName, cardId, cardIdPhoto1, cardIdPhoto2, cardIdPhoto3);
			} else {
				applyReal.setCardIdPhoto1(cardIdPhoto1);
				applyReal.setCardIdPhoto2(cardIdPhoto2);
				applyReal.setCardIdPhoto3(cardIdPhoto3);
				applyReal.setStatus(EnumVerified.NOT_VERIFIED);
				applyReal.setRealName(realName);
				applyReal.setCardId(cardId);
				userService.updateApplyUserReal(applyReal);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}

	}
}
