package com.hyq.controller;

import com.hyq.condition.Condition;
import com.hyq.entity.Bug;
import com.hyq.entity.Project;
import com.hyq.entity.User;
import com.hyq.entity.Version;
import com.hyq.service.MyService;
import com.hyq.util.CheckUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by genius on 2017/3/14.
 */
@Controller
@RequestMapping("/version")
public class VersionController {

    @Resource
    private MyService myService;

    @RequestMapping("/preSave")
    public ModelAndView preSave(Integer id){
        ModelAndView mav = new ModelAndView();
        if (CheckUtil.isNotNull(id)){
            Version version = (Version) myService.getEntityById(Version.class,id);
            mav.addObject("version",version);
        }
        mav.addObject("subPage","/version/saveVersion.jsp");
        mav.setViewName("main");
        return mav;
    }


    @RequestMapping("/list")
    public ModelAndView list(Version version){
        ModelAndView mav = new ModelAndView();
        List<Version> versionList = myService.findEntityList(new Condition(version),null);
        mav.addObject("versionList",versionList);
        mav.addObject("subPage","/version/versionList.jsp");
        mav.setViewName("main");
        return mav;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public String delete(@RequestParam Integer id){
        myService.deleteEntity(Version.class,id);
        return "{\"success\":true}";
    }

    @RequestMapping("/save")
    public String save(Version version, HttpServletRequest request, @RequestParam(value = "file",required = false)CommonsMultipartFile file)throws IOException {
        if (CheckUtil.isNotNull(file) && CheckUtil.isNotNull(file.getOriginalFilename())){
            String fileName = file.getOriginalFilename();
            String contextPath = request.getServletContext().getContextPath();
            StringBuffer sb = request.getRequestURL();
            int tag = sb.toString().indexOf(contextPath);
            String result = sb.toString().substring(0,tag);
            String realPath = request.getServletContext().getRealPath("/upload/version/")+fileName;
            String url = result+contextPath+"/upload/version/"+fileName;
            File newFile = new File(realPath);
            file.transferTo(newFile);
            version.setAttachment("<a href='"+url+"'>"+fileName+"</a>");
            System.out.println(fileName);
            System.out.println(url);
            System.out.println("realPath:"+realPath);
        }

        if (CheckUtil.isNotNull(version.getOwner())){
            User owner = (User) myService.getEntityById(User.class,version.getOwner().getId());
            version.setOwner(owner);
        }
        if (CheckUtil.isNotNull(version.getProject())){
            Project project = (Project) myService.getEntityById(Project.class,version.getProject().getId());
            version.setProject(project);
        }

        if (CheckUtil.isNotNull(version.getId())){
            myService.updateEntity(version);
        }else{
            User currentUser = (User) request.getSession().getAttribute("currentUser");
            version.setCreator(currentUser);
            myService.saveEntity(version);
        }
        return "redirect:/version/list.do";
    }

    @ResponseBody
    @RequestMapping(value = "/list_json",produces = "text/html;charset=UTF-8")
    public String list_json(){
        List<Version> versionList = myService.findEntityList(new Condition(Version.class),null);
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(versionList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

}
