package com.hyq.entity.enum_;

/**
 * 公告类型
 * Created by genius on 2017/3/12.
 */
public enum  Affiche_bulType {
    全体公告("all"),项目公告("project"),行政公告("administration"),通知("msgnotice");

    private String name;

    Affiche_bulType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

