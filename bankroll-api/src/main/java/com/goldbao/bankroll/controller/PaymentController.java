package com.goldbao.bankroll.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.goldbao.bankroll.model.user.UserFund;
import com.goldbao.bankroll.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goldbao.bankroll.exception.EnumServiceMessage;
import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.PageableList;
import com.goldbao.bankroll.model.enums.EnumTradeType;
import com.goldbao.bankroll.model.trade.TradeRecord;
import com.goldbao.bankroll.model.user.User;
import com.goldbao.bankroll.model.user.UserToken;
import com.goldbao.bankroll.service.trade.RechargeService;
import com.goldbao.bankroll.service.trade.TradeRecordService;
import com.goldbao.bankroll.service.user.UserService;
import com.goldbao.pay.PayApi;
import com.goldbao.pay.PayException;
import com.goldbao.pay.PayParameter;
import com.goldbao.utils.CommonUtil;
import com.goldbao.utils.MathUtil;
import org.springframework.web.servlet.ModelAndView;

/**
 * 充值提现相关业务
 */
@Controller
@RequestMapping("/trade")
public class PaymentController {

    private PayApi cfcaPayApi;

    private UserService userService;

    private RechargeService rechargeService;

    private TradeRecordService tradeRecordService;

    // 目前是中金的接口模式，后期有新加接口需做调整
    @RequestMapping("/recharge")
    public @ResponseBody ModelTemplate<PaymentResult> pay(String token, String amount, String bankId,String returnUrl, String remark, String productName) {

        ModelTemplate<PaymentResult> r = null;
        try {
            UserToken userToken = userService.getUserToken(token);
            if (userToken == null) {
                throw new ServiceException(EnumServiceMessage.USER_TOKEN_NOT_EXIST);
            }

            User user = userToken.getUser();

            String orderNo = CommonUtil.CFCA_ORDER_NO;

            String serialNo = CommonUtil.randomOrderNo();

            // 先加数据库
            if (rechargeService.addRecharge(user, orderNo, serialNo, MathUtil.format(amount), MathUtil.format("0"), remark)) {

                PayParameter param = new PayParameter();

                param.setAmount(MathUtil.format(amount));
                param.setFee(MathUtil.format("0"));
                param.setOrderNo(orderNo);
                param.setSerialNo(serialNo);
                param.setBankId(bankId);
                param.setAccountId(user.getId() + "");
                param.setAccountName(user.getUsername());
//                param.setNotifyUrl(notifyUrl);
                param.setReturnUrl(returnUrl);

                Map<String, String> p = cfcaPayApi.pay(param);
                PaymentResult result = new PaymentResult();
                result.setOrderNo(serialNo);
                result.setForm(p);
                r = new ModelTemplate<PaymentResult>(result);
            } else {
                throw new ServiceException("添加记录失败");
            }
        } catch (ServiceException ex) {
            r = new ModelTemplate<PaymentResult>(ex);
        } catch (PayException ex) {
            r = new ModelTemplate<PaymentResult>(ex);
        } catch (Exception ex) {
            r = new ModelTemplate<PaymentResult>(ex);
            ex.printStackTrace();
        }

        return r;
    }

    @RequestMapping("/pay2")
    public ModelAndView pay2(String token, String amount, String bankId,String returnUrl, String remark, String productName) {
        ModelAndView mav = new ModelAndView("pay/cfca");
        try {
            UserToken userToken = userService.getUserToken(token);
            if (userToken == null) {
                throw new ServiceException(EnumServiceMessage.USER_TOKEN_NOT_EXIST);
            }

            User user = userToken.getUser();
            String orderNo = CommonUtil.CFCA_ORDER_NO; // CommonUtil.randomOrderNo();
            String serialNo = CommonUtil.randomOrderNo();

            // 先加数据库
            if (rechargeService.addRecharge(user, orderNo, serialNo, MathUtil.format(amount), MathUtil.format("0"), remark)) {

                PayParameter param = new PayParameter();

                param.setAmount(MathUtil.format(amount));
                param.setFee(MathUtil.format("0"));
                param.setOrderNo(orderNo);
                param.setSerialNo(serialNo);
                param.setBankId(bankId);
                param.setAccountId(user.getId() + "");
                param.setAccountName(user.getUsername());
//                param.setNotifyUrl(notifyUrl);
                param.setReturnUrl(returnUrl);

                Map<String, String> p = cfcaPayApi.pay(param);
                PaymentResult result = new PaymentResult();
                result.setOrderNo(orderNo);
                result.setForm(p);
                mav.addObject("r", result.getForm());
                mav.addObject("orderNo", orderNo);
                mav.addObject("serialNo", param.getSerialNo());
            } else {
                throw new ServiceException("添加记录失败");
            }
        } catch (ServiceException ex) {
            mav.addObject("error", ex.getMessage());
        } catch (PayException ex) {
            mav.addObject("error", ex.getMessage());
        } catch (Exception ex) {
            mav.addObject("error", ex.getMessage());
            ex.printStackTrace();
        }

        return mav;
    }

    // 提交提现申请，后台审核并打款给用户，目前是手动模式，不自动操作
    // 后期如果有固定的提现模式，可考虑自动提现以便提高效率
    @RequestMapping("/applyCash")
    public @ResponseBody ModelTemplate<Empty> applyCash(String token, String amount, String bankId, String accountNumber, String branchName, String province, String city) {
        ModelTemplate<Empty> r = null;

        try {
            UserToken userToken = userService.getUserToken(token);
            if (userToken == null) {
                throw new ServiceException(EnumServiceMessage.USER_TOKEN_NOT_EXIST);
            }

            UserFund userFund = userToken.getUser().getUserFund();

            BigDecimal cashAmount = MathUtil.format(amount);

            tradeRecordService.addApplyCash(userFund, cashAmount, bankId, accountNumber, branchName, province, city);


        } catch (ServiceException ex) {

        }

        return r;

    }

    @RequestMapping("/getTradeRecord")
    public @ResponseBody ModelTemplate<TradeRecordResult> getTradeRecord(String token, String orderNo) {
        ModelTemplate<TradeRecordResult> r = null;
        try {
            UserToken userToken = userService.getUserToken(token);
            if (userToken == null) {
                throw new ServiceException(EnumServiceMessage.USER_TOKEN_NOT_EXIST);
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
    public @ResponseBody ModelTemplate<Pages<TradeRecordResult>> getTradeRecordList(String token, int tradeType, int index, int size) {
        ModelTemplate<Pages<TradeRecordResult>> r = null;
        try {
            UserToken userToken = userService.getUserToken(token);
            if (userToken == null) {
                throw new ServiceException(EnumServiceMessage.USER_TOKEN_NOT_EXIST);
            }

            PageableList<TradeRecord> result = tradeRecordService.getTradeRecordList(index, size, userToken.getUser().getId(), EnumTradeType.parse(tradeType));
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

    @Autowired
    public void setCfcaPayApi(PayApi cfcaPayApi) {
        this.cfcaPayApi = cfcaPayApi;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setRechargeService(RechargeService rechargeService) {
        this.rechargeService = rechargeService;
    }
    @Autowired
    public void setTradeRecordService(TradeRecordService tradeRecordService) {
        this.tradeRecordService = tradeRecordService;
    }
}
