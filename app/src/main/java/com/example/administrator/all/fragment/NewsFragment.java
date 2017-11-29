package com.example.administrator.all.fragment;


import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


import com.example.administrator.all.MainActivity;
import com.example.administrator.all.R;
import com.example.administrator.all.pager.TabDetailPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;

import static com.example.administrator.all.R.id.tabpageindicator;

/**
 * 新闻页面的显示
 */

public class NewsFragment extends Fragment {
    private ImageButton menu;
     private TabPageIndicator tabPageIndicator;
    private ImageButton ib_tab_next;
    private ViewPager viewPager;
    private final String[] titles = {"头条", "社会", "国内", "国际", "娱乐", "体育", "军事", "科技", "财经", "时尚"};
    private final String[] titles_en =
            {"top", "shehui", "guonei", "guoji", "yule", "tiyu", "junshi", "keji", "caijing",
                    "shishang"};

    private ArrayList<TabDetailPager> pagers;

   //下拉刷新和上啦加载




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_news,null);
        menu= (ImageButton) view.findViewById(R.id.ib_menu);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity main= (MainActivity) getContext();
                main.getSlidingMenu().toggle();
            }
        });
        tabPageIndicator= (TabPageIndicator) view.findViewById(tabpageindicator);
        ib_tab_next= (ImageButton) view.findViewById(R.id.ib_tab_next);
        viewPager= (ViewPager) view.findViewById(R.id.viewpager);
        ib_tab_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            }
        });

        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        pagers=new ArrayList<>();
        for(int i=0;i<titles.length;i++){
            pagers.add(new TabDetailPager(getContext(),titles_en[i]));
        }
        viewPager.setAdapter(new MyNewsMenuDetailAdapter());
        tabPageIndicator.setViewPager(viewPager);



        tabPageIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==0){
                    //全屏滑动
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_FULLSCREEN);
                }else{

                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }
    class MyNewsMenuDetailAdapter extends PagerAdapter {


        @Override
        public String getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getCount() {
            return pagers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return object==view;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TabDetailPager tabDetailPager=pagers.get(position);
            View rootView=tabDetailPager.rootView;
            tabDetailPager.initData();
            container.addView(rootView);
            return rootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
    private void isEnableSlidingMenu(int touchmode) {
        MainActivity mainActivity = (MainActivity) getContext();
        mainActivity.getSlidingMenu().setTouchModeAbove(touchmode);
    }

}
