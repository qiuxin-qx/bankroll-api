package com.goldbao.bankroll.tests.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.goldbao.bankroll.exception.EnumServiceMessage;
import com.goldbao.bankroll.model.enums.EnumPayType;
import com.goldbao.bankroll.model.user.UserFund;
import com.goldbao.bankroll.model.user.UserToken;
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
import com.goldbao.bankroll.model.PageableList;
import com.goldbao.bankroll.model.company.CompanyFund;
import com.goldbao.bankroll.model.enums.EnumCompanyFundType;
import com.goldbao.bankroll.model.enums.EnumTradeType;
import com.goldbao.bankroll.model.trade.TradeRecord;
import com.goldbao.bankroll.model.user.User;
import com.goldbao.bankroll.service.company.CompanyFundService;
import com.goldbao.bankroll.service.trade.RechargeService;
import com.goldbao.bankroll.service.trade.TradeRecordService;
import com.goldbao.bankroll.service.user.UserService;
import com.goldbao.bankroll.vo.TradeRecordResult;
import com.goldbao.utils.CommonUtil;
import com.goldbao.utils.MathUtil;

/**
 * 充值（提现）接口测试用例
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:bankroll-api-service.xml" })
public class PaymentServiceTest {

    private final static Logger logger = LoggerFactory.getLogger(PaymentServiceTest.class);

    RechargeService rechargeService;
    UserService userService;
    CompanyFundService companyFundService;
    private TradeRecordService tradeRecordService;


    // 添加一条充值记录
    @Test
    public void testAddRecharge() {
        try {
            User user = userService.login("aaaa2", "1111111");
            String orderNo = CommonUtil.randomOrderNo();
            rechargeService.addRecharge(user, orderNo, orderNo, MathUtil.format("200000"), MathUtil.format("0"), "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateRecharge() {

        String orderNo = "753e43c6bbaf40c9828c9001bcec2db6";
        try {
            rechargeService.updateDealRecharge(orderNo, orderNo, EnumPayType.CFCA);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddCompanyFund() {
        CompanyFund companyFund = new CompanyFund();
        companyFund.setAmount(MathUtil.format("0"));
        companyFund.setLastIncome(MathUtil.format("0"));
        companyFund.setLastAmount(MathUtil.format("0"));
        companyFund.setFundCode("TEST_MANAGE_FEE");
        companyFund.setFundName("测试管理费");
        companyFund.setFundType(EnumCompanyFundType.MANAGEMENT_FEE);
        Long id = null;
        try {
            id = companyFundService.addCompanyFund(companyFund);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(new Long(3L), id);
    }

    @Test
    public void testGetTradeRecordByOrderNo() {
        String orderNo = "cd01d185906d4be7b7bc05a879c04423";
        TradeRecord record = tradeRecordService.getTradeRecordByOrderNo(orderNo);
        Assert.assertNotNull(record);

        TradeRecordResult result = new TradeRecordResult();

        BeanUtils.copyProperties(record, result);
        result.setPayee(record.getPayee().getId());
        result.setPayer(record.getPayer().getId());
        result.setDealTime(CommonUtil.formatDate(record.getDealTime()));
        result.setTradeStatus(record.getTradeStatus().ordinal());
        result.setTradeType(record.getTradeType().ordinal());

        logger.debug(CommonUtil.serializeJSON(result));
    }

    @Test
    public void testGetTradeRecordList() {
        int index = 1;
        int size = 10;

        long userid = 1L;
        PageableList<TradeRecord> result =  tradeRecordService
            .getTradeRecordList(index, size, userid, EnumTradeType.RECHARGE);

        if (result.getList().size() > 0) {
            List<TradeRecordResult> r = new ArrayList<TradeRecordResult>();
            for (TradeRecord record: result.getList()) {
                TradeRecordResult result1 = new TradeRecordResult();

                BeanUtils.copyProperties(record, result1);
                result1.setPayee(record.getPayee().getId());
                result1.setPayer(record.getPayer().getId());
                result1.setDealTime(CommonUtil.formatDate(record.getDealTime()));
                result1.setTradeStatus(record.getTradeStatus().ordinal());
                result1.setTradeType(record.getTradeType().ordinal());
                r.add(result1);
            }
            logger.debug(CommonUtil.serializeJSON(r));
        }
    }

    @Test
    public void testAddApplyCash() throws ServiceException {
        String token = "b0d61e1b16c542ec4d6c893b23649453";
        String amount = "10";
        String bankId = "102";
        String accountNumber = "111";
        String branchName = "222";
        String province = null;
        String city = null;
        UserToken userToken = userService.getUserToken(token);
        if (userToken == null) {
            throw new ServiceException(EnumServiceMessage.USER_TOKEN_NOT_EXIST);
        }

        UserFund userFund = userToken.getUser().getUserFund();

        BigDecimal cashAmount = MathUtil.format(amount);

        tradeRecordService.addApplyCash(userFund, cashAmount, bankId, accountNumber, branchName, province, city);

    }

    @Autowired
    public void setRechargeService(RechargeService rechargeService) {
        this.rechargeService = rechargeService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setCompanyFundService(CompanyFundService companyFundService) {
        this.companyFundService = companyFundService;
    }
    @Autowired
    public void setTradeRecordService(TradeRecordService tradeRecordService) {
        this.tradeRecordService = tradeRecordService;
    }
}
