package com.whitekapok.passwordnote.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.whitekapok.passwordnote.interfaces.IPageTitle;

import java.util.List;

/**
 *
 * Created by Lance on 2017/6/16.
 */

public class MainPageAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;

    public MainPageAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
        this.list=list;
    }

    @Override
    public Fragment getItem(int position) {
        return list==null?null:list.get(position);
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Fragment itemFrag=getItem(position);
        if(itemFrag instanceof IPageTitle){
            return ((IPageTitle)itemFrag).getPageTitle();
        }
        return super.getPageTitle(position);
    }
}
