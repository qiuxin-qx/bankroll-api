package com.goldbao.homs;

import com.hundsun.t2sdk.interfaces.T2SDKException;
import com.hundsun.t2sdk.interfaces.share.event.IEvent;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author shuyu.fang
 */
public class HomsSendImpl implements HomsSend {


    public HomsServer homsServer;

    @Override
    public IEvent sendReceive(IEvent event) {
        return sendReceive(event, 10000);
    }

    @Override
    public IEvent sendReceive(IEvent event, long l) {
        try {
            return homsServer.getClient().sendReceive(event, l);
        } catch (T2SDKException e) {
            throw new HomsException(e.getMessage());
        }
    }

    @Autowired
    public void setHomsServer(HomsServer homsServer) {
        this.homsServer = homsServer;
    }
}
