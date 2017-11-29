package com.example.administrator.all.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.all.R;
import com.example.administrator.all.bean.ChannelTitleBean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/23 0023.
 */

public class ChannelAdapter extends BaseAdapter {
    private Context context;
    private List<ChannelTitleBean> channel;
    public ChannelAdapter(Context context, List<ChannelTitleBean> channel) {
        this.context=context;
        this.channel=channel;
    }

    public ChannelAdapter(Context context) {

    }

    @Override
    public int getCount() {
        return channel.size();
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            view=View.inflate(context,R.layout.channel_item,null);
            holder=new ViewHolder();
            holder.img= (ImageView) view.findViewById(R.id.iv_channel);
            holder.title= (TextView) view.findViewById(R.id.tv_channel);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        ChannelTitleBean bean = channel.get(position);
        holder.img.setImageResource(bean.getImgaddress());
        holder.title.setText(bean.getTitle());

        return view;
    }
    class ViewHolder{
        private ImageView img;
        private TextView title;
    }
}
