package com.hyq.entity.enum_;

/**
 * Created by genius on 2017/3/15.
 */
public enum  SysActivity_type {

    设置("setting"),访问("access");

    private String name;

    SysActivity_type(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
