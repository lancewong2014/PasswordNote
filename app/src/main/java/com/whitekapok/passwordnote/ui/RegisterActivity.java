package com.whitekapok.passwordnote.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.whitekapok.passwordnote.MainApp;
import com.whitekapok.passwordnote.R;
import com.whitekapok.passwordnote.db.DBHelper;
import com.whitekapok.passwordnote.entity.UserEntity;
import com.whitekapok.passwordnote.event.LoginEvent;
import com.whitekapok.passwordnote.ui.base.BaseActivity;
import com.whitekapok.passwordnote.utils.ActivityUtils;
import com.whitekapok.passwordnote.utils.EncryptorUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *
 * Created by Lance on 2017/6/9.
 */

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.tl_register)
    Toolbar tlRegister;
    @BindView(R.id.et_register_username)
    TextInputEditText etRegisterUsername;
    @BindView(R.id.et_register_password)
    TextInputEditText etRegisterPassword;
    @BindView(R.id.et_register_password_confirm)
    TextInputEditText etRegisterPasswordConfirm;
    @BindView(R.id.tv_register)
    TextView tvRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_register);
        ButterKnife.bind(this);
        initActivity();
    }

    private void initActivity(){
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(tlRegister);
        tlRegister.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


    @OnClick(R.id.tv_register)
    public void onClick(View view){
        //注册
        String username=etRegisterUsername.getText().toString();
        String firstPassword=etRegisterPassword.getText().toString();
        String confirmPassword=etRegisterPasswordConfirm.getText().toString();
        if(username.isEmpty()){
            MainApp.getInstance().getToastUtil().showToast(R.string.register_username_not_empty);
            return;
        }
        if(firstPassword.isEmpty()||confirmPassword.isEmpty()){
            MainApp.getInstance().getToastUtil().showToast(R.string.register_password_not_empty);
            return;
        }
        if(!firstPassword.equals(confirmPassword)){
            MainApp.getInstance().getToastUtil().showToast(getString(R.string.register_password_not_equal));
            return;
        }
        UserEntity userEntity= DBHelper.getInstance().getUser(username);
        if(userEntity!=null){
            MainApp.getInstance().getToastUtil().showToast(getString(R.string.register_username_exist));
            return;
        }
        userEntity=new UserEntity();
        userEntity.setUid(UUID.randomUUID().toString());
        userEntity.setUsername(username);
        userEntity.setPassword(EncryptorUtil.encryptMD5ToString(firstPassword,userEntity.getUid()));
        DBHelper.getInstance().saveUser(userEntity);
        userEntity= DBHelper.getInstance().getUser(username);
        DBHelper.getInstance().updateLoginUser(userEntity);
        EventBus.getDefault().post(new LoginEvent(userEntity));
        ActivityUtils.startActivity(this,MainActivity.class);
        this.finish();
    }
}
