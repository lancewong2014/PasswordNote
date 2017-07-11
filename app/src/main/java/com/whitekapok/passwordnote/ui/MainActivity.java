package com.whitekapok.passwordnote.ui;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.whitekapok.passwordnote.R;
import com.whitekapok.passwordnote.adapter.MainPageAdapter;
import com.whitekapok.passwordnote.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.action_main_add_item)
    FloatingActionButton actionMainAddItem;
    @BindView(R.id.action_main_add_group)
    FloatingActionButton actionMainAddGroup;
    @BindView(R.id.main_fab)
    FloatingActionsMenu mainFab;
    @BindView(R.id.nav_main_view)
    NavigationView navMainView;
    @BindView(R.id.toolbar_main)
    Toolbar toolbarMain;
    @BindView(R.id.drawer_main)
    DrawerLayout drawerMain;
    @BindView(R.id.tab_main)
    TabLayout tabMain;
    @BindView(R.id.vp_main)
    ViewPager vpMain;

    private List<Fragment> mainFragList;
    private MainPageAdapter mAdapter;
    private MainItemFrag mainItemFrag;
    private MainFavFrag mainFavFrag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbarMain);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerMain, toolbarMain, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerMain.setDrawerListener(toggle);
        toggle.syncState();
        navMainView.setNavigationItemSelectedListener(this);
        initActivity();
    }

    private void initActivity(){
        initViewPager();
    }

    private void initViewPager(){
        mainFragList=new ArrayList<>();
        mainItemFrag= (MainItemFrag) getSupportFragmentManager().findFragmentByTag("ItemFrag");
        if(mainItemFrag==null){
            mainItemFrag=new MainItemFrag();
        }
        mainFragList.add(mainItemFrag);
        mainFavFrag=(MainFavFrag)getSupportFragmentManager().findFragmentByTag("FavFrag");
        if(mainFavFrag==null){
            mainFavFrag=new MainFavFrag();
        }
        mainFragList.add(mainFavFrag);
        mAdapter=new MainPageAdapter(getSupportFragmentManager(),mainFragList);
        vpMain.setAdapter(mAdapter);
        tabMain.setupWithViewPager(vpMain);
    }

    @Override
    public void onBackPressed() {
        if (drawerMain.isDrawerOpen(GravityCompat.START)) {
            drawerMain.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        drawerMain.closeDrawer(GravityCompat.START);
        return true;
    }
}
