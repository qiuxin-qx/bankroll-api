package com.goldbao.bankroll.tests.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

import com.goldbao.bankroll.model.Organize;
import com.goldbao.bankroll.model.bankroll.BankrollRuleDay;
import com.goldbao.bankroll.model.bankroll.BankrollSolutionDay;
import com.goldbao.bankroll.service.bankroll.BankrollSolutionDayService;
import com.goldbao.bankroll.service.organize.OrganizeService;
import com.goldbao.bankroll.vo.BankrollRuleDayResult;
import com.goldbao.bankroll.vo.BankrollSolutionDayResult;
import com.goldbao.utils.MathUtil;

/**
 * 配资方案 - 按天 测试用例
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:bankroll-api-service.xml" })
public class BankrollSolutionDayServiceTest {

    private final static Logger log = LoggerFactory.getLogger(BankrollSolutionDayServiceTest.class);

    private BankrollSolutionDayService bankrollSolutionDayService;
    private OrganizeService organizeService;

    @Test
    public void testAddSolutionDay() {
    	Long id = 1L;
    	Organize organize = organizeService.getOrganizeById(id);
    	
        BankrollSolutionDay solution = new BankrollSolutionDay();
        solution.setMinLever(5);
        solution.setMaxLever(15);
        solution.setMaxinstantlyTransacMoney(MathUtil.format("5000000"));
        solution.setMaxLimitPositionMoney(MathUtil.format("60000"));
        solution.setMaxMoney(MathUtil.format("60000000"));
        solution.setMinMoney(MathUtil.format("3000"));
        solution.setOrganize(organize);

        solution = bankrollSolutionDayService.addSolution(solution);

        Assert.assertEquals(new Long(1L), solution.getId());
    }

    @Test
    public void testAddRuleDay() {
    	Long solutionId = 1L;
        BankrollSolutionDay solution = bankrollSolutionDayService.getSolutionLazyById(solutionId);

        BankrollRuleDay rule = new BankrollRuleDay();
        rule.setMinUseDays(3);
        rule.setMaxUseDays(30);
        rule.setLever(5);
        rule.setMaxMoney(MathUtil.format("60000000"));
        rule.setManageFeeRate(MathUtil.format("0.35"));
        rule.setWarningLine(MathUtil.format("1.10"));
        rule.setOpenLine(MathUtil.format("1.08"));
        rule.setSolution(solution);

        rule = bankrollSolutionDayService.addRule(rule);

        BankrollRuleDay rule2 = new BankrollRuleDay();
        rule2.setMinUseDays(3);
        rule2.setMaxUseDays(30);
        rule2.setLever(10);
        rule2.setMaxMoney(MathUtil.format("60000000"));
        rule2.setManageFeeRate(MathUtil.format("0.35"));
        rule2.setWarningLine(MathUtil.format("1.10"));
        rule2.setOpenLine(MathUtil.format("1.08"));
        rule2.setSolution(solution);

        rule2 = bankrollSolutionDayService.addRule(rule2);

        BankrollRuleDay rule3 = new BankrollRuleDay();
        rule3.setMinUseDays(3);
        rule3.setMaxUseDays(30);
        rule3.setLever(15);
        rule3.setMaxMoney(MathUtil.format("60000000"));
        rule3.setManageFeeRate(MathUtil.format("0.35"));
        rule3.setWarningLine(MathUtil.format("1.10"));
        rule3.setOpenLine(MathUtil.format("1.08"));
        rule3.setSolution(solution);

        rule3 = bankrollSolutionDayService.addRule(rule3);
    }

    @Test
    public void testGetSolutionById() throws IOException {
        Long solutionId = 1L;

        BankrollSolutionDay solution = bankrollSolutionDayService.getSolutionById(solutionId);

        Assert.assertNotNull(solution);

        BankrollSolutionDayResult result = new BankrollSolutionDayResult();
        BeanUtils.copyProperties(solution, result);

        Assert.assertNotNull(solution.getRules());
        Assert.assertEquals(3, solution.getRules().size());

        if (solution.getRules() != null && solution.getRules().size() > 0) {
            Iterator<BankrollRuleDay> rules = solution.getRules().iterator();
            List<BankrollRuleDayResult> ruleResults = new ArrayList<BankrollRuleDayResult>();
            while (rules.hasNext()) {
                BankrollRuleDay rule = rules.next();
                BankrollRuleDayResult ruleResult = new BankrollRuleDayResult();
                BeanUtils.copyProperties(rule, ruleResult);
                ruleResults.add(ruleResult);
            }
            result.setRules(ruleResults);
        }
        String json = new ObjectMapper().writeValueAsString(result);
        log.debug(json);

    }

    @Autowired
    public void setBankrollSolutionDayService(BankrollSolutionDayService bankrollSolutionDayService) {
        this.bankrollSolutionDayService = bankrollSolutionDayService;
    }

    @Autowired
	public void setOrganizeService(OrganizeService organizeService) {
		this.organizeService = organizeService;
	}
}
