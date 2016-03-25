package com.goldbao.homs;

import com.hundsun.t2sdk.impl.util.AbstractLogAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * homs日志适配器
 * @author shuyu.fang
 */
public class HomsLogAdapter extends AbstractLogAdapter {

    private final static Logger logger = LoggerFactory.getLogger(HomsLogAdapter.class);

    public HomsLogAdapter() {}

    @Override
    public void export(String s) {
        logger.debug("homs - " + s);
    }
}
