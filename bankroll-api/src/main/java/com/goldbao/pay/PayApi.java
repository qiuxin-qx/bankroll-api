package com.goldbao.pay;

import java.util.Map;

/**
 * 统一支付抽象接口
 */
public interface PayApi {

    /**
     * 支付接口标准构造form
     * @param param 支付参数
     * @return 不同支付方式返回不同的form构造，action表示请求地址
     * @throws PayException
     */
    Map<String, String> pay(PayParameter param) throws PayException;

    /**
     * 资金结算，目前只有中金一家
     * @param param 结算参数
     * @throws PayException
     */
    SettlementResult settlement(SettlementParameter param) throws PayException;


    /**
     * 支付订单查询，目前只有中金一家
     * @param param
     * @throws PayException
     */
    QueryPayResult queryPay(QueryPayParameter param) throws PayException;

    WithholdingResult withholding(WithHoldingParameter param) throws PayException;


}
