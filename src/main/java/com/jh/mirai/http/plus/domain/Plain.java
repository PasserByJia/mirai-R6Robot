package com.jh.mirai.http.plus.domain;

public class Plain {
    private String type = "Plain";
    private String text ;

    public Plain(String text){
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
