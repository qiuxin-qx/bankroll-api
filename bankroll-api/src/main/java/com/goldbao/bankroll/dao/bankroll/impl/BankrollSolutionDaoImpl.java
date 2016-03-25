package com.goldbao.bankroll.dao.bankroll.impl;

import java.math.BigDecimal;
import java.util.List;

import com.goldbao.bankroll.dao.GenericDaoSupport;
import com.goldbao.bankroll.dao.bankroll.BankrollSolutionDao;
import com.goldbao.bankroll.model.bankroll.BankrollInterest;
import com.goldbao.bankroll.model.bankroll.BankrollRule;
import com.goldbao.bankroll.model.bankroll.BankrollSolution;
import com.goldbao.bankroll.model.enums.EnumDr;

public class BankrollSolutionDaoImpl extends GenericDaoSupport<BankrollSolution> implements BankrollSolutionDao {

    @Override
    public BankrollSolution addMonthSolution(BankrollSolution solution) {
        this.getHibernateTemplate().save(solution);
        return solution;
    }

    @Override
    public BankrollSolution getSolutionById(Long solutionId) {
        BankrollSolution solution = this.getHibernateTemplate().get(BankrollSolution.class, solutionId);
        return solution;
    }

    @Override
    public BankrollRule addRule(BankrollRule rule) {
        this.getHibernateTemplate().save(rule);
        return rule;
    }

    @Override
    public BankrollRule getRuleById(Long ruleId) {
        return this.getHibernateTemplate().get(BankrollRule.class, ruleId);
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<BankrollInterest> getInterestsByRuleId(Long ruleId) {
        List<BankrollInterest> interestList = this.getHibernateTemplate().find("from BankrollInterest i where i.rule.id=? order by i.months asc", ruleId);
//        Set<BankrollInterest> interests = new HashSet<BankrollInterest>(interestList);
        return interestList;
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<BankrollRule> getRuleBySolutionId(Long solutionId) {
        String hql = "from BankrollRule as rule where rule.solution.id=? and rule.dr=?"; // and rule.solution.cycleUnit=?
        List<BankrollRule> ruleList =this.getHibernateTemplate().find(hql, solutionId, EnumDr.NORMAL); // ,EnumCycleUnit.MONTH

//        Set<BankrollRule> rules = new HashSet<BankrollRule>(ruleList);

        return ruleList;
    }

    @Override
    public BankrollSolution getSolution(Integer lever, BigDecimal principal) {
        String hql = "from BankrollSolution s where s.dr=? and s.minLever<=? and s.maxLever>=? and s.minPrincipal<=? and s.maxPrincipal>=?";

        List<BankrollSolution> solutions = this.list(BankrollSolution.class, hql, EnumDr.NORMAL, lever, lever, principal, principal);
        if (solutions != null && solutions.size() > 0) return solutions.get(0);
        return null;
    }

    @Override
    public BankrollInterest addInterest(BankrollInterest interest) {
        this.getHibernateTemplate().save(interest);
        return interest;
    }

    @Override
    public BankrollRule getRule(Long solutionId, BigDecimal money) {
        String hql = "from BankrollRule r where r.dr=? and r.minMoney<=? and r.maxMoney>=? and r.solution.id=?";
        List<BankrollRule> list = this.list(BankrollRule.class, hql, EnumDr.NORMAL, money, money, solutionId);
        if (list != null && list.size() > 0) return list.get(0);
        return null;
    }

    @Override
    public BankrollInterest getInterest(Long id, Integer cycle) {
        String hql = "from BankrollInterest r where r.dr=? and r.months=? and r.rule.id=?";
        List<BankrollInterest> list = this.list(BankrollInterest.class, hql, EnumDr.NORMAL, cycle, id);
        if (list != null && list.size() > 0) return list.get(0);
        return null;
    }
}
