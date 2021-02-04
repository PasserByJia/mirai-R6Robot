package com.jh.mirai.http.plus.service;

import com.jh.mirai.http.plus.domain.GroupMessage;
import com.jh.mirai.http.plus.domain.Message;

public abstract class MiraiPlug {
    public abstract  boolean onPrivateMessage(Mirai mirai, Message message);
    public abstract  boolean onGroupMessage(Mirai mirai, GroupMessage message) throws Exception;
    public abstract  boolean onTempMessage(Mirai mirai, Message message);
}
