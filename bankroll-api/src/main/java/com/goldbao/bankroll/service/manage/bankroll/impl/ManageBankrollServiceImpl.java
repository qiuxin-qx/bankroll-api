package com.goldbao.bankroll.service.manage.bankroll.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.goldbao.bankroll.dao.company.CompanyFundDao;
import com.goldbao.bankroll.dao.manage.bankroll.ManageBankrollBillDao;
import com.goldbao.bankroll.dao.manage.bankroll.ManageBankrollHomsDao;
import com.goldbao.bankroll.dao.manage.bankroll.ManageTradingDayDao;
import com.goldbao.bankroll.exception.EnumServiceMessage;
import com.goldbao.bankroll.model.bankroll.*;
import com.goldbao.bankroll.model.company.CompanyFund;
import com.goldbao.bankroll.model.company.CompanyFundLog;
import com.goldbao.bankroll.model.enums.*;
import com.goldbao.bankroll.model.user.User;
import com.goldbao.utils.CalendarUtil;
import com.goldbao.utils.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.goldbao.bankroll.dao.manage.bankroll.ManageBankrollDao;
import com.goldbao.bankroll.dao.user.UserFundDao;
import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.Organize;
import com.goldbao.bankroll.model.PageableList;
import com.goldbao.bankroll.model.manage.sysuser.SysUser;
import com.goldbao.bankroll.model.user.UserFund;
import com.goldbao.bankroll.model.user.UserFundLog;
import com.goldbao.bankroll.service.manage.bankroll.ManageBankrollService;
import com.goldbao.utils.MathUtil;

/**
 * @author shuyu.fang
 */
public class ManageBankrollServiceImpl implements ManageBankrollService {
    private ManageBankrollDao manageBankrollDao;
    private ManageBankrollBillDao manageBankrollBillDao;
    private UserFundDao userFundDao;
    private CompanyFundDao companyFundDao;

    private final static Logger logger = LoggerFactory.getLogger(ManageBankrollServiceImpl.class);
    private ManageBankrollHomsDao manageBankrollHomsDao;
    private ManageTradingDayDao manageTradingDayDao;

    @Override
    public PageableList<BankrollApply> getApplyList(EnumCycleUnit unit, EnumBankrollApplyStatus status, int index, int size) {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("unit", unit);
        options.put("status", status);
        return manageBankrollDao.getApplyList(options, index, size);
    }

    @Override
    public BankrollApply getApplyById(Long id) {
        return manageBankrollDao.getApplyById(id);
    }

    @Override
    public BankrollRecord addBankrollRecord(UserFund userFund, BankrollApply apply, SysUser sysUser, Organize org) throws ServiceException {

//        1. 添加配资记录（并加日志）
        BankrollRecord bankrollRecord = new BankrollRecord();
        BeanUtils.copyProperties(apply, bankrollRecord);
        bankrollRecord.setApply(apply);
//        bankrollRecord.setApplicant(userFund.getUser());
//        bankrollRecord.setBankrollNo(apply.getBankrollNo());
        bankrollRecord.setCreator(sysUser);
        bankrollRecord.setUpdateTime(new Date());
        if (org != null) { // 这里不确定是否可以获取到机构信息，接下来分配homs账户还是可以指定org
            bankrollRecord.setOrganize(org);
        }
        bankrollRecord.setStatus(EnumBankrollRecordStatus.NOT_ALLOCATION_HOMS);
        Long recordId = manageBankrollDao.addBankrollRecord(bankrollRecord);

        if (recordId > 0) {
            // 添加配资日志
            BankrollRecordLog recordLog = new BankrollRecordLog();
            // TODO 后期再看用哪个字段来显示创建人
            recordLog.setCreatorName(sysUser.getUsername());
            recordLog.setMessage("审核通过，等待分配homs账户");
            recordLog.setRecord(bankrollRecord);
            manageBankrollDao.addBankrollRecordLog(recordLog);
        }

        // 计算当期付款总金额
//        BigDecimal money = apply.getMoney(); // 配资金额
        BigDecimal deposit = apply.getDeposit();  // 保证金
        BigDecimal managementFee = getCurrentManagementFee(apply); // 当期应收服务费

//        BigDecimal totalMoney = MathUtil.add(money, deposit);
        BigDecimal totalMoney = MathUtil.add(deposit, managementFee); // 当期总付款金额

        BigDecimal userFreeze = userFund.getFreeze();      // 原冻结金额
        BigDecimal userBalance = userFund.getBalance();    // 原余额
//        BigDecimal usePrincipal = userFund.getPrincipal(); // 原本金
//        BigDecimal useBankroll = userFund.getBankroll();   // 原配资金额

        BigDecimal freeze = MathUtil.add(userFreeze, totalMoney);
        BigDecimal balance = MathUtil.subtract(userBalance, totalMoney);

        userFund.setFreeze(freeze);
        userFund.setBalance(balance);
//        2. 用户账户信息修改（并加交易日志，通知）
        if (userFundDao.updateUserFund(userFund)) {
            // 添加个人账户入账日志
            UserFundLog userFundLog = new UserFundLog();
            userFundLog.setAmount(totalMoney);
            userFundLog.setBalance(balance);
            userFundLog.setPayer(userFund.getUser());
            userFundLog.setPayee(userFund.getUser());
            userFundLog.setRemark("账户配资资金冻结-" + totalMoney);
            userFundLog.setTradeDirection(EnumTradeDirection.DIRECTION_OUT);
            userFundLog.setTradeType(EnumTradeType.FREEZE);
            userFundDao.addUserFundLog(userFundLog);
        }
//        3. 修改配资申请为已审核
        manageBankrollDao.updateBankrollApplyToDeal(apply);
        return bankrollRecord;

    }

    private BigDecimal getCurrentManagementFee(BankrollApply apply) {
        if (apply.getCycleUnit().equals(EnumCycleUnit.MONTH))
            return apply.getManagementFee();
        else {
            BigDecimal managementFee = MathUtil.format(apply.getManagementFee(), 1) ;
            BigDecimal totalManagementFee = MathUtil.format(MathUtil.multiply(managementFee, apply.getCycle()), 1);
            return totalManagementFee;
        }
    }

    @Override
    public BankrollRecord getBankrollRecordById(long recordId) {
        return manageBankrollDao.getBankrollRecordById(recordId);
    }

    @Override
    public PageableList<BankrollRecord> getBankrollRecordList(EnumCycleUnit unit, EnumBankrollRecordStatus status, int index, int size) {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("unit", unit);
        options.put("status", status);
        return manageBankrollDao.getBankrollRecordList(options, index, size);
    }

    @Override
    public boolean addAllocationHoms(Long recordId, String homsOperator, String homsPwd, Date startDate, Date endDate, SysUser updater) throws ServiceException {
        // 1. 获取配资记录
        BankrollRecord record = manageBankrollDao.getBankrollRecordById(recordId);
        if (record == null) {
            throw new ServiceException(EnumServiceMessage.BANKROLL_RECORD_NOT_EXIST);
        }

        if (record.getStatus() != EnumBankrollRecordStatus.NOT_ALLOCATION_HOMS) {
            throw new ServiceException(EnumServiceMessage.BANKROLL_RECORD_ALLOCATIONED_HOMS);
        }

        // 2. 获取用户账户记录
        UserFund fund = record.getApplicant().getUserFund();
        // 3. 计算当期账单
        BigDecimal totalMoney = record.getDeposit();

        BigDecimal managementFee = getCurrentManagementFee(record.getApply());

        totalMoney = MathUtil.add(totalMoney, managementFee); // 当期总付款金额

        // 4. 检查用户账户冻结资金是否满足要求
        BigDecimal freeze = fund.getFreeze();
        if (MathUtil.gt(totalMoney, freeze)) {
            throw new ServiceException(EnumServiceMessage.BANKROLL_APPLY_USER_FREEZE_NOT_ENOUGH);
        }

        // 5. 用户账户-冻结金额转到（配资本金），配资金额累加 并记录日志
        BigDecimal realFreeze = MathUtil.subtract(freeze, totalMoney);

        fund.setFreeze(realFreeze);
        BigDecimal principal = fund.getPrincipal();
        BigDecimal bankroll = fund.getBankroll();

        principal = MathUtil.add(principal, record.getDeposit());
        bankroll = MathUtil.add(bankroll, record.getMoney());

        fund.setPrincipal(principal);
        fund.setBankroll(bankroll);


        if(userFundDao.updateUserFund(fund)) {
            // 添加个人账户入账日志
            UserFundLog userFundLog = new UserFundLog();
            userFundLog.setAmount(totalMoney);
            userFundLog.setBalance(fund.getBalance());
            userFundLog.setPayer(fund.getUser());
            userFundLog.setPayee(fund.getUser());
            userFundLog.setRemark("账户配资资金解冻-" + totalMoney + "，充值保证金-"+record.getDeposit()+"，服务费-"+managementFee);
            userFundLog.setTradeDirection(EnumTradeDirection.DIRECTION_OUT);
            userFundLog.setTradeType(EnumTradeType.BANKROLL);
            userFundDao.addUserFundLog(userFundLog);
        }

        // 6. 分配homs帐号，
        record.setHomsOperatorNo(homsOperator);
        record.setHomsOperatorPwd(homsPwd);
        record.setStartDate(startDate);
        record.setEndDate(endDate);
        record.setRenewNumber(0);
        record.setStatus(EnumBankrollRecordStatus.ALLOCATIONED_HOMS);
        if (manageBankrollDao.updateBankrollRecordToAllocateHoms(record)) {
            // 添加配资日志
            BankrollRecordLog recordLog = new BankrollRecordLog();
            // TODO 后期再看用哪个字段来显示创建人
            recordLog.setCreatorName(updater.getUsername());
            recordLog.setMessage("homs帐号分配成功，祝您投资顺利");
            recordLog.setRecord(record);
            manageBankrollDao.addBankrollRecordLog(recordLog);
        }
        // 7. 公司服务费账户累加， 并记录日志
        CompanyFund manageFeeCompanyFund = companyFundDao.getFundByType(EnumCompanyFundType.MANAGEMENT_FEE);
        if (manageFeeCompanyFund == null) throw new ServiceException(EnumServiceMessage.COMPANY_FUND_NOT_EXIST);

        if (companyFundDao.updateFund(manageFeeCompanyFund, managementFee)) {
            // 添加公司管理费收入日志
            BigDecimal currentAmount = manageFeeCompanyFund.getAmount();
            if (currentAmount == null)
                currentAmount = MathUtil.format("0");

            BigDecimal balance = MathUtil.add(currentAmount, managementFee);
            // 添加公司充值记录日志
            CompanyFundLog companyFundLog = new CompanyFundLog();
            companyFundLog.setTradeDirection(EnumTradeDirection.DIRECTION_IN);
            companyFundLog.setRemark("账户" + record.getApplicant().getId() + "投入配资本金" + record.getDeposit() + "，管理费"+managementFee);
            companyFundLog.setTradeType(EnumTradeType.BANKROLL);
            companyFundLog.setBalance(balance);
            companyFundLog.setAmount(managementFee);
            companyFundLog.setPayee(record.getApplicant());
            companyFundLog.setFundType(EnumCompanyFundType.MANAGEMENT_FEE);
            companyFundDao.addFundLog(companyFundLog);
        }

        CompanyFund rechargeCompanyFund = companyFundDao.getFundByType(EnumCompanyFundType.RECHARGE);
        if (rechargeCompanyFund == null) throw new ServiceException(EnumServiceMessage.COMPANY_FUND_NOT_EXIST);


        // 8. 更新公司账户信息
        if (companyFundDao.updateFund(rechargeCompanyFund, MathUtil.subtract("0", record.getDeposit()))) {
            BigDecimal currentAmount = rechargeCompanyFund.getAmount();
            if (currentAmount == null)
                currentAmount = MathUtil.format("0");

            BigDecimal balance = MathUtil.add(currentAmount, MathUtil.subtract("0", record.getDeposit()));
            // 添加公司充值记录日志
            CompanyFundLog companyFundLog = new CompanyFundLog();
            companyFundLog.setTradeDirection(EnumTradeDirection.DIRECTION_OUT);
            companyFundLog.setRemark("账户" + record.getApplicant().getId() + "配资本金投入" + record.getDeposit());
            companyFundLog.setTradeType(EnumTradeType.BANKROLL);
            companyFundLog.setFundType(EnumCompanyFundType.BANKROLL);
            companyFundLog.setPayee(record.getApplicant());
            companyFundLog.setAmount(MathUtil.subtract("0", record.getDeposit()));
            companyFundLog.setBalance(balance);
            companyFundDao.addFundLog(companyFundLog);
        }

        // 9. 生成账单
        if (generateBill(record, fund.getUser())) {
            // TODO 发送账单生成报告通知
        }

        return true;
    }

    @Override
    public PageableList<BankrollApplyDTO> getApplyDTOList(EnumCycleUnit eUnit, EnumBankrollApplyStatus eStatus, int fundStat, int index, int size) {
        return manageBankrollDao.getApplyDTOList(eUnit, eStatus, fundStat, index, size);
    }

    // 生成账单
    private boolean generateBill(BankrollRecord record, User user) throws ServiceException {

        int cycle = record.getCycle();
        EnumCycleUnit cycleUnit = record.getCycleUnit();
        BigDecimal managementFee = getCurrentManagementFee(record.getApply());//record.getManagementFee();
        Date startDate = record.getStartDate();
        EnumBankrollBillStatus status = EnumBankrollBillStatus.REPAYMENTED;


        // 按月生成
        if (cycleUnit.equals(EnumCycleUnit.MONTH)) {

            for (int i = 1; i <= cycle; i++) {
                BankrollBill bill = new BankrollBill();
                bill.setRecord(record);
                bill.setNum(i);
                bill.setAmount(managementFee);
                bill.setRealAmount(MathUtil.ZERO);
                bill.setShouldFk(MathUtil.ZERO);
                bill.setRealFk(MathUtil.ZERO);
                bill.setBiller(user);
                bill.setShouldTime(CalendarUtil.nextMonthDay(startDate, i-1));
                if (i != 1) {
                    status = EnumBankrollBillStatus.NOT_REPAYMENT;

                } else {
                    bill.setRealAmount(managementFee);
                    bill.setRealTime(new Date());
                }
                bill.setStatus(status);

                manageBankrollDao.addBankrollBill(bill);
            }
        }

        // 按天生成
        if (cycleUnit.equals(EnumCycleUnit.DAY)) {
            BankrollBill bill = new BankrollBill();
            bill.setRecord(record);
            bill.setNum(1);
            bill.setAmount(managementFee);
            bill.setRealAmount(managementFee);
            bill.setRealTime(new Date());
            bill.setShouldFk(MathUtil.ZERO);
            bill.setRealFk(MathUtil.ZERO);
            bill.setBiller(user);
            bill.setShouldTime(startDate);
            bill.setStatus(status);
            manageBankrollDao.addBankrollBill(bill);
        }
        return true;
    }

    @Override
    public List<BankrollBill> getNotRepaymentBillListByMonth(Date startTime) {
        return manageBankrollBillDao.getBankrollBillListByMonth(startTime);
    }

    @Override
    public boolean updateBillRepaymentOfMonth(BankrollBill bill) {

        try {
            BankrollRecord record = bill.getRecord();
            User user = bill.getBiller();

            BigDecimal managementFee = bill.getAmount();

            UserFund fund = user.getUserFund();
            if (MathUtil.gt(managementFee, fund.getBalance())) {
                logger.debug("账户{}，余额{}不足以支付配资{}第{}期服务费{}(按月)", user.getId(), MathUtil.format(fund.getBalance()), record.getId(), bill.getNum(), MathUtil.format(bill.getAmount()));
                return false;
            }

            BigDecimal balance = fund.getBalance();

            balance = MathUtil.subtract(balance, managementFee);
            fund.setBalance(balance);

            if (userFundDao.updateUserFund(fund)) {
                // 添加个人账户入账日志
                UserFundLog userFundLog = new UserFundLog();
                userFundLog.setAmount(managementFee);
                userFundLog.setBalance(fund.getBalance());
                userFundLog.setPayer(fund.getUser());
                userFundLog.setPayee(fund.getUser());
                userFundLog.setRemark("账户配资-" + record.getBankrollNo() + "第" + bill.getNum() + "期服务费-" + managementFee);
                userFundLog.setTradeDirection(EnumTradeDirection.DIRECTION_OUT);
                userFundLog.setTradeType(EnumTradeType.MANAGEMENT_FEE);
                userFundDao.addUserFundLog(userFundLog);
            }

            CompanyFund manageFeeCompanyFund = companyFundDao.getFundByType(EnumCompanyFundType.MANAGEMENT_FEE);
            if (manageFeeCompanyFund == null) throw new ServiceException(EnumServiceMessage.COMPANY_FUND_NOT_EXIST);

            // 5. 添加公司服务费收入
            if (companyFundDao.updateFund(manageFeeCompanyFund, managementFee)) {
                // 添加公司管理费收入日志
                BigDecimal currentAmount = manageFeeCompanyFund.getAmount();
                if (currentAmount == null)
                    currentAmount = MathUtil.format("0");

                BigDecimal balance1 = MathUtil.add(currentAmount, managementFee);
                // 添加公司充值记录日志
                CompanyFundLog companyFundLog = new CompanyFundLog();
                companyFundLog.setTradeDirection(EnumTradeDirection.DIRECTION_IN);
                companyFundLog.setRemark("账户" + fund.getUser() + "缴纳配资管理费"+managementFee);
                companyFundLog.setTradeType(EnumTradeType.RECHARGE);
                companyFundLog.setBalance(balance1);
                companyFundLog.setAmount(managementFee);
                companyFundLog.setPayee(fund.getUser());
                companyFundLog.setFundType(EnumCompanyFundType.MANAGEMENT_FEE);
                companyFundDao.addFundLog(companyFundLog);
            }

            bill.setRealTime(new Date());
            bill.setRealAmount(managementFee);
            bill.setRealTime(new Date());
            bill.setRealFk(MathUtil.ZERO);
            bill.setStatus(EnumBankrollBillStatus.REPAYMENTED);
            manageBankrollBillDao.updateBill(bill);


        } catch (ServiceException ex) {
            logger.error(ex.getMessage());
            return false;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public List<BankrollRecord> getBankrollRecordListByDate(Date date) {

        return manageBankrollDao.getBankrollRecordListByDate(date);
    }

    @Override
    public boolean updateBillRepaymentOfDay(BankrollRecord record) {
        // 1. 获取配资将要将要到期的记录（差一天）
        // 2. 从账户扣除一天的服务费
        // 3. 配资记录延期一天
        // 4.按天的不需要申请

        try {
            BigDecimal managementFee = record.getManagementFee();

            // 当期
            String currentNum = CommonUtil.formatDate(record.getEndDate());

            User user = record.getApplicant();
            UserFund fund = user.getUserFund();

            if (MathUtil.gt(managementFee, fund.getBalance())) {
                logger.debug("账户{}，余额{}不足以支付配资{}第{}期服务费{}（按天）", user.getId(), MathUtil.format(fund.getBalance()), record.getId(), currentNum, MathUtil.format(managementFee));
                return false;
            }

            // 获取下一个交易日
            TradingDay tradingDay = manageTradingDayDao.getNextTradingDay();
            if (tradingDay == null) {
                logger.debug("无法获取下个交易日");
                return false;
            }

            BigDecimal balance = fund.getBalance();
            balance = MathUtil.subtract(balance, managementFee);
            fund.setBalance(balance);

            if (userFundDao.updateUserFund(fund)) {
                // 添加个人账户入账日志
                UserFundLog userFundLog = new UserFundLog();
                userFundLog.setAmount(managementFee);
                userFundLog.setBalance(fund.getBalance());
                userFundLog.setPayer(fund.getUser());
                userFundLog.setPayee(fund.getUser());
                userFundLog.setRemark("账户配资-" + record.getBankrollNo() + "第" + currentNum + "期服务费-" + managementFee);
                userFundLog.setTradeDirection(EnumTradeDirection.DIRECTION_OUT);
                userFundLog.setTradeType(EnumTradeType.MANAGEMENT_FEE);
                userFundDao.addUserFundLog(userFundLog);
            }

            CompanyFund manageFeeCompanyFund = companyFundDao.getFundByType(EnumCompanyFundType.MANAGEMENT_FEE);
            if (manageFeeCompanyFund == null) throw new ServiceException(EnumServiceMessage.COMPANY_FUND_NOT_EXIST);

            // 添加公司服务费收入
            if (companyFundDao.updateFund(manageFeeCompanyFund, managementFee)) {
                // 添加公司管理费收入日志
                BigDecimal currentAmount = manageFeeCompanyFund.getAmount();
                if (currentAmount == null)
                    currentAmount = MathUtil.format("0");

                BigDecimal balance1 = MathUtil.add(currentAmount, managementFee);
                // 添加公司充值记录日志
                CompanyFundLog companyFundLog = new CompanyFundLog();
                companyFundLog.setTradeDirection(EnumTradeDirection.DIRECTION_IN);
                companyFundLog.setRemark("账户" + fund.getUser() + "缴纳配资管理费"+managementFee);
                companyFundLog.setTradeType(EnumTradeType.RECHARGE);
                companyFundLog.setBalance(balance1);
                companyFundLog.setAmount(managementFee);
                companyFundLog.setPayee(fund.getUser());
                companyFundLog.setFundType(EnumCompanyFundType.MANAGEMENT_FEE);
                companyFundDao.addFundLog(companyFundLog);
            }

            BankrollBill bill = new BankrollBill();
            bill.setRecord(record);
            bill.setNum(1);
            bill.setAmount(managementFee);
            bill.setRealAmount(managementFee);
            bill.setRealTime(new Date());
            bill.setShouldFk(MathUtil.ZERO);
            bill.setRealFk(MathUtil.ZERO);
            bill.setBiller(user);
            bill.setShouldTime(record.getEndDate()); // TODO 按天还款优化
            bill.setStatus(EnumBankrollBillStatus.REPAYMENTED);
            manageBankrollDao.addBankrollBill(bill);

            // TODO 按天续约没有考虑逾期的情况
            Integer renewNumber = record.getRenewNumber();
            renewNumber++;
            record.setRenewNumber(renewNumber);
            record.setEndDate(tradingDay.getDate());
            record.setUpdateTime(new Date());
            manageBankrollDao.updateBankrollRecordToRenewByDay(record);


        } catch (ServiceException ex) {
            logger.error(ex.getMessage());
            return false;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return false;
        }


        return false;
    }

    @Override
    public List<BankrollRecord> getBankrollRecordBySatus(EnumBankrollRecordStatus status, Long orgId) {
        return manageBankrollDao.getBankrollRecordListByStatus(status, orgId);
    }

    @Override
    public BankrollRecord getBankrollRecordByOperatorNo(String operatorNo) {
        return manageBankrollDao.getBankrollRecordByOperatorNo(operatorNo);
    }

    @Override
    public BankrollHomsInfo getBankrollHomsInfo(Long recordId) {
        return manageBankrollHomsDao.getBankrollHomsInfo(recordId);
    }

    @Override
    public boolean addBankrollHomsInfo(BankrollHomsInfo homsInfo) throws ServiceException {
        return manageBankrollHomsDao.addBankrollHomsInfo(homsInfo) > 0;
    }

    @Override
    public boolean updateBankrollHomsInfo(BankrollHomsInfo homsInfo) {
        return manageBankrollHomsDao.updateBankrollHomsInfo(homsInfo);
    }

    @Override
    public boolean addBankrollHomsInfoLog(BankrollHomsInfoLog homsInfoLog) throws ServiceException {
        return manageBankrollHomsDao.addBankrollHomsInfoLog(homsInfoLog);
    }

    @Override
    public List<BankrollHomsInfoLog> getBankrollHomsInfoLog(long recordId) {
        return  manageBankrollHomsDao.getBankrollHomsInfoLog(recordId);
    }

    @Override
    public boolean updateBankrollRecordToFinish(long recordId, SysUser updater) throws ServiceException {

        BankrollRecord record = manageBankrollDao.getBankrollRecordById(recordId);

        if (record == null) {
            throw new ServiceException(EnumServiceMessage.BANKROLL_RECORD_NOT_EXIST);
        }

        if (record.getStatus() != EnumBankrollRecordStatus.ALLOCATIONED_HOMS) {
            throw new ServiceException("状态不是\"已分配homs账号\"");
        }
        List<BankrollBill> bills = manageBankrollBillDao.getBankrollBillListByRecordId(record.getId());

        // 将所有未还款的订单取消掉
        // TODO 接下来看看这块，应该没这么简单
        for (BankrollBill bill : bills) {
            bill.setStatus(EnumBankrollBillStatus.SYSTEM_CANCEL);
            manageBankrollBillDao.updateBill(bill);
        }

        record.setStatus(EnumBankrollRecordStatus.FINISH);
        record.setRealEndDate(new Date());
        record.setUpdateTime(new Date());
        if (manageBankrollDao.updateBankrollRecord(record)) {
            BankrollRecordLog recordLog = new BankrollRecordLog();
            recordLog.setRecord(record);
            recordLog.setCreatorName(updater.getUsername());
            recordLog.setMessage("配资结束，等待结算");
            manageBankrollDao.addBankrollRecordLog(recordLog);
        }

        return true;
    }

    @Override
    public boolean updateBankrollRecordToRepayment(Long recordId, BigDecimal repaymentMoney, SysUser user) throws ServiceException {
        // 1. 获取配资记录，并判断状态是否为finish
        // 2. 修改用户资金
        // 3. 修改配资记录状态，并加日志
        // 4. 公司账户记录更新，并加日志

        BankrollRecord record = manageBankrollDao.getBankrollRecordById(recordId);

        if (record == null) {
            throw new ServiceException(EnumServiceMessage.BANKROLL_RECORD_NOT_EXIST);
        }

        if (record.getStatus() != EnumBankrollRecordStatus.FINISH) {
            throw new ServiceException("状态不是\"已完成，等待结算\"");
        }

        User applicant = record.getApplicant();

        UserFund userFund = applicant.getUserFund();

        BigDecimal bankroll = userFund.getBankroll();
        BigDecimal principal = userFund.getPrincipal();
        BigDecimal balance = userFund.getBalance();
        BigDecimal profitAndLoss = userFund.getProfitAndLoss();

        bankroll = MathUtil.subtract(bankroll, record.getMoney());
        principal = MathUtil.subtract(principal, record.getDeposit());
        balance = MathUtil.add(balance, repaymentMoney);
        BigDecimal realProfitAndLoss = MathUtil.subtract(repaymentMoney, record.getDeposit());
        profitAndLoss = MathUtil.add(profitAndLoss, realProfitAndLoss);

        userFund.setBalance(balance);
        userFund.setBankroll(bankroll);
        userFund.setProfitAndLoss(profitAndLoss);
        userFund.setPrincipal(principal);

        if (userFundDao.updateUserFund(userFund)) {
            // 添加个人账户入账日志
            UserFundLog userFundLog = new UserFundLog();
            userFundLog.setAmount(repaymentMoney);
            userFundLog.setBalance(balance);
            userFundLog.setPayer(userFund.getUser());
            userFundLog.setPayee(userFund.getUser());
            userFundLog.setRemark("账户配资-" + record.getBankrollNo()+", 收入"+repaymentMoney+", 配资款"+record.getMoney()+", 本金"+record.getDeposit());
            userFundLog.setTradeDirection(EnumTradeDirection.DIRECTION_IN);
            userFundLog.setTradeType(EnumTradeType.BANKROLL);
            userFundDao.addUserFundLog(userFundLog);
        }

        CompanyFund rechargeCompanyFund = companyFundDao.getFundByType(EnumCompanyFundType.RECHARGE);
        if (rechargeCompanyFund == null) throw new ServiceException(EnumServiceMessage.COMPANY_FUND_NOT_EXIST);


        // 更新公司账户信息
        if (companyFundDao.updateFund(rechargeCompanyFund, repaymentMoney)) {
            BigDecimal currentAmount = rechargeCompanyFund.getAmount();
            if (currentAmount == null)
                currentAmount = MathUtil.format("0");

            BigDecimal balance1 = MathUtil.add(currentAmount, repaymentMoney);
            // 添加公司充值记录日志
            CompanyFundLog companyFundLog = new CompanyFundLog();
            companyFundLog.setTradeDirection(EnumTradeDirection.DIRECTION_OUT);
            companyFundLog.setRemark("账户" + record.getApplicant().getId() + "配资结束收入" + repaymentMoney);
            companyFundLog.setTradeType(EnumTradeType.BANKROLL);
            companyFundLog.setFundType(EnumCompanyFundType.BANKROLL);
            companyFundLog.setPayee(record.getApplicant());
            companyFundLog.setAmount(repaymentMoney);
            companyFundLog.setBalance(balance1);
            companyFundDao.addFundLog(companyFundLog);
        }



        record.setStatus(EnumBankrollRecordStatus.REPAYMENT);
        record.setSettleMoney(repaymentMoney);
        if (manageBankrollDao.updateBankrollRecord(record)) {
            BankrollRecordLog recordLog = new BankrollRecordLog();
            recordLog.setRecord(record);
            recordLog.setCreatorName(user.getUsername());
            recordLog.setMessage("配资结束，结算金额为"+repaymentMoney);
            manageBankrollDao.addBankrollRecordLog(recordLog);
        }
        return true;
    }

    @Autowired
    public void setManageBankrollDao(ManageBankrollDao manageBankrollDao) {
        this.manageBankrollDao = manageBankrollDao;
    }
    @Autowired
    public void setUserFundDao(UserFundDao userFundDao) {
        this.userFundDao = userFundDao;
    }
    @Autowired
    public void setCompanyFundDao(CompanyFundDao companyFundDao) {
        this.companyFundDao = companyFundDao;
    }
    @Autowired
    public void setManageBankrollBillDao(ManageBankrollBillDao manageBankrollBillDao) {
        this.manageBankrollBillDao = manageBankrollBillDao;
    }
    @Autowired
    public void setManageBankrollHomsDao(ManageBankrollHomsDao manageBankrollHomsDao) {
        this.manageBankrollHomsDao = manageBankrollHomsDao;
    }
    @Autowired
    public void setManageTradingDayDao(ManageTradingDayDao manageTradingDayDao) {
        this.manageTradingDayDao = manageTradingDayDao;
    }
}
