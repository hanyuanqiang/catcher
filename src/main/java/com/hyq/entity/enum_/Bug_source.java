package com.hyq.entity.enum_;

/**
 * Created by genius on 2017/3/12.
 */
public enum  Bug_source {
    开发人员("developer"),测试人员("tester"),客户("customer");

    private String name;

    Bug_source(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
