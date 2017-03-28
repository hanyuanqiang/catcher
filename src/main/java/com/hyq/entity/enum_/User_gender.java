package com.hyq.entity.enum_;

/**
 * Created by genius on 2017/3/11.
 */
public enum User_gender {
    女("female"), 男("male");

    private String name;

    User_gender(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
