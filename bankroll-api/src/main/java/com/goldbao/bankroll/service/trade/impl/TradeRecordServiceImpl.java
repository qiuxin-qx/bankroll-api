package com.goldbao.bankroll.service.trade.impl;

import com.goldbao.bankroll.dao.trade.TradeRecordDao;
import com.goldbao.bankroll.dao.user.UserFundDao;
import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.PageableList;
import com.goldbao.bankroll.model.enums.EnumPayType;
import com.goldbao.bankroll.model.enums.EnumTradeDirection;
import com.goldbao.bankroll.model.enums.EnumTradeStatus;
import com.goldbao.bankroll.model.enums.EnumTradeType;
import com.goldbao.bankroll.model.manage.sysuser.SysUser;
import com.goldbao.bankroll.model.trade.Cash;
import com.goldbao.bankroll.model.trade.TradeRecord;
import com.goldbao.bankroll.model.trade.UserBank;
import com.goldbao.bankroll.model.user.UserFund;
import com.goldbao.bankroll.model.user.UserFundLog;
import com.goldbao.bankroll.service.trade.TradeRecordService;
import com.goldbao.utils.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * @author shuyu.fang
 */
public class TradeRecordServiceImpl implements TradeRecordService {

    private TradeRecordDao tradeRecordDao;
    private UserFundDao userFundDao;

    @Override
    public TradeRecord getTradeRecordByOrderNo(String orderNo) {
        return tradeRecordDao.getTradeRecordBySerialNo(orderNo);
    }

    @Override
    public PageableList<TradeRecord> getTradeRecordList(int index, int size, Long userId, EnumTradeType tradeType) {
        return tradeRecordDao.getTradeRecordList(index, size, userId, tradeType);
    }

    @Override
    public void addApplyCash(UserFund userFund, BigDecimal cashAmount, String bankId, String accountNumber, String branchName, String province, String city) throws ServiceException {
        // 1. 修改用户账户，将余额调整至冻结
        // 2. 添加申请记录
        if (MathUtil.gt(cashAmount, userFund.getBalance())) {
            throw new ServiceException("余额不足");
        }

        BigDecimal newBalance = MathUtil.subtract(userFund.getBalance(), cashAmount);
        BigDecimal newFreeze = MathUtil.add(userFund.getFreeze(), cashAmount);
        userFund.setBalance(newBalance);
        userFund.setFreeze(newFreeze);
        if (userFundDao.updateUserFund(userFund)) {
            // 添加个人账户入账日志
            UserFundLog userFundLog = new UserFundLog();
            userFundLog.setAmount(cashAmount);
            userFundLog.setBalance(newBalance);
            userFundLog.setPayer(userFund.getUser());
            userFundLog.setPayee(userFund.getUser());
            userFundLog.setRemark("账户取现申请，冻结资金-" + cashAmount);
            userFundLog.setTradeDirection(EnumTradeDirection.DIRECTION_OUT);
            userFundLog.setTradeType(EnumTradeType.CASH);
            userFundDao.addUserFundLog(userFundLog);
        }
        // 添加银行卡
        UserBank userBank = new UserBank();
        userBank.setAccountName(userFund.getUser().getRealName());
        userBank.setAccountNumber(accountNumber);
        userBank.setBankId(bankId);
        userBank.setBranchName(branchName);
        userBank.setCity(city);
        userBank.setProvince(province);
        userBank.setUser(userBank.getUser());
        userFundDao.addUserBank(userBank);
        // 添加申请记录
        Cash cash = new Cash();
        cash.setCreator(userFund.getUser());
        cash.setAmount(cashAmount);
        cash.setFee(MathUtil.ZERO);
        cash.setStatus(EnumTradeStatus.INIT);
//        cash.setPayType(EnumPayType.CFCA);
        cash.setUserBank(userBank);

        tradeRecordDao.addCash(cash);


    }

    @Override
    public PageableList<Cash> getApplyCashList(EnumTradeStatus tradeStatus, int index, int size) {
        return tradeRecordDao.getApplyCashList(tradeStatus, index, size);
    }

    @Override
    public Cash getApplyCashById(long cashId) {
        return tradeRecordDao.getApplyCashById(cashId);
    }

    @Override
    public boolean addCashToTradeRecord(Cash cash, String orderNo, EnumPayType payType, SysUser user) throws ServiceException {

        TradeRecord tradeRecord = new TradeRecord();
        tradeRecord.setOrderNo(orderNo);
        tradeRecord.setSerialNo(orderNo);
        tradeRecord.setTradeStatus(EnumTradeStatus.SUCCESS);
        tradeRecord.setAmount(cash.getAmount());
        tradeRecord.setCreator(cash.getCreator());
        tradeRecord.setFee(cash.getFee());
        tradeRecord.setPayee(cash.getCreator());
        tradeRecord.setPayer(cash.getCreator());
        tradeRecord.setSysCreator(user);
        tradeRecord.setTradeType(EnumTradeType.CASH);
        tradeRecordDao.addTradeRecord(tradeRecord);


        return false;
    }

    @Override
    public void updateCash(Cash cash, String orderNo, EnumPayType payType) {

        cash.setPayType(payType);
        cash.setOrderNo(orderNo);
        tradeRecordDao.updateCash(cash);

    }

    @Autowired
    public void setTradeRecordDao(TradeRecordDao tradeRecordDao) {
        this.tradeRecordDao = tradeRecordDao;
    }
    @Autowired
    public void setUserFundDao(UserFundDao userFundDao) {
        this.userFundDao = userFundDao;
    }
}
