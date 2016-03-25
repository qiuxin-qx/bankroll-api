package com.goldbao.homs;

import com.hundsun.t2sdk.impl.client.T2Services;
import com.hundsun.t2sdk.interfaces.IClient;
import com.hundsun.t2sdk.interfaces.T2SDKException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shuyu.fang
 */
public class HomsServerImpl implements HomsServer {

    private T2Services server;

    private String serverNode;

    private final static Logger logger = LoggerFactory.getLogger(HomsServerImpl.class);

    public void init() {
        logger.debug("homs init");
        server = T2Services.getInstance();

        try {
            server.init();
            server.start();
        } catch (T2SDKException e) {
            logger.error("homs 初始化异常：\n"+e.getMessage());
//            throw new HomsException("homs 初始化异常");
        }
    }

    public void destory() {
        if (server != null) {
            server.stop();
            logger.debug("homs stop");
        }
    }

    @Override
    public IClient getClient() {
        try {
            return server.getClient(serverNode);
        } catch (T2SDKException e) {
            logger.error("homs获取client异常");
            throw new HomsException("homs 获取client异常\n" + e.getMessage());
        }
    }

    public void setServerNode(String serverNode) {
        logger.debug("homs server节点："+serverNode);
        this.serverNode = serverNode;
    }
}
