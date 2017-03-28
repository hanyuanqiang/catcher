package com.hyq.entity.enum_;

/**
 * Created by genius on 2017/3/12.
 */
public enum  Bug_type {
    实现错误("sxcw"),需求变动("xqbd"),新增需求("xzxq"),性能优化("xnyh");

    private String name;

    Bug_type(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
