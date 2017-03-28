package com.hyq.entity.enum_;

/**
 * bug重现频率
 * Created by genius on 2017/3/12.
 */
public enum  Bug_frequency {
    必然出现("certain"),经常出现("often"),偶尔出现("seldom"),无法重现("cantReproduce");

    private String name;

    Bug_frequency(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
