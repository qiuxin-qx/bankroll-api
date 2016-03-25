package com.goldbao.bankroll.controller;

import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.enums.EnumPayType;
import com.goldbao.bankroll.service.trade.RechargeService;
import com.goldbao.bankroll.vo.ModelTemplate;
import com.goldbao.bankroll.vo.RechargeResult;
import com.goldbao.utils.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import payment.api.notice.Notice1318Request;
import payment.api.notice.NoticeRequest;
import payment.api.notice.NoticeResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author shuyu.fang
 */
@Controller
@RequestMapping("/paymentReturn")
public class PaymentReturnController {

    private final static Logger logger = LoggerFactory.getLogger(PaymentReturnController.class);

    private RechargeService rechargeService;

    // 中金支付回调
    @RequestMapping("/cfca")
    public @ResponseBody
    ModelTemplate<RechargeResult> cfca(HttpServletRequest req) {
        String json = CommonUtil.serializeJSON(req.getParameterMap());
        logger.debug(json);

        ModelTemplate<RechargeResult> r = null;
        try {
            String message = req.getParameter("message");
            String signature = req.getParameter("signature");

            NoticeRequest noticeRequest = new NoticeRequest(message, signature);

            logger.debug("cfca response body: {}", noticeRequest.getPlainText());

            Notice1318Request nr = new Notice1318Request(noticeRequest.getDocument());

            RechargeResult result = new RechargeResult();
            result.setOrderNo(nr.getPaymentNo());
            result.setDealTime(nr.getBankNotificationTime());
            result.setResp(new NoticeResponse().getMessage());

            logger.debug("cfca response orderNo:{} - amount: {} - status: {}", nr.getPaymentNo(), nr.getAmount(), nr.getStatus());
            if (nr.getStatus() != 20) {
                throw new ServiceException("支付未成功");
            }

            rechargeService.updateDealRecharge(nr.getPaymentNo(), nr.getPaymentNo(), EnumPayType.CFCA);
            r = new ModelTemplate<RechargeResult>(result);
        } catch (Exception e) {
            r = new ModelTemplate<RechargeResult>(e);
        }

        return r;
    }

    @Autowired
    public void setRechargeService(RechargeService rechargeService) {
        this.rechargeService = rechargeService;
    }
}
