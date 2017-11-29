package com.example.administrator.all.shopfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.all.MainActivity;
import com.example.administrator.all.R;
import com.example.administrator.all.adapter.ChannelAdapter;
import com.example.administrator.all.adapter.ShopHomeFragmentAdapter;
import com.example.administrator.all.bean.ChannelTitleBean;
import com.example.administrator.all.bean.ShopHomeBean;
import com.example.administrator.all.utils.Contants;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopHomeFragment extends Fragment {
    private LinearLayout sweep;
    private LinearLayout message;
    private LinearLayout search;
    private RecyclerView rv_home;
    private ImageButton ib_top;
    private String s;
    private   List<ShopHomeBean.DatasBean> datas;
    private ShopHomeFragmentAdapter adapter;
    private List<ChannelTitleBean> channel;
    private String[] title=new String[]{"壹号生鲜","食品饮料","粮油副食","中外名酒","全部分类",
            "母婴用品","美妆个护","居家生活","营养保健","中国搜索"};
    private int[] imgs=new int[]{R.drawable.column_fresh,R.drawable.column_food,
            R.drawable.column_oil, R.drawable.column_wine,
            R.drawable.column_all, R.drawable.column_baby,
            R.drawable.column_mask,R.drawable.column_life,
            R.drawable.column_health,R.drawable.column_search
    };

    public ShopHomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_shop_home,null);
        //扫一扫
        sweep= (LinearLayout) view.findViewById(R.id.sweep);
        //消息
        message= (LinearLayout) view.findViewById(R.id.message);
        //搜索
        search= (LinearLayout) view.findViewById(R.id.search);
        //回到顶部的按钮
        ib_top= (ImageButton) view.findViewById(R.id.ib_top);
        rv_home= (RecyclerView) view.findViewById(R.id.rv_home);
        initListener();
        initData();
        return view;
    }

    private void initData() {
        channel = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            ChannelTitleBean titleBean = new ChannelTitleBean();
            titleBean.setTitle(title[i]);
            titleBean.setImgaddress(imgs[i]);
            channel.add(titleBean);
            String url = Contants.SHHOP_URL;
            OkHttpClient client = new OkHttpClient();
            final Request request = new Request.Builder().
                    get().url(url).build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Toast.makeText(getContext(), "网络请求失败", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    s = response.body().string();
                    Log.e("tag", s);
                    // 解析数据
                    MainActivity main = (MainActivity) getContext();
                    main.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            processData(s);
                        }
                    });


                }

            });

        }
    }

    private void processData(String json) {
        ShopHomeBean bean=prase(json);
         datas = bean.getDatas();
        if(datas!=null){
            adapter=new ShopHomeFragmentAdapter(getContext(),datas);
            rv_home.setAdapter(adapter);
            //设置布局管理者
            GridLayoutManager manager=new GridLayoutManager(getContext(),1);
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
               //设置滑动到那个位置利润的监听
                @Override
                public int getSpanSize(int position) {
                    if(position<=3){
                        ib_top.setVisibility(View.GONE);
                    }else{
                        ib_top.setVisibility(View.VISIBLE);
                    }
                    return 1;
                }
            });


            rv_home.setLayoutManager(manager);

        }



    }

    private ShopHomeBean prase(String json) {
        return new Gson().fromJson(json,ShopHomeBean.class);
    }

    private void initListener() {
        ib_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 rv_home.scrollToPosition(0);
            }
        });

              // 搜素的监听
            search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), " 搜索",
                            Toast.LENGTH_SHORT).show();
                }
            });
              // 消息的监听
            message.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), " 进入消息中心",
                            Toast.LENGTH_SHORT).show();
                }
            });
        //扫一扫的监听
        sweep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), " 扫一扫",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

}
