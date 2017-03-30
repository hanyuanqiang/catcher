package com.hyq.entity;

import com.hyq.entity.enum_.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by genius on 2017/3/12.
 */
@Entity
@Table(name = "bug")
public class Bug {

    private Integer id;
    private String label;
    private String attachment;
    private String description; //描述
    private String resolution;  //处理过程

    private Bug_type type;  //类型
    private Bug_severity severity;  //严重程度
    private Bug_priority priority;  //优先级
    private Bug_findStage findStage;    //发现阶段
    private Bug_source source;    //缺陷来源
    private Bug_frequency frequency;    //重现频率
    private Bug_closeReason closeReason;    //关闭理由
    private Bug_status status;  //当前状态


    private Date createTime;    //创建时间
    private Date updateTime;    //最近更新时间
    private Date deadline;  //解决期限
    private Date resolveDate;   //解决时间
    private Date endDate;       //关闭时间

    private User creator;   //创建者
//    private User processor;     //当前处理人
    private User solver;    //解决者
    private User verifier;  //验证者

    private Version raisedVersion;    //发现问题版本
    private Version fixedVersion;   //实际解决问题版本

    private Project project;    //所属项目

    private Bug_action action;     //操作动作，不存入数据库中

    public Bug() {
    }

    @Id
    @GeneratedValue(generator = "_native")
    @GenericGenerator(name = "_native",strategy = "native")
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

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    @Lob
    @Column(columnDefinition="TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Lob
    @Column(columnDefinition="TEXT")
    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    @Enumerated(EnumType.STRING)
    public Bug_type getType() {
        return type;
    }

    public void setType(Bug_type type) {
        this.type = type;
    }

    @Enumerated(EnumType.STRING)
    public Bug_severity getSeverity() {
        return severity;
    }

    public void setSeverity(Bug_severity severity) {
        this.severity = severity;
    }

    @Enumerated(EnumType.STRING)
    public Bug_priority getPriority() {
        return priority;
    }

    public void setPriority(Bug_priority priority) {
        this.priority = priority;
    }

    @Enumerated(EnumType.STRING)
    public Bug_findStage getFindStage() {
        return findStage;
    }

    public void setFindStage(Bug_findStage findStage) {
        this.findStage = findStage;
    }

    @Enumerated(EnumType.STRING)
    public Bug_source getSource() {
        return source;
    }

    public void setSource(Bug_source source) {
        this.source = source;
    }

    @Enumerated(EnumType.STRING)
    public Bug_frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Bug_frequency frequency) {
        this.frequency = frequency;
    }

    @Enumerated(EnumType.STRING)
    public Bug_closeReason getCloseReason() {
        return closeReason;
    }

    public void setCloseReason(Bug_closeReason closeReason) {
        this.closeReason = closeReason;
    }

    @Enumerated(EnumType.STRING)
    public Bug_status getStatus() {
        return status;
    }

    public void setStatus(Bug_status status) {
        this.status = status;
    }

    @Column(updatable = false,columnDefinition = "datetime default now()",insertable = false)  //不允许插入和更新该字段
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(updatable = false,columnDefinition = "TIMESTAMP ON UPDATE CURRENT_TIMESTAMP default CURRENT_TIMESTAMP",insertable = false)  //不允许插入和更新该字段
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    public Date getResolveDate() {
        return resolveDate;
    }

    public void setResolveDate(Date resolveDate) {
        this.resolveDate = resolveDate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "creator",referencedColumnName = "id",updatable = false)
    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    /*@ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "processor",referencedColumnName = "id")
    public User getProcessor() {
        return processor;
    }

    public void setProcessor(User processor) {
        this.processor = processor;
    }*/

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "solver",referencedColumnName = "id")
    public User getSolver() {
        return solver;
    }

    public void setSolver(User solver) {
        this.solver = solver;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "verifier",referencedColumnName = "id")
    public User getVerifier() {
        return verifier;
    }

    public void setVerifier(User verifier) {
        this.verifier = verifier;
    }

    @ManyToOne(targetEntity = Version.class)
    @JoinColumn(name = "raisedVersion",referencedColumnName = "id")
    public Version getRaisedVersion() {
        return raisedVersion;
    }

    public void setRaisedVersion(Version raisedVersion) {
        this.raisedVersion = raisedVersion;
    }

    @ManyToOne(targetEntity = Version.class)
    @JoinColumn(name = "fixedVersion",referencedColumnName = "id")
    public Version getFixedVersion() {
        return fixedVersion;
    }

    public void setFixedVersion(Version fixedVersion) {
        this.fixedVersion = fixedVersion;
    }

    @ManyToOne(targetEntity = Project.class)
    @JoinColumn(name = "project",referencedColumnName = "id")
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Transient  //不映射到数据库中
    public Bug_action getAction() {
        return action;
    }

    public void setAction(Bug_action action) {
        this.action = action;
    }
}
