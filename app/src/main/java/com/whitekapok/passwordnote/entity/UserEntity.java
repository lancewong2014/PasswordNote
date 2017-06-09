package com.whitekapok.passwordnote.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * Created by Lance on 2017/5/31.
 */

@Data
@Entity
@EqualsAndHashCode
@ToString
public class UserEntity implements Serializable {
    public static final long serialVersionUID=536871008L;
    @Id(autoincrement = true) private Long id;
    @Unique private String uid; //预留处理
    @Unique private String username;
    private String password;
    private String nickname;

    @Generated(hash = 1763584696)
    public UserEntity(Long id, String uid, String username, String password,
            String nickname) {
        this.id = id;
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }
    @Generated(hash = 1433178141)
    public UserEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUid() {
        return this.uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNickname() {
        return this.nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
