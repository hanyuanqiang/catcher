package com.hyq.entity.enum_;

/**
 * Created by genius on 2017/3/12.
 */
public enum Bug_status {
    创建中("creating","<span>创建中&nbsp;&nbsp;</span><i class='fa fa-square' style='font-size: 16px;color: #00bcd4;'></i>"),
    待分配("unassign","<span>待分配&nbsp;&nbsp;</span><i class='fa fa-square' style='font-size: 16px;color: #00bcd4;'></i>"),
    处理中("process","<span>处理中&nbsp;&nbsp;</span><i class='fa fa-square' style='font-size: 16px;color: #00bcd4;'></i>"),
    验证中("validate","<span>验证中&nbsp;&nbsp;</span><i class='fa fa-square' style='font-size: 16px;color: #00bcd4;'></i>"),
    已废弃("abandon","<span>已废弃&nbsp;&nbsp;</span><i class='fa fa-square' style='font-size: 16px;color: #00bcd4;'></i>"),
    已关闭("close","<span>已关闭&nbsp;&nbsp;</span><i class='fa fa-square' style='font-size: 16px;color: #00bcd4;'></i>"),
    已挂起("hang","<span>已挂起&nbsp;&nbsp;</span><i class='fa fa-square' style='font-size: 16px;color: #00bcd4;'></i>"),
    重打开("rOpen","<span>重打开&nbsp;&nbsp;</span><i class='fa fa-square' style='font-size: 16px;color: #00bcd4;'></i>");

    private String name;
    private String showInHtml;
    Bug_status(String name,String showInHtml){
        this.name = name;
        this.showInHtml = showInHtml;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShowInHtml() {
        return showInHtml;
    }

    public void setShowInHtml(String showInHtml) {
        this.showInHtml = showInHtml;
    }
}
