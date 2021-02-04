package com.jh.mirai.http.plus.domain;

/**
 * 临时会话消息，本质上与群消息会话一致。
 */
public class TempMessage {

    private GroupSender sender;

    public Long getSenderId(){//获取发送者的id(qq号)
        return sender.getId();
    }
    public Long getGroupId(){ //获取群id(q群号)
        return sender.getGroupId();
    }

    public GroupSender getSender() {
        return sender;
    }

    public void setSender(GroupSender sender) {
        this.sender = sender;
    }
}
