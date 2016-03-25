package com.goldbao.bankroll.dao.company;

import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.company.CompanyFund;
import com.goldbao.bankroll.model.company.CompanyFundLog;
import com.goldbao.bankroll.model.enums.EnumCompanyFundType;

import java.math.BigDecimal;

/**
 * @author shuyu.fang
 */
public interface CompanyFundDao {
    /**
     * 更新公司账户
     * @param fund 账户
     * @param amount   金额
     * @return
     * @throws ServiceException
     */
    boolean updateFund(CompanyFund fund, BigDecimal amount) throws ServiceException;

    /**
     * 根据账户类型获取公司账户信息
     * @param fundType
     * @return
     */
    CompanyFund getFundByType(EnumCompanyFundType fundType);

    /**
     * 添加一条公司账户（需要绑定账户类型，所以不能经常添加）
     * @param fund
     * @return
     * @throws ServiceException
     */
    Long addFund(CompanyFund fund) throws ServiceException;

    /**
     * 添加一条公司账户日志
     * @param companyFundLog
     */
    Long addFundLog(CompanyFundLog companyFundLog) throws ServiceException;
}
