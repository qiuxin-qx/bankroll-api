package com.goldbao.bankroll.manage.controller;

import com.goldbao.bankroll.exception.EnumServiceMessage;
import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.PageableList;
import com.goldbao.bankroll.model.enums.EnumPayType;
import com.goldbao.bankroll.model.enums.EnumTradeStatus;
import com.goldbao.bankroll.model.enums.EnumTradeType;
import com.goldbao.bankroll.model.manage.sysuser.SysUserToken;
import com.goldbao.bankroll.model.trade.Cash;
import com.goldbao.bankroll.model.trade.TradeRecord;
import com.goldbao.bankroll.model.user.User;
import com.goldbao.bankroll.service.manage.sysuser.SysUserService;
import com.goldbao.bankroll.service.manage.user.ManageUserService;
import com.goldbao.bankroll.service.trade.RechargeService;
import com.goldbao.bankroll.service.trade.TradeRecordService;
import com.goldbao.bankroll.vo.*;
import com.goldbao.pay.PayApi;
import com.goldbao.pay.WithHoldingParameter;
import com.goldbao.pay.WithholdingResult;
import com.goldbao.utils.CommonUtil;
import com.goldbao.utils.MathUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * 交易（转账，提现处理。。。）处理
 * @author shuyu.fang
 */
@Controller
@RequestMapping("/admin/trade")
public class ManageTradeController {

    private ManageUserService manageUserService;

    private SysUserService sysUserService;

    private RechargeService rechargeService;
    private TradeRecordService tradeRecordService;

    private PayApi cfcaPayApi;

    // 后台充值转账
    @RequestMapping("/bgRecharge")
    public @ResponseBody ModelTemplate<RechargeResult> bgRecharge(String token, long userId, String money, String remark) {
        ModelTemplate<RechargeResult> r = null;
        try {
            SysUserToken userToken = sysUserService.getToken(token);

            if (userToken == null) {
                throw new ServiceException(EnumServiceMessage.SYS_USER_TOKEN_NOT_EXIST);
            }

            User user = manageUserService.getUserById(userId);
            if (user == null) {
                throw new ServiceException(EnumServiceMessage.USER_NOT_EXIST);
            }
            BigDecimal transferMoney = MathUtil.format(money);

            String orderNo = CommonUtil.randomOrderNo();

            if (rechargeService.addRecharge(user, orderNo, orderNo, transferMoney, MathUtil.ZERO, remark)) {
                rechargeService.updateDealRecharge(orderNo, orderNo, EnumPayType.OFFLINE, userToken.getUser());
            }

            RechargeResult result = new RechargeResult();
            result.setOrderNo(orderNo);
            result.setResp("");
            result.setDealTime(CommonUtil.formatDate(new Date()));

            r = new ModelTemplate<RechargeResult>(result);

        } catch (ServiceException ex) {
            r = new ModelTemplate<RechargeResult>(ex);
        }catch (Exception ex) {
            r = new ModelTemplate<RechargeResult>(ex);
        }

        return r;

    }

    @RequestMapping("/getTradeRecord")
    public @ResponseBody ModelTemplate<TradeRecordResult> getTradeRecord(String token, String orderNo) {
        ModelTemplate<TradeRecordResult> r = null;
        try {
            SysUserToken userToken = sysUserService.getToken(token);

            if (userToken == null) {
                throw new ServiceException(EnumServiceMessage.SYS_USER_TOKEN_NOT_EXIST);
            }

            TradeRecord record = tradeRecordService.getTradeRecordByOrderNo(orderNo);

            if (record == null)
                throw new ServiceException(EnumServiceMessage.TRADE_RECORD_NOT_EXIST);

            TradeRecordResult result = new TradeRecordResult();

            BeanUtils.copyProperties(record, result);
            result.setPayee(record.getPayee().getId());
            result.setPayer(record.getPayer().getId());
            result.setAddTime(CommonUtil.formatDate(record.getAddTime()));
            result.setDealTime(CommonUtil.formatDate(record.getDealTime()));
            result.setTradeStatus(record.getTradeStatus().ordinal());
            result.setTradeType(record.getTradeType().ordinal());

            r = new ModelTemplate<TradeRecordResult>(result);

        } catch (ServiceException ex) {
            r = new ModelTemplate<TradeRecordResult>(ex);
        }
        return r;
    }

    @RequestMapping("/getTradeRecordList")
    public @ResponseBody ModelTemplate<Pages<TradeRecordResult>> getTradeRecordList(String token, int tradeType, Long userId, int index, int size) {
        ModelTemplate<Pages<TradeRecordResult>> r = null;
        try {
            SysUserToken userToken = sysUserService.getToken(token);

            if (userToken == null) {
                throw new ServiceException(EnumServiceMessage.SYS_USER_TOKEN_NOT_EXIST);
            }

            PageableList<TradeRecord> result = tradeRecordService.getTradeRecordList(index, size, userId, EnumTradeType.parse(tradeType));
            Pages<TradeRecordResult> pages = new Pages<TradeRecordResult>();
            List<TradeRecordResult> resultList = new ArrayList<TradeRecordResult>();
            for (TradeRecord record: result.getList()) {
                TradeRecordResult result1 = new TradeRecordResult();

                BeanUtils.copyProperties(record, result1);
                result1.setPayee(record.getPayee().getId());
                result1.setPayer(record.getPayer().getId());
                result1.setAddTime(CommonUtil.formatDate(record.getAddTime()));
                result1.setDealTime(CommonUtil.formatDate(record.getDealTime()));
                result1.setTradeStatus(record.getTradeStatus().ordinal());
                result1.setTradeType(record.getTradeType().ordinal());
                resultList.add(result1);
            }
            BeanUtils.copyProperties(result, pages);
            pages.setPageCount(result.getPageCount());
            pages.setList(resultList);
            r = new ModelTemplate<Pages<TradeRecordResult>>(pages);
        } catch (ServiceException ex) {
            r = new ModelTemplate<Pages<TradeRecordResult>>(ex);
        }
        return r;
    }
    @RequestMapping("/getApplyCashList")
    public @ResponseBody ModelTemplate<Pages<CashResult>> getApplyCashList(String token, int status, int index, int size) {
        ModelTemplate<Pages<CashResult>> r = null;

        try {
            SysUserToken userToken = sysUserService.getToken(token);

            if (userToken == null) {
                throw new ServiceException(EnumServiceMessage.SYS_USER_TOKEN_NOT_EXIST);
            }

            EnumTradeStatus tradeStatus = EnumTradeStatus.parse(status);

            PageableList<Cash> cashes = tradeRecordService.getApplyCashList(tradeStatus, index, size);

            Pages<CashResult> vo = new Pages<CashResult>();
            List<CashResult> result = new ArrayList<CashResult>();

            for (Cash cash : cashes.getList()) {
                CashResult cashResult = new CashResult();
                BeanUtils.copyProperties(cash, cashResult);
                cashResult.setStatus(cash.getStatus().ordinal());
                cashResult.setPayType(EnumPayType.valueOf(cash.getPayType()));
                cashResult.setDealTime(CommonUtil.formatDate(cash.getDealTime()));
                cashResult.setAddTime(CommonUtil.formatDate(cash.getAddTime()));
                cashResult.setCreatorId(cash.getCreator().getId());
                cashResult.setCreatorName(cash.getCreator().getRealName());
                UserBankResult userBankResult = new UserBankResult();
                BeanUtils.copyProperties(cash.getUserBank(), userBankResult);
                cashResult.setUserBank(userBankResult);
                result.add(cashResult);
            }

            BeanUtils.copyProperties(cashes, vo);
            vo.setPageCount(cashes.getPageCount());
            vo.setList(result);

            r = new ModelTemplate<Pages<CashResult>>(vo);
        } catch (ServiceException ex) {
            r = new ModelTemplate<Pages<CashResult>>(ex);
        }catch (Exception ex) {
            r = new ModelTemplate<Pages<CashResult>>(ex);
        }

        return r;
    }

    @RequestMapping("/getApplyCash")
    public @ResponseBody ModelTemplate<CashResult> getApplyCash(String token, long cashId) {
        ModelTemplate<CashResult> r = null;

        try {
            SysUserToken userToken = sysUserService.getToken(token);

            if (userToken == null) {
                throw new ServiceException(EnumServiceMessage.SYS_USER_TOKEN_NOT_EXIST);
            }

            Cash cash = tradeRecordService.getApplyCashById(cashId);

            if (cash == null) {
                throw new ServiceException("提现申请不存在");
            }
            CashResult cashResult = new CashResult();
            BeanUtils.copyProperties(cash, cashResult);
            cashResult.setStatus(cash.getStatus().ordinal());
            cashResult.setPayType(EnumPayType.valueOf(cash.getPayType()));
            cashResult.setDealTime(CommonUtil.formatDate(cash.getDealTime()));
            cashResult.setAddTime(CommonUtil.formatDate(cash.getAddTime()));
            cashResult.setCreatorId(cash.getCreator().getId());
            cashResult.setCreatorName(cash.getCreator().getRealName());
            UserBankResult userBankResult = new UserBankResult();
            BeanUtils.copyProperties(cash.getUserBank(), userBankResult);
            cashResult.setUserBank(userBankResult);
            r = new ModelTemplate<CashResult>(cashResult);

        } catch (ServiceException ex) {
            r = new ModelTemplate<CashResult>(ex);
        } catch (Exception ex) {
            r = new ModelTemplate<CashResult>(ex);
        }
        return r;
    }

    // 批准并执行取现操作
    public ModelTemplate<Empty> cash(String token, long cashId) {
        ModelTemplate<Empty> r = null;
        try {
            SysUserToken userToken = sysUserService.getToken(token);

            if (userToken == null) {
                throw new ServiceException(EnumServiceMessage.SYS_USER_TOKEN_NOT_EXIST);
            }
            Cash cash = tradeRecordService.getApplyCashById(cashId);

            if (cash == null) {
                throw new ServiceException("提现申请不存在");
            }
            if (!cash.getStatus().equals(EnumTradeStatus.INIT)) {
                throw new ServiceException("提现申请状态不是\"已提交\"");
            }

            String orderNo = CommonUtil.randomOrderNo();

            WithHoldingParameter withHoldingParameter = new WithHoldingParameter();
            withHoldingParameter.setAmount(cash.getAmount());
            withHoldingParameter.setOrderNo(orderNo);
            withHoldingParameter.setSerialNo(orderNo);
            WithholdingResult withholdingResult = cfcaPayApi.withholding(withHoldingParameter);
            // 先发起代收请求，如果成功，则修改cash状态为success，并添加交易记录，修改用户的冻结资金
            if (withholdingResult.getStatus().equals("30")) {// 30代扣成功，可以直接处理业务
                tradeRecordService.addCashToTradeRecord(cash, orderNo, EnumPayType.CFCA, userToken.getUser());
            } else if (withholdingResult.getStatus().equals("20")) { // 代扣处理中，此时可以将流水号给cash记录加上
                tradeRecordService.updateCash(cash, orderNo, EnumPayType.CFCA);
            }
        } catch (ServiceException ex) {
            r = new ModelTemplate<Empty>(ex);
        }catch (Exception ex) {
            r = new ModelTemplate<Empty>(ex);
        }
        return r;
    }

    @Autowired
    public void setManageUserService(ManageUserService manageUserService) {
        this.manageUserService = manageUserService;
    }
    @Autowired
    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }
    @Autowired
    public void setRechargeService(RechargeService rechargeService) {
        this.rechargeService = rechargeService;
    }
    @Autowired
    public void setTradeRecordService(TradeRecordService tradeRecordService) {
        this.tradeRecordService = tradeRecordService;
    }
    @Autowired
    public void setCfcaPayApi(PayApi cfcaPayApi) {
        this.cfcaPayApi = cfcaPayApi;
    }
}
