package com.hyq.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.hyq.condition.Condition;
import com.hyq.entity.PageBean;
import com.hyq.entity.Project;
import com.hyq.entity.User;
import com.hyq.entity.enum_.Project_riskStatus;
import com.hyq.entity.enum_.Project_status;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
            project.setRiskStatus(Project_riskStatus.正常);
            myService.saveEntity(project);
        }
        return "redirect:/project/list.do";
    }

    @RequestMapping("/list")
    public ModelAndView list(Project project,String tag){
        ModelAndView mav = new ModelAndView();
        Condition con = new Condition(project);
        if ("delay".equals(tag)){
            project.setPlanEndDate(new Date());
            project.setStatus(Project_status.进行中);
            con.addCondition("planEndDate",Condition.LT);
            con.setEntity(project);
        }
        List<Project> projectList = myService.findEntityList(con,null);
        mav.addObject("projectList",projectList);
        mav.addObject("subPage","/project/projectList.jsp");
        mav.setViewName("main");
        return mav;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public String delete(Integer id,String ids){
        if (CheckUtil.isNotNull(id)){
            myService.deleteEntity(Project.class,id);
        }else if (CheckUtil.isNotNull(ids)){
            String[] idArray = ids.split(",");
            Set<Integer> idSet = Sets.newHashSet();
            for (String idString : idArray){
                idSet.add(Integer.parseInt(idString));
            }
            myService.deleteEntity(Project.class,idSet);
        }else{
            return "{\"success\":false}";
        }
        return "{\"success\":true}";
    }

    @RequestMapping("/status")
    public ModelAndView projectStatus(){
        ModelAndView mav = new ModelAndView();
        //获取处于危险状态的项目
        Project s_project = new Project();
        s_project.setRiskStatus(Project_riskStatus.危险);
        List<Project> dangerProjectList = myService.findEntityList(new Condition(s_project),null);

        //获取处于警惕状态的项目
        s_project.setRiskStatus(Project_riskStatus.警惕);
        List<Project> warningProjectList = myService.findEntityList(new Condition(s_project),null);

        //获取已经延期的项目
        s_project.setRiskStatus(null);
        s_project.setPlanEndDate(new Date());
        s_project.setStatus(Project_status.进行中);
        Condition condition = new Condition(s_project);
        condition.addCondition("planEndDate",Condition.LT);
        List<Project> delayProjectList = myService.findEntityList(condition,null);

        //获取正在进行中的项目
        s_project = new Project();
        s_project.setStatus(Project_status.进行中);
        List<Project> ingProjectList = myService.findEntityList(new Condition(s_project),null);

        //已完成的项目
        s_project = new Project();
        s_project.setStatus(Project_status.已完成);
        List<Project> finishProjectList = myService.findEntityList(new Condition(s_project),null);

        //已完成的项目
        s_project = new Project();
        s_project.setStatus(Project_status.已归档);
        List<Project> doneProjectList = myService.findEntityList(new Condition(s_project),null);

        mav.addObject("dangerProjectList",dangerProjectList);
        mav.addObject("warningProjectList",warningProjectList);
        mav.addObject("delayProjectList",delayProjectList);
        mav.addObject("ingProjectList",ingProjectList);
        mav.addObject("finishProjectList",finishProjectList);
        mav.addObject("doneProjectList",doneProjectList);

        mav.setViewName("main");
        mav.addObject("subPage","/project/projectStatus.jsp");
        return mav;
    }

    @RequestMapping("/myProject")
    public ModelAndView myProject() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("main");
        mav.addObject("subPage","/workbench/myProject.jsp");
        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "/list_json",produces = "text/html;charset=UTF-8")
    public String list_json(HttpServletRequest request,Integer offset,Integer limit,Project project,String tag){
        PageBean pageBean = null;
        if (offset !=null && limit !=null){
            pageBean = new PageBean(limit);
            pageBean.setStart(offset);
        }

        if ("iOwner".equals(tag)){
            User user = (User) request.getSession().getAttribute("currentUser");
            project.setOwner(user);
        }else if ("iJoin".equals(tag)){

        }

        List<Project> projectList = myService.findEntityList(new Condition(project),pageBean);
        Map<String ,Object> result = Maps.newHashMap();
        result.put("rows",projectList);
        result.put("total",myService.findEntityList(new Condition(project),null).size());
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        String json = "";
        try {
            json = mapper.writeValueAsString(result);
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
