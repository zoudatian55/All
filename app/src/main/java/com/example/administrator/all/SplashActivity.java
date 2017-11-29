package com.example.administrator.all;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.example.administrator.all.utils.CacheUtils;


public class SplashActivity extends Activity {
    public Intent intent;

    public static final String START_MAIN = "start_main";
    private RelativeLayout rl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
       rl= ((RelativeLayout) findViewById(R.id.rl));

        //渐变动画
        AlphaAnimation aa=new AlphaAnimation(0,1);
        aa.setDuration(500);
        aa.setFillAfter(true);

        ScaleAnimation sa=new ScaleAnimation(0,1,0,1,ScaleAnimation.RELATIVE_TO_SELF,0.5f,ScaleAnimation.RELATIVE_TO_SELF,0.5f);
        sa.setDuration(500);
        sa.setFillAfter(true);

        RotateAnimation ra=new RotateAnimation(0,360,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        ra.setDuration(500);
        ra.setFillAfter(true);

        AnimationSet as=new AnimationSet(false);
        as.addAnimation(aa);
        as.addAnimation(sa);
        as.addAnimation(ra);
        as.setDuration(2000);

        rl.startAnimation(as);

        as.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
               //判断是否已经进入主界面
           boolean isStartMain= CacheUtils.getBoolean(SplashActivity.this, START_MAIN);
           if(isStartMain){
               //进入过主界面，直接进入
                intent=new Intent(SplashActivity.this, MainActivity.class);


           }else {
               //没有进入主界面，进入引导界面‘

               intent= new Intent(SplashActivity.this, GuideActivity.class);


           }
                startActivity(intent);

           finish();


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


}
