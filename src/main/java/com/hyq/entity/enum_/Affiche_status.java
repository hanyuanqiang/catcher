package com.hyq.entity.enum_;

import com.hyq.entity.Affiche;

/**
 * Created by genius on 2017/3/12.
 */
public enum  Affiche_status {
    已发布("release"),未发布("unrelease");

    private String name;

    Affiche_status(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
