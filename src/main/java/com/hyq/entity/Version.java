package com.hyq.entity;

import com.google.common.collect.Lists;
import com.hyq.entity.enum_.Version_status;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by genius on 2017/3/12.
 */
@Entity
@Table(name = "version")
public class Version {
    private Integer id;
    private String label;   //版本名称
    private String description; //版本描述
    private User owner;     //负责人
    private User creator;   //创建者

    private Date planDate;  //计划发布时间
    private Date actualDate;    //实际发布时间
    private Date createTime;    //创建时间
    private String attachment;  //附件

    private Version_status status;  //版本状态

    /*private List<Bug> raisedBugs = Lists.newArrayList();    //在该版本发现的bug
    private List<Bug> fixedBugs = Lists.newArrayList();     //在该版本修复的bug*/

    private Project project;    //所属项目

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
    @Column(columnDefinition="TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "owner",referencedColumnName = "id")
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "creator",referencedColumnName = "id",updatable = false)
    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    public Date getPlanDate() {
        return planDate;
    }

    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    public Date getActualDate() {
        return actualDate;
    }

    public void setActualDate(Date actualDate) {
        this.actualDate = actualDate;
    }

    @Column(updatable = false,columnDefinition = "datetime default now()",insertable = false)  //不允许插入和更新该字段
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(length = 1000)
    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    @Enumerated(EnumType.STRING)
    public Version_status getStatus() {
        return status;
    }

    public void setStatus(Version_status status) {
        this.status = status;
    }

    /*@OneToMany(targetEntity = Bug.class,mappedBy = "raisedVersion",fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    public List<Bug> getRaisedBugs() {
        return raisedBugs;
    }

    public void setRaisedBugs(List<Bug> raisedBugs) {
        this.raisedBugs = raisedBugs;
    }

    @OneToMany(targetEntity = Bug.class,mappedBy = "fixedVersion",fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    public List<Bug> getFixedBugs() {
        return fixedBugs;
    }

    public void setFixedBugs(List<Bug> fixedBugs) {
        this.fixedBugs = fixedBugs;
    }*/

    @NotNull
    @ManyToOne(targetEntity = Project.class)
    @JoinColumn(name = "project")
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

}
