package com.hyq.entity.enum_;

/**
 * Created by genius on 2017/3/12.
 */
public enum Version_status {
    已发布("alreadyPublish"),未发布("noPublish");

    private String name;

    Version_status(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
