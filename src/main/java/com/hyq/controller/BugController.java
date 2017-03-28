package com.hyq.controller;

import com.hyq.entity.Bug;
import com.hyq.entity.Project;
import com.hyq.entity.User;
import com.hyq.entity.Version;
import com.hyq.entity.enum_.Bug_action;
import com.hyq.entity.enum_.Bug_status;
import com.hyq.service.MyService;
import com.hyq.util.CheckUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String save(Bug bug, HttpServletRequest request, @RequestParam(value = "file",required = false)CommonsMultipartFile file)throws IOException {

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

        /*if (CheckUtil.isNotNull(bug.getVerifier())){
            User verifier = (User) myService.getEntityById(User.class,bug.getVerifier().getId());
            bug.setVerifier(verifier);
        }

        if (CheckUtil.isNotNull(bug.getProcessor())){
            User processor = (User) myService.getEntityById(User.class,bug.getProcessor().getId());
            bug.setProcessor(processor);
        }

        if (CheckUtil.isNotNull(bug.getRaisedVersion())){
            Version raisedVersion = (Version) myService.getEntityById(Version.class,bug.getRaisedVersion().getId());
            bug.setRaisedVersion(raisedVersion);
            bug.setProject(raisedVersion.getProject());
        }

        if (bug.getProcessor() == null){
            bug.setStatus(Bug_status.创建中);
        }else if (bug.getProcessor() != null && bug.getVerifier() == null) {
            bug.setStatus(Bug_status.处理中);
        } else if (bug.getVerifier() != null){
            bug.setStatus(Bug_status.验证中);
        }*/

        if (CheckUtil.isNotNull(bug.getId())){
            if (Bug_status.创建中.equals(bug.getStatus())){
                if (Bug_action.abandon.equals(bug.getAction())){
                    bug.setStatus(Bug_status.已废弃);
                }else if (Bug_action.commit.equals(bug.getAction())){
                    if (bug.getSolver() == null){
                        bug.setStatus(Bug_status.待分配);
                    }else{
                        bug.setStatus(Bug_status.处理中);
                    }
                }else if (Bug_action.unassign.equals(bug.getAction())){
                    bug.setStatus(Bug_status.待分配);
                }else if (Bug_action.save.equals(bug.getAction())){
                    bug.setStatus(Bug_status.创建中);
                }
            }else if (Bug_status.待分配.equals(bug.getStatus())){
                if (Bug_action.commit.equals(bug.getAction())){
                    bug.setStatus(Bug_status.处理中);
                }else if (Bug_action.receive.equals(bug.getAction())){
                    bug.setStatus(Bug_status.处理中);
                }else if (Bug_action.hang.equals(bug.getAction())){
                    bug.setStatus(Bug_status.已挂起);
                }else if (Bug_action.rtnCreating.equals(bug.getAction())){
                    bug.setStatus(Bug_status.创建中);
                }else if (Bug_action.save.equals(bug.getAction())){
                    bug.setStatus(Bug_status.待分配);
                }
            }else if (Bug_status.处理中.equals(bug.getStatus())){
                if (Bug_action.commit.equals(bug.getAction())){
                    bug.setStatus(Bug_status.验证中);
                }else if (Bug_action.rtnCreating.equals(bug.getAction())){
                    bug.setStatus(Bug_status.创建中);
                }else if (Bug_action.rtnUnassign.equals(bug.getAction())){
                    bug.setStatus(Bug_status.待分配);
                }else if (Bug_action.hang.equals(bug.getAction())){
                    bug.setStatus(Bug_status.已挂起);
                }else if (Bug_action.save.equals(bug.getAction())){
                    bug.setStatus(Bug_status.处理中);
                }
            }else if (Bug_status.已废弃.equals(bug.getStatus())){
                if (Bug_action.reactivate.equals(bug.getAction())){
                    bug.setStatus(Bug_status.创建中);
                }else if (Bug_action.save.equals(bug.getAction())){
                    bug.setStatus(Bug_status.已废弃);
                }
            }else if (Bug_status.验证中.equals(bug.getStatus())){
                if (Bug_action.close.equals(bug.getAction())){
                    bug.setStatus(Bug_status.已关闭);
                }else if (Bug_action.rtnProcess.equals(bug.getAction())){
                    bug.setStatus(Bug_status.处理中);
                }else if (Bug_action.save.equals(bug.getAction())){
                    bug.setStatus(Bug_status.验证中);
                }
            }
            myService.updateEntity(bug);
        }else{
            User currentUser = (User) request.getSession().getAttribute("currentUser");
            bug.setCreator(currentUser);
            if (Bug_action.save.equals(bug.getAction())){
                bug.setStatus(Bug_status.创建中);
            }else if (Bug_action.commit.equals(bug.getAction())){
                if (bug.getSolver() == null){
                    bug.setStatus(Bug_status.待分配);
                }else{
                    bug.setStatus(Bug_status.处理中);
                }
            }else if (Bug_action.unassign.equals(bug.getAction())){
                bug.setStatus(Bug_status.待分配);
            }
            myService.saveEntity(bug);
        }
        return "redirect:/bug/list.do";
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
