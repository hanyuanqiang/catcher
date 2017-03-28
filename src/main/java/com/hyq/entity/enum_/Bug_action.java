package com.hyq.entity.enum_;

/**
 * Created by genius on 2017/3/25.
 */
public enum Bug_action {

    save("保存"),commit("提交"),close("关闭"),unassign("未分配"),rtnUnassign("退回未分配"),rtnCreating("退回创建中"),reactivate("重新激活"),rtnProcess("退回处理中"),
    returm("退回"),hang("挂起"),receive("领取"),abandon("废弃"),rOpen("重打开");

    private String name;
    Bug_action(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
