package com.hyq.aspect;

import com.hyq.dao.MyDAO;
import com.hyq.entity.SysActivity;
import com.hyq.entity.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * Created by genius on 2017/3/30.
 */
@Component
@Aspect
public class LogAspect {

    private static ClassPathResource cpr = new ClassPathResource("app.properties");
    private static Properties properties = new Properties();

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private MyDAO<SysActivity> myDAO;

    @Pointcut("execution(* com.hyq.controller..*(..))")
    public void aspect(){};

    @Before("aspect()")
    public void before(JoinPoint joinPoint){
        System.out.println("面向controller切面");
    }

    @After("aspect()")
    public void after(JoinPoint joinPoint) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {
        /*Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        properties.load(cpr.getInputStream());
        SysActivity sysActivity = new SysActivity();
        sysActivity.setOwner((User) request.getSession().getAttribute("currentUser"));
        StringBuffer message = new StringBuffer();

        if ("getEntityByField".equals(signature.getName())){
            String className = ((Class)args[0]).getName();
            String modelName = (String) properties.get(className);
            sysActivity.setModel(modelName);
            if ("com.hyq.entity.User".equals(className) && "account".equals(args[1].toString())){
                sysActivity.setAction("登录");
                message.append("登入系统");
            }else{
                sysActivity.setAction("查询");
                message.append("通过指定字段查询实体");
            }
        }else if ("findEntityList".equals(signature.getName())){
            String className = ((Class)args[0]).getName();
            String modelName = (String) properties.get(className);
            sysActivity.setModel(modelName);
            sysActivity.setAction("查询");
            message.append("查询实体列表");
        }if ("getEntityById".equals(signature.getName())){
            String className = ((Class)args[0]).getName();
            String modelName = (String) properties.get(className);
            sysActivity.setModel(modelName);
            sysActivity.setAction("查看");
            message.append("查看实体详情");
        }if ("saveEntity".equals(signature.getName())){
            String className = args[0].getClass().getName();
            String modelName = (String) properties.get(className);
            sysActivity.setModel(modelName);
            sysActivity.setAction("保存");
            message.append("新建实体");
        }if ("updateEntity".equals(signature.getName()) || "mergeEntity".equals(signature.getName())){
            String className = args[0].getClass().getName();
            String modelName = (String) properties.get(className);
            sysActivity.setModel(modelName);
            sysActivity.setAction("修改");
            message.append("更新实体");
        }if ("deleteEntity".equals(signature.getName())){
            String className = ((Class)args[0]).getName();
            String modelName = (String) properties.get(className);
            sysActivity.setModel(modelName);
            sysActivity.setAction("删除");
            message.append("删除实体");
        }else if ("updateOneFieldOfEntity".equals(signature.getName())){
            String className = ((Class)args[0]).getName();
            String modelName = (String) properties.get(className);
            sysActivity.setModel(modelName);
            if ("com.hyq.entity.User".equals(className) && "password".equals(args[1].toString())){
                sysActivity.setAction("修改密码");
                message.append("修改密码");
            }else{
                sysActivity.setAction("更新");
                message.append("更新实体的指定字段");
            }
        }
        sysActivity.setMessage(message.toString());

        myDAO.save(sysActivity);*/


        /*System.out.println("1:"+signature.getName());
        System.out.println("2:"+signature.getDeclaringType().getName());
        System.out.println("3:"+signature.getDeclaringTypeName());
        System.out.println("4:"+signature.getModifiers());
        System.out.println("5:"+joinPoint.getArgs().toString());
        System.out.println("6:"+joinPoint.getTarget().getClass().getName());
        System.out.println("7:"+joinPoint.getKind());*/
    }
}
