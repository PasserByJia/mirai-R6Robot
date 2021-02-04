package com.jh.mirai.http.plus.domain;

public class Group {

    private long id; //群号
    private String name; //群名
    private String permission; // 机器人在该群权限

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
