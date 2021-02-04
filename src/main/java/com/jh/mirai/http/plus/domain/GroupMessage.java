package com.jh.mirai.http.plus.domain;

public class GroupMessage extends Message{

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
