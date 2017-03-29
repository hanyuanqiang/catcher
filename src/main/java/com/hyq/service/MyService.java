package com.hyq.service;

import com.hyq.entity.Affiche;
import com.hyq.entity.PageBean;
import com.hyq.util.StringUtil;

import java.util.List;
import java.util.Set;

/**
 * Created by genius on 2017/3/14.
 */
public interface MyService<T> {

    public List<T> findEntityList(Class<T> c,T s_o, PageBean pageBean);

    public T getEntityById(Class<T> c, int id);

    public Object saveEntity(T o);    //保存一个实体

    public void updateEntity(T o);      //更新一个实体

    public void deleteEntity(Class<T> c, int id);   //删除单个元素

    public int deleteEntity(Class<T> c, Set<Integer> ids);    //批量删除

    public T getEntityByField(Class<T> c, String fieldName, String fieldValue); //指定字段查找

    public Object mergeEntity(T o);

    public void updateOneFieldOfEntity(Class<T> c, String fieldName, Object fieldValue, int id);

    public T getEntityByIdAndClearSession(Class<T> c,int id);
}
