package com.hyq.entity.enum_;

/**
 * Created by genius on 2017/3/12.
 */
public enum Bug_status {
    创建中("creating"),待分配("unassign"),
    处理中("process"),验证中("validate"),
    已废弃("abandon"), 已关闭("close"),
    已挂起("hang"),重打开("rOpen");

    private String name;
    Bug_status(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
