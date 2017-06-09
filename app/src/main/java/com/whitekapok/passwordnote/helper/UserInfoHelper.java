package com.whitekapok.passwordnote.helper;

import android.text.TextUtils;

import com.whitekapok.passwordnote.db.DBHelper;
import com.whitekapok.passwordnote.entity.UserEntity;
import com.whitekapok.passwordnote.utils.EncryptorUtil;

/**
 *
 * Created by Lance on 2017/5/31.
 */

public class UserInfoHelper {
    private static final UserInfoHelper ourInstance = new UserInfoHelper();

    public static UserInfoHelper getInstance() {
        return ourInstance;
    }
    private UserEntity userEntity;
    private boolean isLogin;
    private boolean isInit=false;
    private UserInfoHelper() {
    }

    /**
     * 返回登录状态
     * @return  isLogin
     */
    public boolean isLogin() {
        return isLogin;
    }

    public UserEntity getUserEntity() {
        if(!isInit){
            //避免回收后登录状态丢失
            DBHelper.getInstance().initLogin();
            isInit=true;
        }
        if(userEntity==null){
            return new UserEntity();
        }
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        isInit=true;
        this.userEntity = userEntity;
        if(userEntity!=null&&!TextUtils.isEmpty(userEntity.getUid())){
            isLogin=true;
        }else {
            isLogin=false;
        }
    }

    /**
     * 密码直接保存本地，不再做
     * @param entity
     * @return
     */
    public boolean checkLogin(UserEntity entity){
        if(entity==null||TextUtils.isEmpty(entity.getUsername())){
            return false;
        }
        UserEntity resultEntity=DBHelper.getInstance().getUser(entity.getUsername());
        //如果密码正确
        if(resultEntity!=null&&TextUtils.equals(resultEntity.getPassword(), EncryptorUtil.encryptMD5ToString(entity.getPassword(),resultEntity.getUid()))){
            return true;
        }
        return false;
    }

}
