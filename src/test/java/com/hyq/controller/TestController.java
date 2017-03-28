package com.hyq.controller;

import com.google.common.collect.Sets;
import com.hyq.dao.MyDAO;
import com.hyq.dao.impl.BaseDAOImpl;
import com.hyq.dao.impl.MyDAOImpl;
import com.hyq.entity.Bug;
import com.hyq.entity.User;
import com.hyq.entity.Version;
import com.hyq.service.MyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by genius on 2017/3/13.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Resource
    private BaseDAOImpl baseDAO;

    @Resource
    private MyDAOImpl myDAO;

    @Resource
    private MyService<Bug> myService;

    @RequestMapping("/saveVersion")
    public String saveVersion(Map<String, Object> map){
        Version version = new Version();
        version.setId(1);
        version.setLabel("version 1.0");
        baseDAO.save(version);
        map.put("message","version保存成功");
        return "hello";
    }

    @RequestMapping("/saveBug")
    public String saveBug(Map<String, Object> map){
        Version version = new Version();
        version.setId(1);
        for (int i =1;i<=10;i++){
            Bug bug = new Bug();
            bug.setId(i);
            bug.setLabel("bug"+i);
            bug.setRaisedVersion(version);
            baseDAO.save(bug);
        }
        map.put("message","bug保存成功");
        return "hello";
    }

    @RequestMapping("/getBugThroughVersion")
    public String getBugThroughVersion(Map<String, Object> map){
        Version version = (Version)baseDAO.get(Version.class,1);
        map.put("message","raisedVersion的总数是："+version.getRaisedBugs().size());
        return "hello";
    }

    @RequestMapping("/findBug")
    public String findBug(Map<String, Object> map){
        /*Bug s_bug = new Bug();
        s_bug.setId(1);
        s_bug.setLabel("BUG");
        List<Bug> bugs = myDAO.find(s_bug,null);
        map.put("message","查询到bug的数量为："+bugs.size());*/
        /*Version version = new Version();
        version.setId(1);
        version.setLabel("ver");
        List<Version> versions = myDAO.find(version,null);
        map.put("message","查询到bug的数量为："+versions.get(0).getRaisedBugs().size());*/

        /*Version version = new Version();
        User user = new User();
        user.setAccount("adm");
        version.setOwner(user);
        List<Version> versions = myDAO.find(version,null);
        map.put("message","查询到bug的数量为："+versions.get(0).getRaisedBugs().size());*/

        Bug s_bug = new Bug();
        Version raisedVersion = new Version();
//        raisedVersion.setId(1);
        raisedVersion.setLabel("ver");
//        s_bug.setRaisedVersion(raisedVersion);
        List<Bug> bugs = myDAO.find(Bug.class,s_bug,null);
        map.put("message","查询到bug的数量为："+bugs.size());

        return "hello";
    }

    @RequestMapping("/crud")
    public String crud(Map<String, Object> map){
        /*Bug bug = new Bug();
        bug.setId(12);
        bug.setLabel("BUG"+100);
        bug.setSubmitDate(new Date());
        myService.saveEntity(bug);
        map.put("message","bug保存成功");*/

        /*Bug bug = new Bug();
        bug.setId(12);
        bug.setLabel("BUG"+11);
        bug.setEndDate(new Date());
        bug.setSubmitDate(new Date());
        myService.saveEntity(bug);
        map.put("message","bug修改成功");*/

        /*myService.deleteEntity(Bug.class,11);
        map.put("message","bug删除成功");*/

        /*Version raisedVersion = new Version();
        raisedVersion.setId(1);
        Bug s_bug = new Bug();
        s_bug.setRaisedVersion(raisedVersion);
        List<Bug> bugs = myService.findEntityList(s_bug,null);
        map.put("message","bug查询成功，共"+bugs.size()+"条");*/

        Set<Integer> ids = Sets.newHashSet(7,9,11);
        int temp = myService.deleteEntity(Bug.class,ids);
        map.put("message","批量删除成功，影响数目："+temp);
        return "hello";
    }

}
