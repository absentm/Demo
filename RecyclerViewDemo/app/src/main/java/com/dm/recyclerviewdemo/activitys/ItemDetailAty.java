package com.dm.recyclerviewdemo.activitys;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.dm.recyclerviewdemo.R;
import com.dm.recyclerviewdemo.beans.NewsBean;

/**
 * ItemDetailAty
 * Created by dm on 16-11-18.
 */
public class ItemDetailAty extends BaseActivity {
    private NumberProgressBar mNumberProgressBar;
    private WebView mWebView;

    private NewsBean mNewsBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        initView();
        fillNewsDetailInfos();
    }

    private void initView() {
        setTitle("ItemDetail");
        mNumberProgressBar = (NumberProgressBar) findViewById(R.id.news_item_npar);
        mWebView = (WebView) findViewById(R.id.news_item_webview);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void fillNewsDetailInfos() {
        Intent intent = getIntent();
        mNewsBean = (NewsBean) intent.getSerializableExtra("newsInfos");

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setSupportZoom(false);

        mWebView.setWebChromeClient(new ItemDetailAty.MyWebChromeClient());
        mWebView.setWebViewClient(new ItemDetailAty.MyWebViewClient());
        mWebView.loadUrl(mNewsBean.getUrl());
    }

    private class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            mNumberProgressBar.setProgress(newProgress);
            if (newProgress == 100) {
                mNumberProgressBar.setVisibility(View.GONE);
            } else {
                mNumberProgressBar.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }
    }

    private class MyWebViewClient extends WebViewClient {
        @SuppressWarnings("deprecation")
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url != null) {
                view.loadUrl(url);
            }
            return true;
        }
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (event.getAction() == KeyEvent.ACTION_DOWN) {
//            switch (keyCode) {
//                case KeyEvent.KEYCODE_BACK:
//                    if (mWebView.canGoBack()) {
//                        mWebView.goBack();
//                    } else {
//                        finish();
//                    }
//                    return true;
//            }
//        }
//        return super.onKeyDown(keyCode, event);
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT,
                        "娱乐新闻: " + mNewsBean.getTitle() + ", 链接地址： " + mNewsBean.getUrl());
                shareIntent.setType("text/plain");
                //设置分享列表的标题，并且每次都显示分享列表
                startActivity(Intent.createChooser(shareIntent, "分享到"));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.destroy();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
        }
    }
}
