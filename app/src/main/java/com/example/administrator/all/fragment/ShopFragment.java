package com.example.administrator.all.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.all.R;
import com.example.administrator.all.shopfragment.ShopCartFragment;
import com.example.administrator.all.shopfragment.ShopCategoryFragment;
import com.example.administrator.all.shopfragment.ShopHomeFragment;
import com.example.administrator.all.shopfragment.ShopMineFragment;

public class ShopFragment extends Fragment {
    private FragmentTabHost tabHost;
    private Class[] fragments=new Class[]{
            ShopHomeFragment.class,
            ShopCategoryFragment.class,
            ShopCartFragment.class,
            ShopMineFragment.class};
    private int[] imgSelector=new int[]{
            R.drawable.shop_home_selector,
            R.drawable.shop_category_selector,
            R.drawable.shop_cart_selector,
            R.drawable.shop_mine_selector
    };
    private String[] tabtexts=new String[]{
            "首页","分类","购物车","我的"
    };

    public ShopFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_shop,null);
        tabHost= (FragmentTabHost) view.findViewById(R.id.tabHost);
        tabHost.setup(getActivity(),getChildFragmentManager(),android.R.id.tabcontent);

       for (int i=0;i<fragments.length;i++){
           View inflate=getLayoutInflater(getArguments()).inflate(R.layout.shop_tab_item,null);
           ImageView tab_iv= (ImageView) inflate.findViewById(R.id.shop_tab_iv);
           TextView tab_tv= (TextView) inflate.findViewById(R.id.shop_tab_tv);
           tab_iv.setImageResource(imgSelector[i]);
           tab_tv.setText(tabtexts[i]);
           tabHost.addTab(tabHost.newTabSpec(tabtexts[i]).setIndicator(inflate),fragments[i],null);
       }

        return view;
    }

}
