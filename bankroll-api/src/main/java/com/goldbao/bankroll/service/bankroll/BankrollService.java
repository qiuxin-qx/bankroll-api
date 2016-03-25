package com.goldbao.bankroll.service.bankroll;

import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.PageableList;
import com.goldbao.bankroll.model.bankroll.*;
import com.goldbao.bankroll.model.enums.EnumBankrollApplyStatus;
import com.goldbao.bankroll.model.enums.EnumBankrollRecordStatus;
import com.goldbao.bankroll.model.enums.EnumCycleUnit;

/**
 * 配资操作
 * @author shuyu.fang
 */
public interface BankrollService {

    /**
     * 添加一条申请配资记录
     * @param apply
     * @return
     */
    Long addApplyBankroll(BankrollApply apply) throws ServiceException;

    /**
     * 获取申请配资列表
     * @param applicantId
     * @param unit
     * @param status
     * @return
     */
    PageableList<BankrollApply> getApplyList(Long applicantId, EnumCycleUnit unit, EnumBankrollApplyStatus status, int index, int size);

    /**
     * 获取申请配资列表
     * @return
     */
    PageableList<BankrollApply> getApplyList(int index, int size);

    BankrollApply getApplyById(long applyId);

    PageableList<BankrollRecord> getBankrollRecordList(EnumCycleUnit munit, EnumBankrollRecordStatus mStatus, Long applicantId, int index, int size);

    /**
     * 获取申请列表（原生sql实现）
     * @param applicantId
     * @param unit
     * @param status
     * @param fundStat  0(余额不足，不能进入审核),1（审核中，可以审核）
     * @param index
     * @param size
     * @return
     */
    PageableList<BankrollApplyDTO> getApplyDTOList(Long applicantId, EnumCycleUnit unit, EnumBankrollApplyStatus status, int fundStat, int index, int size);

    BankrollRecord getBankrollRecordById(long recordId);

	BankrollHomsInfo getBankrollHomsInfo(long recordId);

    /**
     * 配资申请统计
     * @return
     */
    BankrollApplyStatDTO getBankrollApplyStat();

}
