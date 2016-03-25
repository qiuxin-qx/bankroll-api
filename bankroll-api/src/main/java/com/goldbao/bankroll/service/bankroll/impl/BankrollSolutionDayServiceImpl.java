package com.goldbao.bankroll.service.bankroll.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.goldbao.bankroll.dao.bankroll.BankrollSolutionDayDao;
import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.bankroll.BankrollRuleDay;
import com.goldbao.bankroll.model.bankroll.BankrollSolutionDay;
import com.goldbao.bankroll.model.enums.EnumDr;
import com.goldbao.bankroll.service.bankroll.BankrollSolutionDayService;

/**
 * @author shuyu.fang
 */
public class BankrollSolutionDayServiceImpl implements BankrollSolutionDayService {

    private BankrollSolutionDayDao bankrollSolutionDayDao;

    @Override
    public BankrollSolutionDay addSolution(BankrollSolutionDay solution) {
        solution.setDr(EnumDr.NORMAL);
        return bankrollSolutionDayDao.addSolution(solution);
    }

    @Override
    public BankrollSolutionDay getSolutionLazyById(Long solutionId) {
        return bankrollSolutionDayDao.getSolutionById(solutionId);
    }

    @Override
    public BankrollSolutionDay getSolutionById(Long solutionId) {
        BankrollSolutionDay solution = bankrollSolutionDayDao.getSolutionById(solutionId);
        List<BankrollRuleDay> rules = bankrollSolutionDayDao.getRulesBySolutionId(solutionId);
        solution.setRules(rules);
        return solution;
    }

    @Override
    public BankrollRuleDay addRule(BankrollRuleDay rule) {
        rule.setDr(EnumDr.NORMAL);
        return bankrollSolutionDayDao.addRule(rule);
    }

    @Override
    public BankrollRuleDay getRule(BigDecimal money, Integer lever, Integer days) throws ServiceException {
        // 根据风险保证金和杠杆计算配资金额
//        BigDecimal principal = MathUtil.format(MathUtil.divide(money, lever));

        BankrollSolutionDay solution = bankrollSolutionDayDao.getSolution(money, lever);
        if (solution == null)
            throw new ServiceException("没有对应的配资方案");

        BankrollRuleDay rule =  bankrollSolutionDayDao.getRule(solution.getId(),money, lever, days);
        if (rule == null)
            throw new ServiceException("没有对应的配资规则");
        return rule;
    }

    @Autowired
    public void setBankrollSolutionDayDao(BankrollSolutionDayDao bankrollSolutionDayDao) {
        this.bankrollSolutionDayDao = bankrollSolutionDayDao;
    }
}
