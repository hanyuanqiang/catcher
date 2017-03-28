package com.hyq.util;

import com.hyq.dao.MyDAO;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

/**
 * Created by genius on 2017/3/20.
 */
public class ClassUtil<T> {

    @Resource
    private MyDAO<T> myDAO;

    public T updateEntity(T dbEntity,T upEntity) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ParseException {
        Field[] fields = dbEntity.getClass().getDeclaredFields();
            for (Field field : fields) {
                String fieldName = field.getName();        //字段名
                String fieldType = field.getType().getName();    //字段类型
                String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);    //合成该字段的getter方法
                Object fieldValue1 = dbEntity.getClass().getMethod(methodName).invoke(dbEntity);    //执行getter方法获取该字段的值
                Object fieldValue2 = upEntity.getClass().getMethod(methodName).invoke(upEntity);    //执行getter方法获取该字段的值
                /*if (!CheckUtil.isEqual(fieldValue1,fieldValue2)){
                    String setterMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);    //合成该字段的setter方法
                    upEntity.getClass().getMethod(setterMethodName,Class.forName(fieldType)).invoke(upEntity,fi)
                }*/

                if (fieldValue1 !=null && fieldValue2 == null){ //更新时删除掉了

                }

            }
        return null;
    }

}
