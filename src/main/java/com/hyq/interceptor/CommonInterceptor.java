package com.hyq.interceptor;

import com.hyq.entity.SysActivity;
import com.hyq.entity.User;
import com.hyq.service.MyService;
import com.hyq.util.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

/**
 * Created by genius on= 2017/3/31.
 */
public class CommonInterceptor extends HandlerInterceptorAdapter{

    private static ClassPathResource cpr = new ClassPathResource("app.properties");
    private static Properties properties = new Properties();

    @Autowired
    private MyService myService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean tag = true;
        properties.load(cpr.getInputStream());
        HandlerMethod hm = (HandlerMethod)handler;
        String className = hm.getBeanType().getName();
        String methodName = hm.getMethod().getName();
        SysActivity sysActivity=new SysActivity();
        sysActivity.setOwner((User) request.getSession().getAttribute("currentUser"));
        String modelName = (String) properties.get(className);
        sysActivity.setModel(modelName);
        StringBuffer message = new StringBuffer();

        String id = request.getParameter("id");
        String entityClassName = className.replace("controller","entity").replace("Controller","");

        String label = "";
        if (CheckUtil.isNotNull(id) && !className.equals("com.hyq.controller.SysActivityController")){
            Object object = myService.getEntityByIdAndClearSession(Class.forName(entityClassName),Integer.parseInt(id));
            label = (String) object.getClass().getMethod("getLabel").invoke(object);
        }

        if ("preSave".equals(methodName)){
//                MethodParameter[] paras = hm.getMethodParameters();
            if (CheckUtil.isNotNull(id)){
                sysActivity.setAction("查看");
                message.append("查看了"+modelName+"\""+label+"\"");
            }else{
                tag = false;
            }
        }else if("list".equals(methodName)){
            sysActivity.setAction("查询");
            message.append("查询了"+modelName+"列表");
        }else if ("delete".equals(methodName)){
            sysActivity.setAction("删除");
            message.append("删除了"+modelName+"\""+label+"\"");
        }else if ("save".equals(methodName)){
            sysActivity.setAction("新建");
            label = request.getParameter("label");
            message.append("新建"+modelName+"\""+label+"\"");
        }else if ("updatePassword".equals(methodName)){
            //更新密码操作
            sysActivity.setAction("修改密码");
            message.append("修改了"+modelName+"\""+label+"\"的密码");
        }else if ("bgmain".equals(methodName)){
            sysActivity.setAction("访问");
            message.append("访问主页");
        }else{
            tag = false;
        }

        if (tag){
            sysActivity.setMessage(message.toString());
            myService.saveEntity(sysActivity);
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        properties.load(cpr.getInputStream());
        HandlerMethod hm = (HandlerMethod)handler;
        String className = hm.getBeanType().getName();
        String methodName = hm.getMethod().getName();
        SysActivity sysActivity=new SysActivity();
        sysActivity.setOwner((User) request.getSession().getAttribute("currentUser"));
        String modelName = (String) properties.get(className);
        sysActivity.setModel(modelName);
        StringBuffer message = new StringBuffer();

        if ("login".equals(methodName)){
            sysActivity.setAction("登陆");
            if ("redirect:/user/main.do".equals(modelAndView.getViewName())){
                if (sysActivity.getOwner() == null){
                    sysActivity.setOwner((User) request.getSession().getAttribute("currentUser"));
                }
                message.append("登陆成功");
            }else{
                message.append("用户名或密码错误");
            }
            sysActivity.setMessage(message.toString());
            myService.saveEntity(sysActivity);
        }


        super.postHandle(request, response, handler, modelAndView);
    }
}
