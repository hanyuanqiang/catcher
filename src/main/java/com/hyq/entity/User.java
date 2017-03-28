package com.hyq.entity;

import com.google.common.collect.Sets;
import com.hyq.entity.enum_.User_gender;
import com.hyq.entity.enum_.User_post;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

/**
 * Created by genius on 2017/3/11.
 */
@Entity
@Table(name = "user")
public class User {
    private Integer id;
    private String label;
    private String account;
    private String password;
    private String email;
    private String phone;
    private Date birth;
    private Date createTime;

    private Boolean isAdmin;    //默认不是管理员

    private User_gender gender; //性别
    private User_post post; //岗位

//    private Set<Project> project = Sets.newHashSet();

    public User(){}

    @Id
    @GeneratedValue(generator = "_native")
    @GenericGenerator(name = "_native",strategy = "native")
    @OrderBy("id DESC")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(length = 100)
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Column(length = 50,nullable = false)
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Email
    @Column(length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Column(updatable = false,columnDefinition = "datetime default now()",insertable = false)  //不允许插入和更新该字段
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Enumerated(EnumType.STRING)
    public User_gender getGender() {
        return gender;
    }

    public void setGender(User_gender gender) {
        this.gender = gender;
    }

    /*@ManyToMany(targetEntity = Project.class)
    @JoinTable(name = "project_user",
        joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "project_id",referencedColumnName = "id"))
    public Set<Project> getProject() {
        return project;
    }

    public void setProject(Set<Project> project) {
        this.project = project;
    }*/

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Enumerated(EnumType.STRING)
    public User_post getPost() {
        return post;
    }

    public void setPost(User_post post) {
        this.post = post;
    }
}
