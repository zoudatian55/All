package com.example.administrator.all;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Window;

import com.example.administrator.all.fragment.ContentFragment;
import com.example.administrator.all.fragment.LeftFragment;
import com.example.administrator.all.utils.DensityUtil;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {

        public static final String MAIN_CONTENT_TAG = "main_content_tag";
        public static final String LEFT_MENU_TAG = "left_menu_tag";

        @Override
        public void onCreate(Bundle savedInstanceState) {

            requestWindowFeature(Window.FEATURE_NO_TITLE);
            super.onCreate(savedInstanceState);
            //是SlidingMenu的方法，设置主页面
            setContentView(R.layout.activity_main);
            //设置左侧菜单
            setBehindContentView(R.layout.leftmenu);
            //设置右侧菜单
            SlidingMenu slidingMenu=getSlidingMenu();
//        slidingMenu.setSecondaryMenu();//设置右侧菜单
            //设置模式，显示模式
            slidingMenu.setMode(SlidingMenu.LEFT);
            //设置滑动模式
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
            //设置占屏的宽度
            slidingMenu.setBehindOffset(DensityUtil.dip2px(MainActivity.this,200));


            //初始化Fragment
            initFragment();


        }

    private void initFragment() {
        //得到FragemntManger,开启事物，替换，提交
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fl_main_content,new ContentFragment(), MAIN_CONTENT_TAG);
        ft.replace(R.id.fl_left_menu,new LeftFragment(), LEFT_MENU_TAG);
        ft.commit();
    }

    public LeftFragment getleftmenufragemnt() {
        return (LeftFragment) getSupportFragmentManager().findFragmentByTag(LEFT_MENU_TAG);

    }

    public ContentFragment getContentFragment() {
        return (ContentFragment) getSupportFragmentManager().findFragmentByTag(MAIN_CONTENT_TAG);


    }
    }





