package com.goldbao.bankroll.dao.company.impl;

import java.math.BigDecimal;
import java.util.Date;

import com.goldbao.bankroll.dao.GenericDaoSupport;
import com.goldbao.bankroll.dao.company.CompanyFundDao;
import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.company.CompanyFund;
import com.goldbao.bankroll.model.company.CompanyFundLog;
import com.goldbao.bankroll.model.enums.EnumCompanyFundType;
import com.goldbao.utils.MathUtil;

/**
 * @author shuyu.fang
 */
public class CompanyFundDaoHibernateImpl extends GenericDaoSupport<CompanyFund> implements CompanyFundDao {

    @Override
    public Long addFund(CompanyFund fund) throws ServiceException {
        fund.setLastAmount(MathUtil.format("0"));
        fund.setLastIncome(MathUtil.format("0"));
        fund.setLastDoTime(new Date());
        return this.save(fund);
    }

    @Override
    public boolean updateFund(CompanyFund fund, BigDecimal amount) throws ServiceException {

        BigDecimal currentAmount = fund.getAmount();
        if (currentAmount == null)
            currentAmount = MathUtil.format("0");

        BigDecimal realAmount = MathUtil.add(currentAmount, amount);
//        realAmount = MathUtil.save(realAmount, fee);

        int c = this.update("update CompanyFund f set f.amount=?," +
            " f.lastAmount=?," +
            " f.lastDoTime=? where fundType=?", realAmount, currentAmount, new Date(), fund.getFundType());

        return c > 0;
    }

    @Override
    public CompanyFund getFundByType(EnumCompanyFundType fundType) {
        CompanyFund fund = this.get("from CompanyFund f where f.fundType=?", fundType);
        return fund;
    }

    @Override
    public Long addFundLog(CompanyFundLog companyFundLog) throws ServiceException {
       return this.save(companyFundLog, CompanyFundLog.class);
    }
}
