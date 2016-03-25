package com.goldbao.bankroll.service.bankroll;

import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.bankroll.BankrollInterest;
import com.goldbao.bankroll.model.bankroll.BankrollRule;
import com.goldbao.bankroll.model.bankroll.BankrollSolution;

import java.math.BigDecimal;
import java.util.Set;

/**
 * 配资方案 - 按月
 * @author shuyu.fang
 */
public interface BankrollSolutionService {

    /**
     * 添加一条方案
     * @param solution
     * @return
     */
    BankrollSolution addMonthSolution(BankrollSolution solution);

    /**
     * 根据id获取一条方案
     * @param solutionId
     * @return
     */
    BankrollSolution getSolutionById(Long solutionId);

    /**
     * 添加一条规则
     * @param rule
     * @return
     */
    BankrollRule addRule(BankrollRule rule);

    /**
     * 根据id获取一条规则
     * @param ruleId
     * @return
     */
    BankrollRule getRuleById(Long ruleId);

    /**
     * 添加几条利率计算到规则中
     * @param ruleId
     * @param interests
     * @return
     */
    boolean addInterests(Long ruleId, Set<BankrollInterest> interests);

    BankrollRule getRule(BigDecimal principal, Integer lever, Integer cycle) throws ServiceException;
}
