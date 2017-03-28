package com.hyq.entity.enum_;

/**
 * Created by genius on 2017/3/12.
 */
public enum ReceiveBox_status {
    已读("read"),未读("unread"),回收("recovery");

    private String name;

    ReceiveBox_status(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
