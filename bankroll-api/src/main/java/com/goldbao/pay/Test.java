package com.goldbao.pay;

import payment.api.system.PaymentEnvironment;
import payment.api.tx.marketorder.Tx1311Request;
import payment.tools.util.Base64;

/**
 * Created by krain on 14/12/17.
 */
public class Test {

    public static void main(String[] args) {
        String paymentConfigPath = "/Users/krain/projects/bankroll-api/bankroll-api/src/main/resources/config/payment";
        try {
            PaymentEnvironment.initialize(paymentConfigPath);

            Tx1311Request request = new Tx1311Request();

            request.process();

            System.out.println("\t"+Base64.decode(request.getRequestMessage(), "utf-8"));
            System.out.println("\t"+request.getRequestSignature());
            System.out.println("\t"+PaymentEnvironment.paymentURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
