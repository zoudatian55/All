package com.example.administrator.all.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.example.administrator.all.MainActivity;
import com.example.administrator.all.R;
import com.example.administrator.all.base.BaseFragment;
import com.example.administrator.all.bean.MenuTitleBean;
import com.example.administrator.all.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;


public class LeftFragment extends BaseFragment {
    private ListView listView;

    private List<MenuTitleBean> menuTitle;

    private MenuListAdapter adapter;
    private int prepostion;




    public View initView() {
        listView = new ListView(context);
        //设置分割线的高度为0
        listView.setDividerHeight(0);
//       距离顶部为40
       listView.setPadding(0, DensityUtil.dip2px(context, 40), 0, 0);
        listView.setCacheColorHint(Color.TRANSPARENT);

       listView.setSelector(android.R.color.transparent);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                prepostion=i;
                adapter.notifyDataSetChanged();
                //2.把左侧菜单关闭
                MainActivity mainActivity= (MainActivity) context;
                mainActivity.getSlidingMenu().toggle();

                FragmentManager fm=getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                //默认选中第一个界面

                switch (prepostion){
                    case 0:
                       //切换到新闻的碎片
                      ft.replace(R.id.fl_content,new NewsFragment()).commit();
                       break;
                    case 1:
                        ft.replace(R.id.fl_content,new ShopFragment()).commit();
                        break;

                }
            }
        });

         return listView;
    }

    public void initData() {
        super.initData();
        menuTitle=new ArrayList<>();
        MenuTitleBean bean=new MenuTitleBean();
      bean.setMenutitle("新闻中心");
        MenuTitleBean bean1=new MenuTitleBean();
        bean1.setMenutitle("购物中心");
        menuTitle.add(bean);
        menuTitle.add(bean1);
        adapter=new MenuListAdapter();
        listView.setAdapter(adapter);

    }
    class MenuListAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return menuTitle.size();
        }

        @Override
        public Object getItem(int i) {
            return menuTitle.get(i);
        }
        @Override

        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            TextView textView= (TextView) View.inflate(context,R.layout.item_menu,null);
            MenuTitleBean titleBean = menuTitle.get(position);
            textView.setText(titleBean.getMenutitle());
            if (position == prepostion) {
                textView.setEnabled(true);
            } else {
                textView.setEnabled(false);
            }
            return textView;
        }
    }


}
