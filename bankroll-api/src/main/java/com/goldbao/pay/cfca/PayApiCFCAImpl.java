package com.goldbao.pay.cfca;

import com.goldbao.pay.*;
import com.goldbao.utils.CalendarUtil;
import com.goldbao.utils.CommonUtil;
import com.goldbao.utils.MathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import payment.api.system.PaymentEnvironment;
import payment.api.system.TxMessenger;
import payment.api.tx.marketorder.*;
import payment.api.vo.BankAccount;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 支付接口 ~ 中金实现
 */
public class PayApiCFCAImpl implements PayApi {

    private final static Logger logger = LoggerFactory.getLogger(PayApiCFCAImpl.class);

    private Properties config;


    public void init() {
        String realPath = config.getProperty("cfcaConfigPath");

        logger.debug(realPath);

        logger.debug("中金支付初始化");
        try {
//            String cfcaConfigPath = "config"+ File.separatorChar+"payment";
//            cfcaConfigPath = realPath + cfcaConfigPath;

            PaymentEnvironment.initialize(realPath);
        } catch (Exception e) {
            logger.error("中金支付初始化失败：\n{}", e.getMessage());
        }
    }

    @Override
    public Map<String, String> pay(PayParameter param) throws PayException {
        Tx1311Request request = new Tx1311Request();

        request.setOrderNo(param.getOrderNo());
        request.setPaymentNo(param.getSerialNo());


        long realAmount = MathUtil.yuanToFen(param.getAmount()).longValue();
        long realFee = MathUtil.yuanToFen(param.getFee()).longValue();
        request.setAmount(realAmount);
        request.setFee(realFee);

        request.setPayerID(param.getAccountId());
        request.setPayerName(param.getAccountName());
        request.setUsage(param.getProductName());
        request.setRemark(param.getRemark());
        request.setNotificationURL(param.getReturnUrl());
        request.setBankID(param.getBankId());

        request.setInstitutionID(config.getProperty("institutionID"));
        request.setAccountType(Integer.parseInt(config.getProperty("accountType")));

        try {
            request.process();
            Map<String, String> r = new HashMap<String, String>();
            r.put("message", request.getRequestMessage());
            r.put("signature", request.getRequestSignature());
            r.put("action", PaymentEnvironment.paymentURL);

            return r;
        } catch (Exception e) {
            throw new PayException(e.getMessage());
        }
    }

    @Override
    public SettlementResult settlement(SettlementParameter param) throws PayException {
        Tx1341Request tx = new Tx1341Request();
        // 结算基本信息
        tx.setAccountType(Integer.parseInt(config.getProperty("accountType")));
        tx.setAmount(MathUtil.yuanToFen(param.getAmount()).longValue());
        tx.setInstitutionID(config.getProperty("institutionID"));
        tx.setOrderNo(param.getOrderNo());
        tx.setSerialNumber(param.getSerialNumber());

        BankAccount bankAccount = new BankAccount();

        BankAccount2 bankAccount2 = param.getBankAccount();
        bankAccount.setBankID(bankAccount2.getBankID());
        bankAccount.setAccountName(bankAccount2.getAccountName());
        bankAccount.setAccountNumber(bankAccount2.getAccountNumber());
        bankAccount.setBranchName(bankAccount2.getBranchName());
        bankAccount.setProvince(bankAccount2.getProvince());
        bankAccount.setCity(bankAccount2.getCity());
        tx.setBankAccount(bankAccount);

        // 结算流水
//        if (param.getRepaymentList() != null && param.getRepaymentList().size() > 0) {
//            tx.setPaymentNoList(param.getRepaymentList());
//        }
        tx.setRemark("");
        try {
            tx.process();

            TxMessenger txMessenger = new TxMessenger();
            String[] respMsg = txMessenger.send(tx.getRequestMessage(), tx.getRequestSignature());
            Tx134xResponse resp = new Tx134xResponse(respMsg[0], respMsg[1]);
            SettlementResult r = new SettlementResult();
            if (resp.getCode().equals("2000")) {
                r.setStatus("20");
                r.setMessage(resp.getMessage());
            } else {
                r.setStatus(r.getStatus());
                r.setMessage(resp.getMessage());
            }
            return r;
        } catch (Exception ex) {
            throw new PayException(ex.getMessage());
        }
    }

    @Override
    public QueryPayResult queryPay(QueryPayParameter param) throws PayException {

        Tx1320Request req = new Tx1320Request();
        req.setInstitutionID(config.getProperty("institutionID"));
        req.setPaymentNo(param.getSerialNo());

        QueryPayResult result = null;

        try {
            req.process();

            TxMessenger txMessenger = new TxMessenger();
            String[] respMsg = txMessenger.send(req.getRequestMessage(), req.getRequestSignature());

            Tx1320Response resp = new Tx1320Response(respMsg[0], respMsg[1]);
            if (resp != null && resp.getCode().equals("2000")) {
                result = new QueryPayResult();
                result.setOrderNo(CommonUtil.CFCA_ORDER_NO);
                result.setSerialNo(resp.getPaymentNo());
                result.setAmount(String.valueOf(resp.getAmount()));
                result.setStatus(String.valueOf(resp.getStatus()));
                result.setDealTime(CommonUtil.parseDate(resp.getBankNotificationTime(), "yyyyMMddHHmmss"));
                return result;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
//            throw new PayException(ex.getMessage());
        }

        return null;
    }

    @Override
    public WithholdingResult withholding(WithHoldingParameter param) throws PayException {
        Tx1361Request request = new Tx1361Request();

        request.setAccountType(Integer.parseInt(config.getProperty("accountType")));
        request.setAmount(MathUtil.yuanToFen(param.getAmount()).longValue());

        request.setOrderNo(param.getOrderNo());
        request.setTxSN(param.getSerialNo());

        request.setInstitutionID(config.getProperty("institutionID"));
        request.setAccountName("aaa");
        request.setAccountNumber("1111");

        request.setBankID("700");

        request.setIdentificationType("0");
        request.setIdentificationNumber("12322222222222222");

        WithholdingResult result = new WithholdingResult();
        try {
            request.process();

            TxMessenger txMessenger = new TxMessenger();
            String[] respMsg = txMessenger.send(request.getRequestMessage(), request.getRequestSignature());

            Tx1361Response txResponse = new Tx1361Response(respMsg[0], respMsg[1]);
            if (txResponse.getCode().equals("2000")) {
                result.setOrderNo(param.getOrderNo());
                result.setSerialNo(param.getSerialNo());
                result.setAmount(param.getAmount());
                result.setStatus(String.valueOf(txResponse.getStatus()));
            }else {
                result.setStatus("500");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("500");
        }
        return  result;
    }

    /**
     * 中金支付的一些配置信息
     * @param config
     */
    public void setConfig(Properties config) {
        this.config = config;
    }
}
