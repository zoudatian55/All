package com.example.administrator.all.fragment;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.example.administrator.all.R;
import com.example.administrator.all.base.BaseFragment;


public class ContentFragment extends BaseFragment {

    public ContentFragment() {

    }


    @Override
    public View initView() {
        View view=View.inflate(context, R.layout.fragment_content,null);
        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fl_content,new NewsFragment()).commit();
        return view;
    }

    @Override
    public void initData() {
        super.initData();


    }

}