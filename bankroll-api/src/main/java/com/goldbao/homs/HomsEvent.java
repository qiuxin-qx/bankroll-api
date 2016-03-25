package com.goldbao.homs;


import com.goldbao.homs.result.*;

import java.util.List;

/**
 * homs 事件接口
 */
public interface HomsEvent {

    /**
     * 登录
     */
    String LOGIN = "769000";

    /**
     * 退出登录
     */
    String LOGINOUT = "769001";

    /**
     * 修改密码
     */
    String CHANGE_PASSWORD = "769002";

    /**
     * 普通委托
     */
    String ENTRUST = "769100";

    /**
     * 委托撤销
     */
    String ENTRUST_WITHDRAW = "769101";

    /**
     * 资金划转
     */
    String ASSET_MOVE = "769115";

    /**
     * 组合持仓查询
     */
    String QRY_COMBOSTOCK = "769150";

    /**
     * 组合层现货可用资金查询
     */
    String QRY_COMBOFUND = "769151";

    /**
     * 组合层期货可用资金查询
     */
    String QRY_FUTU_COMBOFUND = "769152";

    /**
     * 委托查询
     */
    String QRY_ENTRUST = "769153";

    /**
     * 成交查询
     */
    String QRY_REALDEAL = "769155";

    /**
     * 组合层现货可用数量查询
     */
    String QRY_AMOUNT = "769156";

    /**
     * 组合层期货可用数量查询
     */
    String QRY_FUTURE_AMOUNT = "769157";

    /**
     * 历史委托查询
     */
    String QRY_HISTORY_ENTRUST = "769900";

    /**
     * 历史成交查询
     */
    String QRY_HISTORY_REALDEAL = "769901";

    /**
     * 组合信息查询
     */
    String QRY_COMB_INFO = "769902";

    /**
     * 修改单元信息
     */
    String CHANGE_ASSET_INFO = "769952";

    /**
     * 组合资产查询
     */
    String QRY_COMB_ASSET = "769201";

    /**
     * 组合流水查询
     */
    String QRY_CURRENT = "769202";

    /**
     * 股票资产查询
     */
    String QRY_STOCK_CURRENT = "769203";

    /**
     * 公司层已设置风控信息查询
     */
    String QRY_RISK_CONTROL = "769905";

    /**
     * 修改操作员信息
     */
    String CHANGE_OPERATOR_INFO = "769301";

    /**
     * 借贷信息查询
     */
    String QRY_FUTURE_LOAN = "769204";

    /**
     * 操作员信息查询
     */
    String QRY_OPERATOR_INFO = "769302";

    /**
     * 账户操作权限查询
     */
    String QRY_OPERATOR_RIGHT = "769303";

    /**
     * 修改账户操作权限
     */
    String CHANGE_ASSET_RIGHT = "769304";

    /**
     * 股票划转
     */
    String MOVE_STOCK = "769117";



    /**
     * 登录
     * @param operatorNo
     * @param password
     * @param ip
     * @param mac
     * @return
     */
    HomsLoginResult login(String operatorNo, String password, String ip, String mac);
    /**
     * 登录
     * @param operatorNo
     * @param password
     * @return
     */
    HomsLoginResult login(String operatorNo, String password);

    /**
     * 退出登录
     * @param token
     * @param operatorNo
     * @return
     */
    HomsExecuteResult loginout(String token, String operatorNo) throws HomsException;

    /**
     * 修改密码<br/>
     * 修改本人密码，必须不能传operatorNo, 但需传原密<br/>
     * 修改别人（同公司）,必须不能传原密
     * @param token
     * @param operatorNo
     * @param oldPassword
     * @param newPassword
     * @return
     */
    HomsExecuteResult changePassword(String token, String operatorNo, String oldPassword, String newPassword) throws HomsException;

    /**
     * 普通委托
     * @param token
     * @param operatorNo
     * @param fundAccount
     * @param combineId
     * @param stockAccount
     * @param seatNo
     * @param exchangeType
     * @param stockCode
     * @param entrustDirection
     * @param ampriceType
     * @param entrustAmount
     * @param entrustPrice
     * @param investWay
     * @param closeDirection
     * @return
     */
    HomsEntrustResult entrust(String token, String operatorNo, String fundAccount, String combineId, String stockAccount,
                   String seatNo, String exchangeType, String stockCode, String entrustDirection, String ampriceType,
                   String entrustAmount, String entrustPrice, String investWay, String closeDirection) throws HomsException;

    /**
     * 委托撤销
     * @param token
     * @param operatorNo
     * @param entrustNo
     * @return
     */
    HomsExecuteResult entrustWithdraw(String token, String operatorNo, String entrustNo) throws HomsException;

    /**
     * 资金划转
     * @param token
     * @param fundAccount
     * @param combineId
     * @param targetCombineId
     * @param occurBalance
     * @param businOpflag 107--现金划转，117--保证金划转
     * @param remark
     * @return
     */
    MoveAssetResult moveAsset(String token, String fundAccount, String combineId, String targetCombineId,
                     String occurBalance, String businOpflag, String remark) throws HomsException;

    /**
     * 组合持仓查询
     * @param token
     * @param initDate
     * @param fundAccount
     * @param combineId
     * @param exchangeType
     * @param stockCode
     * @return
     */
    List<HomsComboStockResult> queryComboStock(String token, String initDate, String fundAccount, String combineId,
                           String exchangeType, String stockCode) throws HomsException;

    /**
     * 组合层现货可用资金查询
     * @param token
     * @param fundAccount
     * @param combineId
     * @return
     */
    List<HomsComboFundResult> queryComboFund(String token, String fundAccount, String combineId) throws HomsException;

    /**
     * 组合层期货可用资金查询
     * @param token
     * @param fundAccount
     * @param combineId
     * @return
     */
    List<HomsFutuComboFundResult> queryFutuComboFund(String token, String fundAccount, String combineId) throws HomsException;

    /**
     * 委托查询
     * @param token
     * @param fundAccount
     * @param combineId
     * @param enAmEntrustStatus
     * @param exchangeType
     * @param entrustDirection
     * @param beginBatchNo
     * @param endBatchNo
     * @param begineEntrustNo
     * @param endEntrustNo
     * @return
     */
    List<HomsQueryEntrustResult> queryEntrust(String token, String fundAccount, String combineId, String enAmEntrustStatus, String exchangeType,
                        String entrustDirection, String beginBatchNo, String endBatchNo, String begineEntrustNo, String endEntrustNo) throws HomsException;

    /**
     * 成交查询
     * @param token
     * @param fundAccount
     * @param combineId
     * @return
     * @throws HomsException
     */
    List<HomsRealdealResult> queryRealdeal(String token, String fundAccount, String combineId) throws HomsException;

    /**
     * 成交查询
     * @param token
     * @param fundAccount
     * @param combineId
     * @param exchangeType
     * @param entrustDirection
     * @param beginBatchNo
     * @param endBatchNo
     * @param begineEntrustNo
     * @param endEntrustNo
     * @return
     */
    List<HomsRealdealResult> queryRealdeal(String token, String fundAccount, String combineId, String exchangeType, String entrustDirection,
                         String beginBatchNo, String endBatchNo, String begineEntrustNo, String endEntrustNo) throws HomsException;

    /**
     * 组合层现货可用数量查询
     * @param token
     * @param fundAccount
     * @param combineId
     * @param exchangeType
     * @param stockCode
     * @param tradingDirection
     * @return
     */
    List<HomsQueryAmountResult> queryAmount(String token, String fundAccount, String combineId, String exchangeType,
                                    String stockCode, String tradingDirection) throws HomsException;

    /**
     * 组合层期货可用数量查询
     * @param token
     * @param fundAccount
     * @param combineId
     * @param exchangeType
     * @param stockCode
     * @param investWay
     * @return
     */
    List<HomsQueryFutureAmount> queryFutureAmount(String token, String fundAccount, String combineId, String exchangeType,
                                          String stockCode, String investWay) throws HomsException;

    /**
     * 历史委托查询
     * @param token
     * @param initDate
     * @param beginBatchNo
     * @param endBatchNo
     * @param begineEntrustNo
     * @param endEntrustNo
     * @return
     */
    List<HomsHistoryEntrustResult> queryHistoryEntrust(String token, String initDate, String beginBatchNo, String endBatchNo,
                                            String begineEntrustNo, String endEntrustNo) throws HomsException;

    /**
     * 历史成交查询
     * @param token
     * @param initDate
     * @param beginBatchNo
     * @param endBatchNo
     * @param begineEntrustNo
     * @param endEntrustNo
     * @return
     */
    List<HomsHistoryRealdealResult> queryHistoryReaddeal(String token, String initDate, String beginBatchNo, String endBatchNo,
                                             String begineEntrustNo, String endEntrustNo) throws HomsException;

    /**
     * 组合信息查询
     * @param token
     * @param fundAccount
     * @param combineId
     * @return
     */
    List<HomsCombInfoResult> queryCombInfo(String token, String fundAccount, String combineId) throws HomsException;

    /**
     * 组合信息查询（获取所有）
     * @param token
     * @return
     */
    List<HomsCombInfoResult> queryCombInfo(String token) throws HomsException;

    /**
     * 修改单元信息
     * @param token
     * @param fundAccount
     * @param combineId
     * @param assetName
     * @param assetStatus
     * @return
     */
    HomsExecuteResult changeAssetInfo(String token, String fundAccount, String combineId, String assetName,
                                        String assetStatus) throws HomsException;

    /**
     * 组合资产查询
     * @param token
     * @param fundAccount
     * @param combineId
     * @return
     */
    HomsQueryCombAssetResult queryCombAsset(String token, String fundAccount, String combineId) throws HomsException;

    /**
     * 组合流水查询<br/>
     * 查询指定组全的流水信息，包括成交流水和资金划转相关的流水
     * @param token
     * @param fundAccount
     * @param combineId
     * @param initDate
     * @return
     */
    List<HomsQueryCurrentResult> queryCurrent(String token, String fundAccount, String combineId, String initDate) throws HomsException;

    /**
     * 股票资产查询<br/>
     * 查询单元股票市值和现金资产，股票市值使用持仓数据计算，现金资产取数据库的现金资产
     * @param token
     * @param fundAccount
     * @param combineId
     * @return
     */
    List<HomsQueryStockCurrentResult> queryStockCurrent(String token, String fundAccount, String combineId) throws HomsException;

    /**
     * 公司层已设置风控信息查询<br/>
     * 查询当前操作员所属公司的所有已经设置的风险控制信息
     * @param token
     * @param serial
     * @return
     */
    List<HomsQueryRiskControlResult> queryRiskControl(String token, String serial) throws HomsException;

    /**
     * 修改操作员信息
     * @param token
     * @param operatorNo
     * @param businOpFlag 3修改，6冻结，7解冻
     * @param operatorName
     * @param expireDay 密码有效天数 1~999天
     * @param forceChgPassword 登录后是否需要修改密码，1需要，2不需要
     * @param stockOpRight 股票操作权限 0不限制，1限制买入，2限制卖出，3限制买入卖出
     * @param futureOpRight 期货操作权限 0不限制，1限制开仓，2限制平仓，3限制开仓平仓
     * @return
     */
    HomsExecuteResult changeOperatorInfo(String token, String operatorNo, String businOpFlag, String operatorName, String expireDay, String forceChgPassword,
                              String stockOpRight, String futureOpRight) throws HomsException;

    /**
     * 借贷信息查询
     * @param token
     * @return
     */
    List<HomsQueryFutureLoanResult> queryFutureLoan(String token) throws HomsException;

    /**
     * 操作员信息查询
     * @param token
     * @param operatorNo
     * @param operatorStatus
     * @return
     */
    List<HomsOperatorInfoResult> queryOperatorInfo(String token, String operatorNo, String operatorStatus) throws HomsException;

    /**
     * 账户操作权限查询
     * @param token
     * @param operatorNo
     * @param fundAccount
     * @param combineId
     * @return
     */
    List<HomsOperatorRightResult> queryOperatorRight(String token, String operatorNo, String fundAccount, String combineId) throws HomsException;

    /**
     * 修改账户操作权限
     * @param token
     * @param operatorNo
     * @param fundAccount
     * @param combineId
     * @param assetId
     * @param rights
     * @return
     */
    HomsExecuteResult changeAssetRight(String token, String operatorNo, String fundAccount, String combineId, String assetId,
                            String rights) throws HomsException;

    /**
     * 股票划转
     * @param token
     * @param fundAccount
     * @param combineId
     * @param targetCombineId
     * @param occurPrice 划转单价
     * @param occurAmount 划转数量
     * @param occurCost 划转成本
     * @param occurProfit 划转收益
     * @param exchangeType 市场代码
     * @param stockCode 证券代码
     * @return
     */
    HomsExecuteResult moveStock(String token, String fundAccount, String combineId, String targetCombineId,
                     String occurPrice, String occurAmount, String occurCost, String occurProfit,
                     String exchangeType, String stockCode) throws HomsException;
}
