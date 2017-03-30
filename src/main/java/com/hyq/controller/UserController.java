package com.hyq.controller;

import com.hyq.entity.User;
import com.hyq.service.MyService;
import com.hyq.util.CheckUtil;
import com.hyq.util.StringUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by genius on 2017/3/14.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private MyService myService;

    @RequestMapping("/login")
    public String login(@Valid User loginUser, HttpServletRequest request,Map<String,Object> map){
//        ModelAndView mav = new ModelAndView();
        String password = StringUtil.getMd5(loginUser.getPassword());
        loginUser = (User) myService.getEntityByField(User.class,"account",loginUser.getAccount());
        if (loginUser != null){
            if (loginUser.getPassword().equals(password)){
                request.getSession().setAttribute("currentUser",loginUser);

                return "redirect:/user/main.do";
            }else{
                map.put("message","登陆密码错误");
                return "error";
            }
        }else {
            map.put("message","登陆密码错误");
            return "error";
        }
    }

    @RequestMapping("/main")
    public String bgmain(Map<String,Object> map){
        return "main";
    }

    @RequestMapping("/register")
    public ModelAndView register(@Valid User registerUser){
        ModelAndView mav = new ModelAndView();
        String password = StringUtil.getMd5(registerUser.getPassword());
        registerUser.setPassword(password);
        myService.saveEntity(registerUser);
        mav.addObject("message","用户添加成功");
        mav.setViewName("hello");
        return mav;
    }

    @RequestMapping("/list")
    public ModelAndView list(){
        ModelAndView mav = new ModelAndView();
        List<User> userList = myService.findEntityList(User.class,null,null);
        mav.addObject("userList",userList);
        mav.addObject("subPage","/user/userList.jsp");
        mav.setViewName("main");
        return mav;
    }

    @RequestMapping("/preSave")
    public ModelAndView preSave(Integer id){
        ModelAndView mav = new ModelAndView();
        if (CheckUtil.isNotNull(id)){
            User user = (User) myService.getEntityById(User.class,id);
            mav.addObject("user",user);
        }
        mav.addObject("subPage","/user/saveUser.jsp");
        mav.setViewName("main");
        return mav;
    }

    @RequestMapping("/save")
    public String save(User user){

        if(CheckUtil.isNotNull(user.getId())){  //更新操作
            myService.updateEntity(user);
        }else{  //添加操作
            user.setCreateTime(new Date());
            user.setPassword(StringUtil.getMd5(user.getPassword()));    //加密密码
            myService.saveEntity(user);
        }

        return "redirect:/user/list.do";
    }


    @ResponseBody
    @RequestMapping("/updatePassword")
    public String updatePassword(Integer id,String password){
        password = StringUtil.getMd5(password);
        myService.updateOneFieldOfEntity(User.class,"password",password,id);
        return "{\"success\":true,\"password\":\""+password+"\"}";
    }

    @ResponseBody
    @RequestMapping("/delete")
    public String delete(@RequestParam Integer id){
        myService.deleteEntity(User.class,id);
        return "{\"success\":true}";
    }

    @ResponseBody
    @RequestMapping(value = "/list_json",produces = "text/html;charset=UTF-8")
    public String list_json(){
        List<User> userList = myService.findEntityList(User.class,null,null);
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(userList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }


    /*@ExceptionHandler
    public ModelAndView error(Exception ex){
        ModelAndView mav = new ModelAndView();
        System.out.println(ex.getMessage());
        mav.addObject("message","发生错误");
        mav.setViewName("hello");
        return mav;
    }*/
}
