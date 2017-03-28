package com.hyq.controller;

import com.hyq.dao.impl.BaseDAOImpl;
import com.hyq.entity.EntityTest;
import com.hyq.entity.enum_.User_gender;
import com.hyq.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by genius on 2017/3/10.
 */
@Controller
@RequestMapping("/people")
public class PeopleController {

    @Resource
    private BaseDAOImpl baseDAO;

    @RequestMapping("/test1")
    public ModelAndView test1(@RequestParam(value = "param", required = false)String param, HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        mav.addObject("name","test1,天才");
        mav.addObject("age","test1,11");
        mav.addObject("hello","test1,"+param);
        mav.setViewName("hello");
        return mav;
    }

    @RequestMapping("/test2")
    public String test2(Map<String ,Object> map){
        map.put("name","test2天才");
        map.put("age","test2,11");
        map.put("hello","test2,hello");
        return "hello";
    }

    @RequestMapping("/test3")
    public String test3(Map<String ,Object> map){
        map.put("name","test3天才");
        map.put("age","test3,11");
        map.put("hello","test3,hello");
        return "redirect:/error.jsp";
    }

    @RequestMapping(value="/test4",method= RequestMethod.POST)
    public String test4(HttpServletRequest req) throws Exception{
        MultipartHttpServletRequest mreq = (MultipartHttpServletRequest)req;
        MultipartFile file = mreq.getFile("file");
        String fileName = file.getOriginalFilename();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        FileOutputStream fos = new FileOutputStream(req.getSession().getServletContext().getRealPath("/")+
                "upload/"+sdf.format(new Date())+fileName.substring(fileName.lastIndexOf('.')));
        fos.write(file.getBytes());
        fos.flush();
        fos.close();

        return "hello";
    }

    @RequestMapping(value="/test5",method= RequestMethod.POST)
    public String test5(@Valid EntityTest people) throws Exception{
        System.out.println("姓名："+people.getName());
        System.out.println("年龄："+people.getAge());
        return "hello";
    }

    @RequestMapping(value="/test6")
    public String test6() throws Exception{
        User user = new User();
        user.setPassword("111");
        int id = (Integer) baseDAO.save(user);
        System.out.println(id);
        user = (User)baseDAO.get(User.class,id);
        baseDAO.delete(user);
        return "hello";
    }

    @RequestMapping(value="/test7")
    public String test7() throws Exception{
        User u = new User();
        u.setPassword("1232");
        u.setAccount("account");
        u.setLabel("tiancia");
        u.setCreateTime(new Date());
        u.setGender(User_gender.女);
        int userId = (Integer) baseDAO.save(u);
        return "hello";
    }

    /*@ExceptionHandler
    public ModelAndView error(Exception ex){
        ModelAndView mav = new ModelAndView();
        System.out.println(ex.getMessage());
        mav.addObject("name","发生错误");
        mav.addObject("age","0");
        mav.setViewName("hello");
        return mav;
    }*/
}
