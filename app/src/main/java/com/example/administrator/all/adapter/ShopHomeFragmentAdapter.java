package com.example.administrator.all.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.all.R;
import com.example.administrator.all.bean.ChannelTitleBean;
import com.example.administrator.all.bean.ShopHomeBean;
import com.example.administrator.all.view.MyGridView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.listener.OnLoadImageListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/23 0023.
 */

public class ShopHomeFragmentAdapter extends RecyclerView.Adapter {
    private  int currentType=0;
    //广告页
    public static  final int BANNER=0;
    //频道页
    public static final int CHANNER=1;
    //商品展示类型
    public static final int GOODSHOW_1=2;
    public static final int GOODSHOW_2=3;
    public static final int GOODSHOW_3=4;
    public static final int GOODSHOW_4=5;
    public static final int GOODSHOW_5=6;
    public static final int GOODSHOW_6=7;
    public static final int GOODSHOW_7=8;


    private Context context;
    private List<ShopHomeBean.DatasBean> data;
    private LayoutInflater mlayout;
    private String[] title=new String[]{"壹号生鲜","食品饮料","粮油副食","中外名酒","全部分类",
            "母婴用品","美妆个护","居家生活","营养保健","中国搜索"};
    private int[] imgs=new int[]{R.drawable.column_fresh,R.drawable.column_food,
            R.drawable.column_oil, R.drawable.column_wine,
            R.drawable.column_all, R.drawable.column_baby,
            R.drawable.column_mask,R.drawable.column_life,
            R.drawable.column_health,R.drawable.column_search
                       };

    private List<ChannelTitleBean> channel;
    private  ChannelAdapter channeladapter;
    private GoodShowAdapter goodadapter;


    public ShopHomeFragmentAdapter(Context context, List<ShopHomeBean.DatasBean> datas) {
        this.context=context;
        this.data=datas;
         mlayout=LayoutInflater.from(context);
    }


    //创建ViewHolder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==BANNER){
            return new BannerViewHolder(context,mlayout.inflate(R.layout.banner_viewpager,null));
        }else  if(viewType==CHANNER){
            return new ChannelViewHolder(context,mlayout.inflate(R.layout.channel_viewpager,null));
        }else if(viewType==GOODSHOW_1){
            return new GoodViewHolder(context,mlayout.inflate(R.layout.good_viewpger,null));
        }
        else if(viewType==GOODSHOW_2){
            return new GoodViewHolder(context,mlayout.inflate(R.layout.good_viewpger,null));
        }
        else if(viewType==GOODSHOW_3){
            return new GoodViewHolder(context,mlayout.inflate(R.layout.good_viewpger,null));
        }
        else if(viewType==GOODSHOW_4){
            return new GoodViewHolder(context,mlayout.inflate(R.layout.good_viewpger,null));
        }
        else if(viewType==GOODSHOW_5){
            return new GoodViewHolder(context,mlayout.inflate(R.layout.good_viewpger,null));
        }else if(viewType==GOODSHOW_6){
            return new GoodViewHolder(context,mlayout.inflate(R.layout.good_viewpger,null));
        }
        else if(viewType==GOODSHOW_7){
            return new GoodViewHolder(context,mlayout.inflate(R.layout.good_viewpger,null));
        }

        return null;
    }
//绑定ViewHoder
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position)==BANNER){
            BannerViewHolder bannerViewHolder= (BannerViewHolder) holder;
            bannerViewHolder.setData(data.get(0).getAdv_list().getItem());
        }else if(getItemViewType(position)==CHANNER){
            ChannelViewHolder channelViewHolder= (ChannelViewHolder) holder;
            channelViewHolder.setChannelData();
        }else if(getItemViewType(position)==GOODSHOW_1) {
            GoodViewHolder goodViewHolder = (GoodViewHolder) holder;
                goodViewHolder.setGoodData(data.get(1).getGoods());
            }
        else if(getItemViewType(position)==GOODSHOW_2) {
            GoodViewHolder goodViewHolder = (GoodViewHolder) holder;
            goodViewHolder.setGoodData(data.get(2).getGoods());
        }
        else if(getItemViewType(position)==GOODSHOW_3) {
            GoodViewHolder goodViewHolder = (GoodViewHolder) holder;
            goodViewHolder.setGoodData(data.get(3).getGoods());
        }
        else if(getItemViewType(position)==GOODSHOW_4) {
            GoodViewHolder goodViewHolder = (GoodViewHolder) holder;
            goodViewHolder.setGoodData(data.get(4).getGoods());
        }else if(getItemViewType(position)==GOODSHOW_5) {
            GoodViewHolder goodViewHolder = (GoodViewHolder) holder;
            goodViewHolder.setGoodData(data.get(5).getGoods());
        }else if(getItemViewType(position)==GOODSHOW_6) {
            GoodViewHolder goodViewHolder = (GoodViewHolder) holder;
            goodViewHolder.setGoodData(data.get(6).getGoods());
        }else if(getItemViewType(position)==GOODSHOW_7) {
            GoodViewHolder goodViewHolder = (GoodViewHolder) holder;
            goodViewHolder.setGoodData(data.get(7).getGoods());
        }


    }

    /**
     * 总共有几种类型
     * @return
     */
    @Override
    public int getItemCount() {
        return 9;
    }

    /**
     * 得到类型
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        switch (position){
            case  BANNER:
                currentType=BANNER;
                break;
            case CHANNER:
                currentType=CHANNER;
                break;
            case GOODSHOW_1:
                currentType=GOODSHOW_1;
                break;
            case GOODSHOW_2:
                currentType=GOODSHOW_2;
                break;
            case GOODSHOW_3:
                currentType=GOODSHOW_3;
                break;
            case GOODSHOW_4:
                currentType=GOODSHOW_4;
                break;
            case GOODSHOW_5:
                currentType=GOODSHOW_5;
                break;
            case GOODSHOW_6:
                currentType=GOODSHOW_6;
                break;
            case GOODSHOW_7:
                currentType=GOODSHOW_7;
                break;
        }
        return currentType;
    }
    class BannerViewHolder extends RecyclerView.ViewHolder{
        private Context context;
        private View itemView;
        private Banner banner;

        public BannerViewHolder(Context context, View itemView) {
           super(itemView);
            this.context=context;
            this.itemView=itemView;
           this.banner= (Banner) itemView.findViewById(R.id.banner);

        }

        public void setData(List<ShopHomeBean.DatasBean.AdvListBean.ItemBean> item) {
            //设置Banner的数据
            List<String> imageurl=new ArrayList<>();
            //得到图片的地址
            for(int i=0;i<item.size();i++){
                String imageUrl=item.get(i).getImage();
                imageurl.add(imageUrl);

            }
            //设置是效果
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            banner.setBannerAnimation(Transformer.Accordion);
            banner.setImages(imageurl, new OnLoadImageListener() {
                @Override
                public void OnLoadImage(ImageView view, Object url) {
                    Glide.with(context).load(url).into(view);
                }
            });
            banner.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {

                    Toast.makeText(context, "position"+position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    class ChannelViewHolder extends RecyclerView.ViewHolder{
        private Context context;
        private View view;
        private GridView gridView;

        public ChannelViewHolder(Context context, View view) {
            super(view);
            this.context=context;
            gridView= (GridView) view.findViewById(R.id.gridview);


        }

        public void setChannelData() {
            channel=new ArrayList<>();
            for(int i=0;i<title.length;i++){
                ChannelTitleBean titleBean=new ChannelTitleBean();
                titleBean.setTitle(title[i]);
                titleBean.setImgaddress(imgs[i]);
                channel.add(titleBean);

            }

            channeladapter=new ChannelAdapter(context,channel);
            gridView.setAdapter(channeladapter);
           //gridView.setNumColumns(5);
            gridView.setNumColumns(5);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                }
            });

        }
    }
    class GoodViewHolder extends RecyclerView.ViewHolder{
        private Context context;
        private View view;
        private TextView good_title;
        private MyGridView good_gridView;


        public GoodViewHolder(Context context, View inflate) {
            super( inflate);
            this.context=context;
            this.view=inflate;
            good_title= (TextView) view.findViewById(R.id.good_title);
            good_gridView= (MyGridView) view.findViewById(R.id.good_gridview);

        }

        public void setGoodData(ShopHomeBean.DatasBean.GoodsBean goods) {
            good_title.setText(goods.getTitle());
            List<ShopHomeBean.DatasBean.GoodsBean.ItemBeanX> goodsItem = goods.getItem();
            goodadapter=new GoodShowAdapter(context,goodsItem);
            good_gridView.setAdapter(goodadapter);


        }
    }
}
