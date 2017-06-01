package com.whitekapok.passwordnote.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.whitekapok.passwordnote.R;
import com.whitekapok.passwordnote.ui.base.BaseActivity;

/**
 *
 * Created by Lance on 2017/5/31.
 */

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
    }
}
