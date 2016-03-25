package com.goldbao.bankroll.controller;

import javax.servlet.http.HttpServletRequest;

import com.goldbao.bankroll.exception.ServiceException;
import com.goldbao.bankroll.model.enums.EnumPayType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import payment.api.notice.Notice1318Request;
import payment.api.notice.Notice1363Request;
import payment.api.notice.NoticeRequest;
import payment.api.notice.NoticeResponse;

import com.goldbao.bankroll.service.trade.RechargeService;
import com.goldbao.bankroll.vo.ModelTemplate;
import com.goldbao.bankroll.vo.RechargeResult;
import com.goldbao.utils.CommonUtil;

/**
 * 支付回调接口
 * @author shuyu.fang
 */
@Controller
@RequestMapping("/paymentNotify")
public class PaymentNotifyController {

    private final static Logger logger = LoggerFactory.getLogger(PaymentNotifyController.class);

    private RechargeService rechargeService;


    // 中金支付回调
    @RequestMapping("/cfca")
    public @ResponseBody ModelTemplate<RechargeResult> cfca(HttpServletRequest req) {
        String json = CommonUtil.serializeJSON(req.getParameterMap());
        logger.debug(json);

        ModelTemplate<RechargeResult> r = null;
        RechargeResult result = new RechargeResult();
        try {
            String message = req.getParameter("message");
            String signature = req.getParameter("signature");

            NoticeRequest noticeRequest = new NoticeRequest(message, signature);
            if(noticeRequest.getTxCode().equals("1318")) {
                // 支付回调
                Notice1318Request nr = new Notice1318Request(noticeRequest.getDocument());
                logger.debug("cfca 1318 response body: {}", noticeRequest.getPlainText());
                logger.debug("cfca 1318 response orderNo:{} - amount: {} - status: {}", nr.getPaymentNo(), nr.getAmount(), nr.getStatus());

                if (nr.getStatus() == 20) { // 20，已支付
                    result.setOrderNo(nr.getPaymentNo());
                    result.setDealTime(nr.getBankNotificationTime());
                    rechargeService.updateDealRecharge(nr.getPaymentNo(), nr.getPaymentNo(), EnumPayType.CFCA);
                }
            } else if (noticeRequest.getTxCode().equals("1363")) {
                // 代收回调
                Notice1363Request nr = new Notice1363Request(noticeRequest.getDocument());
                logger.debug("cfca 1363 response body: {}", noticeRequest.getPlainText());
                logger.debug("cfca 1363 response orderNo:{} - amount: {} - status: {}", nr.getTxSN(), nr.getAmount(), nr.getStatus());

                if (nr.getStatus() == 20 || nr.getStatus() == 30) { // 20 已接受请求，30已处理请求
                    result.setOrderNo(nr.getTxSN());
                    result.setDealTime(nr.getBankTxTime());
                }
            }

        } catch (Exception e) {
//            r = new ModelTemplate<RechargeResult>(e);
            e.printStackTrace();
        }

        result.setResp(CommonUtil.base64Encode(new NoticeResponse().getMessage()));
        r = new ModelTemplate<RechargeResult>(result);

        return r;
    }
}
