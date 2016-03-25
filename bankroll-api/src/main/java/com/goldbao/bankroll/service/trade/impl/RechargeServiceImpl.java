package com.goldbao.bankroll.service.trade.impl;

import com.goldbao.bankroll.dao.company.CompanyFundDao;
import com.goldbao.bankroll.dao.trade.RechargeDao;
import com.goldbao.bankroll.dao.trade.TradeRecordDao;
import com.goldbao.bankroll.dao.user.UserFundDao;
import com.goldbao.bankroll.exception.EnumServiceMessage;
import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.company.CompanyFund;
import com.goldbao.bankroll.model.company.CompanyFundLog;
import com.goldbao.bankroll.model.enums.*;
import com.goldbao.bankroll.model.manage.sysuser.SysUser;
import com.goldbao.bankroll.model.trade.Recharge;
import com.goldbao.bankroll.model.trade.TradeRecord;
import com.goldbao.bankroll.model.user.User;
import com.goldbao.bankroll.model.user.UserFund;
import com.goldbao.bankroll.model.user.UserFundLog;
import com.goldbao.bankroll.service.trade.RechargeService;
import com.goldbao.utils.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author shuyu.fang
 */
public class RechargeServiceImpl implements RechargeService {

    private RechargeDao rechargeDao;

    private TradeRecordDao tradeRecordDao;

    private UserFundDao userFundDao;

    private CompanyFundDao companyFundDao;

    @Override
    public boolean addRecharge(User creator, String orderNo, String serialNo, BigDecimal amount, BigDecimal fee, String remark) throws ServiceException {
        // 1. 添加一条交易订单记录(TradeRecord)
        if (creator.getId() == null || creator.getId() == 0) throw new ServiceException("无效的用户");

        TradeRecord record = new TradeRecord();
        record.setOrderNo(orderNo);
        record.setSerialNo(serialNo);
        record.setTradeStatus(EnumTradeStatus.INIT);
        record.setPayee(creator);
        record.setPayer(creator);
        record.setCreator(creator);
        record.setAmount(amount);
        record.setFee(fee);
        record.setTradeType(EnumTradeType.RECHARGE);
        record.setRemark(remark);
        tradeRecordDao.addTradeRecord(record);
        return true;
    }

    @Override
    public boolean updateDealRecharge(String orderNo, String serialNo, EnumPayType payType, SysUser sysUser) throws ServiceException {
        // 1. 获取充值记录表信息
        TradeRecord record = tradeRecordDao.getTradeRecordBySerialNo(serialNo);
        if (record == null)
            throw new ServiceException(EnumServiceMessage.TRADE_RECORD_NOT_EXIST);
        if (record.getTradeStatus() == EnumTradeStatus.SUCCESS)
            return true;
        // TODO 如果tradeStatus为fail时的处理，暂时还没用这个状态

        // 2. 添加充值表
        Date dealTime = new Date();
        Recharge recharge = new Recharge();
        recharge.setOrderNo(record.getSerialNo());
        recharge.setStatus(EnumTradeStatus.SUCCESS);
        recharge.setAmount(record.getAmount());
        recharge.setFee(record.getFee());
        recharge.setDealTime(dealTime);
        recharge.setCreator(record.getCreator());
        recharge.setPayType(payType);
        rechargeDao.addRecharge(recharge);

        // 3. 更新账户信息
        User payer = record.getPayee();
        UserFund userFund = userFundDao.getUserFundByUserId(payer.getId());
        BigDecimal userBalance = MathUtil.add(userFund.getBalance(), record.getAmount());
        userFund.setBalance(userBalance);
        if (userFundDao.updateUserFund(userFund)) {
            // 添加个人账户入账日志
            UserFundLog userFundLog = new UserFundLog();
            userFundLog.setAmount(record.getAmount());
            userFundLog.setBalance(userBalance);
            userFundLog.setPayer(record.getPayer());
            userFundLog.setPayee(record.getPayee());
            userFundLog.setRemark("账户充值入账-" + record.getAmount());
            userFundLog.setTradeDirection(EnumTradeDirection.DIRECTION_IN);
            userFundLog.setTradeType(EnumTradeType.RECHARGE);
            userFundDao.addUserFundLog(userFundLog);
            // 添加个人账户充值通知
        }

        CompanyFund rechargeCompanyFund = companyFundDao.getFundByType(EnumCompanyFundType.RECHARGE);
        if (rechargeCompanyFund == null) throw new ServiceException(EnumServiceMessage.COMPANY_FUND_NOT_EXIST);

        // 4. 更新公司账户信息
        if (companyFundDao.updateFund(rechargeCompanyFund, record.getAmount())) {
            BigDecimal currentAmount = rechargeCompanyFund.getAmount();
            if (currentAmount == null)
                currentAmount = MathUtil.format("0");

            BigDecimal balance = MathUtil.add(currentAmount, record.getAmount());
            // 添加公司充值记录日志
            CompanyFundLog companyFundLog = new CompanyFundLog();
            companyFundLog.setTradeDirection(EnumTradeDirection.DIRECTION_IN);
            companyFundLog.setRemark("账户" + record.getPayer().getId() + "充值" + record.getAmount());
            companyFundLog.setTradeType(EnumTradeType.RECHARGE);
            companyFundLog.setFundType(EnumCompanyFundType.RECHARGE);
            companyFundLog.setPayee(record.getPayer());
            companyFundLog.setAmount(record.getAmount());
            companyFundLog.setBalance(balance);
            companyFundDao.addFundLog(companyFundLog);
        }

        if (MathUtil.gt(record.getFee(), "0")) {
            CompanyFund manageFeeCompanyFund = companyFundDao.getFundByType(EnumCompanyFundType.MANAGEMENT_FEE);
            if (manageFeeCompanyFund == null) throw new ServiceException(EnumServiceMessage.COMPANY_FUND_NOT_EXIST);

            // 5. 添加公司服务费收入
            if (companyFundDao.updateFund(manageFeeCompanyFund, record.getFee())) {
                // 添加公司管理费收入日志
                BigDecimal currentAmount = manageFeeCompanyFund.getAmount();
                if (currentAmount == null)
                    currentAmount = MathUtil.format("0");

                BigDecimal balance = MathUtil.add(currentAmount, record.getFee());
                // 添加公司充值记录日志
                CompanyFundLog companyFundLog = new CompanyFundLog();
                companyFundLog.setTradeDirection(EnumTradeDirection.DIRECTION_IN);
                companyFundLog.setRemark("账户" + record.getPayer().getId() + "充值" + record.getAmount() + "，管理费"+record.getFee());
                companyFundLog.setTradeType(EnumTradeType.RECHARGE);
                companyFundLog.setBalance(balance);
                companyFundLog.setAmount(record.getFee());
                companyFundLog.setPayee(record.getPayer());
                companyFundLog.setFundType(EnumCompanyFundType.MANAGEMENT_FEE);
                companyFundDao.addFundLog(companyFundLog);
            }
        }

        // 5. 更新订单表
//        record.setSerialNo(serialNo);
        if (sysUser != null) { // 如果sysUser不为null，则为后台处理
            record.setSysCreator(sysUser);
        }
        tradeRecordDao.updateTradeRecordToDeal(record);
        return false;
    }

    @Override
    public boolean updateDealRecharge(String orderNo, String serialNo, EnumPayType payType) throws ServiceException {
        return updateDealRecharge(orderNo, serialNo, payType, null);
    }

    @Override
    public Recharge getRechargeByOrderNo(String orderNo) {
        return null;
    }

    @Override
    public List<Recharge> getRechargeListByUserId(Long userId) {
        return null;
    }

    @Autowired
    public void setRechargeDao(RechargeDao rechargeDao) {
        this.rechargeDao = rechargeDao;
    }
    @Autowired
    public void setTradeRecordDao(TradeRecordDao tradeRecordDao) {
        this.tradeRecordDao = tradeRecordDao;
    }
    @Autowired
    public void setUserFundDao(UserFundDao userFundDao) {
        this.userFundDao = userFundDao;
    }
    @Autowired
    public void setCompanyFundDao(CompanyFundDao companyFundDao) {
        this.companyFundDao = companyFundDao;
    }
}
