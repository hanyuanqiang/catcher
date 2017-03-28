package com.hyq.entity;

import com.hyq.entity.enum_.Affiche_bulType;
import com.hyq.entity.enum_.Affiche_priority;
import com.hyq.entity.enum_.Affiche_status;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 公告
 * Created by genius on 2017/3/12.
 */
@Entity
@Table(name = "affiche")
public class Affiche {

    private Integer id;
    private String label;
    private String content;
    private String attachment;

    private Date createTime;
    private User publisher;

    private Project belongToProject;    //所属项目(组织)

    private Affiche_bulType bulType;
    private Affiche_priority priority;
    private Affiche_status status;

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
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    @Column(updatable = false,columnDefinition = "datetime default now()",insertable = false)  //不允许插入和更新该字段
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "publisher",referencedColumnName = "id")
    public User getPublisher() {
        return publisher;
    }

    public void setPublisher(User publisher) {
        this.publisher = publisher;
    }

    @ManyToOne(targetEntity = Project.class)
    @JoinColumn(name = "belongToProject",referencedColumnName = "id")
    public Project getBelongToProject() {
        return belongToProject;
    }

    public void setBelongToProject(Project belongToProject) {
        this.belongToProject = belongToProject;
    }

    @Enumerated(EnumType.STRING)
    public Affiche_bulType getBulType() {
        return bulType;
    }

    public void setBulType(Affiche_bulType bulType) {
        this.bulType = bulType;
    }

    @Enumerated(EnumType.STRING)
    public Affiche_priority getPriority() {
        return priority;
    }

    public void setPriority(Affiche_priority priority) {
        this.priority = priority;
    }

    @Enumerated(EnumType.STRING)
    public Affiche_status getStatus() {
        return status;
    }

    public void setStatus(Affiche_status status) {
        this.status = status;
    }
}
