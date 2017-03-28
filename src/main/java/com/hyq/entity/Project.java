package com.hyq.entity;

import com.google.common.collect.Lists;
import com.hyq.entity.enum_.Project_riskStatus;
import com.hyq.entity.enum_.Project_status;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * 项目实体
 * Created by genius on 2017/3/11.
 */
@Entity
@Table(name = "project")
public class Project {

    private Integer id;
    private String label;   //项目名称
    private String description;     //项目描述
    private Date planStartDate;     //计划项目开始时间
    private Date planEndDate;       //计划项目结束时间
    private Date createTime;        //项目创建时间
    private User creator;       //项目创建者
    private User owner;         //项目负责人
    private Long riskValue;     //项目风险值
    private Project_riskStatus riskStatus;     //项目风险状态
    private Project_status status;  //项目的状态
    private String attachment;      //项目附件
    private List<User> members = Lists.newArrayList();     //项目成员

    @Id
    @GeneratedValue(generator = "_native")
    @GenericGenerator(name = "_native",strategy = "native")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Lob
    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    public Date getPlanStartDate() {
        return planStartDate;
    }

    public void setPlanStartDate(Date planStartDate) {
        this.planStartDate = planStartDate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    public Date getPlanEndDate() {
        return planEndDate;
    }

    public void setPlanEndDate(Date planEndDate) {
        this.planEndDate = planEndDate;
    }

    @Column(updatable = false,columnDefinition = "datetime default now()",insertable = false)  //不允许插入和更新该字段
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "creator",referencedColumnName = "id",updatable = false)
    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "owner",referencedColumnName = "id")
    @Cascade(CascadeType.ALL)
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Long getRiskValue() {
        return riskValue;
    }

    public void setRiskValue(Long riskValue) {
        this.riskValue = riskValue;
    }

    @Enumerated(EnumType.STRING)
    public Project_riskStatus getRiskStatus() {
        return riskStatus;
    }

    public void setRiskStatus(Project_riskStatus riskStatus) {
        this.riskStatus = riskStatus;
    }

    @Enumerated(EnumType.STRING)
    public Project_status getStatus() {
        return status;
    }

    public void setStatus(Project_status status) {
        this.status = status;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    @ManyToMany(targetEntity = User.class)
    @JoinTable(name = "project_user",
            joinColumns = @JoinColumn(name = "project_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"))
    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }
}
