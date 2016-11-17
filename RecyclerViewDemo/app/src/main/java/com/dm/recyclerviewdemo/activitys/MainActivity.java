package com.dm.recyclerviewdemo.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dm.recyclerviewdemo.R;
import com.dm.recyclerviewdemo.adapters.MainPagesAdapter;
import com.dm.recyclerviewdemo.beans.NewsBean;
import com.dm.recyclerviewdemo.beans.NewsInfosBean;
import com.dm.recyclerviewdemo.utils.SystemUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener,
        MainPagesAdapter.OnRecyclerViewItemClickListener,
        MainPagesAdapter.OnRecyclerViewItemLongClickListener {

    private static final String TAG = "MainActivity";

    private CoordinatorLayout mCoordinatorLayout;
    private Toolbar mToolbar;
    private FloatingActionButton mFloatingActionButton;
    private ProgressBar mProgressBar;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<NewsBean> mDatas = new ArrayList<>();
    private MainPagesAdapter mMainPagesAdapter;

    private boolean isConnect;
    private NewsInfosBean mNewsInfosBean;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String newsJsonStr = msg.obj.toString();
            Log.d("FindVideoAty", " Json: " + newsJsonStr);

            Gson gson = new Gson();
            mNewsInfosBean = gson.fromJson(newsJsonStr, NewsInfosBean.class);
            if ((mNewsInfosBean.getCode() == 200) && mNewsInfosBean != null) {
                mDatas = mNewsInfosBean.getNewslist();

                mMainPagesAdapter = new MainPagesAdapter(MainActivity.this, mDatas);
                mLayoutManager = new LinearLayoutManager(MainActivity.this,
                        LinearLayoutManager.VERTICAL, false);
                mRecyclerView.setAdapter(mMainPagesAdapter);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());//默认动画
                mRecyclerView.setHasFixedSize(true);//效率最高
                mMainPagesAdapter.setOnItemClickListener(MainActivity.this);
            } else {
                Snackbar.make(mCoordinatorLayout, "数据加载出错 !!!",
                        Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }

            mProgressBar.setVisibility(View.GONE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        mFloatingActionButton.setOnClickListener(MainActivity.this);

        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        mProgressBar.setVisibility(View.VISIBLE);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        isConnect = SystemUtils.checkNetworkConnection(MainActivity.this);
        if (isConnect) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String newsDatas = SystemUtils.getNewsJsonStr();
                    if (!newsDatas.equals("")) {
                        Message message = mHandler.obtainMessage();
                        message.obj = newsDatas;
                        mHandler.sendMessage(message);
                    }
                }
            }).start();
        } else {
            Snackbar.make(mCoordinatorLayout, "No NetWork !!!",
                    Snackbar.LENGTH_LONG).setAction("Action", null).show();
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_switch:
                Toast.makeText(this, "切换", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_about:
                Toast.makeText(this, "关于", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
//                Snackbar.make(view, "Replace with your own action",
//                        Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                break;
        }

    }

    @Override
    public void onItemClick(View view, NewsBean data) {

    }

    @Override
    public void onItemLongClick(View view, NewsBean data) {

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        isConnect = SystemUtils.checkNetworkConnection(MainActivity.this);
        if (!isConnect) {
            Snackbar.make(mCoordinatorLayout, "No NetWork !!!",
                    Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
    }
}
