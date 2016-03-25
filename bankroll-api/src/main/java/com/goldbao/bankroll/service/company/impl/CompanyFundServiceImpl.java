package com.goldbao.bankroll.service.company.impl;

import com.goldbao.bankroll.dao.company.CompanyFundDao;
import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.company.CompanyFund;
import com.goldbao.bankroll.service.company.CompanyFundService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author shuyu.fang
 */
public class CompanyFundServiceImpl implements CompanyFundService {

    private CompanyFundDao companyFundDao;

    @Override
    public Long addCompanyFund(CompanyFund fund) throws ServiceException {

        return companyFundDao.addFund(fund);
    }

    @Autowired
    public void setCompanyFundDao(CompanyFundDao companyFundDao) {
        this.companyFundDao = companyFundDao;
    }
}
