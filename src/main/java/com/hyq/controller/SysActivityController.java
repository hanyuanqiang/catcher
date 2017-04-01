package com.hyq.controller;

import com.hyq.condition.Condition;
import com.hyq.entity.SysActivity;
import com.hyq.entity.User;
import com.hyq.service.MyService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by genius on 2017/3/14.
 */
@Controller
@RequestMapping("/sysActivity")
public class SysActivityController {

    @Resource
    private MyService myService;

    @RequestMapping("/list")
    public ModelAndView list(SysActivity sysActivity, HttpServletRequest request){
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        if (!currentUser.getIsAdmin()){
            sysActivity.setOwner(currentUser);
        }else {
            sysActivity.setOwner(null);
        }
        ModelAndView mav = new ModelAndView();
        List<SysActivity> sysActivityList = myService.findEntityList(new Condition(sysActivity),null);
        mav.addObject("sysActivityList",sysActivityList);
        mav.addObject("subPage","/sysActivity/sysActivityList.jsp");
        mav.setViewName("main");
        return mav;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public String delete(@RequestParam Integer id){
        myService.deleteEntity(SysActivity.class,id);
        return "{\"success\":true}";
    }

    @ResponseBody
    @RequestMapping(value = "/list_json",produces = "text/html;charset=UTF-8")
    public String list_json(){
        List<SysActivity> sysActivityList = myService.findEntityList(new Condition(SysActivity.class),null);
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(sysActivityList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

}
