package com.hyq.controller;

import com.hyq.entity.Bug;
import com.hyq.entity.Project;
import com.hyq.entity.User;
import com.hyq.entity.Version;
import com.hyq.entity.enum_.Bug_action;
import com.hyq.entity.enum_.Bug_status;
import com.hyq.service.MyService;
import com.hyq.util.CheckUtil;
import com.hyq.util.DateUtil;
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
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by genius on 2017/3/14.
 */
@Controller
@RequestMapping("/bug")
public class BugController {

    @Resource
    private MyService myService;

    @RequestMapping("/preSave")
    public ModelAndView preSave(Integer id){
        ModelAndView mav = new ModelAndView();
        if (CheckUtil.isNotNull(id)){
            Bug bug = (Bug) myService.getEntityById(Bug.class,id);
            mav.addObject("bug",bug);
        }
        mav.addObject("subPage","/bug/saveBug.jsp");
        mav.setViewName("main");
        return mav;
    }

    @RequestMapping("/save")
    public String save(Bug bug, HttpServletRequest request, @RequestParam(value = "file",required = false)CommonsMultipartFile file) throws IOException, ParseException {

        if (CheckUtil.isNotNull(file) && CheckUtil.isNotNull(file.getOriginalFilename())){
            String fileName = file.getOriginalFilename();
            String contextPath = request.getServletContext().getContextPath();
            StringBuffer sb = request.getRequestURL();
            int tag = sb.toString().indexOf(contextPath);
            String result = sb.toString().substring(0,tag);
            String realPath = request.getServletContext().getRealPath("/upload/bug/")+fileName;
            String url = result+contextPath+"/upload/bug/"+fileName;
            File newFile = new File(realPath);
            file.transferTo(newFile);
            bug.setAttachment("<a href='"+url+"'>"+fileName+"</a>");
            System.out.println(fileName);
            System.out.println(url);
            System.out.println("realPath:"+realPath);
        }

        User currentUser = (User) request.getSession().getAttribute("currentUser");
        StringBuffer resolution = new StringBuffer("<p style='margin-top: 7px;background-color:#FFFFE0'>操作人："+currentUser.getLabel()+"，操作时间："+ DateUtil.transDate2Str(new Date())+"，状态变化：");

        if (CheckUtil.isNotNull(bug.getId())){
            Bug bugFromDB = (Bug) myService.getEntityByIdAndClearSession(Bug.class,bug.getId());
            if (bug.getSolver() == null){
                if (bugFromDB.getSolver()!=null){
                    bug.setSolver(bugFromDB.getSolver());
                }
            }else{
                User solver = (User) myService.getEntityById(User.class,bug.getSolver().getId());
                bug.setSolver(solver);
            }

            if (bug.getVerifier() == null){
                if (bugFromDB.getVerifier()!=null){
                    bug.setVerifier(bugFromDB.getVerifier());
                }
            }else{
                User verifier = (User) myService.getEntityById(User.class,bug.getVerifier().getId());
                bug.setVerifier(verifier);
            }

//            bug.setResolution(bugFromDB.getResolution());

            if (Bug_status.创建中.equals(bug.getStatus())){
                if (Bug_action.abandon.equals(bug.getAction())){
                    resolution.append(Bug_status.创建中.getShowInHtml()+" —> "+Bug_status.已废弃.getShowInHtml());
                    bug.setStatus(Bug_status.已废弃);
                }else if (Bug_action.commit.equals(bug.getAction())){
                    if (bug.getSolver() == null){
                        resolution.append(Bug_status.创建中.getShowInHtml()+" —> "+Bug_status.待分配.getShowInHtml());
                        bug.setStatus(Bug_status.待分配);
                    }else{
                        resolution.append(Bug_status.创建中.getShowInHtml()+" —> "+Bug_status.处理中.getShowInHtml());
                        bug.setStatus(Bug_status.处理中);
                    }
                }else if (Bug_action.unassign.equals(bug.getAction())){
                    resolution.append(Bug_status.创建中.getShowInHtml()+" —> "+Bug_status.待分配.getShowInHtml());
                    bug.setStatus(Bug_status.待分配);
                }else if (Bug_action.save.equals(bug.getAction())){
                    resolution.append(Bug_status.创建中.getShowInHtml()+" —> "+Bug_status.已废弃.getShowInHtml());
                    bug.setStatus(Bug_status.创建中);
                }
            }else if (Bug_status.待分配.equals(bug.getStatus())){
                resolution.append(Bug_status.待分配.getShowInHtml()+" —> ");
                if (Bug_action.commit.equals(bug.getAction())){
                    resolution.append(Bug_status.处理中.getShowInHtml());
                    bug.setStatus(Bug_status.处理中);
                }else if (Bug_action.receive.equals(bug.getAction())){
                    resolution.append(Bug_status.处理中.getShowInHtml());
                    bug.setStatus(Bug_status.处理中);
                }else if (Bug_action.hang.equals(bug.getAction())){
                    resolution.append(Bug_status.已挂起.getShowInHtml());
                    bug.setStatus(Bug_status.已挂起);
                }else if (Bug_action.rtnCreating.equals(bug.getAction())){
                    resolution.append(Bug_status.创建中.getShowInHtml());
                    bug.setStatus(Bug_status.创建中);
                }else if (Bug_action.save.equals(bug.getAction())){
                    resolution.append(Bug_status.待分配.getShowInHtml());
                    bug.setStatus(Bug_status.待分配);
                }
            }else if (Bug_status.处理中.equals(bug.getStatus())){
                resolution.append(Bug_status.处理中.getShowInHtml()+" —> ");
                if (Bug_action.commit.equals(bug.getAction())){
                    resolution.append(Bug_status.验证中.getShowInHtml());
                    bug.setStatus(Bug_status.验证中);
                }else if (Bug_action.rtnCreating.equals(bug.getAction())){
                    resolution.append(Bug_status.创建中.getShowInHtml());
                    bug.setStatus(Bug_status.创建中);
                }else if (Bug_action.rtnUnassign.equals(bug.getAction())){
                    resolution.append(Bug_status.待分配.getShowInHtml());
                    bug.setStatus(Bug_status.待分配);
                }else if (Bug_action.hang.equals(bug.getAction())){
                    resolution.append(Bug_status.已挂起.getShowInHtml());
                    bug.setStatus(Bug_status.已挂起);
                }else if (Bug_action.save.equals(bug.getAction())){
                    resolution.append(Bug_status.处理中.getShowInHtml());
                    bug.setStatus(Bug_status.处理中);
                }
            }else if (Bug_status.已废弃.equals(bug.getStatus())){
                resolution.append(Bug_status.已废弃.getShowInHtml()+" —> ");
                if (Bug_action.reactivate.equals(bug.getAction())){
                    resolution.append(Bug_status.创建中.getShowInHtml());
                    bug.setStatus(Bug_status.创建中);
                }else if (Bug_action.save.equals(bug.getAction())){
                    resolution.append(Bug_status.已废弃.getShowInHtml());
                    bug.setStatus(Bug_status.已废弃);
                }
            }else if (Bug_status.验证中.equals(bug.getStatus())){
                resolution.append(Bug_status.验证中.getShowInHtml()+" —> ");
                if (Bug_action.close.equals(bug.getAction())){
                    resolution.append(Bug_status.已关闭.getShowInHtml());
                    bug.setStatus(Bug_status.已关闭);
                }else if (Bug_action.rtnProcess.equals(bug.getAction())){
                    resolution.append(Bug_status.处理中.getShowInHtml());
                    bug.setStatus(Bug_status.处理中);
                }else if (Bug_action.save.equals(bug.getAction())){
                    resolution.append(Bug_status.验证中.getShowInHtml());
                    bug.setStatus(Bug_status.验证中);
                }
            }else if(Bug_status.已挂起.equals(bug.getStatus())){
                resolution.append(Bug_status.已挂起.getShowInHtml()+" —> ");
                if (Bug_action.save.equals(bug.getAction())){
                    resolution.append(Bug_status.已挂起.getShowInHtml());
                    bug.setStatus(Bug_status.已挂起);
                }else if (Bug_action.rtnUnassign.equals(bug.getAction())){
                    resolution.append(Bug_status.待分配.getShowInHtml());
                    bug.setStatus(Bug_status.待分配);
                }
            }else if (Bug_status.重打开.equals(bug.getStatus())){
                resolution.append(Bug_status.重打开.getShowInHtml()+" —> ");
                if (Bug_action.save.equals(bug.getAction())){
                    resolution.append(Bug_status.重打开.getShowInHtml());
                    bug.setStatus(Bug_status.重打开);
                }else if (Bug_action.rtnProcess.equals(bug.getAction())){
                    resolution.append(Bug_status.处理中.getShowInHtml());
                    bug.setStatus(Bug_status.处理中);
                }else if (Bug_action.receive.equals(bug.getAction())){
                    resolution.append(Bug_status.处理中.getShowInHtml());
                    bug.setStatus(Bug_status.处理中);
                }
            }else if (Bug_status.已关闭.equals(bug.getStatus())){
                resolution.append(Bug_status.已关闭.getShowInHtml()+" —> ");
                if (Bug_action.rOpen.equals(bug.getAction())){
                    resolution.append(Bug_status.重打开.getShowInHtml());
                    bug.setStatus(Bug_status.重打开);
                }
            }else if (Bug_status.已废弃.equals(bug.getStatus())){
                resolution.append(Bug_status.已废弃.getShowInHtml()+" —> ");
                if (Bug_action.rtnCreating.equals(bug.getAction())) {
                    resolution.append(Bug_status.创建中.getShowInHtml());
                    bug.setStatus(Bug_status.创建中);
                }
            }

            bug.setResolution(bugFromDB.getResolution()+resolution.toString());
            myService.updateEntity(bug);
//            myService.mergeEntity(bug);

        }else{
            bug.setCreator(currentUser);
            if (Bug_action.save.equals(bug.getAction())){
                resolution.append("未创建 —> "+Bug_status.创建中.getShowInHtml());
                bug.setStatus(Bug_status.创建中);
            }else if (Bug_action.commit.equals(bug.getAction())){
                if (bug.getSolver() == null){
                    resolution.append("未创建 —> "+Bug_status.待分配.getShowInHtml());
                    bug.setStatus(Bug_status.待分配);
                }else{
                    resolution.append("未创建 —> "+Bug_status.处理中.getShowInHtml());
                    bug.setStatus(Bug_status.处理中);
                }
            }else if (Bug_action.unassign.equals(bug.getAction())){
                resolution.append("未创建 —> "+Bug_status.待分配.getShowInHtml());
                bug.setStatus(Bug_status.待分配);
            }
            resolution.append("</p>");
            bug.setResolution(resolution.toString());
            myService.saveEntity(bug);
        }
        return "redirect:/bug/list.do";
    }

    @ResponseBody
    @RequestMapping("/delete")
    public String delete(@RequestParam Integer id){
        myService.deleteEntity(Bug.class,id);
        return "{\"success\":true}";
    }

    @RequestMapping("/list")
    public ModelAndView list(Bug bug){
        ModelAndView mav = new ModelAndView();
        List<Bug> bugList = myService.findEntityList(Bug.class,bug,null);
        mav.addObject("bugList",bugList);
        mav.addObject("subPage","/bug/bugList.jsp");
        mav.setViewName("main");
        return mav;
    }

}
