package com.hyq.entity.enum_;

/**
 * Created by genius on 2017/3/12.
 */
public enum  SendBox_priority {
    最高("highest"),较高("higher"),一般("normal"),较低("lower"),最低("lowest");

    private String name;

    SendBox_priority(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
