package com.goldbao.bankroll.service.bankroll.impl;

import com.goldbao.bankroll.dao.bankroll.BankrollDao;
import com.goldbao.bankroll.dao.bankroll.BankrollHomsDao;
import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.PageableList;
import com.goldbao.bankroll.model.bankroll.*;
import com.goldbao.bankroll.model.enums.EnumBankrollApplyStatus;
import com.goldbao.bankroll.model.enums.EnumBankrollRecordStatus;
import com.goldbao.bankroll.model.enums.EnumCycleUnit;
import com.goldbao.bankroll.service.bankroll.BankrollService;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shuyu.fang
 */
public class BankrollServiceImpl implements BankrollService {
    private BankrollDao bankrollDao;
	private BankrollHomsDao bankrollHomsDao;

    @Override
    public Long addApplyBankroll(BankrollApply apply) throws ServiceException {
        apply.setStatus(EnumBankrollApplyStatus.VERIFYING);

        Long id = bankrollDao.addApplyBankroll(apply);
        return id;
    }

    @Override
    public PageableList<BankrollApply> getApplyList(Long applicantId, EnumCycleUnit unit, EnumBankrollApplyStatus status, int index, int size) {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("applicantId", applicantId);
        options.put("unit", unit);
        options.put("status", status);
        return bankrollDao.getApplyList(options, index, size);
    }

    @Override
    public BankrollApply getApplyById(long applyId) {
        return bankrollDao.getApplyById(applyId);
    }

    @Override
    public PageableList<BankrollRecord> getBankrollRecordList(EnumCycleUnit munit, EnumBankrollRecordStatus mStatus, Long applicantId, int index, int size) {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("unit", munit);
        options.put("status", mStatus);
        options.put("applicantId", applicantId);
        return bankrollDao.getBankrollRecordList(options, index, size);
    }

    @Override
    public PageableList<BankrollApplyDTO> getApplyDTOList(Long applicantId, EnumCycleUnit unit, EnumBankrollApplyStatus status, int fundStat, int index, int size) {

        return bankrollDao.getApplyDTOList(applicantId, unit, status, fundStat, index, size);
    }

    @Override
    public BankrollRecord getBankrollRecordById(long recordId) {
        return bankrollDao.getBankrollRecordById(recordId);
    }
    
    @Override
	public BankrollHomsInfo getBankrollHomsInfo(long recordId) {
		return bankrollHomsDao.getBankrollHomsInfo(recordId);
	}

    @Override
    public PageableList<BankrollApply> getApplyList(int index, int size) {
        Map<String, Object> options = new HashMap<String, Object>();
        return bankrollDao.getApplyList(options, index, size);
    }

    @Override
    public BankrollApplyStatDTO getBankrollApplyStat() {
        return bankrollDao.getBankrollApplyStat();
    }

    @Autowired
    public void setBankrollDao(BankrollDao bankrollDao) {
        this.bankrollDao = bankrollDao;
    }
    @Autowired
	public void setBankrollHomsDao(BankrollHomsDao bankrollHomsDao) {
		this.bankrollHomsDao = bankrollHomsDao;
	}

	
}
