package com.hyq.entity.enum_;

/**
 * bug严重程度
 * Created by genius on 2017/3/12.
 */
public enum  Bug_severity {

    阻塞(1.6,"block"),严重(0.8,"critical"),主要(0.4,"major"),次要(0.2,"minor"),提示(0.1,"trivial");

    private double value;
    private String name;

    Bug_severity(double value,String name){
        this.value = value;
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
