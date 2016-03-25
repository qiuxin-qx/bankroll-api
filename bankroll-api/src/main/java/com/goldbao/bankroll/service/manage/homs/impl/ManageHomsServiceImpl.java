package com.goldbao.bankroll.service.manage.homs.impl;

import com.goldbao.bankroll.dao.manage.homs.ManageHomsDao;
import com.goldbao.bankroll.model.PageableList;
import com.goldbao.bankroll.model.homs.HomsOperatorInfo;
import com.goldbao.bankroll.service.manage.homs.ManageHomsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shuyu.fang
 */
public class ManageHomsServiceImpl implements ManageHomsService {
    private ManageHomsDao manageHomsDao;

    @Override
    public PageableList<HomsOperatorInfo> getHomsOperatorList(String operatorNo, int index, int size) {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("operatorNo", operatorNo);
        return manageHomsDao.getHomsOperatorList(options, index, size);
    }
    @Autowired
    public void setManageHomsDao(ManageHomsDao manageHomsDao) {
        this.manageHomsDao = manageHomsDao;
    }
}
