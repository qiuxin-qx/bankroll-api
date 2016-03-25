package com.goldbao.bankroll.service.homs.impl;

import com.goldbao.bankroll.dao.homs.HomsDao;
import com.goldbao.bankroll.model.bankroll.BankrollHomsInfoDTO;
import com.goldbao.bankroll.model.homs.*;
import com.goldbao.bankroll.service.homs.HomsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author shuyu.fang
 */
public class HomsServiceImpl implements HomsService {

    private HomsDao homsDao;

    @Override
    public HomsUser login(String operatorNo, String homsPassword, String userToken) {
        // TODO 需要判断，operatorNo是否可为多用户重复使用
        // 1. 检测该operatorNo是否存在
        HomsUser homsUser = homsDao.getUserByOperatorNo(operatorNo);

        if (homsUser == null) {
            // 2. 如果不存在，则添加一条新纪录
            homsUser = new HomsUser();
            homsUser.setOperatorNo(operatorNo);
            homsUser.setPassword(homsPassword);
            homsUser.setUserToken(userToken);
            return homsDao.addHomsUser(0L, homsUser);
        } else {
            // 3. 否则修改现有记录
            homsUser.setUserToken(userToken);
            return homsDao.updateHomsUserLoginInfo(homsUser);
        }
    }

    @Override
    public void addCombInfos(List<HomsCombInfo> list) {
        for (HomsCombInfo i: list) {
            HomsCombInfo combInfo = this.homsDao.getCombInfoByCombineId(i.getCombineId());
            if (combInfo == null) {
                this.homsDao.addCombInfo(i);
            } else {
                BeanUtils.copyProperties(i, combInfo, "id", "addTime", "dr", "ver");
                combInfo.setUpdateTime(new Date());
                this.homsDao.updateCombInfo(combInfo);
            }
        }
    }

    @Override
    public void addHistoryRealdealRecords(List<HomsHistoryRealdealRecord> list) {
        for (HomsHistoryRealdealRecord i: list) {
            this.homsDao.addHistoryRealdeal(i);
        }
    }

    @Override
    public void addComboStocks(List<HomsComboStock> list) {
        for (HomsComboStock i: list) {
            this.homsDao.addComboStock(i);
        }
    }

    @Override
    public void addOperators(List<HomsOperatorInfo> list) {
        for (HomsOperatorInfo i: list) {
            this.homsDao.addOperator(i);
        }
    }

    @Override
    public void addQueryEntrust(List<HomsQueryEntrust> list) {
        for (HomsQueryEntrust i: list) {
            this.homsDao.addQUeryEntrust(i);
        }
    }

    @Override
    public void addRealdealRecords(List<HomsRealdealRecord> list) {
        for (HomsRealdealRecord i: list) {
            this.homsDao.addRealdeal(i);
        }
    }

    @Override
    public void addAmount(List<HomsAmount> list) {
        for (HomsAmount i: list) {
            this.homsDao.addAmount(i);
        }
    }

    @Override
    public void addCurrentStock(List<HomsCurrentStock> stocks) {
        for (HomsCurrentStock i: stocks) {
            this.homsDao.addCurrentStock(i);
        }
    }

    @Override
    public void addOrUpdateFutureLoan(List<HomsFutureLoan> loans) {
        for (HomsFutureLoan i: loans) {
            HomsFutureLoan futureLoan = homsDao.getFutureLoanByCombineId(i.getCombineId());
            if (futureLoan == null) {
                i.setUpdateTime(new Date());
                this.homsDao.addFutureLoan(i);
            } else {
                BeanUtils.copyProperties(i, futureLoan, "id", "dr", "ver");
                futureLoan.setUpdateTime(new Date());
                this.homsDao.updateFutureLoan(futureLoan);
            }
        }
    }

    @Override
    public void addComboFunds(List<HomsComboFund> funds) {
        for (HomsComboFund i: funds) {
            this.homsDao.addComboFunds(i);
        }
    }

    @Override
    public void addCurrents(List<HomsCurrent> homsCurrents) {
        for (HomsCurrent i: homsCurrents) {
            this.homsDao.addCurrent(i);
        }
    }

    @Override
    public void addOperatorRights(List<HomsOperatorRight> rights) {
        for (HomsOperatorRight i: rights) {
            this.homsDao.addOperatorRight(i);
        }
    }

    @Override
    public void addRiskControls(List<HomsRiskControl> controls) {
        for (HomsRiskControl i: controls) {
            this.homsDao.addRiskControl(i);
        }
    }

    @Override
    public void addOrUpdateCombAsset(HomsCombAsset asset) {
        HomsCombAsset combAsset = homsDao.getCombAssetByCombineId(asset.getCombineId());
        if (combAsset == null) {
            asset.setUpdateTime(new Date());
            this.homsDao.addAsset(asset);
        } else {
            BeanUtils.copyProperties(asset, combAsset, "id", "updateTime", "dr", "ver");

            combAsset.setUpdateTime(new Date());
            homsDao.updateCombAsset(combAsset);
        }
    }

    @Override
    public HomsUser getHomsUser(String operatorNo) {
        HomsUser homsUser = homsDao.getUserByOperatorNo(operatorNo);
        return homsUser;
    }

    @Override
    public BankrollHomsInfoDTO getHomsInfoDTO(String homsOperatorNo) {
        return homsDao.getHomsInfoDTO(homsOperatorNo);
    }

    @Override
    public List<HomsCombInfo> getCombInfoList() {
        return homsDao.getCombInfoList();
    }

    @Autowired
    public void setHomsDao(HomsDao homsDao) {
        this.homsDao = homsDao;
    }
}
