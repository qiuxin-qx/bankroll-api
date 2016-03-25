package com.goldbao.bankroll.tests.homs;

import com.goldbao.homs.HomsEvent;
import com.goldbao.homs.HomsException;
import com.goldbao.homs.result.*;
import com.goldbao.utils.StringUtil;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * homs接口对接测试
 */
public class HomsTest {

    private final static Logger logger = LoggerFactory.getLogger(HomsTest.class);

    private static HomsEvent homsEvent;

    private static String token = "";

    // 登录并初始化token
    @BeforeClass
    public static void init() {
        @SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:bankroll-api-homs.xml");
        logger.debug("登录并初始化token");
        homsEvent = (HomsEvent) context.getBean("homsEvent");
        HomsLoginResult r = homsEvent.login("20549001", "2054", "", "");
//        HomsLoginResult r = homsEvent.login("20546005", "2054", "", "");
        logger.debug("the login result is \n{}", r.getUserToken());

        token = r.getUserToken();
    }

    // 执行完毕，注销
    @AfterClass
    public static void destory() {
        logger.debug("执行完毕，注销");
        if (!StringUtil.isEmpty(token)) {
            HomsExecuteResult r = homsEvent.loginout(token, "20549001");
            logger.debug("the loginout result is \n{} - {}",r.getStatus(), r.getMessage());
        }
    }

//    注销
//    @Test
//    public void test2() {
//        HomsExecuteResult r = homsEvent.loginout(token, "20549001");
//        logger.debug("the loginout result is \n{} - {}",r.getStatus(), r.getMessage());
//    }

    // 修改密码
//    @Test
//    public void test3() {
//        HomsExecuteResult r = homsEvent.changePassword(token, "", "2054", "2054");
//        logger.debug("the change password result is \n{} - {}",r.getStatus(), r.getMessage());
//    }

    // 查询组合持仓
    @Test
    public void test4() {
        logger.debug("查询组合持仓");
        List<HomsComboStockResult> r = homsEvent.queryComboStock(token, "", "", "", "", "");
        if (r != null && r.size() > 0) {
            for (HomsComboStockResult item: r) {
                logger.debug("item: {}, {}, {}", item.getFundAccount(), item.getCombineId(), item.getInitDate());
            }
        }
    }

    // 历史委托查询
    @Test
    public void test5() {
        logger.debug("历史委托查询");
        List<HomsHistoryEntrustResult> r = homsEvent.queryHistoryEntrust(token, "2014121", "", "", "", "");
        Assert.assertNotNull(r);
        logger.debug("{}", r.size());
    }

    // 组合层现货可用数量查询
    @Test
    public void test6() {
        logger.debug("组合层现货可用数量查询");
        try {
            List<HomsQueryAmountResult> r = homsEvent.queryAmount(token, "20540001", "10462", "1", "600004", "");
            Assert.assertEquals(0, r.size());
        } catch (HomsException ex) {
            logger.debug(ex.getMessage());
        }
    }

    // 普通委托
    @Test
    public void test7() {
        logger.debug("普通委托");
//        String stockCode = "600229";
        String stockCode = "600072";
        HomsEntrustResult r =  homsEvent.entrust(token, "", "20540001", "10467", "", "",
                HomsDict.ExchangeType.SHANG_JIAO_SUO.getFlag(),
                stockCode,
                HomsDict.EntrustDirection.STOCK_BUY.getFlag(),
                HomsDict.AmpriceType.LIMIT_PRICE.getFlag(),
                "100", "12.27", "", "");

        Assert.assertEquals("0", r.getErrorNo());
    }

    // 组合信息查询 ~ 必要接口
    @Test
    public void test8() {
        logger.debug("组合信息查询 ~ 必要接口");
        List<HomsCombInfoResult> r = homsEvent.queryCombInfo(token, "", "");


        Assert.assertEquals(1, r.size());

        logger.debug("{}", r);
    }

    // 组合资产查询 ~ 必要接口
    @Test
    public void test9() {
        logger.debug("组合资产查询 ~ 必要接口");
        HomsQueryCombAssetResult r =  homsEvent.queryCombAsset(token, "20540001", "10298");

        Assert.assertNotNull(r);

        Assert.assertEquals("10298", r.getCombineId());
    }

    // 历史成交查询
    @Test
    public void test10() {
        logger.debug("历史成交查询");
        List<HomsHistoryRealdealResult> r =  homsEvent.queryHistoryReaddeal(token, "20141216", "", "", "", "");

        Assert.assertEquals(0, r.size());
    }

    // 组合层现货可用资金查询
    @Test
    public void test11() {
        logger.debug("组合层现货可用资金查询");
        List<HomsComboFundResult> r = homsEvent.queryComboFund(token, "20540001", "10467");

        Assert.assertEquals(1, r.size());
    }

    // 委托查询
    @Test
    public void test12() {
        logger.debug("委托查询");
        List<HomsQueryEntrustResult> r = homsEvent.queryEntrust(token, "", "", "", "", "", "", "", "", "");

        Assert.assertEquals(1, r.size());
    }

    // 成交查询
    @Test
    public void test13() {
        logger.debug("成交查询");
        List<HomsRealdealResult> r = homsEvent.queryRealdeal(token, "", "", "", "", "", "", "", "");

        Assert.assertEquals(1, r.size());
    }

   // 委托撤销
    @Test
    public void test14() {
        logger.debug("委托撤销");
        HomsExecuteResult r = homsEvent.entrustWithdraw(token, "20540001", "2320643");
        Assert.assertEquals("0", r.getStatus());
    }

    // 修改单元信息
    @Test
    public void test15() {
        logger.debug("修改单元信息");

        HomsExecuteResult r = homsEvent.changeAssetInfo(token, "20540001", "10467", "05的单元信息", "");
        Assert.assertEquals("0", r.getStatus());
    }

    // 组合流水查询
    @Test
    public void test16() {
        logger.debug("组合流水查询");
        List<HomsQueryCurrentResult> r = homsEvent.queryCurrent(token, "20540001", "10466", "");

        Assert.assertEquals(2, r.size());
    }

    // 股票资产查询 ~ 必要接口
    @Test
    public void test17() {
        logger.debug("股票资产查询 ~ 必要接口");
        List<HomsQueryStockCurrentResult> r = homsEvent.queryStockCurrent(token, "20540001", "10462");

        Assert.assertEquals(1, r.size());
    }

    // 公司层已设置风控信息查询
    @Test
    public void test18() {
        logger.debug("公司层已设置风控信息查询");
        List<HomsQueryRiskControlResult> r = homsEvent.queryRiskControl(token, "");

        Assert.assertEquals(2, r.size());
    }

    // 资金划转
    @Test
    public void test19() {
        logger.debug("资金划转");
        MoveAssetResult r = homsEvent.moveAsset(token, "20540001", "10298", "10466", "0.01",
                "107", "111");

        Assert.assertNotNull(r);

    }

    // 修改操作员信息
    @Test
    public void test20() {
        logger.debug("修改操作员信息");
        HomsExecuteResult r = homsEvent.changeOperatorInfo(token, "20546005", "3", "", "",
                "", "", "");

        Assert.assertNotNull(r);
    }

    // 借贷信息查询
    @Test
    public void test21() {
        logger.debug("借贷信息查询");
        List<HomsQueryFutureLoanResult> r = homsEvent.queryFutureLoan(token);
        // TODO 风险度（risk_ratio）为null，需看下
        Assert.assertNotNull(r);
    }

    // 操作员信息查询
    @Test
    public void test22() {
        logger.debug("操作员信息查询");
        List<HomsOperatorInfoResult> r = homsEvent.queryOperatorInfo(token, "", "");
        Assert.assertEquals(6, r.size());
    }

    // 账户操作权限查询
    @Test
    public void test23() {
        logger.debug("账户操作权限查询");
        List<HomsOperatorRightResult> r = homsEvent.queryOperatorRight(token, "", "", "");
        Assert.assertEquals(8, r.size());
    }

    // 修改账户操作权限
    @Test
    public void test24() {
        logger.debug("修改账户操作权限");
        HomsExecuteResult r = homsEvent.changeAssetRight(token, "", "20540001", "10467", "", "0");
        Assert.assertEquals("0", r.getStatus());
    }

    // 股票划转
    @Test
    public void test25() {
        logger.debug("股票划转");
        HomsExecuteResult r = homsEvent.moveStock(token, "20540001", "10467", "10466", "20", "1",
                "", "", "", "600229");
        Assert.assertEquals("0", r.getStatus());
    }

    // 组合层期货可用数量查询
    @Test
    public void test26() {
        logger.debug("组合层期货可用数量查询");
        List<HomsQueryFutureAmount> r = homsEvent.queryFutureAmount(token, "20540001", "10467", HomsDict.ExchangeType.SHANG_JIAO_SUO.getFlag(), "600229", "");

        Assert.assertEquals(0, r.size());
    }

    // 组合层期货可用资金查询
    @Test
    public void test27() {
        logger.debug("组合层期货可用资金查询");
        List<HomsFutuComboFundResult> r = homsEvent.queryFutuComboFund(token, "20540001", "10467");

        Assert.assertEquals(1, r.size());
    }
}
