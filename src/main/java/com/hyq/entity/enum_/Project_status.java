package com.hyq.entity.enum_;

/**
 * Created by genius on 2017/3/22.
 */
public enum Project_status {

    进行中("ing"),已完成("done"),已归档("finish");

    private String name;

    Project_status(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
