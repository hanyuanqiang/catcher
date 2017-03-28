package com.hyq.controller;

import com.google.common.collect.Lists;
import com.hyq.entity.Project;
import com.hyq.entity.User;
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
@RequestMapping("/project")
public class ProjectController {

    @Resource
    private MyService myService;


    @RequestMapping("/preSave")
    public ModelAndView preSave(Integer id){
        ModelAndView mav = new ModelAndView();
        if (CheckUtil.isNotNull(id)){
            Project project = (Project) myService.getEntityById(Project.class,id);
            mav.addObject("project",project);
        }
        mav.addObject("subPage","/project/saveProject.jsp");
        mav.setViewName("main");
        return mav;
    }

    @RequestMapping("/save")
    public String save(Project project, HttpServletRequest request, @RequestParam(value = "file",required = false)CommonsMultipartFile file)throws IOException{

        if (CheckUtil.isNotNull(file) && CheckUtil.isNotNull(file.getOriginalFilename())){
            String fileName = file.getOriginalFilename();
            String contextPath = request.getServletContext().getContextPath();
            StringBuffer sb = request.getRequestURL();
            int tag = sb.toString().indexOf(contextPath);
            String result = sb.toString().substring(0,tag);
            String realPath = request.getServletContext().getRealPath("/upload/project/")+fileName;
            String url = result+contextPath+"/upload/project/"+fileName;
            File newFile = new File(realPath);
            file.transferTo(newFile);
            project.setAttachment("<a href='"+url+"'>"+fileName+"</a>");
            System.out.println(fileName);
            System.out.println(url);
            System.out.println("realPath:"+realPath);
        }

        if (CheckUtil.isNotNull(project.getOwner())){
            User owner = (User) myService.getEntityById(User.class,project.getOwner().getId());
            project.setOwner(owner);
        }

        project.setMembers(handleMembers(project.getMembers()));

        if (CheckUtil.isNotNull(project.getId())){
            myService.updateEntity(project);
        }else{
            User currentUser = (User) request.getSession().getAttribute("currentUser");
            project.setCreator(currentUser);
            project.setMembers(handleMembers(project.getMembers()));
            myService.saveEntity(project);
        }
        return "redirect:/project/list.do";
    }

    @RequestMapping("/list")
    public ModelAndView list(Project project){
        ModelAndView mav = new ModelAndView();
        List<Project> projectList = myService.findEntityList(Project.class,project,null);
        mav.addObject("projectList",projectList);
        mav.addObject("subPage","/project/projectList.jsp");
        mav.setViewName("main");
        return mav;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public String delete(@RequestParam Integer id){
        myService.deleteEntity(Project.class,id);
        return "{\"success\":true}";
    }

    @ResponseBody
    @RequestMapping(value = "/list_json",produces = "text/html;charset=UTF-8")
    public String list_json(){
        List<Project> projectList = myService.findEntityList(Project.class,null,null);
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(projectList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }



    /*去掉空的members*/
    public List<User> handleMembers(List<User> members){
        List<User> userList = Lists.newArrayList();
        for (User user : members){
            if (user == null || user.getId() == null){
            }else{
                userList.add(user);
            }
        }
        return userList;
    }

}
