package com.goldbao.bankroll.service.trade;

import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.PageableList;
import com.goldbao.bankroll.model.enums.EnumPayType;
import com.goldbao.bankroll.model.enums.EnumTradeStatus;
import com.goldbao.bankroll.model.enums.EnumTradeType;
import com.goldbao.bankroll.model.manage.sysuser.SysUser;
import com.goldbao.bankroll.model.trade.Cash;
import com.goldbao.bankroll.model.trade.TradeRecord;
import com.goldbao.bankroll.model.user.UserFund;

import java.math.BigDecimal;

/**
 * 交易记录
 * @author shuyu.fang
 */
public interface TradeRecordService {
    /**
     * 根据订单号获取一条交易记录
     * @param orderNo
     * @return
     */
    TradeRecord getTradeRecordByOrderNo(String orderNo);

    /**
     * 分页获取交易记录
     * @param index
     * @param size
     * @param recharge
     * @return
     */
    PageableList<TradeRecord> getTradeRecordList(int index, int size, Long userId, EnumTradeType recharge);

    /**
     * 申请提现
     * @param userFund 用户的资金账户信息
     * @param cashAmount 提现金额
     * @param bankId     中金银行编号
     * @param accountNumber  帐号
     * @param branchName     分支行名称
     * @param province       分支行所属省（市，区）
     * @param city           分支行所属市
     * @throws ServiceException
     */
    void addApplyCash(UserFund userFund, BigDecimal cashAmount, String bankId, String accountNumber, String branchName, String province, String city) throws ServiceException;

    /**
     * 获取取现申请列表
     * @param tradeStatus
     * @param index
     * @param size
     * @return
     */
    PageableList<Cash> getApplyCashList(EnumTradeStatus tradeStatus, int index, int size);

    /**
     * 根据id获取提现记录
     * @param cashId
     * @return
     */
    Cash getApplyCashById(long cashId);

    /**
     * 代收完成后，将cash记录加到record，并将用户冻结资金解冻，添加日志，等待结算
     * @param cash
     * @param orderNo
     * @param payType
     * @param user
     * @return
     */
    boolean addCashToTradeRecord(Cash cash, String orderNo, EnumPayType payType, SysUser user) throws ServiceException;
    /**
     * 代收提交，等待接受代收结果时，将流水号负给cash
     * @param cash
     * @param orderNo
     * @param payType
     * @return
     */
    void updateCash(Cash cash, String orderNo, EnumPayType payType);
}
