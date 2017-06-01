package com.whitekapok.passwordnote.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *所有信息需要加密
 * 分组密码
 * Created by Lance on 2017/5/31.
 */
@Entity
@EqualsAndHashCode
@ToString
public class GroupEntity implements Serializable {
    public static final long serialVersionUID=536871008L;
    @Id(autoincrement = true)  private Long id;
    @Unique private String groupName;   //分组名称
    private String password;    //密码
    @Generated(hash = 211762447)
    public GroupEntity(Long id, String groupName, String password) {
        this.id = id;
        this.groupName = groupName;
        this.password = password;
    }
    @Generated(hash = 954040478)
    public GroupEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getGroupName() {
        return this.groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
   
}
