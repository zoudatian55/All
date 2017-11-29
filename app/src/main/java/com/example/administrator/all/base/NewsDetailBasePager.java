package com.example.administrator.all.base;

import android.content.Context;
import android.view.View;

/**
 * Created by Administrator on 2017/7/24 0024.
 */

public  abstract class NewsDetailBasePager {
    public Context context;

    public View rootView;
    public NewsDetailBasePager(Context context){
        this.context=context;
       rootView=initView();
    }

    public abstract View initView();


    /**
     * 子页面需要绑定数据联网请求数据的时候重写该方法
     */
    public void initData(){


    }
}
