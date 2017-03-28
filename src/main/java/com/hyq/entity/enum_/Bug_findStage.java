package com.hyq.entity.enum_;

/**
 * bug发现阶段
 * Created by genius on 2017/3/12.
 */
public enum  Bug_findStage {
    设计阶段("design"),开发阶段("develop"),测试阶段("test"),运行阶段("run");



    private String name;
    Bug_findStage(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
