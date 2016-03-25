package com.goldbao.homs;

import com.goldbao.homs.result.*;
import com.goldbao.utils.CommonUtil;
import com.goldbao.utils.StringUtil;
import com.hundsun.t2sdk.common.core.context.ContextUtil;
import com.hundsun.t2sdk.common.share.dataset.DatasetService;
import com.hundsun.t2sdk.interfaces.share.dataset.IDataset;
import com.hundsun.t2sdk.interfaces.share.dataset.IDatasets;
import com.hundsun.t2sdk.interfaces.share.event.EventReturnCode;
import com.hundsun.t2sdk.interfaces.share.event.EventType;
import com.hundsun.t2sdk.interfaces.share.event.IEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * homs 事件
 * @author shuyu.fang
 */
public class HomsEventImpl implements HomsEvent {

    private final static Logger logger = LoggerFactory.getLogger(HomsEventImpl.class);

    private HomsSend homsSend;

    @Override
    public HomsLoginResult login(String operatorNo, String password, String ip, String mac) {
        Map<String, String> p = new HashMap<String, String>();
        p.put("operator_no", operatorNo);
        p.put("password", password);
        p.put("ip_address", ip);
        p.put("mac_address", mac);
        return sendAndParseObject(LOGIN, p, HomsLoginResult.class);
    }

    @Override
    public HomsLoginResult login(String operatorNo, String password) {
        return login(operatorNo, password, "", "");
    }

    @Override
    public HomsExecuteResult loginout(String token, String operatorNo) throws HomsException {
        Map<String, String> p = new HashMap<String, String>();
        p.put("operator_no", operatorNo);
        p.put("user_token", token);
        return sendAndParseObject(LOGINOUT, p, HomsExecuteResult.class);
    }

    @Override
    public HomsExecuteResult changePassword(String token, String operatorNo, String oldPassword, String newPassword) throws HomsException {
        Map<String, String> p = new HashMap<String, String>();
        p.put("operator_no", operatorNo);
        p.put("user_token", token);
        p.put("password_old", oldPassword);
        p.put("password_new", newPassword);
        return sendAndParseObject(CHANGE_PASSWORD, p, HomsExecuteResult.class);
    }

    @Override
    public HomsEntrustResult entrust(String token, String operatorNo, String fundAccount, String combineId,
                                     String stockAccount, String seatNo, String exchangeType, String stockCode,
                                     String entrustDirection, String ampriceType, String entrustAmount, String entrustPrice,
                                     String investWay, String closeDirection) throws HomsException {
        Map<String, String> p = new HashMap<String, String>();
        p.put("operator_no", operatorNo);
        p.put("user_token", token);
        p.put("fund_account", fundAccount);
        p.put("combine_id", combineId);
        p.put("stock_account", stockAccount);
        p.put("seat_no", seatNo);

        p.put("exchange_type", exchangeType);
        p.put("stock_code", stockCode);
        p.put("entrust_direction", entrustDirection);
        p.put("amprice_type", ampriceType);
        p.put("entrust_amount", entrustAmount);
        p.put("entrust_price", entrustPrice);
        p.put("invest_way", investWay);
        p.put("close_direction", closeDirection);
        return sendAndParseObject(ENTRUST, p, HomsEntrustResult.class);
    }

    @Override
    public HomsExecuteResult entrustWithdraw(String token, String operatorNo, String entrustNo) throws HomsException {
        Map<String, String> p = new HashMap<String, String>();
        p.put("user_token", token);
        p.put("operator_no", operatorNo);
        p.put("entrust_no", entrustNo);
        return sendAndParseObject(ENTRUST_WITHDRAW, p, HomsExecuteResult.class);
    }

    @Override
    public MoveAssetResult moveAsset(String token, String fundAccount, String combineId, String targetCombineId, String occurBalance, String businOpflag, String remark) throws HomsException {
        Map<String, String> p = new HashMap<String, String>();
        p.put("user_token", token);
        p.put("fund_account", fundAccount);
        p.put("combine_id", combineId);
        p.put("target_combine_id", targetCombineId);
        p.put("occur_balance", occurBalance);
        p.put("busin_opflag", businOpflag);
        p.put("remark", remark);
        return sendAndParseObject(ASSET_MOVE, p, MoveAssetResult.class);
    }

    @Override
    public List<HomsComboStockResult> queryComboStock(String token, String initDate, String fundAccount, String combineId, String exchangeType, String stockCode) throws HomsException {
        Map<String, String> p = new HashMap<String, String>();
        p.put("user_token", token);
        p.put("init_date", initDate);
        return sendAndParseList(QRY_COMBOSTOCK, p, HomsComboStockResult.class);
    }

    @Override
    public List<HomsComboFundResult> queryComboFund(String token, String fundAccount, String combineId) throws HomsException {
        Map<String, String> p = new HashMap<String, String>();
        p.put("user_token", token);
        p.put("fund_account", fundAccount);
        p.put("combine_id", combineId);

        return sendAndParseList(QRY_COMBOFUND, p, HomsComboFundResult.class);
    }

    @Override
    public List<HomsFutuComboFundResult> queryFutuComboFund(String token, String fundAccount, String combineId) throws HomsException {
        Map<String, String> p = new HashMap<String, String>();
        p.put("user_token", token);
        p.put("fund_account", fundAccount);
        p.put("combine_id", combineId);

        return sendAndParseList(QRY_FUTU_COMBOFUND, p, HomsFutuComboFundResult.class);
    }

    @Override
    public List<HomsQueryEntrustResult> queryEntrust(String token, String fundAccount, String combineId, String enAmEntrustStatus, String exchangeType, String entrustDirection, String beginBatchNo, String endBatchNo, String begineEntrustNo, String endEntrustNo) throws HomsException {
        Map<String, String> p = new HashMap<String, String>();
        p.put("user_token", token);
        p.put("fund_account", fundAccount);
        p.put("combine_id", combineId);
        return sendAndParseList(QRY_ENTRUST, p, HomsQueryEntrustResult.class);
    }
    @Override
    public List<HomsRealdealResult> queryRealdeal(String token, String fundAccount, String combineId) throws HomsException {
        return queryRealdeal(token, fundAccount, combineId, "", "", "", "", "", "");
    }
    @Override
    public List<HomsRealdealResult> queryRealdeal(String token, String fundAccount, String combineId, String exchangeType, String entrustDirection, String beginBatchNo, String endBatchNo, String begineEntrustNo, String endEntrustNo) throws HomsException {
        Map<String, String> p = new HashMap<String, String>();
        p.put("user_token", token);
        p.put("fund_account", fundAccount);
        p.put("combine_id", combineId);
        p.put("exchange_type", exchangeType);
        p.put("entrust_direction", entrustDirection);
        p.put("begin_batch_no", beginBatchNo);
        p.put("end_batch_no", endBatchNo);
        p.put("begin_entrust_no", begineEntrustNo);
        p.put("end_entrust_no", endEntrustNo);
        return sendAndParseList(QRY_REALDEAL, p, HomsRealdealResult.class);
    }

    @Override
    public List<HomsQueryAmountResult> queryAmount(String token, String fundAccount, String combineId, String exchangeType, String stockCode, String tradingDirection) throws HomsException {
        Map<String, String> p = new HashMap<String, String>();
        p.put("user_token", token);
        p.put("fund_account", fundAccount);
        p.put("combine_id", combineId);
        p.put("exchange_type", exchangeType);
        p.put("stock_code", stockCode);
//        p.put("asset_id", "14500");
        return sendAndParseList(QRY_AMOUNT, p, HomsQueryAmountResult.class);
    }

    @Override
    public List<HomsQueryFutureAmount> queryFutureAmount(String token, String fundAccount, String combineId, String exchangeType, String stockCode, String investWay) throws HomsException {
        Map<String, String> p = new HashMap<String, String>();
        p.put("user_token", token);
        p.put("fund_account", fundAccount);
        p.put("combine_id", combineId);
        p.put("exchange_type", exchangeType);
        p.put("stock_code", stockCode);
        p.put("invest_way", investWay);
        return sendAndParseList(QRY_FUTURE_AMOUNT, p, HomsQueryFutureAmount.class);
    }

    @Override
    public List<HomsHistoryEntrustResult> queryHistoryEntrust(String token, String initDate, String beginBatchNo, String endBatchNo, String begineEntrustNo, String endEntrustNo) throws HomsException {
        Map<String, String> p = new HashMap<String, String>();
        p.put("user_token", token);
        p.put("init_date", initDate);
        return sendAndParseList(QRY_HISTORY_ENTRUST, p, HomsHistoryEntrustResult.class);
    }

    @Override
    public List<HomsHistoryRealdealResult> queryHistoryReaddeal(String token, String initDate, String beginBatchNo, String endBatchNo, String begineEntrustNo, String endEntrustNo) throws HomsException {
        Map<String, String> p = new HashMap<String, String>();
        p.put("user_token", token);
        p.put("init_date", initDate);
        return sendAndParseList(QRY_HISTORY_REALDEAL, p, HomsHistoryRealdealResult.class);
    }

    @Override
    public List<HomsCombInfoResult> queryCombInfo(String token, String fundAccount, String combineId) throws HomsException {
        Map<String, String> p = new HashMap<String, String>();
        p.put("user_token", token);
        p.put("fund_account", fundAccount);
        p.put("combine_id", combineId);
        return sendAndParseList(QRY_COMB_INFO, p, HomsCombInfoResult.class);
    }

    @Override
    public List<HomsCombInfoResult> queryCombInfo(String token) throws HomsException {
        return queryCombInfo(token, "", "");
    }

    @Override
    public HomsExecuteResult changeAssetInfo(String token, String fundAccount, String combineId, String assetName, String assetStatus) throws HomsException {
        Map<String, String> p = new HashMap<String, String>();
        p.put("user_token", token);
        p.put("fund_account", fundAccount);
        p.put("combine_id", combineId);
        p.put("asset_name", assetName);
        p.put("asset_status", assetStatus);
        return sendAndParseObject(CHANGE_ASSET_INFO, p, HomsExecuteResult.class);
    }

    @Override
    public HomsQueryCombAssetResult queryCombAsset(String token, String fundAccount, String combineId) throws HomsException {
        Map<String, String> p = new HashMap<String, String>();
        p.put("user_token", token);
        p.put("fund_account", fundAccount);
        p.put("combine_id", combineId);

        return sendAndParseObject(QRY_COMB_ASSET, p, HomsQueryCombAssetResult.class);
    }

    @Override
    public List<HomsQueryCurrentResult> queryCurrent(String token, String fundAccount, String combineId, String initDate) throws HomsException {
        Map<String, String> p = new HashMap<String, String>();
        p.put("user_token", token);
        p.put("fund_account", fundAccount);
        p.put("combine_id", combineId);
        p.put("init_date", initDate);

        return sendAndParseList(QRY_CURRENT, p, HomsQueryCurrentResult.class);
    }

    @Override
    public List<HomsQueryStockCurrentResult> queryStockCurrent(String token, String fundAccount, String combineId) throws HomsException {
        Map<String, String> p = new HashMap<String, String>();
        p.put("user_token", token);
        p.put("fund_account", fundAccount);
        p.put("combine_id", combineId);

        return sendAndParseList(QRY_STOCK_CURRENT, p, HomsQueryStockCurrentResult.class);
    }

    @Override
    public List<HomsQueryRiskControlResult> queryRiskControl(String token, String serial) throws HomsException {
        Map<String, String> p = new HashMap<String, String>();
        p.put("user_token", token);
        p.put("serial_no", serial);

        return sendAndParseList(QRY_RISK_CONTROL, p, HomsQueryRiskControlResult.class);
    }

    @Override
    public HomsExecuteResult changeOperatorInfo(String token, String operatorNo, String businOpFlag, String operatorName, String expireDay, String forceChgPassword, String stockOpRight, String futureOpRight) throws HomsException {
        Map<String, String> p = new HashMap<String, String>();
        p.put("user_token", token);
        p.put("operator_no", operatorNo);
        p.put("busin_opflag", businOpFlag);
        p.put("operator_name", operatorName);
        p.put("expire_day", expireDay);
        p.put("force_chgpassword", forceChgPassword);
        p.put("stock_opright", stockOpRight);
        p.put("future_opright", futureOpRight);

        return sendAndParseObject(CHANGE_OPERATOR_INFO, p, HomsExecuteResult.class);
    }

    @Override
    public List<HomsQueryFutureLoanResult> queryFutureLoan(String token) throws HomsException {
        Map<String, String> p = new HashMap<String, String>();
        p.put("user_token", token);

        return sendAndParseList(QRY_FUTURE_LOAN, p, HomsQueryFutureLoanResult.class);
    }

    @Override
    public List<HomsOperatorInfoResult> queryOperatorInfo(String token, String operatorNo, String operatorStatus) throws HomsException {
        Map<String, String> p = new HashMap<String, String>();
        p.put("user_token", token);
        p.put("operator_no", operatorNo);
        p.put("operator_status", operatorStatus);

        return sendAndParseList(QRY_OPERATOR_INFO, p, HomsOperatorInfoResult.class);
    }

    @Override
    public List<HomsOperatorRightResult> queryOperatorRight(String token, String operatorNo, String fundAccount, String combineId) throws HomsException {
        Map<String, String> p = new HashMap<String, String>();
        p.put("user_token", token);
        p.put("operator_no", operatorNo);
        p.put("fund_account", fundAccount);
        p.put("combine_id", combineId);

        return sendAndParseList(QRY_OPERATOR_RIGHT, p, HomsOperatorRightResult.class);
    }

    @Override
    public HomsExecuteResult changeAssetRight(String token, String operatorNo, String fundAccount, String combineId, String assetId, String rights) throws HomsException {
        Map<String, String> p = new HashMap<String, String>();
        p.put("user_token", token);
        p.put("operator_no", operatorNo);
        p.put("fund_account", fundAccount);
        p.put("combine_id", combineId);
        p.put("asset_id", assetId);
        p.put("rights", rights);

        return sendAndParseObject(CHANGE_ASSET_RIGHT, p, HomsExecuteResult.class);
    }

    @Override
    public HomsExecuteResult moveStock(String token, String fundAccount, String combineId, String targetCombineId,
                                       String occurPrice, String occurAmount, String occurCost, String occurProfit,
                                       String exchangeType, String stockCode) throws HomsException {
        Map<String, String> p = new HashMap<String, String>();
        p.put("user_token", token);
        p.put("fund_account", fundAccount);
        p.put("combine_id", combineId);
        p.put("target_combine_id", targetCombineId);
        p.put("occur_price", occurPrice);
        p.put("occur_amount", occurAmount);
        p.put("occur_cost", occurCost);
        p.put("occur_profit", occurProfit);
        p.put("exchange_type", exchangeType);
        p.put("stock_code", stockCode);

        return sendAndParseObject(MOVE_STOCK, p, HomsExecuteResult.class);
    }

    private <T> T sendAndParseObject(String eventCode, Map<String, String> p, Class<T> clazz) {
        IEvent rsp = getEvent(eventCode, p);
        //获得结果集
        IDatasets result = rsp.getEventDatas();
        //获得结果集总数
        int datasetCount = result.getDatasetCount();
        if (datasetCount == 0)
            throw new HomsException("返回结果为空");
        String r = CommonUtil.serializeJSON(parseToObject(result));
        logger.debug(r);
        return CommonUtil.deserializeJSON(clazz, r);
    }

    private <T> List<T> sendAndParseList(String eventCode, Map<String, String> p, Class<T> clazz) {
        IEvent rsp = getEvent(eventCode, p);
        //获得结果集
        IDatasets result = rsp.getEventDatas();
        //获得结果集总数
        int datasetCount = result.getDatasetCount();
        if (datasetCount == 0)
            throw new HomsException("返回结果为空");
        String r = CommonUtil.serializeJSON(parseToList(result));
        logger.debug(r);
        return CommonUtil.deserializeJSONToList(clazz, r);
    }

    /**
     * homs操作封装
     * @param eventCode 接口调用编码
     * @param p         参数拼接
     * @return          同步操作返回
     */
    private IEvent getEvent(String eventCode, Map<String, String> p) {
        logger.debug("homs service: {}\tparams:\n{}", eventCode, p);
        IEvent event = ContextUtil.getServiceContext().getEventFactory()
                .getEventByAlias(eventCode, EventType.ET_REQUEST);

        if (p != null && p.size() > 0) {
            IDataset dataset = DatasetService.getDefaultInstance().getDataset();
            for (String key: p.keySet()) {
                if (!StringUtil.isEmpty(p.get(key))) {
                    dataset.addColumn(key);
                }
            }
            dataset.appendRow();
            for (String key: p.keySet()) {
                if (!StringUtil.isEmpty(p.get(key))) {
                    dataset.updateString(key, p.get(key));
                }
            }
            event.putEventData(dataset);
        }

        IEvent rsp = homsSend.sendReceive(event);
        if (rsp.getReturnCode() != EventReturnCode.I_OK) {
            throw new HomsException(rsp.getErrorNo(), rsp.getReturnCode()+" - " + rsp.getErrorNo() + " - " + rsp.getErrorInfo());
        }
        return rsp;
    }

    private Map<String, String> parseToObject(IDatasets result) {
        List<Map<String, String>> r = parse(result, 0);
        if (r.size() == 0) throw new HomsException("返回结果为空");
        return r.get(0);
    }

    private List<Map<String, String>> parse(IDatasets result, int i) {
        logger.debug("dataset - " + result.getDatasetName(i));
        // 开始读取单个结果集的信息
        IDataset ds = result.getDataset(i);
        int columnCount = ds.getColumnCount();
        String[] keys = new String[columnCount];
        // 遍历单个结果集列信息
        for (int j = 1; j <= columnCount; j++) {
//            logger.debug("{}|{}", ds.getColumnName(j), ds.getColumnType(j));
            keys[j - 1] = ds.getColumnName(j);
        }
        // 遍历单个结果集记录，遍历前首先将指针置到开始
        ds.beforeFirst();
        List<Map<String, String>> r = new ArrayList<Map<String, String>>();

        while (ds.hasNext()) {
            ds.next();
            Map<String, String> m = new HashMap<String, String>();
            for (int j = 1; j <= columnCount; j++) {
                m.put(keys[j - 1], ds.getString(j));
            }
            r.add(m);
            parseError(m);
        }
        return r;
    }

    private List<Map<String, String>> parseToList(IDatasets result) {
        List<Map<String, String>> r = new ArrayList<Map<String, String>>();
        //获得结果集总数
        int datasetCount = result.getDatasetCount();
        //遍历所有的结果集
        for (int i = 0; i < datasetCount; i++) {
            r.addAll(parse(result, i));
        }

        return r;
    }

    private void parseError(Map<String, String> m) throws HomsException {

        if (m.containsKey("error_no")) {
            String errorNo = m.get("error_no");
            if (!errorNo.equals("0")) {
                throw new HomsException(errorNo, m.get("error_info"));
            }
        }
    }

    @Autowired
    public void setHomsSend(HomsSend homsSend) {
        this.homsSend = homsSend;
    }
}
