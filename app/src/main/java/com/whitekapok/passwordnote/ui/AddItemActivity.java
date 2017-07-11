package com.whitekapok.passwordnote.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.whitekapok.passwordnote.R;
import com.whitekapok.passwordnote.ui.base.BaseActivity;

/**
 * 添加条目
 * Created by Lance on 2017/6/19.
 */

public class AddItemActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_add_item);
    }
}
