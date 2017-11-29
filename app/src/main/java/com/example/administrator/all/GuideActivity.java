package com.example.administrator.all;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.example.administrator.all.utils.CacheUtils;
import com.example.administrator.all.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends Activity {
    private ViewPager viewpager;
    private Button btn_start_main;
    private LinearLayout ll_point;
    private List<ImageView> imageViews;
    private ImageView iv_point;
   //两点间的间距
    private int leftmax;
    private int widthdpi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        btn_start_main = (Button) findViewById(R.id.btn_start_main);
        ll_point = (LinearLayout) findViewById(R.id.ll_point);
        iv_point= (ImageView) findViewById(R.id.iv_point);
        int[] ids = new int[]{
                R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3

        };
        widthdpi= DensityUtil.dip2px(this,5);


        imageViews=new ArrayList<>();
        for (int i=0;i<ids.length;i++){
            ImageView imageView=new ImageView(this);
            //设置背景
            imageView.setBackgroundResource(ids[i]);

            //添加到集合
            imageViews.add(imageView);
             //添加灰色的点,到线性布局
            ImageView   point =new ImageView(this);
            point.setBackgroundResource(R.drawable.point_nomal);

            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(widthdpi,widthdpi);//需要适配
           if(i!=0){
               //不包第0个，距离左边的间距
               params.leftMargin=widthdpi;
           }
            point.setLayoutParams(params);

            ll_point.addView(point);

        }
        //设置ViewPager的适配器
        viewpager.setAdapter(new MyPagerAdapter());

        iv_point.getViewTreeObserver().addOnGlobalLayoutListener(new MyOnGlobalLayoutListener());

        //得到屏幕滑动的变分比
        viewpager.addOnPageChangeListener(new OnPageChangeListener());

          btn_start_main.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        //保存曾经进入的界面
        CacheUtils.putBoolean(GuideActivity.this, SplashActivity.START_MAIN,true);

        Intent intent=new Intent(GuideActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
});

    }
    class OnPageChangeListener implements ViewPager.OnPageChangeListener{


        /**
         * 滑动界面调用这个方法
         * @param position              当前的位置
         * @param positionOffset        页面滑动的百分比
         * @param positionOffsetPixels  滑动的像素
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            //两点之间的间距

            int leftmargin= (int) (position*leftmax+leftmax*positionOffset);
            RelativeLayout.LayoutParams params= (RelativeLayout.LayoutParams) ll_point.getLayoutParams();
             params.leftMargin=leftmargin;
            iv_point.setLayoutParams(params);
        }

        @Override
        public void onPageSelected(int position) {
            if(position==imageViews.size()-1){
                //最后一个页面
                btn_start_main.setVisibility(View.VISIBLE);

            }else{
                //其他界面
                btn_start_main.setVisibility(View.GONE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    class  MyOnGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {

        @Override
        public void onGlobalLayout() {
            //只执行一次
            iv_point.getViewTreeObserver().removeGlobalOnLayoutListener(MyOnGlobalLayoutListener.this);


            //间距=第一个点距离左边的距离-第0个点距离左边的距离

            leftmax=ll_point.getChildAt(1).getLeft()-ll_point.getChildAt(0).getLeft();


        }
    }

    class MyPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return imageViews.size();
        }
          //判断是否是当前页面
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView=imageViews.get(position);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
           container.removeView((View) object);
        }
    }

}
