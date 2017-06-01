package com.whitekapok.passwordnote.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *
 * Created by Lance on 2017/5/27.
 */
@Entity( indexes = { @Index(value = "itemGroup DESC,itemAddTime DESC,itemName") })
@EqualsAndHashCode
@ToString
public class NoteEntity implements Serializable{
    public static final long serialVersionUID=536871008L;
    @Id(autoincrement = true)  private Long id;
    @NotNull private String itemName;   //条目名称
    private String itemPassword;    //加密后的密码
    private String itemTag; //添加的标签，可以根据“，”分组
    private String itemNote;    //备注
    private int itemType;   //标记类型，可以为常用，置顶等
    private long itemTopTime; //加入置顶的时间
    private long itemAddTime; //加入的时间

    private String itemGroup;   //分组名称


    @Generated(hash = 1862830817)
    public NoteEntity(Long id, @NotNull String itemName, String itemPassword,
            String itemTag, String itemNote, int itemType, long itemTopTime,
            long itemAddTime, String itemGroup) {
        this.id = id;
        this.itemName = itemName;
        this.itemPassword = itemPassword;
        this.itemTag = itemTag;
        this.itemNote = itemNote;
        this.itemType = itemType;
        this.itemTopTime = itemTopTime;
        this.itemAddTime = itemAddTime;
        this.itemGroup = itemGroup;
    }
    @Generated(hash = 734234824)
    public NoteEntity() {
    }

  
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getItemName() {
        return this.itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public String getItemPassword() {
        return this.itemPassword;
    }
    public void setItemPassword(String itemPassword) {
        this.itemPassword = itemPassword;
    }
    public String getItemTag() {
        return this.itemTag;
    }
    public void setItemTag(String itemTag) {
        this.itemTag = itemTag;
    }
    public String getItemNote() {
        return this.itemNote;
    }
    public void setItemNote(String itemNote) {
        this.itemNote = itemNote;
    }
    public int getItemType() {
        return this.itemType;
    }
    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getItemGroup() {
        return this.itemGroup;
    }
    public void setItemGroup(String itemGroup) {
        this.itemGroup = itemGroup;
    }
    public long getItemTopTime() {
        return this.itemTopTime;
    }
    public void setItemTopTime(long itemTopTime) {
        this.itemTopTime = itemTopTime;
    }
    public long getItemAddTime() {
        return this.itemAddTime;
    }
    public void setItemAddTime(long itemAddTime) {
        this.itemAddTime = itemAddTime;
    }


}
