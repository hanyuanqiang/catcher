package com.hyq.entity;

import com.hyq.entity.enum_.SysActivity_type;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by genius on 2017/3/15.
 */
@Entity
@Table(name = "sysActivity")
public class SysActivity {

    private Integer id;
    private Date createTime;
    private String label;   //操作内容
    private String action;  //操作动作
    private SysActivity_type type;  //活动类型

    @Id
    @GeneratedValue(generator = "_native")
    @GenericGenerator(name = "_native",strategy = "native")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(updatable = false,columnDefinition = "datetime default now()",insertable = false)  //不允许插入和更新该字段
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public SysActivity_type getType() {
        return type;
    }

    public void setType(SysActivity_type type) {
        this.type = type;
    }
}
