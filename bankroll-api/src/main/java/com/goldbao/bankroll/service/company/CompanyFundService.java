package com.goldbao.bankroll.service.company;

import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.company.CompanyFund;

/**
 * @author shuyu.fang
 */
public interface CompanyFundService {

    Long addCompanyFund(CompanyFund fund) throws ServiceException;
}
