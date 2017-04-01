package com.hyq.condition;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by genius on 2017/3/14.
 */
public class Condition {

    public static final String EQ = "eq";//相等
    public static final String NE = "ne";//不相等
    public static final String GT = "gt";//大于
    public static final String GE = "ge";//大于等于
    public static final String LT = "lt";//小于
    public static final String LE = "le";//小于等于

    public static final String LIKE = "like";   //模糊查询
    public static final String ISEMPTY = "isEmpty";
    public static final String ISNOTEMPTY = "isNotEmpty";
    public static final String ISNULL = "isNull";
    public static final String ISNOTNULL = "isNotNull";

    public static String ORDER = "desc";

    private Object entity;

    private Map<String,String> fieldConditionMap = Maps.newHashMap();

    private Class clazz;

    public Condition(Class clazz){
        this.clazz = clazz;
    }

    public Condition(Object entity) {
        clazz = entity.getClass();
        this.entity = entity;
    }

    public void addCondition(String fieldName, String condition){
        fieldConditionMap.put(fieldName,condition);
    }

    public Object getEntity() {
        return entity;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }

    public Map<String, String> getFieldConditionMap() {
        return fieldConditionMap;
    }

    public void setFieldConditionMap(Map<String, String> fieldConditionMap) {
        this.fieldConditionMap = fieldConditionMap;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }
}
