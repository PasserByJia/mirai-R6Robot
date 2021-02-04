package com.jh.mirai.http.plus.domain;

public class PrivateMessage extends Message{

    private FriendSender sender; //好友消息发送者信息

    public FriendSender getSender() {
        return sender;
    }

    public void setSender(FriendSender sender) {
        this.sender = sender;
    }

    public Long getSenderId(){//获取发送者的id
        return sender.getId();
    }

}
