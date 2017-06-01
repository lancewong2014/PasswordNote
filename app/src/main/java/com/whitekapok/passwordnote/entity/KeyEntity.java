package com.whitekapok.passwordnote.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * Created by Lance on 2017/5/27.
 */
@Entity
@EqualsAndHashCode
@ToString
public class KeyEntity implements Serializable{
    public static final long serialVersionUID=536871008L;
    public static final String DEFAULT_USER="admin";
    public static final String KEY_LOGIN="loginUser";
    @Id(autoincrement = true) private Long id;
    private String key;
    private String value;
    private String uid;
    @Generated(hash = 1883514315)
    public KeyEntity(Long id, String key, String value, String uid) {
        this.id = id;
        this.key = key;
        this.value = value;
        this.uid = uid;
    }
    @Generated(hash = 330171120)
    public KeyEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getKey() {
        return this.key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getValue() {
        return this.value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getUid() {
        return this.uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
}
