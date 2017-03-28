package com.hyq.controller;

import com.google.common.collect.Sets;
import com.hyq.dao.BaseDAO;
import com.hyq.dao.MyDAO;
import com.hyq.entity.Bug;
import com.hyq.entity.User;
import com.hyq.entity.enum_.*;
import com.hyq.service.MyService;
import com.hyq.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by genius on 2017/3/15.
 */
@Controller()
@RequestMapping("/addData")
public class AddDataController {

    @Resource
    MyService myService;

    @RequestMapping("/addBug")
    public void addBug(){
        for (int i = 1;i<100;i++){
            Bug bug = new Bug();
            bug.setLabel("bug "+i);
            bug.setPriority(Bug_priority.最高);
            bug.setSeverity(Bug_severity.严重);
            bug.setFrequency(Bug_frequency.偶尔出现);
            myService.saveEntity(bug);
        }
    }

    @RequestMapping("/addAdmin")
    public void addAdmin(){
        User user = new User();
        user.setLabel("超级管理员");
        user.setAccount("admin");
        user.setPassword(StringUtil.getMd5("admin"));
        user.setIsAdmin(true);
        myService.saveEntity(user);
    }

    @RequestMapping("/addUsers")
    public void addUsers(){
        for (int i = 1;i<=20;i++){
            User user = new User();
            if (i%2==0){
                user.setGender(User_gender.男);
                user.setPost(User_post.开发工程师);
            }else{
                user.setGender(User_gender.女);
                user.setPost(User_post.测试工程师);
            }
            user.setBirth(new Date());
            user.setAccount("user"+i);
            user.setLabel("用户 "+i);
            user.setPassword(StringUtil.getMd5(i+""));
            user.setEmail("user"+i+"@catcher.com");
            user.setPhone("11111");
            myService.saveEntity(user);
        }

    }
}
