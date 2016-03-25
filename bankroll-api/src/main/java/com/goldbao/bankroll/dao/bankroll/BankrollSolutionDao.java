package com.goldbao.bankroll.dao.bankroll;

import com.goldbao.bankroll.model.bankroll.BankrollInterest;
import com.goldbao.bankroll.model.bankroll.BankrollRule;
import com.goldbao.bankroll.model.bankroll.BankrollSolution;

import java.math.BigDecimal;
import java.util.List;

/**
 * 配资方案数据库操作
 */
public interface BankrollSolutionDao {

    /**
     * 添加一条方案
     */
    BankrollSolution addMonthSolution(BankrollSolution solution);

    /**
     * 根据Id获取一条方案
     * @param solutionId
     * @return
     */
    BankrollSolution getSolutionById(Long solutionId);


    BankrollRule addRule(BankrollRule rule);

    BankrollRule getRuleById(Long ruleId);

    BankrollInterest addInterest(BankrollInterest interest);

    List<BankrollInterest> getInterestsByRuleId(Long ruleId);

    List<BankrollRule> getRuleBySolutionId(Long solutionId);

    BankrollRule getRule(Long solutionId, BigDecimal money);

    BankrollSolution getSolution(Integer lever, BigDecimal principal);

    BankrollInterest getInterest(Long id, Integer cycle);
    
}
