package com.hyq.service.impl;

import com.hyq.dao.MyDAO;
import com.hyq.entity.Affiche;
import com.hyq.entity.PageBean;
import com.hyq.service.AfficheService;
import com.hyq.service.MyService;
import com.hyq.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Set;

/**
 * Created by genius on 2017/3/13.
 */
@Service("myService")
public class MyServiceImpl<T> implements MyService<T> {

    @Resource
    private MyDAO<T> myDAO;

    public List<T> findEntityList(Class<T> c,T s_o, PageBean pageBean) {
        return myDAO.find(c,s_o,pageBean);
    }

    public T getEntityById(Class<T> c, int id) {
        return myDAO.get(c,id);
    }

    public Object saveEntity(T o) {
        return myDAO.save(o);
    }

    public void updateEntity(T o) {
        myDAO.update(o);
    }

    public void deleteEntity(Class<T> c, int id) {
        try {
            T  o = (T) c.newInstance();
            Method met = o.getClass().getMethod("setId",Integer.class);
            met.invoke(o,id);
            myDAO.delete(o);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public int deleteEntity(Class<T> c, Set<Integer> ids) {
        return myDAO.delete(c,ids);
    }

    public T getEntityByField(Class<T> c, String fieldName, String fieldValue) {
        return myDAO.getEntityByField(c,fieldName,fieldValue);
    }

    public Object mergeEntity(T o) {
        return myDAO.merge(o);
    }

    public void updateOneFieldOfEntity(Class<T> c, String fieldName, Object fieldValue, int id) {
        myDAO.updateOneFieldOfEntity(c,fieldName,fieldValue,id);
    }

    public T getEntityByIdAndClearSession(Class<T> c, int id) {
        T o = myDAO.get(c,id);
        myDAO.clearSession();
        return o;
    }
}
