package com.goldbao.bankroll.tests.service;

import com.goldbao.bankroll.model.homs.*;
import com.goldbao.bankroll.service.homs.HomsService;
import com.goldbao.homs.HomsEvent;
import com.goldbao.homs.HomsException;
import com.goldbao.homs.result.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * homs service
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:bankroll-api-service.xml", "classpath:bankroll-api-homs.xml"})
public class HomsServiceTest {

    private final static Logger logger = LoggerFactory.getLogger(HomsServiceTest.class);

    private HomsService homsService;

    private HomsEvent homsEvent;

    private final static String operatorNo = "62519001";
//    private final static String operatorNo = "20546005";
    private final static String operatorPwd = "032776";
//    private final static String operatorPwd = "6251!yf";

    final static String token = "RRBVOGRTNPIYAODGVBSZ";
//    final static String token = "VQAQSQLVWULMAWFSZDYF";

    @Test
    public void testLogin() {
        try {
            HomsLoginResult r = homsEvent.login(operatorNo, operatorPwd, "", "");

            homsService.login(operatorNo, operatorPwd, r.getUserToken());
        } catch (HomsException ex) {
            logger.error(ex.getMessage());
        }
    }

    // 组合（单元）信息查询
    @Test
    public void testQueryCombInfo() {

        List<HomsCombInfoResult> r = homsEvent.queryCombInfo(token, "", "");
        List<HomsCombInfo> list = new ArrayList<HomsCombInfo>();
        if (r.size() > 0) {
            for (HomsCombInfoResult item: r) {
                HomsCombInfo combInfo = new HomsCombInfo();
                BeanUtils.copyProperties(item, combInfo);
                list.add(combInfo);
            }
            homsService.addCombInfos(list);
        }
    }

    // 历史成交记录
    @Test
    public void testQueryHistoryRealdeal() {
        for (int i = 1; i < 10; i++) {
            List<HomsHistoryRealdealResult> r = homsEvent.queryHistoryReaddeal(token, "2014110"+i, "", "", "", "");
            List<HomsHistoryRealdealRecord> list = new ArrayList<HomsHistoryRealdealRecord>();
            if (r.size() > 0) {
                for (HomsHistoryRealdealResult item : r) {
                    HomsHistoryRealdealRecord realdealRecord = new HomsHistoryRealdealRecord();
                    BeanUtils.copyProperties(item, realdealRecord);
                    list.add(realdealRecord);
                }
                homsService.addHistoryRealdealRecords(list);
            }
        }
    }

    // 成交记录？？
    @Test
    public void testQueryRealdeal() {
        List<HomsCombInfoResult> re = homsEvent.queryCombInfo(token, "", "");
        for (HomsCombInfoResult combInfo: re) {
            List<HomsRealdealResult> r = homsEvent.queryRealdeal(token, combInfo.getFundAccount(), combInfo.getCombineId(), "", "", "", "", "", "");
            List<HomsRealdealRecord> list = new ArrayList<HomsRealdealRecord>();
            if (r.size() > 0) {
                for (HomsRealdealResult item : r) {
                    HomsRealdealRecord realdealRecord = new HomsRealdealRecord();
                    BeanUtils.copyProperties(item, realdealRecord);
                    list.add(realdealRecord);
                }
                homsService.addRealdealRecords(list);
            }
        }
    }

    // 组合持仓查询
    @Test
    public void testQueryComboStock() {
        String date = "20150120";
        List<HomsComboStockResult> r = homsEvent.queryComboStock(token, date, "", "", "", "");
        List<HomsComboStock> list = new ArrayList<HomsComboStock>();
        if (r.size() > 0) {
            for (HomsComboStockResult item : r) {
                HomsComboStock realdealRecord = new HomsComboStock();
                BeanUtils.copyProperties(item, realdealRecord);
                list.add(realdealRecord);
            }
            homsService.addComboStocks(list);
        }
    }

    // 账户信息
    @Test
    public void testQueryOperatorInfo() {
        List<HomsOperatorInfoResult> r = homsEvent.queryOperatorInfo(token, "", "");
        List<HomsOperatorInfo> list = new ArrayList<HomsOperatorInfo>();
        if (r.size() > 0) {
            for (HomsOperatorInfoResult item : r) {
                HomsOperatorInfo operator = new HomsOperatorInfo();
                BeanUtils.copyProperties(item, operator);
                list.add(operator);
            }
            homsService.addOperators(list);
        }
    }

    // 组合资产查询
    @Test
    public void testQueryCombAsset() {
        List<HomsCombInfoResult> r = homsEvent.queryCombInfo(token, "", "");
        for (HomsCombInfoResult item: r) {
            HomsQueryCombAssetResult combAsset = null;
            try {
                combAsset = homsEvent.queryCombAsset(token, item.getFundAccount(), item.getCombineId());
            }catch (HomsException ex) {
                logger.error(ex.getMessage());
            }

            if (combAsset != null) {
                HomsCombAsset asset = new HomsCombAsset();
                BeanUtils.copyProperties(combAsset, asset);
                homsService.addOrUpdateCombAsset(asset);
            }
        }
    }

    // 委托查询
    @Test
    public void testQueryEntrust() {
        List<HomsCombInfoResult> re = homsEvent.queryCombInfo(token, "", "");
        for (HomsCombInfoResult combInfo: re) {
            List<HomsQueryEntrustResult> r = homsEvent.queryEntrust(token, combInfo.getFundAccount(), combInfo.getCombineId(), "", "", "", "", "", "", "");
            List<HomsQueryEntrust> list = new ArrayList<HomsQueryEntrust>();
            if (r.size() > 0) {
                for (HomsQueryEntrustResult item : r) {
                    HomsQueryEntrust entrust = new HomsQueryEntrust();
                    BeanUtils.copyProperties(item, entrust);
                    list.add(entrust);
                }
                homsService.addQueryEntrust(list);
            }
        }
    }

    // 组合层现货可用数量查询
    @Test
    public void tstQueryAmount() {
        List<HomsCombInfoResult> re = homsEvent.queryCombInfo(token, "", "");
        for (HomsCombInfoResult combInfo: re) {
            String date = "20141227";
            List<HomsComboStockResult> re2 = homsEvent.queryComboStock(token, date, combInfo.getFundAccount(), combInfo.getCombineId(), "", "");
            for (HomsComboStockResult comboStock: re2) {
                List<HomsQueryAmountResult> r = null;
                try {
                    r = homsEvent.queryAmount(token, combInfo.getFundAccount(), combInfo.getCombineId(),
                        comboStock.getExchangeType(), comboStock.getStockCode(), "");
                } catch (HomsException ex) {
                    logger.error(ex.getMessage());
                }
                List<HomsAmount> list = new ArrayList<HomsAmount>();
                if (r != null && r.size() > 0) {
                    for (HomsQueryAmountResult item : r) {
                        HomsAmount amount = new HomsAmount();
                        BeanUtils.copyProperties(item, amount);
                        amount.setStockCode(comboStock.getStockCode());
                        amount.setExchangeType(comboStock.getExchangeType());
                        amount.setCombineId(combInfo.getCombineId());
//                        amount.setFundAccount(combInfo.getFundAccount());
                        list.add(amount);
                    }
                    homsService.addAmount(list);
                }
            }
        }
    }

    // 组合层现货可用资金查询
    @Test
    public void testQueryComboFund() {
        List<HomsCombInfoResult> re = homsEvent.queryCombInfo(token, "", "");
        for (HomsCombInfoResult combInfo: re) {
            List<HomsComboFundResult> comboFunds = null;
            try {
                comboFunds = homsEvent.queryComboFund(token, combInfo.getFundAccount(), combInfo.getCombineId());
            } catch (HomsException ex) {
                ex.printStackTrace();
            }
            if (comboFunds != null && comboFunds.size() > 0) {
                List<HomsComboFund> funds = new ArrayList<HomsComboFund>();
                for (HomsComboFundResult comboFund: comboFunds) {
                    HomsComboFund fund = new HomsComboFund();
                    BeanUtils.copyProperties(comboFund, fund);
                    funds.add(fund);
                }
                homsService.addComboFunds(funds);
            }
        }
    }

    // 组合流水查询
    @Test
    public void testQueryCurrent() {
        List<HomsCombInfoResult> re = homsEvent.queryCombInfo(token, "", "");
        for (HomsCombInfoResult combInfo: re) {
            List<HomsQueryCurrentResult> currents = homsEvent.queryCurrent(token, combInfo.getFundAccount(), combInfo.getCombineId(), "");
            List<HomsCurrent> homsCurrents = new ArrayList<HomsCurrent>();
            for (HomsQueryCurrentResult item: currents) {
                HomsCurrent current = new HomsCurrent();
                BeanUtils.copyProperties(item, current);
                homsCurrents.add(current);
            }

            homsService.addCurrents(homsCurrents);
        }
    }

    // 股票资产查询
    @Test
    public void testQueryCurrentStock() {
        List<HomsCombInfoResult> re = homsEvent.queryCombInfo(token, "", "");
        for (HomsCombInfoResult comboInfo: re) {
            List<HomsQueryStockCurrentResult> currentStocks = homsEvent.queryStockCurrent(token, comboInfo.getFundAccount(), comboInfo.getCombineId());
            if (currentStocks != null && currentStocks.size() > 0) {
                List<HomsCurrentStock> stocks = new ArrayList<HomsCurrentStock>();
                for (HomsQueryStockCurrentResult currentStock: currentStocks) {
                    HomsCurrentStock stock = new HomsCurrentStock();
                    BeanUtils.copyProperties(currentStock, stock);
                    stocks.add(stock);
                }
                homsService.addCurrentStock(stocks);
            }
        }
    }

    // 借贷信息查询【重要，很重要】
    @Test
    public void testqueryFutureLoan() {
        List<HomsQueryFutureLoanResult> futureLoans = homsEvent.queryFutureLoan(token);
        if (futureLoans != null && futureLoans.size() > 0) {
            List<HomsFutureLoan> loans = new ArrayList<HomsFutureLoan>();

            for (HomsQueryFutureLoanResult futureLoan : futureLoans) {
                HomsFutureLoan loan = new HomsFutureLoan();
                BeanUtils.copyProperties(futureLoan, loan);
                loans.add(loan);
            }

            homsService.addOrUpdateFutureLoan(loans);
        }
    }

    // 账户操作权限查询
    @Test
    public void testQueryOperatorRight() {
        List<HomsOperatorRightResult> rights = homsEvent.queryOperatorRight(token, "", "", "");
        if (rights != null && rights.size() > 0) {
            List<HomsOperatorRight> operatorRights = new ArrayList<HomsOperatorRight>();
            for (HomsOperatorRightResult r: rights) {
                HomsOperatorRight right = new HomsOperatorRight();
                BeanUtils.copyProperties(r, right);
                operatorRights.add(right);
            }

            homsService.addOperatorRights(operatorRights);
        }
    }

    // 公司层已设置风控信息查询
    @Test
    public void testQueryRiskControl() {
        List<HomsQueryRiskControlResult> riskControls = homsEvent.queryRiskControl(token, "");

        List<HomsRiskControl> controls = new ArrayList<HomsRiskControl>();

        for (HomsQueryRiskControlResult r: riskControls) {
            HomsRiskControl c = new HomsRiskControl();
            BeanUtils.copyProperties(r, c);
            controls.add(c);
        }

        homsService.addRiskControls(controls);
    }

    @Autowired
    public void setHomsService(HomsService homsService) {
        this.homsService = homsService;
    }
    @Autowired
    public void setHomsEvent(HomsEvent homsEvent) {
        this.homsEvent = homsEvent;
    }
}
