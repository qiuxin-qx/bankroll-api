package com.goldbao.bankroll.service.homs;

import com.goldbao.bankroll.model.bankroll.BankrollHomsInfoDTO;
import com.goldbao.bankroll.model.homs.*;

import java.util.List;

/**
 * homs service操作
 */
public interface HomsService {

    /**
     * homs登录接口
     * @param operatorNo
     * @param homsPassword
     * @param userToken
     * @return
     */
    HomsUser login(String operatorNo, String homsPassword, String userToken);

    /**
     * 添加组合信息
     * @param list
     */
    void addCombInfos(List<HomsCombInfo> list);

    /**
     * 添加成交记录
     * @param list
     */
    void addHistoryRealdealRecords(List<HomsHistoryRealdealRecord> list);

    /**
     * 组合持仓
     * @param list
     */
    void addComboStocks(List<HomsComboStock> list);


    void addOperators(List<HomsOperatorInfo> list);

    void addQueryEntrust(List<HomsQueryEntrust> list);

    void addRealdealRecords(List<HomsRealdealRecord> list);

    void addAmount(List<HomsAmount> list);

    void addOrUpdateCombAsset(HomsCombAsset asset);

    void addCurrentStock(List<HomsCurrentStock> stocks);

    void addOrUpdateFutureLoan(List<HomsFutureLoan> loans);

    void addComboFunds(List<HomsComboFund> funds);

    void addCurrents(List<HomsCurrent> homsCurrents);

    void addOperatorRights(List<HomsOperatorRight> rights);

    void addRiskControls(List<HomsRiskControl> controls);

    HomsUser getHomsUser(String operatorNo);

    List<HomsCombInfo> getCombInfoList();

    BankrollHomsInfoDTO getHomsInfoDTO(String homsOperatorNo);
}
