package com.goldbao.bankroll.tests.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import com.goldbao.bankroll.model.Organize;
import com.goldbao.bankroll.model.PageableList;
import com.goldbao.bankroll.model.bankroll.BankrollApply;
import com.goldbao.bankroll.model.bankroll.BankrollApplyDTO;
import com.goldbao.bankroll.model.bankroll.BankrollInterest;
import com.goldbao.bankroll.model.bankroll.BankrollRule;
import com.goldbao.bankroll.model.bankroll.BankrollRuleDay;
import com.goldbao.bankroll.model.bankroll.BankrollSolution;
import com.goldbao.bankroll.model.enums.EnumBankrollApplyStatus;
import com.goldbao.bankroll.model.enums.EnumCycleUnit;
import com.goldbao.bankroll.model.enums.EnumIsLimitPosition;
import com.goldbao.bankroll.model.user.UserToken;
import com.goldbao.bankroll.service.bankroll.BankrollService;
import com.goldbao.bankroll.service.bankroll.BankrollSolutionDayService;
import com.goldbao.bankroll.service.bankroll.BankrollSolutionService;
import com.goldbao.bankroll.service.organize.OrganizeService;
import com.goldbao.bankroll.service.user.UserService;
import com.goldbao.bankroll.vo.BankrollInterestResult;
import com.goldbao.bankroll.vo.BankrollRuleResult;
import com.goldbao.bankroll.vo.BankrollSolutionResult;
import com.goldbao.utils.MathUtil;

/**
 * 配资方案
 * @author shuyu.fang
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:bankroll-api-service.xml" })
public class BankrollSolutionServiceTest {

    private final static Logger log = LoggerFactory.getLogger(BankrollSolutionServiceTest.class);


    private BankrollSolutionService bankrollSolutionService;

    private OrganizeService organizeService;
    private UserService userService;
    private BankrollService bankrollService;
    private BankrollSolutionDayService bankrollSolutionDayService;

    @Test
    public void testAddSolutionMonth() {
        Organize organize = organizeService.getOrganizeById(1L);
        BankrollSolution solution = new BankrollSolution();
        
        solution.setMinPrincipal(MathUtil.format("3000"));
        solution.setMaxPrincipal(MathUtil.format("20000000"));
        solution.setMinLever(1);
        solution.setMaxLever(6);
        solution.setOrganize(organize);
        solution = bankrollSolutionService.addMonthSolution(solution);
        log.debug("solution" + solution.getId()+" save successful");
    }

    @Test
    public void testAddRulesMonth() {
        Long solutionId = 1L;
        BankrollSolution solution = bankrollSolutionService.getSolutionById(solutionId);

        BankrollRule rule = new BankrollRule();
        rule.setInstantlyTransac(0);
        rule.setLimitPosition(EnumIsLimitPosition.NO_LIMIT);
        rule.setMinMoney(MathUtil.format("3000"));
        rule.setMaxMoney(MathUtil.format("100000"));
        rule.setWarningLine(MathUtil.format("1.08"));
        rule.setOpenLine(MathUtil.format("1.10"));

        rule.setSolution(solution);

        bankrollSolutionService.addRule(rule);

        BankrollRule rule2 = new BankrollRule();
        rule2.setInstantlyTransac(0);
        rule2.setLimitPosition(EnumIsLimitPosition.NO_LIMIT);
        rule2.setMinMoney(MathUtil.format("100000"));
        rule2.setMaxMoney(MathUtil.format("10000000"));
        rule2.setWarningLine(MathUtil.format("1.08"));
        rule2.setOpenLine(MathUtil.format("1.10"));

        rule2.setSolution(solution);

        bankrollSolutionService.addRule(rule2);

        BankrollRule rule3 = new BankrollRule();
        rule3.setInstantlyTransac(0);
        rule3.setLimitPosition(EnumIsLimitPosition.NO_LIMIT);
        rule3.setMinMoney(MathUtil.format("10000000"));
        rule3.setMaxMoney(MathUtil.format("60000000"));
        rule3.setWarningLine(MathUtil.format("1.08"));
        rule3.setOpenLine(MathUtil.format("1.10"));

        rule3.setSolution(solution);

        bankrollSolutionService.addRule(rule3);
    }

    @Test
    public void testAddInterest() {
        Set<BankrollInterest> interests = new HashSet<BankrollInterest>();

        String[] interest1 = {"1.9", "1.8", "1.7", "1.6", "1.5", "1.4",  "1.3"};

        for (int i = 0; i < 7; i++) {
            BankrollInterest interest = new BankrollInterest();
            interest.setMonths(i + 1);
            interest.setInterest(MathUtil.format(interest1[i]));
            interests.add(interest);
        }
        Long ruleId = 1L;
        bankrollSolutionService.addInterests(ruleId, interests);
        ruleId = 2L;
        bankrollSolutionService.addInterests(ruleId, interests);
        ruleId = 3L;
        bankrollSolutionService.addInterests(ruleId, interests);
    }

    @Test
    public void testGetMonthSolution() throws IOException {
        Long solutionId = 1L;

        BankrollSolution solution = bankrollSolutionService.getSolutionById(solutionId);

        BankrollSolutionResult result = new BankrollSolutionResult();

        BeanUtils.copyProperties(solution, result, "rules");

        if (solution.getRules() != null && solution.getRules().size() > 0) {
            Iterator<BankrollRule> rules = solution.getRules().iterator();
            List<BankrollRuleResult> ruleList = new ArrayList<BankrollRuleResult>();
            while (rules.hasNext()) {
                BankrollRule rule = rules.next();
                BankrollRuleResult r = new BankrollRuleResult();
                if (rule.getInterests() != null && rule.getInterests().size() > 0) {
                    Iterator<BankrollInterest> interests = rule.getInterests().iterator();
                    List<BankrollInterestResult> interestList = new ArrayList<BankrollInterestResult>();
                    while (interests.hasNext()) {
                        BankrollInterest i = interests.next();
                        BankrollInterestResult interestResult = new BankrollInterestResult();
                        BeanUtils.copyProperties(i, interestResult);
                        interestList.add(interestResult);
                    }
                    r.setInterests(interestList);
                }
//                r.setLimitPosition(rule.getLimitPosition().toString());
                BeanUtils.copyProperties(rule, r, "interests");
                r.setLimitPosition(rule.getLimitPosition().toString());
                ruleList.add(r);
            }

            result.setRules(ruleList);
        }

        String json = new ObjectMapper().writeValueAsString(result);
        log.debug(json);
    }

    @Test
    public void testGetMonthRule() throws IOException {
        Long ruleId = 1L;

        BankrollRule rule = bankrollSolutionService.getRuleById(ruleId);

        Assert.assertNotNull(rule);
        Assert.assertEquals(1L, rule.getInterests().size());
    }

    @Test
    public void testApplyByMonth() {
        String token = "686c8842e817ec700123247c083ebd8c";
        String principal = "100000";
        Integer lever = 4;
        Integer cycle = 6;

        try {

            BigDecimal zero = MathUtil.ZERO;
            BigDecimal convertPrincipal = MathUtil.format(principal);

            UserToken userToken = userService.getUserToken(token);
            BankrollRule rule =  bankrollSolutionService.getRule(convertPrincipal, lever, cycle);
            BankrollInterest interest = rule.getInterests().get(0);

            // 保证金
            BigDecimal deposit = convertPrincipal;
            // 配资金额
            BigDecimal money = MathUtil.multiply(convertPrincipal, lever);
            // 总金额（保证金+配资金额）
            BigDecimal total = MathUtil.add(deposit, money);
            // 平仓线金额
            BigDecimal openLineMoney = MathUtil.format(MathUtil.multiply(money, rule.getOpenLine()));
            // 警告线金额
            BigDecimal warningLineMoney = MathUtil.format(MathUtil.multiply(money, rule.getWarningLine()));
            //  每期服务费
            BigDecimal realInterest = MathUtil.divide(interest.getInterest(), "100");
            BigDecimal interestMoney = MathUtil.format(MathUtil.multiply(money, realInterest));

            BankrollApply apply = new BankrollApply();
            apply.setApplicant(userToken.getUser());
            apply.setCycle(cycle);
            apply.setCycleUnit(EnumCycleUnit.MONTH);
            apply.setDeposit(deposit);
            apply.setMoney(money);
            apply.setOpenLineMoney(openLineMoney);
            apply.setWarningLineMoney(warningLineMoney);
            apply.setManagementFee(interestMoney);

            apply.setLever(lever);
            apply.setPrepDeposit(zero);

            Long id = bankrollService.addApplyBankroll(apply);


        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testApplyByDay() {

        String token = "686c8842e817ec700123247c083ebd8c";
        String money = "10000";

        BigDecimal convertMoney = MathUtil.format(money);
        Integer lever = 15;
        Integer cycle = 9;
        try {
            BankrollRuleDay rule = bankrollSolutionDayService.getRule(convertMoney, lever, cycle);

            UserToken userToken = userService.getUserToken(token);

            BigDecimal zero = MathUtil.ZERO;
            // 保证金
            BigDecimal deposit = MathUtil.floor(MathUtil.divide(money, lever));
            // 总金额（保证金+配资金额）
            BigDecimal total = MathUtil.add(deposit, money);
            // 平仓线金额
            BigDecimal openLineMoney = MathUtil.format(MathUtil.multiply(money, rule.getOpenLine()));
            // 警告线金额
            BigDecimal warningLineMoney = MathUtil.format(MathUtil.multiply(money, rule.getWarningLine()));
            //  每期服务费
            BigDecimal realInterest = MathUtil.divide(rule.getManageFeeRate(), "100");
            BigDecimal interestMoney = MathUtil.format(MathUtil.multiply(money, realInterest), 0);

            BankrollApply apply = new BankrollApply();
            apply.setApplicant(userToken.getUser());
            apply.setCycle(cycle);
            apply.setCycleUnit(EnumCycleUnit.DAY);
            apply.setDeposit(deposit);
            apply.setMoney(convertMoney);
            apply.setOpenLineMoney(openLineMoney);
            apply.setWarningLineMoney(warningLineMoney);
            apply.setManagementFee(interestMoney);

            apply.setLever(lever);
            apply.setPrepDeposit(zero);

            Long id = bankrollService.addApplyBankroll(apply);

        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetApplyBankroll() {
        Long applicantId = 1L;
        EnumCycleUnit unit = EnumCycleUnit.MONTH;
        EnumBankrollApplyStatus status = EnumBankrollApplyStatus.VERIFYING;
        int index = 1;
        int size = 10;
        PageableList<BankrollApply> applyList = bankrollService.getApplyList(applicantId, unit, status, index, size);
    }

    @Test
    public void testGetApplyListDTO() throws IOException {
        PageableList<BankrollApplyDTO> r = bankrollService.getApplyDTOList(1L, EnumCycleUnit.DAY, EnumBankrollApplyStatus.VERIFYING, 0, 1, 10);
        log.debug(new ObjectMapper().writeValueAsString(r));
    }

    @Autowired
    public void setBankrollSolutionService(BankrollSolutionService bankrollSolutionService) {
        this.bankrollSolutionService = bankrollSolutionService;
    }
    @Autowired
    public void setOrganizeService(OrganizeService organizeService) {
        this.organizeService = organizeService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setBankrollService(BankrollService bankrollService) {
        this.bankrollService = bankrollService;
    }
    @Autowired
    public void setBankrollSolutionDayService(BankrollSolutionDayService bankrollSolutionDayService) {
        this.bankrollSolutionDayService = bankrollSolutionDayService;
    }
}
