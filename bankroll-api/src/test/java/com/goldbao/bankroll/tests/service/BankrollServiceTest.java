package com.goldbao.bankroll.tests.service;

import com.goldbao.bankroll.model.PageableList;
import com.goldbao.bankroll.model.bankroll.BankrollApply;
import com.goldbao.bankroll.model.bankroll.BankrollApplyStatDTO;
import com.goldbao.bankroll.service.bankroll.BankrollService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author shuyu.fang
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:bankroll-api-service.xml"})
public class BankrollServiceTest {

    private BankrollService bankrollService;

    // 获取申请列表
    @Test
    public void testGetApplyList() {

        PageableList<BankrollApply> r = bankrollService.getApplyList(1, 10);

        Assert.assertTrue(r.getCount() > 0);


    }

    @Test
    public void testGetBankrollApplyStat() {
        BankrollApplyStatDTO applyStat =  bankrollService.getBankrollApplyStat();

        Assert.assertNotNull(applyStat);
    }


    @Autowired
    public void setBankrollService(BankrollService bankrollService) {
        this.bankrollService = bankrollService;
    }
}
