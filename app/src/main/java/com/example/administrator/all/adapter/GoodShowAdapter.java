package com.example.administrator.all.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.all.R;
import com.example.administrator.all.bean.ShopHomeBean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/24 0024.
 */

public class GoodShowAdapter extends BaseAdapter {
    private Context context;
    private List<ShopHomeBean.DatasBean.GoodsBean.ItemBeanX> lists;
    public GoodShowAdapter(Context context, List<ShopHomeBean.DatasBean.GoodsBean.ItemBeanX> item) {
        this.context=context;
        this.lists=item;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
         ViewHolder holder;
        if(view==null){
            view=View.inflate(context, R.layout.good_item,null);
            holder=new ViewHolder();
            holder.imageView= (ImageView) view.findViewById(R.id.iv_hot);
            holder.title= (TextView) view.findViewById(R.id.tv_name);
            holder.price= (TextView) view.findViewById(R.id.tv_price);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        ShopHomeBean.DatasBean.GoodsBean.ItemBeanX beanX = lists.get(i);
        Glide.with(context).load(beanX.getGoods_image()).into(holder.imageView);
        holder.title.setText(beanX.getGoods_name());
        holder.price.setText("¥"+beanX.getGoods_promotion_price());
        return view;
    }

    class ViewHolder{
        ImageView imageView;
        TextView title;
        TextView price;
    }

}
