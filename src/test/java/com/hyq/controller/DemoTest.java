package com.hyq.controller;

import com.google.common.collect.Sets;
import com.hyq.dao.MyDAO;
import com.hyq.dao.impl.MyDAOImpl;
import com.hyq.entity.Bug;
import com.hyq.entity.Project;
import com.hyq.entity.Version;
import com.hyq.entity.enum_.User_gender;
import com.hyq.entity.User;
import com.hyq.service.MyService;
import com.hyq.service.impl.MyServiceImpl;
import com.hyq.util.StringUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
 * Created by genius on 2017/3/11.
 */
public class DemoTest {
    Configuration conf = null;
    SessionFactory sf = null;
    Session session = null;
    Transaction tx = null;


    @Before
    public void before(){
        conf = new Configuration().configure("hibernate.cfg.test.xml");
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();//实例化服务登记
        sf = conf.buildSessionFactory(serviceRegistry);//获取Session工厂
        session = sf.openSession();
        tx = session.beginTransaction();
    }

    @Test
    public void test01(){

        Project project = new Project();
        project.setLabel("project1");
        project.setDescription("project description");
        project.setId(1);
        project.setCreateTime(new Date());
        session.merge(project);

        for(int i=1;i<=10;i++){
            User user = new User();
//            user.setId(i);
            user.setAccount("admin");
            user.setPassword("admin");
            user.setGender(User_gender.女);
//            user.setProject(Sets.newHashSet(project));
            session.merge(user);
        }

        User user = (User)session.load(User.class,1);
        Version version = new Version();
        version.setId(1);
        version.setActualDate(new Date());
        version.setDescription("desc");
        version.setOwner(user);
        version.setLabel("version1.0");
        version.setProject(project);
        session.merge(version);

        for(int i=1;i<=10;i++){
            Bug bug = new Bug();
            bug.setId(i);
            bug.setCreator(user);
            bug.setRaisedVersion(version);
            bug.setLabel("BUG"+i);
            bug.setCreateTime(new Date());
            bug.setFixedVersion(version);
//            bug.setProcessor(user);
            bug.setProject(project);
            bug.setSolver(user);
            bug.setVerifier(user);
            session.merge(bug);
        }
        Version v = (Version) session.load(Version.class,1);
        /*List<Bug> bugList = v.getRaisedBugs();
        for(Bug bug : bugList){
            System.out.println(bug.getLabel());
        }*/
        /*System.out.println("----------------------");
        List<Bug> bugs = session.createCriteria(Bug.class).list();
        System.out.println(bugs.size());*/
    }

    @Test
    public void test02(){
        System.out.println(Collection.class.isAssignableFrom(List.class));
        System.out.println(Collection.class.isAssignableFrom(Set.class));
        System.out.println(Collection.class.isAssignableFrom(String.class));

        System.out.println("----------------------");
        String className = "java.lang.String";
        className = className.substring(className.lastIndexOf(".")+1);
        System.out.println(className);

        System.out.println("----------------------");
        String newString = StringUtil.replaceLast("11elect * from user where id in(?,?,?,?,?,)","11","s");
        System.out.println(newString);


    }

    @Test
    public void test03(){
        /*User user = new User();
        user.setAccount("admin1");
        user.setPassword("admin1");
        user.setIsAdmin(true);
        user.setLabel("管理员");
        session.save(user);*/

        /*User user = (User)session.get(User.class,1);
        user.setIsAdmin(false);
        session.update(user);*/
//        System.out.println(user.getIsAdmin());
        String className = "String";
        System.out.println(className.substring(className.lastIndexOf(".")+1));
    }

    @After
    public void after(){
        tx.commit();
        session.close();
        sf.close();
    }
}
