package com.goldbao.bankroll.service.trade;

import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.enums.EnumPayType;
import com.goldbao.bankroll.model.manage.sysuser.SysUser;
import com.goldbao.bankroll.model.trade.Recharge;
import com.goldbao.bankroll.model.user.User;

import java.math.BigDecimal;
import java.util.List;

/**
 * 充值service
 */
public interface RechargeService {

    /**
     * 添加一条充值记录
     * @param creator
     * @param orderNo
     * @param amount
     * @param fee
     * @return
     * @throws ServiceException
     */
    boolean addRecharge(User creator, String orderNo, String serialNo, BigDecimal amount, BigDecimal fee, String remark) throws ServiceException;

    /**
     * 修改一条充值记录为成交
     * @param orderNo 交易订单号
     * @return
     */
    boolean updateDealRecharge(String orderNo, String serialNo, EnumPayType payType) throws ServiceException;

    /**
     * 修改一条充值记录为成交
     * @param orderNo 交易订单号
     * @return
     */
    boolean updateDealRecharge(String orderNo, String serialNo, EnumPayType payType, SysUser sysUser) throws ServiceException;

    /**
     * 获取一条充值记录
     * @param orderNo
     * @return
     */
    Recharge getRechargeByOrderNo(String orderNo);

    /**
     * 获取用户的所有充值记录
     * @param userId
     * @return
     */
    List<Recharge> getRechargeListByUserId(Long userId);
}
