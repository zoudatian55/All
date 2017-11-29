package com.example.administrator.all.pager;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.UiThread;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.all.MainActivity;
import com.example.administrator.all.NewsDetailActivity;
import com.example.administrator.all.R;
import com.example.administrator.all.base.NewsDetailBasePager;
import com.example.administrator.all.bean.NewsListBean;
import com.example.administrator.all.utils.CacheUtils;
import com.example.administrator.all.utils.Contants;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Administrator on 2017/7/27 0027.
 */

public class TabDetailPager extends NewsDetailBasePager {
    public static final String READ_ARRAY_LIST = "read_array_list";
    private String type;
    private String url;
    private ListView listview;
    //新闻列表的集合
    private List<NewsListBean.ResultBean.DataBean> newsdata;
    private String s;
    private TabDetailPagerListAdapter adapter;



    public TabDetailPager(Context context,String type) {
        super(context);
        this.type=type;
    }

    @Override
    public View initView() {
       View view=View.inflate(context, R.layout.tabdetailpager,null);
//        textView=new TextView(context);
        listview= (ListView) view.findViewById(R.id.listView);

        return view;
    }



    private void getDataFromNet() {
    url= Contants.BASE_URL+type+Contants.NEWS_CENTER_URL;
        Log.e("tag",url);

    //使用OkHttp进行网络请求
        OkHttpClient client=new OkHttpClient();
      final Request request=new Request.Builder().
              get().url(url).build();
        Call call=client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(context,"网络请求失败",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
               s = response.body().string();
                Log.e("tag",s);
               // 解析数据
               MainActivity main= (MainActivity) context;
                main.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        processData(s);
                    }
                });



            }
        });

    }



    private void processData(String s) {
      NewsListBean bean=  parsedJson(s);
     newsdata = bean.getResult().getData();
        //设置适配器
        adapter=new TabDetailPagerListAdapter();
        listview.setAdapter(adapter);
        //点击事件
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                NewsListBean.ResultBean.DataBean dataBean = newsdata.get(position);
                Intent intent=new Intent(context,NewsDetailActivity.class);
                intent.putExtra("url",dataBean.getUrl());
                context.startActivity(intent);

            }
        });
    }

    private NewsListBean parsedJson(String json) {

        return new Gson().fromJson(json,NewsListBean.class);
    }



    @Override
    public void initData() {
        super.initData();
//
       getDataFromNet();

    }
    class  TabDetailPagerListAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return newsdata.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
          ViewHolder holder;
            if(convertView==null){
                convertView = View.inflate(context,R.layout.item_tabdetail_pager,null);
                holder = new ViewHolder();
                holder.iv_photo= (ImageView) convertView.findViewById(R.id.iv_photo);
                holder.tv_title= (TextView) convertView.findViewById(R.id.tv_title);
                holder.tv_date= (TextView) convertView.findViewById(R.id.tv_date);

                holder.tv_author= (TextView) convertView.findViewById(R.id.tv_author);
                convertView.setTag(holder);
            }else{
                holder= (ViewHolder) convertView.getTag();
            }
            NewsListBean.ResultBean.DataBean dataBean = newsdata.get(position);
            holder.tv_title.setText(dataBean.getTitle());
            holder.tv_date.setText(dataBean.getDate());
            holder.tv_author.setText(dataBean.getAuthor_name());

            Log.e("tag",holder.tv_title.getText().toString());
            //图片加载
            Glide.with(context).load(dataBean.getThumbnail_pic_s()).into(holder.iv_photo);


            return convertView;
        }
    }
    static class ViewHolder{
        ImageView iv_photo;
        private TextView tv_title;
        private TextView tv_date;

        private TextView tv_author;


    }
}
