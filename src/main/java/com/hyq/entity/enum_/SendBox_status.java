package com.hyq.entity.enum_;

/**
 * Created by genius on 2017/3/12.
 */
public enum  SendBox_status {
    未发送("draft"),已发送("send"),已回收("recovery");

    private String name;

    SendBox_status(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
