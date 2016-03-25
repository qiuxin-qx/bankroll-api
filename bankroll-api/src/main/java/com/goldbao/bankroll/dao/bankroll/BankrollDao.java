package com.goldbao.bankroll.dao.bankroll;

import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.PageableList;
import com.goldbao.bankroll.model.bankroll.BankrollApply;
import com.goldbao.bankroll.model.bankroll.BankrollApplyDTO;
import com.goldbao.bankroll.model.bankroll.BankrollApplyStatDTO;
import com.goldbao.bankroll.model.bankroll.BankrollRecord;
import com.goldbao.bankroll.model.enums.EnumBankrollApplyStatus;
import com.goldbao.bankroll.model.enums.EnumCycleUnit;

import java.util.Map;

/**
 * @author shuyu.fang
 */
public interface BankrollDao {

    Long addApplyBankroll(BankrollApply apply) throws ServiceException;

    /**
     *
     * @param options
     * @param index
     * @param size
     * @return
     * @see com.goldbao.bankroll.service.bankroll.impl.BankrollServiceImpl
     */
    PageableList<BankrollApply> getApplyList(Map<String, Object> options, int index, int size);

    BankrollApply getApplyById(long applyId);

    PageableList<BankrollRecord> getBankrollRecordList(Map<String, Object> options, int index, int size);

    BankrollRecord getBankrollRecordById(long recordId);

    PageableList<BankrollApplyDTO> getApplyDTOList(Long applicantId, EnumCycleUnit unit, EnumBankrollApplyStatus status, int fundStat, int index, int size);

    BankrollApplyStatDTO getBankrollApplyStat();


}
