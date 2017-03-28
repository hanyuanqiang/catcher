package com.hyq.service.impl;

import com.hyq.dao.MyDAO;
import com.hyq.entity.Affiche;
import com.hyq.entity.PageBean;
import com.hyq.service.AfficheService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * Created by genius on 2017/3/13.
 */
@Service("afficheService")
public class AfficheServiceImpl implements AfficheService{

//    @Resource
//    private BaseDAO<Affiche> baseDAO;

    @Resource
    private MyDAO<Affiche> myDAO;

    public List<Affiche> findAfficheList(Affiche s_affiche, PageBean pageBean) {
        return myDAO.find(Affiche.class,s_affiche,pageBean);
    }

    public Affiche getAfficheById(int id) {
        return myDAO.get(Affiche.class,id);
    }

    public Object saveAffiche(Affiche affiche) {
        return myDAO.merge(affiche);
    }

    public void deleteAffiche(int id) {
        Affiche affiche = new Affiche();
        affiche.setId(id);
        myDAO.delete(affiche);
    }

    public int deleteAffiches(Set<Integer> ids) {
        return myDAO.delete(Affiche.class,ids);
    }

}
