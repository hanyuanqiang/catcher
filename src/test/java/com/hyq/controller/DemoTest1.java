package com.hyq.controller;

import com.google.common.collect.Lists;
import com.hyq.entity.Bug;
import com.hyq.util.StringUtil;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by genius on 2017/3/13.
 */
public class DemoTest1 {

    @Test
    public void test(){
        Bug bug = new Bug();
        bug.setId(100);
        Class<?> clazz = bug.getClass();
        Field[] fields = bug.getClass().getDeclaredFields();
        for (Field field : fields){
            String fieldName = field.getName();
            String fieldType = field.getType().getName();
            String methodName = "get"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
            System.out.println("方法名："+methodName);
            try {
                Object fieldValue = bug.getClass().getMethod(methodName).invoke(bug);
                System.out.println(fieldName+" : "+fieldType+" : "+fieldValue);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test01(){
        List<String> c = Lists.newArrayList();
        System.out.println(c.getClass().getName());
        System.out.println(StringUtil.getMd5("8235143++"));
        Date date = new Date();
        System.out.println(date.getClass().getName());

        System.out.println(Collection.class.isAssignableFrom(List.class));
        System.out.println(Collection.class.isAssignableFrom(Set.class));

        List<Integer> ids1 = Lists.newArrayList(1,2,4,2);
        List<Integer> ids2 = Lists.newArrayList(1,2,4,2,2);
        System.out.println(ids1.toString().equals(ids2.toString()));
        System.out.println(List.class.getName());
    }
}
