package com.whitekapok.passwordnote.helper;

import android.text.TextUtils;

import com.whitekapok.passwordnote.db.DBHelper;
import com.whitekapok.passwordnote.entity.UserEntity;

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



}
