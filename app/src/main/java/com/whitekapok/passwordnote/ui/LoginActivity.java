package com.whitekapok.passwordnote.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.TextView;

import com.whitekapok.passwordnote.R;
import com.whitekapok.passwordnote.event.LoginEvent;
import com.whitekapok.passwordnote.ui.base.BaseActivity;
import com.whitekapok.passwordnote.utils.ActivityUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *
 * Created by Lance on 2017/5/31.
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_login_name)
    TextInputEditText etLoginName;
    @BindView(R.id.il_login_name)
    TextInputLayout ilLoginName;
    @BindView(R.id.et_login_password)
    TextInputEditText etLoginPassword;
    @BindView(R.id.il_login_password)
    TextInputLayout ilLoginPassword;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_login_register)
    TextView tvLoginRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.tv_login,R.id.tv_login_register})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_login:
                break;
            case R.id.tv_login_register:
                ActivityUtils.startActivity(this,RegisterActivity.class);
                break;
        }
    }

    /**
     * 登录时关闭页面
     * @param loginEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginEvent(LoginEvent loginEvent){
        this.finish();
    }

}
