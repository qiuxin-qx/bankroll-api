package com.goldbao.bankroll.dao.homs;

import com.goldbao.bankroll.model.bankroll.BankrollHomsInfoDTO;
import com.goldbao.bankroll.model.homs.*;

import java.util.List;

/**
 * homs数据库层操作
 */
public interface HomsDao {

    /**
     * 获取某operatorNo在系统中的数量
     * @param operatorNo
     * @return
     */
    long getUserCountByOperatorNo(String operatorNo);
    /**
     * 修改一条homs用户登录记录
     * @param homsUser
     * @return
     */
    HomsUser updateHomsUserLoginInfo(HomsUser homsUser);

    /**
     * 根据operatorNo获取一条homs用户
     * @param operatorNo
     * @return
     */
    HomsUser getUserByOperatorNo(String operatorNo);

    /**
     * 添加一条homs用户记录
     * @param userId
     * @param homsUser
     * @return
     */
    HomsUser addHomsUser(Long userId, HomsUser homsUser);

    void addCombInfo(HomsCombInfo i);

    void addHistoryRealdeal(HomsHistoryRealdealRecord i);

    void addComboStock(HomsComboStock i);

    void addOperator(HomsOperatorInfo i);

    void addQUeryEntrust(HomsQueryEntrust i);

    void addRealdeal(HomsRealdealRecord i);

    void addAmount(HomsAmount i);

    void addAsset(HomsCombAsset asset);

    void addCurrentStock(HomsCurrentStock i);

    void addFutureLoan(HomsFutureLoan i);

    void addComboFunds(HomsComboFund i);

    void addCurrent(HomsCurrent i);


    void addOperatorRight(HomsOperatorRight i);

    void addRiskControl(HomsRiskControl i);

    HomsCombInfo getCombInfoByCombineId(String combineId);

    void updateCombInfo(HomsCombInfo combInfo);

    List<HomsCombInfo> getCombInfoList();


    HomsCombAsset getCombAssetByCombineId(String combineId);

    void updateCombAsset(HomsCombAsset combAsset);

    HomsFutureLoan getFutureLoanByCombineId(String combineId);

    void updateFutureLoan(HomsFutureLoan futureLoan);

    BankrollHomsInfoDTO getHomsInfoDTO(String homsOperatorNo);
}
