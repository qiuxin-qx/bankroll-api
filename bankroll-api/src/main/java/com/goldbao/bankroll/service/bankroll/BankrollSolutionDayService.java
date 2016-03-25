package com.goldbao.bankroll.service.bankroll;

import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.bankroll.BankrollRuleDay;
import com.goldbao.bankroll.model.bankroll.BankrollSolutionDay;

import java.math.BigDecimal;

/**
 * 配资方案 - 按天
 */
public interface BankrollSolutionDayService {
    /**
     * 添加一条规则
     * @param solution
     * @return
     */
    BankrollSolutionDay addSolution(BankrollSolutionDay solution);

    /**
     * 根据id获取一条规则（懒加载，不加载对应规则）
     * @param solutionId
     * @return
     */
    BankrollSolutionDay getSolutionLazyById(Long solutionId);
    /**
     * 根据id获取一条规则（全加载，对应规则也一块加载）
     * @param solutionId
     * @return
     */
    BankrollSolutionDay getSolutionById(Long solutionId);

    /**
     * 添加一条规则
     * @param rule
     * @return
     */
    BankrollRuleDay addRule(BankrollRuleDay rule);

    /**
     * 获取一条最佳匹配的按天配资方案
     * @param money 配资金额
     * @param lever 配资杠杆
     * @param days 配资周期（天）
     * @return
     */
    BankrollRuleDay getRule(BigDecimal money, Integer lever, Integer days) throws ServiceException;


}
