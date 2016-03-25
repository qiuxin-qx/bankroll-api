package com.goldbao.bankroll.dao.bankroll.impl;

import java.math.BigDecimal;
import java.util.List;

import com.goldbao.bankroll.dao.GenericDaoSupport;
import com.goldbao.bankroll.dao.bankroll.BankrollSolutionDayDao;
import com.goldbao.bankroll.model.bankroll.BankrollRuleDay;
import com.goldbao.bankroll.model.bankroll.BankrollSolutionDay;
import com.goldbao.bankroll.model.enums.EnumDr;

/**
 * @author shuyu.fang
 */
public class BankrollSolutionDayDaoImpl extends GenericDaoSupport<BankrollSolutionDay> implements BankrollSolutionDayDao {
    @Override
    public BankrollSolutionDay addSolution(BankrollSolutionDay solution) {
        this.getHibernateTemplate().save(solution);
        return solution;
    }

    @Override
    public BankrollSolutionDay getSolutionById(Long solutionId) {
        return this.getHibernateTemplate().get(BankrollSolutionDay.class, solutionId);
    }

    @Override
    public BankrollRuleDay addRule(BankrollRuleDay rule) {
        this.getHibernateTemplate().save(rule);
        return rule;
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<BankrollRuleDay> getRulesBySolutionId(Long solutionId) {
        String hql = "from BankrollRuleDay r where r.dr=? and r.solution.id=?";
        List<BankrollRuleDay> ruleList = this.getHibernateTemplate().find(hql, EnumDr.NORMAL, solutionId);
//        Set<BankrollRuleDay> rules = new HashSet<BankrollRuleDay>(ruleList);
        return ruleList;
    }

	@Override
	public BankrollRuleDay getRule(Long solutionId, BigDecimal money, Integer lever, Integer days) {
		String hql = "from BankrollRuleDay r where r.dr=? and r.minUseDays<=? and r.maxUseDays>=? and r.maxMoney>=? and r.lever=? and r.solution.id=? order by r.addTime";
		List<BankrollRuleDay> list = this.list(BankrollRuleDay.class, hql, EnumDr.NORMAL, days, days, money, lever, solutionId);
		if (list != null && list.size() > 0) return list.get(0);
		return null;
	}

    @Override
    public BankrollSolutionDay getSolution(BigDecimal money, Integer lever) {
        String hql = "from BankrollSolutionDay r where r.dr=? and r.minLever<=? and r.maxLever>=? and r.minMoney<=? and r.maxMoney>=?";
        List<BankrollSolutionDay> list = this.list(BankrollSolutionDay.class, hql, EnumDr.NORMAL, lever, lever, money, money);
        if (list != null && list.size() > 0) return list.get(0);
        return null;
    }
}
