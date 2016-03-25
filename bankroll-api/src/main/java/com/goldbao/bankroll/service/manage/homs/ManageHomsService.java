package com.goldbao.bankroll.service.manage.homs;

import com.goldbao.bankroll.model.PageableList;
import com.goldbao.bankroll.model.homs.HomsOperatorInfo;

/**
 * homs相关服务
 * @author shuyu.fang
 */
public interface ManageHomsService {

    /**
     *
     * @param operatorNo
     * @return
     */
    PageableList<HomsOperatorInfo> getHomsOperatorList(String operatorNo, int index, int size);
}
