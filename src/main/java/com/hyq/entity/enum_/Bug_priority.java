package com.hyq.entity.enum_;

/**
 * bug模型的优先级
 * Created by genius on 2017/3/12.
 */
public enum Bug_priority {
    最高(1.0,"highest"),较高(0.9,"higher"),一般(0.8,"normal"),较低(0.7,"lower"),最低(0.6,"lowest");

    private double value;
    private String name;

    Bug_priority(double value,String name){
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
