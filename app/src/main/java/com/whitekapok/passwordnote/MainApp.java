package com.whitekapok.passwordnote;

import android.app.Application;

import com.whitekapok.passwordnote.utils.ToastUtil;

/**
 *
 * Created by Lance on 2017/5/27.
 */

public class MainApp extends Application {
    private static MainApp instance;
    private ToastUtil toastUtil;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MainApp getInstance(){
        return instance;
    }

    public ToastUtil getToastUtil() {
        if(toastUtil==null){
            toastUtil=new ToastUtil(this);
        }
        return toastUtil;
    }
}
