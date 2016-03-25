package com.goldbao.bankroll.dao.bankroll;

import java.math.BigDecimal;
import java.util.List;

import com.goldbao.bankroll.model.bankroll.BankrollRuleDay;
import com.goldbao.bankroll.model.bankroll.BankrollSolutionDay;

/**
 * 配资方案 - 按天
 */
public interface BankrollSolutionDayDao {
    /**
     * 添加一条配资方案 - 按天
     */
    BankrollSolutionDay addSolution(BankrollSolutionDay solution);

    BankrollSolutionDay getSolutionById(Long solutionId);

    BankrollRuleDay addRule(BankrollRuleDay rule);

    List<BankrollRuleDay> getRulesBySolutionId(Long solutionId);

    BankrollRuleDay getRule(Long solutionId, BigDecimal money, Integer lever, Integer days);

    BankrollSolutionDay getSolution(BigDecimal money, Integer lever);
}
