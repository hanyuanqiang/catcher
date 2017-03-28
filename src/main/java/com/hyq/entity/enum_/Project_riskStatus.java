package com.hyq.entity.enum_;

/**
 * Created by genius on 2017/3/20.
 */
public enum Project_riskStatus {
    正常(0),警惕(10),危险(20);

    private double value;
    Project_riskStatus(double value){
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
