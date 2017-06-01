package com.whitekapok.passwordnote;

import android.app.Application;

import com.whitekapok.passwordnote.helper.UserInfoHelper;

/**
 *
 * Created by Lance on 2017/5/27.
 */

public class MainApp extends Application {
    private static MainApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MainApp getInstance(){
        return instance;
    }
}
