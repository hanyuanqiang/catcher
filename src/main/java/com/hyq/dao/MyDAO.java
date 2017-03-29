package com.hyq.dao;

import com.hyq.entity.PageBean;
import com.hyq.util.StringUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by genius on 2017/3/13.
 */
public interface MyDAO<T> {

    /**
     * 保存一个对象
     *
     * @param o
     * @return
     */
    public Serializable save(T o);

    /**
     * 删除一个对象
     *
     * @param o
     */
    public void delete(T o);

    /**
     * 更新一个对象
     *
     * @param o
     */
    public void update(T o);

    /**
     * 保存或更新对象
     *
     * @param o
     */
    public void saveOrUpdate(T o);

    /**
     * 合并对象
     * @param o
     */
    public Object merge(T o);

    /**
     * 获得一个对象
     *
     * @param c
     *            对象类型
     * @param id
     * @return Object
     */
    public T get(Class<T> c, Serializable id);

    public List<T> find(Class<T> c,T s_o, PageBean pageBean);

    public int  delete(Class<T> c, Set<Integer> ids);

    public T getEntityByField(Class<T> c, String fieldName, String fieldValue);

    public void updateOneFieldOfEntity(Class<T> c, String fieldName,Object fieldValue,int id);

    public void clearSession();
}
