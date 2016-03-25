package com.goldbao.bankroll.service.manage.trade.impl;

import com.goldbao.bankroll.dao.manage.trade.ManageTradeRecordDao;
import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.enums.EnumPayType;
import com.goldbao.bankroll.model.enums.EnumSettlementStatus;
import com.goldbao.bankroll.model.enums.EnumTradeStatus;
import com.goldbao.bankroll.model.manage.sysuser.SysUser;
import com.goldbao.bankroll.model.trade.Settlement;
import com.goldbao.bankroll.model.trade.TradeRecord;
import com.goldbao.bankroll.model.user.User;
import com.goldbao.bankroll.service.manage.trade.ManageTradeRecordService;
import com.goldbao.utils.CommonUtil;
import com.goldbao.utils.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author shuyu.fang
 */
public class ManageTradeRecordServiceImpl implements ManageTradeRecordService {

    private ManageTradeRecordDao manageTradeRecordDao;

    @Override
    public boolean transfer(SysUser user, User user1, BigDecimal transferMoney) {
        return false;
    }

    @Override
    public List<TradeRecord> getTradeRecordOfWaitSync(Date date, EnumPayType payType) {
        return manageTradeRecordDao.getTradeRecordOfWaitSync(date, payType);
    }

    @Override
    public List<TradeRecord> getTradeRecordOfSuccess(Date date, EnumPayType payType) {
        List<TradeRecord> tradeRecordList = manageTradeRecordDao.getTradeRecordOfSuccess(date, payType);
        return tradeRecordList;
    }

    @Override
    public Settlement addSettlement(List<TradeRecord> tradeRecordList) throws ServiceException {
        Settlement settlement = new Settlement();
        String serialNo = CommonUtil.randomOrderNo();
        settlement.setOrderNo(CommonUtil.CFCA_ORDER_NO);
        settlement.setSerialNo(serialNo);
        settlement.setStatus(EnumSettlementStatus.WAIT);
        manageTradeRecordDao.addSettlement(settlement);
//        List<TradeRecord> tradeRecords = new ArrayList<TradeRecord>();

        BigDecimal totalAmount = MathUtil.ZERO;
        for (TradeRecord tradeRecord: tradeRecordList) {
            totalAmount = totalAmount.add(tradeRecord.getAmount());
            tradeRecord.setSettlement(settlement);
            manageTradeRecordDao.updateTradeRecordToWaitSettlement(tradeRecord);

//            tradeRecords.add(tradeRecord);
        }


        settlement.setAmount(totalAmount);
//        settlement.setTradeRecords(tradeRecords);
        manageTradeRecordDao.updateSettlement(settlement);


        return settlement;
    }

    @Override
    public void updateSettlementToFinish(Long id) {

        Settlement settlement = manageTradeRecordDao.getSettlementById(id);

        List<TradeRecord> tradeRecords = manageTradeRecordDao.getTradeRecordBySettlementId(settlement.getId());

        for (TradeRecord tradeRecord: tradeRecords) {
            manageTradeRecordDao.updateTradeRecordToSettlement(tradeRecord);
        }

        settlement.setDealTime(new Date());
        settlement.setStatus(EnumSettlementStatus.FINISH);
        manageTradeRecordDao.updateSettlement(settlement);

    }

    @Autowired
    public void setManageTradeRecordDao(ManageTradeRecordDao manageTradeRecordDao) {
        this.manageTradeRecordDao = manageTradeRecordDao;
    }
}
