package com.hyq.entity.enum_;

/**
 * bug关闭理由
 * Created by genius on 2017/3/12.
 */
public enum  Bug_closeReason {
    已解决("solved"),不是问题("notIssue"),重复问题("knownIssue"),无法重现("cantReproduce"),无法解决("cantSolve"),暂不解决("suspend");

    private String name;

    Bug_closeReason(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
