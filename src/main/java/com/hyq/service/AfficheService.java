package com.hyq.service;

import com.hyq.entity.Affiche;
import com.hyq.entity.PageBean;

import java.util.List;
import java.util.Set;

/**
 * Created by genius on 2017/3/13.
 */
public interface AfficheService {

    public List<Affiche> findAfficheList(Affiche s_affiche, PageBean pageBean);

    public Affiche getAfficheById(int id);

    public Object saveAffiche(Affiche affiche);    //保存一个实体

    public void deleteAffiche(int id);   //删除单个元素

    public int deleteAffiches(Set<Integer> ids);    //批量删除
}
