package com.goldbao.bankroll.service.bankroll.impl;

import com.goldbao.bankroll.dao.bankroll.BankrollSolutionDao;
import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.bankroll.BankrollInterest;
import com.goldbao.bankroll.model.bankroll.BankrollRule;
import com.goldbao.bankroll.model.bankroll.BankrollSolution;
import com.goldbao.bankroll.model.enums.EnumDr;
import com.goldbao.bankroll.service.bankroll.BankrollSolutionService;
import com.goldbao.utils.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class BankrollSolutionServiceImpl implements BankrollSolutionService {


    BankrollSolutionDao bankrollSolutionDao;

    @Override
    public BankrollSolution addMonthSolution(BankrollSolution solution) {
        solution.setDr(EnumDr.NORMAL);
        return bankrollSolutionDao.addMonthSolution(solution);
    }

    @Override
    public BankrollSolution getSolutionById(Long solutionId) {
        BankrollSolution solution =  bankrollSolutionDao.getSolutionById(solutionId);
        List<BankrollRule> rules =  bankrollSolutionDao.getRuleBySolutionId(solutionId);
        for (BankrollRule rule: rules) {
            List<BankrollInterest> interests = bankrollSolutionDao.getInterestsByRuleId(rule.getId());
            rule.setInterests(interests);
        }

        solution.setRules(rules);
        return solution;
    }

    @Override
    public BankrollRule addRule(BankrollRule rule) {
        rule.setDr(EnumDr.NORMAL);
        return bankrollSolutionDao.addRule(rule);
    }

    @Override
    public BankrollRule getRuleById(Long ruleId) {
        BankrollRule rule =  bankrollSolutionDao.getRuleById(ruleId);

        List<BankrollInterest> interests = bankrollSolutionDao.getInterestsByRuleId(ruleId);
        rule.setInterests(interests);

        return rule;
    }

    @Override
    public boolean addInterests(Long ruleId, Set<BankrollInterest> interests) {

        BankrollRule rule = bankrollSolutionDao.getRuleById(ruleId);

        for (BankrollInterest interest: interests) {
            interest.setDr(EnumDr.NORMAL);
            interest.setRule(rule);
            bankrollSolutionDao.addInterest(interest);
        }

        return true;

    }

    @Override
    public BankrollRule getRule(BigDecimal principal, Integer lever, Integer cycle) throws ServiceException {
        // 根据配资金额和杠杆计算本金
        BigDecimal money = MathUtil.multiply(principal, lever);
        // 先获取solution
        BankrollSolution solution = bankrollSolutionDao.getSolution(lever, principal);
        if (solution == null)
            throw new ServiceException("没有对应的配资方案");

        // 获取配资规则
        BankrollRule rule =  bankrollSolutionDao.getRule(solution.getId(), money);
        if (rule == null)
            throw new ServiceException("没有对应的配资规则");

        BankrollInterest interest = bankrollSolutionDao.getInterest(rule.getId(), cycle);
        if (interest == null)
            throw new ServiceException("没有对应的配资利率");

        List<BankrollInterest> interests = new ArrayList<BankrollInterest>();
        interests.add(interest);

        rule.setInterests(interests);
        return rule;
    }

    @Autowired
    public void setBankrollSolutionDao(BankrollSolutionDao bankrollSolutionDao) {
        this.bankrollSolutionDao = bankrollSolutionDao;
    }
}
