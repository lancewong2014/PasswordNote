package com.whitekapok.passwordnote.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whitekapok.passwordnote.MainApp;
import com.whitekapok.passwordnote.R;
import com.whitekapok.passwordnote.interfaces.IPageTitle;
import com.whitekapok.passwordnote.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 *
 * Created by Lance on 2017/6/16.
 */

public class MainFavFrag extends BaseFragment implements IPageTitle{

    @BindView(R.id.rv_main_item)
    RecyclerView rvMainItem;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_main_item, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public CharSequence getPageTitle() {
        return MainApp.getInstance().getString(R.string.main_tab_fav);
    }
}
