package com.hyq.entity.enum_;

/**
 * 用户岗位
 * Created by genius on 2017/3/12.
 */
public enum  User_post {

    开发工程师("developer"),测试工程师("tester"),需求工程师("requirementEngr");

    private String name;
    User_post(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
