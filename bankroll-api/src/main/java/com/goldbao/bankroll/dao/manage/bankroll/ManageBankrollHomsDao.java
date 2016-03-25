package com.goldbao.bankroll.dao.manage.bankroll;

import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.bankroll.BankrollHomsInfo;
import com.goldbao.bankroll.model.bankroll.BankrollHomsInfoLog;

import java.util.List;

/**
 * 配资homs相关操作
 * @author shuyu.fang
 */
public interface ManageBankrollHomsDao {
    /**
     * 根据recordId获取其homs相关信息
     * @param recordId
     * @return
     */
    BankrollHomsInfo getBankrollHomsInfo(Long recordId);

    Long addBankrollHomsInfo(BankrollHomsInfo homsInfo) throws ServiceException;

    boolean updateBankrollHomsInfo(BankrollHomsInfo homsInfo);

    boolean addBankrollHomsInfoLog(BankrollHomsInfoLog homsInfoLog) throws ServiceException;

    List<BankrollHomsInfoLog> getBankrollHomsInfoLog(long recordId);
}
