package com.jh.mirai.http.plus.domain;

public class GroupSender extends Sender{

    private String permission; //发送者在该群权限

    private Group group; // 群信息

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
    public Long getGroupId(){
        return group.getId();
    }
}
