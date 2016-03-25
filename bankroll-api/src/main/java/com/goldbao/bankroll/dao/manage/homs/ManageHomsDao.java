package com.goldbao.bankroll.dao.manage.homs;

import com.goldbao.bankroll.model.PageableList;
import com.goldbao.bankroll.model.homs.HomsOperatorInfo;

import java.util.Map;

/**
 * @author shuyu.fang
 */
public interface ManageHomsDao {

    PageableList<HomsOperatorInfo> getHomsOperatorList(Map<String, Object> options, int index, int size);

}
