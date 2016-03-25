package com.goldbao.bankroll.dao.manage.bankroll;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.PageableList;
import com.goldbao.bankroll.model.bankroll.*;
import com.goldbao.bankroll.model.enums.EnumBankrollApplyStatus;
import com.goldbao.bankroll.model.enums.EnumBankrollRecordStatus;
import com.goldbao.bankroll.model.enums.EnumCycleUnit;

/**
 * @author shuyu.fang
 */
public interface ManageBankrollDao {

    /**
     * 分页获取
     * @param options
     * @param index
     * @param size
     * @return
     */
	PageableList<BankrollApply> getApplyList(Map<String, Object> options, int index, int size);

    BankrollApply getApplyById(Long id);

    /**
     * 添加配资订单记录
     * @param bankrollRecord
     * @return
     */
    Long addBankrollRecord(BankrollRecord bankrollRecord) throws ServiceException;

    void addBankrollRecordLog(BankrollRecordLog recordLog) throws ServiceException;

    boolean updateBankrollApplyToDeal(BankrollApply apply);

    BankrollRecord getBankrollRecordById(long recordId);

    PageableList<BankrollRecord> getBankrollRecordList(Map<String, Object> options, int index, int size);

    /**
     * 分配homs帐号
     * @param record
     * @return
     */
    boolean updateBankrollRecordToAllocateHoms(BankrollRecord record);

    Long addBankrollBill(BankrollBill bill) throws ServiceException;

    PageableList<BankrollApplyDTO> getApplyDTOList(EnumCycleUnit eUnit, EnumBankrollApplyStatus eStatus, int fundStat, int index, int size);

    List<BankrollRecord> getBankrollRecordListByDate(Date date);

    /**
     * 按天的配资记录续约
     * @param record
     * @return
     */
    boolean updateBankrollRecordToRenewByDay(BankrollRecord record);

    List<BankrollRecord> getBankrollRecordListByStatus(EnumBankrollRecordStatus status, Long orgId);

    BankrollRecord getBankrollRecordByOperatorNo(String operatorNo);

    boolean updateBankrollRecord(BankrollRecord record);
}
