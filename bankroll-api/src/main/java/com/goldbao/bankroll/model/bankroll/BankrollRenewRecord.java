package com.goldbao.bankroll.model.bankroll;

import com.goldbao.bankroll.model.manage.sysuser.SysUser;

/**
 * 配资续约记录
 * @author shuyu.fang
 */
public class BankrollRenewRecord {

    // ManyToOne
    private BankrollRecord record;

    private Integer renewUnit;

    // ManyToOne
    private SysUser creator;
}
