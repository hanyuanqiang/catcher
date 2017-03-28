package com.hyq.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by genius on 2017/3/10.
 */
public class EntityTest {

    private int id;
    @Max(value = 100,message = "不能大于100")
    @Min(value = 1,message = "不能小于1")
    private int age;
    @NotEmpty(message = "姓名不能为空")
    private String name;
    private String hobby;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}