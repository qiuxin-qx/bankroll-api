package com.goldbao.homs;

import com.hundsun.t2sdk.interfaces.share.event.IEvent;

/**
 * 封装homs client发送请求
 */
public interface HomsSend {

    IEvent sendReceive(IEvent event);

    IEvent sendReceive(IEvent event, long l);
}
