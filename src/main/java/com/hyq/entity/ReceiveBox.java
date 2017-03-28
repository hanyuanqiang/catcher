package com.hyq.entity;

import com.hyq.entity.enum_.ReceiveBox_priority;
import com.hyq.entity.enum_.ReceiveBox_status;
import com.hyq.entity.enum_.SendBox_priority;
import com.hyq.entity.enum_.SendBox_status;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 收件箱
 * Created by genius on 2017/3/12.
 */
@Entity
@Table(name = "receiveBox")
public class ReceiveBox {

    private Integer id;
    private String label;   //主题
    private String content;    //内容
    private String attachment;  //附件

    private Date sendTime;  //发送时间
    private Date createTime;    //创建时间

    private User sender;    //发送人
    private User receiver;      //收件人

    private ReceiveBox_priority priority;  //重要程度
    private ReceiveBox_status status;      //状态

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

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    @Column(updatable = false,columnDefinition = "datetime default now()",insertable = false)  //不允许插入和更新该字段
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "sender",referencedColumnName = "id")
    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "receiver",referencedColumnName = "id")
    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    @Enumerated(EnumType.STRING)
    public ReceiveBox_priority getPriority() {
        return priority;
    }

    public void setPriority(ReceiveBox_priority priority) {
        this.priority = priority;
    }

    @Enumerated(EnumType.STRING)
    public ReceiveBox_status getStatus() {
        return status;
    }

    public void setStatus(ReceiveBox_status status) {
        this.status = status;
    }
}
