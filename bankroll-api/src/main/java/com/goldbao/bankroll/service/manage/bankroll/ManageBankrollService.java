package com.goldbao.bankroll.service.manage.bankroll;

import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.Organize;
import com.goldbao.bankroll.model.PageableList;
import com.goldbao.bankroll.model.bankroll.*;
import com.goldbao.bankroll.model.enums.EnumBankrollApplyStatus;
import com.goldbao.bankroll.model.enums.EnumBankrollRecordStatus;
import com.goldbao.bankroll.model.enums.EnumCycleUnit;
import com.goldbao.bankroll.model.manage.sysuser.SysUser;
import com.goldbao.bankroll.model.user.UserFund;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author shuyu.fang
 */
public interface ManageBankrollService {
    /**
     * 获取申请配资列表
     * @param unit
     * @param status
     * @return
     */
	PageableList<BankrollApply> getApplyList(EnumCycleUnit unit, EnumBankrollApplyStatus status, int index, int size);

    /**
     * 根据主键Id获取配资申请
     * @param id
     * @return
     */
    BankrollApply getApplyById(Long id);

    /**
     *
     * 添加一条配资记录<br/>
     * 1. 添加配资记录（并加日志）<br/>
     * 2. 添加交易记录<br/>
     * 3. 生成还款账单<br/>
     * 4. 生成还款详细信息？<br/>
     * 5. 用户账户信息修改（并加交易日志，通知）<br/>
     * 6. 公司账户修改（并加交易日志）<br/>
     * @param userFund 用户账户信息
     * @param apply    申请信息
     * @param sysUser
     */
    BankrollRecord addBankrollRecord(UserFund userFund, BankrollApply apply, SysUser sysUser, Organize org) throws ServiceException;

    /**
     * 获取一条配资记录
     * @param recordId
     * @return
     */
    BankrollRecord getBankrollRecordById(long recordId);

    PageableList<BankrollRecord> getBankrollRecordList(EnumCycleUnit unit, EnumBankrollRecordStatus status, int index, int size);

    /**
     * 添加一条分配homs帐号记录
     * @param recordId
     * @param homsOperator
     * @param homsPwd
     * @param startDate
     * @param endDate
     * @param updater
     * @return
     * @throws ServiceException
     */
    boolean addAllocationHoms(Long recordId, String homsOperator, String homsPwd, Date startDate, Date endDate, SysUser updater) throws ServiceException;

    /**
     * 获取将要到期，尚未还款的账单（按月）
     * @param startTime
     * @return
     */
    List<BankrollBill> getNotRepaymentBillListByMonth(Date startTime);

    /**
     * 按月还款
     * @param bill
     * @return
     */
    boolean updateBillRepaymentOfMonth(BankrollBill bill);

    PageableList<BankrollApplyDTO> getApplyDTOList(EnumCycleUnit eUnit, EnumBankrollApplyStatus eStatus, int fundStat, int index, int size);

    /**
     * 按照日期获取将要到期，尚未结束的配资记录（按天）
     * @param date
     * @return
     */
    List<BankrollRecord> getBankrollRecordListByDate(Date date);

    /**
     * 按天还款
     * @param record
     * @return
     */
    boolean updateBillRepaymentOfDay(BankrollRecord record);

    /**
     * 根据配资状态获取配资列表
     * @param status
     * @return
     */
    List<BankrollRecord> getBankrollRecordBySatus(EnumBankrollRecordStatus status, Long orgId);

    /**
     * 根据分配的homs账号获取尚在进行中的配资记录
     * @param operatorNo
     * @return
     */
    BankrollRecord getBankrollRecordByOperatorNo(String operatorNo);

    /**
     * 根据recordId获取其homs相关信息
     * @param recordId
     * @return
     */
    BankrollHomsInfo getBankrollHomsInfo(Long recordId);

    boolean addBankrollHomsInfo(BankrollHomsInfo homsInfo) throws ServiceException;

    boolean updateBankrollHomsInfo(BankrollHomsInfo homsInfo);

    boolean addBankrollHomsInfoLog(BankrollHomsInfoLog homsInfoLog) throws ServiceException;

    List<BankrollHomsInfoLog> getBankrollHomsInfoLog(long recordId);

    /**
     * 将一笔配资订单标记为完成
     * @param recordId
     * @param updater
     * @return
     * @throws ServiceException
     */
    boolean updateBankrollRecordToFinish(long recordId, SysUser updater) throws ServiceException;

    /**
     * 将一笔配资订单标记为 结算完成（完结）
     * @param recordId
     * @param user
     * @return
     * @throws ServiceException
     */
    boolean updateBankrollRecordToRepayment(Long recordId, BigDecimal repaymentMoney, SysUser user) throws ServiceException;
}
