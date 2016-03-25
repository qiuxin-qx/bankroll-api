package com.goldbao.bankroll.tests.manage;

import com.goldbao.bankroll.model.PageableList;
import com.goldbao.bankroll.model.enums.EnumVerified;
import com.goldbao.bankroll.model.user.UserApplyReal;
import com.goldbao.bankroll.service.manage.user.ManageUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author shuyu.fang
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:bankroll-api-service.xml" })
public class ManageUserServiceTest {

    private ManageUserService manageUserService;

    @Test
    public void testGetUserApplyRealList() {

        EnumVerified status = EnumVerified.NOT_VERIFIED;
        int index = 1;
        int size = 10;
        PageableList<UserApplyReal> list = manageUserService.getApplyUserRealList(status, index, size);
    }

    public void testUpdateUserApplyRealToPass() {

        Long applyId = 1L;

        UserApplyReal real = manageUserService.getApplyUserRealById(applyId);

        boolean flag = manageUserService.updateUserApplyRealToPass(real);


    }

    @Autowired
    public void setManageUserService(ManageUserService manageUserService) {
        this.manageUserService = manageUserService;
    }
}
