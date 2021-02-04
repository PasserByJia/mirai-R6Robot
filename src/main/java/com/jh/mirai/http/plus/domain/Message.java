package com.jh.mirai.http.plus.domain;

public abstract class Message {
    private Long messageId; //消息id
    private String messageType; //消息类型 群消息GroupMessage，好友消息FriendMessage，临时会话TempMessage
    private String message; //消息内容
    private String messageChain; //有关消息链的json字串

    public abstract Long getSenderId();

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageChain() {
        return messageChain;
    }

    public void setMessageChain(String messageChain) {
        this.messageChain = messageChain;
    }
}
