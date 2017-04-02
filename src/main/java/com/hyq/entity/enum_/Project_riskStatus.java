package com.hyq.entity.enum_;

/**
 * Created by genius on 2017/3/20.
 */
public enum Project_riskStatus {
    正常(0,"<span>正常&nbsp;&nbsp;</span><i class='fa fa-square' style='font-size: 16px;color: #00bcd4;'></i>"),
    警惕(10,"<span>警惕&nbsp;&nbsp;</span><i class='fa fa-square' style='font-size: 16px;color: #00bcd4;'></i>"),
    危险(20,"<span>危险&nbsp;&nbsp;</span><i class='fa fa-square' style='font-size: 16px;color: #00bcd4;'></i>");

    private double value;
    private String showInHtml;

    Project_riskStatus(double value,String showInHtml){
        this.value = value;
        this.showInHtml = showInHtml;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getShowInHtml() {
        return showInHtml;
    }

    public void setShowInHtml(String showInHtml) {
        this.showInHtml = showInHtml;
    }
}
