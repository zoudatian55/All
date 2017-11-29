package com.example.administrator.all;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class NewsDetailActivity extends Activity  implements View.OnClickListener{

    private TextView tvTitle;
    private ImageButton ibBack;
    private ImageButton ibTextsize;
    private ImageButton ibShare;
    private WebView webview;
    private ProgressBar pbLoding;
    private String url;
    private WebSettings settings;


    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-08-06 07:35:08 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        tvTitle = (TextView)findViewById( R.id.tv_title );
        ibBack = (ImageButton)findViewById( R.id.ib_back );
        ibTextsize = (ImageButton)findViewById( R.id.ib_textsize );
        ibShare = (ImageButton)findViewById( R.id.ib_share );
        webview = (WebView)findViewById( R.id.webview );
        pbLoding = (ProgressBar)findViewById( R.id.pb_loding );


        ibBack.setOnClickListener( this );
        ibTextsize.setOnClickListener( this );
        ibShare.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2017-08-06 07:35:08 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == ibBack ) {
            // Handle clicks for ibBack
            finish();
        } else if ( v == ibTextsize ) {
            //   Toast.makeText(NewsDetailActivity.this,"文字大小",Toast.LENGTH_LONG).show();
            showChangeTextSizeDialog();
        } else if ( v == ibShare ) {
            // Handle clicks for ibShare
            Toast.makeText(NewsDetailActivity.this,"分享", Toast.LENGTH_LONG).show();
        }
    }
    private int tempSize=2;
    private int realSize=tempSize;

    private void showChangeTextSizeDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("设置文本大小");
        String[] items={"超大字体","大字体","正常字体","小字体","超小字体"};
        builder.setSingleChoiceItems(items, 2, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tempSize=which;
            }
        });
        builder.setNegativeButton("取消",null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                realSize=tempSize;
                ChangeTextSize(realSize);
            }
        });
        builder.show();
    }

    private void ChangeTextSize(int realSize) {
        switch (realSize){
            case 0:settings.setTextZoom(200);
                break;
            case 1:settings.setTextZoom(150);
                break;
            case 2:settings.setTextZoom(100);
                break;
            case 3:settings.setTextZoom(75);
                break;
            case 4:settings.setTextZoom(50);
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        findViews();
        getData();
    }

    private void getData() {
        url=getIntent().getStringExtra("url");
        settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pbLoding.setVisibility(View.GONE);
            }
        });
        webview.loadUrl(url);
    }
}
