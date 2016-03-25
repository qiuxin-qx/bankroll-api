package com.goldbao.bankroll.controller;

import com.goldbao.bankroll.vo.ModelTemplate;
import com.goldbao.utils.CommonUtil;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import payment.api.system.TxMessenger;
import payment.api.tx.marketorder.*;
import payment.api.tx.statement.Tx1810Request;
import payment.api.tx.statement.Tx1810Response;
import payment.api.vo.BankAccount;

import java.util.Date;

/**
 * @author shuyu.fang
 */
@Controller
@RequestMapping("/test")
public class TestController {

    final static String INSTITUTION_ID = "001378";

    final static Logger logger = org.slf4j.LoggerFactory.getLogger(TestController.class);

    // 1341 市场订单结算
    @RequestMapping("/cfca1341")
    public @ResponseBody ModelTemplate<Tx134xResponse> cfca1341(String amount, String orderNo) {
        ModelTemplate<Tx134xResponse> r = null;
        try {
            String serialNo = CommonUtil.randomOrderNo();

            Tx1341Request tx = new Tx1341Request();
            tx.setAccountType(11);
            tx.setAmount(Long.parseLong(amount));
            tx.setInstitutionID(INSTITUTION_ID);
            tx.setOrderNo(orderNo);//CommonUtil.CFCA_ORDER_NO+1);
            tx.setSerialNumber(serialNo);

            logger.debug("结算流水：" + serialNo);

//            tx.setPaymentAccountName("");
//            tx.setPaymentAccountNumber("");

//            List<String> paymentNoList = new ArrayList<String>();
//
//            paymentNoList.add("1220150304");
//            paymentNoList.add("772263807b654f0dbb3d0fcf1523235d");
//            paymentNoList.add("2054eeded9114fbcbc23c0d244104bb3");

//            tx.setPaymentNoList(paymentNoList);

            BankAccount bankAccount = new BankAccount();
            bankAccount.setBankID("700");
            bankAccount.setAccountName("aa");
            bankAccount.setAccountNumber("1111");
            bankAccount.setBranchName("ccs");
            bankAccount.setProvince("sssss");
            bankAccount.setCity("aaaaa");
            tx.setBankAccount(bankAccount);

            tx.setRemark("");

            tx.process();

            TxMessenger txMessenger = new TxMessenger();
            String[] respMsg = txMessenger.send(tx.getRequestMessage(), tx.getRequestSignature());
            Tx134xResponse resp = new Tx134xResponse(respMsg[0], respMsg[1]);

            r = new ModelTemplate<Tx134xResponse>(resp);

        } catch (Exception e) {
            r = new ModelTemplate<Tx134xResponse>(e);
            e.printStackTrace();
        }

        return r;

    }

    // 1320 市场订单查询
    @RequestMapping("/cfca1320")
    public @ResponseBody ModelTemplate<Tx1320Response> cfca1320(String orderNo) {
        ModelTemplate<Tx1320Response> r = null;
        try {

            Tx1320Request req = new Tx1320Request();
            req.setInstitutionID(INSTITUTION_ID);
            req.setPaymentNo(orderNo);

            req.process();

            TxMessenger txMessenger = new TxMessenger();
            String[] respMsg = txMessenger.send(req.getRequestMessage(), req.getRequestSignature());

            Tx1320Response resp = new Tx1320Response(respMsg[0], respMsg[1]);

            r = new ModelTemplate<Tx1320Response>(resp);


        } catch (Exception e) {
            r = new ModelTemplate<Tx1320Response>(e);
            e.printStackTrace();
        }

        return r;

    }

    // 1343 市场订单结算（退款）
    @RequestMapping("/cfca1343")
    public @ResponseBody ModelTemplate<Tx134xResponse> cfca1343() {
        ModelTemplate<Tx134xResponse> r = null;
        try {
            String orderNo = CommonUtil.randomOrderNo();

            Tx1343Request tx = new Tx1343Request();
            tx.setAccountType(11);
            tx.setAmount(1200);
            tx.setInstitutionID(INSTITUTION_ID);
            tx.setOrderNo("5d729425c7114ccf9f05973cf602526f");
            tx.setSerialNumber(orderNo);

            BankAccount bankAccount = new BankAccount();
            bankAccount.setBankID("700");
            bankAccount.setAccountName("");
            bankAccount.setAccountNumber("");
            bankAccount.setBranchName("");
            bankAccount.setProvince("");
            bankAccount.setCity("");
            tx.setBankAccount(bankAccount);

            tx.setRemark("");

            tx.process();

            TxMessenger txMessenger = new TxMessenger();
            String[] respMsg = txMessenger.send(tx.getRequestMessage(), tx.getRequestSignature());
            Tx134xResponse resp = new Tx134xResponse(respMsg[0], respMsg[1]);

            r = new ModelTemplate<Tx134xResponse>(resp);

        } catch (Exception e) {
            r = new ModelTemplate<Tx134xResponse>(e);
            e.printStackTrace();
        }

        return r;
    }

    // 1321 付款账户信息查询
    @RequestMapping("/cfca1321")
    public @ResponseBody ModelTemplate<Tx1321Response> cfca1321(String orderNo) {
        ModelTemplate<Tx1321Response> r = null;
        try {
//            String orderNo = CommonUtil.randomOrderNo();

            Tx1321Request tx = new Tx1321Request();
            tx.setInstitutionID(INSTITUTION_ID);
            tx.setPaymentNo(orderNo);

            tx.process();

            TxMessenger txMessenger = new TxMessenger();
            String[] respMsg = txMessenger.send(tx.getRequestMessage(), tx.getRequestSignature());
            Tx1321Response resp = new Tx1321Response(respMsg[0], respMsg[1]);

            r = new ModelTemplate<Tx1321Response>(resp);



        } catch (Exception e) {
            r = new ModelTemplate<Tx1321Response>(e);
            e.printStackTrace();
        }

        return r;
    }

    // 1361 单笔代付
    @RequestMapping("/cfca1361")
    public @ResponseBody ModelTemplate<Tx1361Response> cfca1361() {

        Tx1361Request request = new Tx1361Request();

        request.setInstitutionID(INSTITUTION_ID);
        request.setAccountName("aaa");
        request.setAccountNumber("1111");
        request.setAccountType(11);
        request.setAmount(1000);
        request.setBankID("700");

        request.setIdentificationType("0");
        request.setIdentificationNumber("12322222222222222");

        request.setOrderNo(CommonUtil.randomOrderNo());
        request.setTxSN(CommonUtil.randomOrderNo());

//        request.setBranchName("ccs");
//        request.setCity("xx");
//        request.setProvince("xx");

        ModelTemplate<Tx1361Response> r = null;
        try {
            request.process();

            TxMessenger txMessenger = new TxMessenger();
            String[] respMsg = txMessenger.send(request.getRequestMessage(), request.getRequestSignature());

            Tx1361Response txResponse = new Tx1361Response(respMsg[0], respMsg[1]);
            r = new ModelTemplate<Tx1361Response>(txResponse);
        } catch (Exception e) {
            r = new ModelTemplate<Tx1361Response>(e);
            e.printStackTrace();
        }

        return r;
    }

    // 结算查询，也就说结算接口的即时反馈结果是不可靠的，还要经常发起结算查询状态，直到成功为止
    @RequestMapping("/cfca1350")
    public @ResponseBody ModelTemplate<Tx1350Response> cfca1350(String orderNo) {
        Tx1350Request request = new Tx1350Request();
        request.setInstitutionID(INSTITUTION_ID);
        request.setSerialNumber(orderNo);

        ModelTemplate<Tx1350Response> r = null;
        try {
            request.process();

            TxMessenger txMessenger = new TxMessenger();
            String[] respMsg = txMessenger.send(request.getRequestMessage(), request.getRequestSignature());

            Tx1350Response response = new Tx1350Response(respMsg[0], respMsg[1]);

            r = new ModelTemplate<Tx1350Response>(response);

        } catch (Exception e) {
            e.printStackTrace();
            r = new ModelTemplate<Tx1350Response>(e);
        }

        return r;
    }

    @RequestMapping("/cfca1362")
    public @ResponseBody ModelTemplate<Tx1362Response> cfca1362(String orderNo) {
        Tx1362Request request = new Tx1362Request();
        request.setInstitutionID(INSTITUTION_ID);
        request.setTxSN(orderNo);

        ModelTemplate<Tx1362Response> r = null;
        try {
            request.process();

            TxMessenger txMessenger = new TxMessenger();
            String[] respMsg = txMessenger.send(request.getRequestMessage(), request.getRequestSignature());

            Tx1362Response response = new Tx1362Response(respMsg[0], respMsg[1]);

            r = new ModelTemplate<Tx1362Response>(response);

        } catch (Exception e) {
            e.printStackTrace();
            r = new ModelTemplate<Tx1362Response>(e);
        }

        return r;
    }
    // TODO 1810接口非常重要，可以确定一笔交易成功与否全看他了。
    @RequestMapping("/cfca1810")
    public @ResponseBody ModelTemplate<Tx1810Response> cfca1810(String date) {
        ModelTemplate<Tx1810Response> r = null;
        try {
            Date vDate = null;
            vDate = CommonUtil.parseDate(date, "yyyy-MM-dd");
            Tx1810Request txRequest = new Tx1810Request();
            txRequest.setInstitutionID(INSTITUTION_ID);
            txRequest.setDate(vDate);

            txRequest.process();

            TxMessenger txMessenger = new TxMessenger();
            String[] respMsg = txMessenger.send(txRequest.getRequestMessage(), txRequest.getRequestSignature());


            Tx1810Response resp =new Tx1810Response(respMsg[0], respMsg[1]);

            r = new ModelTemplate<Tx1810Response>(resp);


        } catch (Exception e) {
            r = new ModelTemplate<Tx1810Response>(e);
            e.printStackTrace();
        }
        return r;

    }

    /**
     * 1. 余额， 股票价格
     * 2. 确定佣金： 余额*0.003 < 5 = 5
     * 3. 可买的金额： （余额-佣金）
     * 4. 大致的可买数量：金额/ 价格
     * 5. 取模：数量%100==0， 数量
     * 6. 数量-模
     * return
     *
     */

}
