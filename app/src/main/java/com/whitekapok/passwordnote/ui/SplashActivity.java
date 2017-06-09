package com.whitekapok.passwordnote.ui;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.whitekapok.passwordnote.entity.UserEntity;
import com.whitekapok.passwordnote.helper.UserInfoHelper;
import com.whitekapok.passwordnote.ui.base.BaseActivity;
import com.whitekapok.passwordnote.utils.ActivityUtils;

/**
 *
 * Created by Lance on 2017/5/31.
 */

public class SplashActivity extends BaseActivity {
    private CountDownTimer timer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTimer();
    }

    private void initTimer(){
        if(timer!=null){
            timer.cancel();
            timer=null;
        }
        timer=new CountDownTimer(1000*3,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if(isFinishing()){
                    return;
                }
                UserEntity entity= UserInfoHelper.getInstance().getUserEntity();
                if(TextUtils.isEmpty(entity.getUid())){
                    ActivityUtils.startActivity(SplashActivity.this,LoginActivity.class);
                }else{
                    ActivityUtils.startActivity(SplashActivity.this,MainActivity.class);
                }
                finish();
            }
        };
        timer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(timer!=null){
            timer.cancel();
            timer=null;
        }
    }
}
