package com.dm.recyclerviewdemo.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import com.afollestad.materialdialogs.MaterialDialog;
import com.dm.recyclerviewdemo.R;
import com.dm.recyclerviewdemo.adapters.MainPagesAdapter;
import com.dm.recyclerviewdemo.beans.NewsBean;
import com.dm.recyclerviewdemo.beans.NewsInfosBean;
import com.dm.recyclerviewdemo.utils.SystemUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static com.dm.recyclerviewdemo.R.id.fab;

public class MainActivity extends BaseActivity
        implements View.OnClickListener,
        MainPagesAdapter.OnRecyclerViewItemClickListener,
        MainPagesAdapter.OnRecyclerViewItemLongClickListener {

    private static final String TAG = "MainActivity";

    private Animation animationIn;  //进入动画
    private Animation animationOut; //退出动画

    private CoordinatorLayout mCoordinatorLayout;
    private Toolbar mToolbar;
    private FloatingActionButton mFloatingActionButton;
    private ProgressBar mProgressBar;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<NewsBean> mDatas = new ArrayList<>();
    private MainPagesAdapter mMainPagesAdapter;

    private boolean isConnect;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String newsJsonStr = msg.obj.toString();
            Log.d("FindVideoAty", " Json: " + newsJsonStr);

            Gson gson = new Gson();
            NewsInfosBean newsInfosBean = gson.fromJson(newsJsonStr, NewsInfosBean.class);
            if (newsInfosBean.getCode() == 200) {
                mDatas = newsInfosBean.getNewslist();

                MainPagesAdapter.layoutFlag = 0;
                mMainPagesAdapter = new MainPagesAdapter(MainActivity.this, mDatas);
                mLayoutManager = new LinearLayoutManager(MainActivity.this,
                        LinearLayoutManager.VERTICAL, false);
                mRecyclerView.setAdapter(mMainPagesAdapter);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());//默认动画
                mRecyclerView.setHasFixedSize(true);//效率最高

                mMainPagesAdapter.setOnItemClickListener(MainActivity.this);
                mMainPagesAdapter.setOnItemLongClickListener(MainActivity.this);
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

        initAnimation();
        initView();
        eventsDeal();
    }

    private void initAnimation() {
        animationIn = AnimationUtils.loadAnimation(this, R.anim.fab_fade_in);
        animationOut = AnimationUtils.loadAnimation(this, R.anim.fab_fade_out);
    }

    private void initView() {
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mFloatingActionButton = (FloatingActionButton) findViewById(fab);
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
                    Log.i(TAG, "newsDatas >>> " + newsDatas);
                    if (!newsDatas.equals("")) {
                        Message message = mHandler.obtainMessage();
                        message.obj = newsDatas;
                        mHandler.sendMessage(message);
                    } else {
                        mProgressBar.setVisibility(View.GONE);
                        Snackbar.make(mCoordinatorLayout, "数据加载出错 !!!",
                                Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }
                }
            }).start();
        } else {
            Snackbar.make(mCoordinatorLayout, "No NetWork !!!",
                    Snackbar.LENGTH_LONG).setAction("Action", null).show();
            mProgressBar.setVisibility(View.GONE);
        }
    }

    private void eventsDeal() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                //上滑比并且按钮不可见 显示按钮,
                //下滑并且按钮可见 隐藏按钮
                if ((dy < 0) && (mFloatingActionButton.getVisibility() == View.GONE)) {
                    mFloatingActionButton.startAnimation(animationIn);
                    mFloatingActionButton.setVisibility(View.VISIBLE);
                } else if ((dy > 0) && (mFloatingActionButton.getVisibility() == View.VISIBLE)) {
                    mFloatingActionButton.startAnimation(animationOut);
                    mFloatingActionButton.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_cardview:
                mLayoutManager = new LinearLayoutManager(MainActivity.this,
                        LinearLayoutManager.VERTICAL, false);
                initRecycleView(0, mDatas, mLayoutManager);
                break;
            case R.id.action_no_gapview:
                mLayoutManager = new LinearLayoutManager(MainActivity.this,
                        LinearLayoutManager.VERTICAL, false);
                initRecycleView(1, mDatas, mLayoutManager);
                break;
            case R.id.action_fluview:
                mLayoutManager = new StaggeredGridLayoutManager(2,
                        StaggeredGridLayoutManager.VERTICAL);
                initRecycleView(2, mDatas, mLayoutManager);
                break;
            case R.id.action_gridview:
                mLayoutManager = new GridLayoutManager(MainActivity.this, 3);
                initRecycleView(3, mDatas, mLayoutManager);
                break;
            case R.id.action_about:
                startActivity(new Intent(MainActivity.this, AboutAty.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case fab:
                //返回顶部
                mLayoutManager.smoothScrollToPosition(mRecyclerView, null, 0);
                break;
        }

    }

    @Override
    public void onItemClick(View view, NewsBean data) {
        // 标记已读，设置标记位
        data.setSelected(true);
        mMainPagesAdapter.notifyDataSetChanged();

        // 跳转至详情页
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("newsInfos", data);
        intent.setClass(MainActivity.this, ItemDetailAty.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(View view, final NewsBean data) {
        new MaterialDialog.Builder(MainActivity.this)
                .title("标记")
                .items(R.array.tag_values)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog,
                                            View itemView,
                                            int position,
                                            CharSequence text) {

                        String listItemStr = (String) text;
                        Log.d(TAG, "listItemStr >>> " + listItemStr);
                        switch (listItemStr) {
                            case "已读":
                                data.setSelected(true);
                                mMainPagesAdapter.notifyDataSetChanged();
                                break;
                            case "未读":
                                data.setSelected(false);
                                mMainPagesAdapter.notifyDataSetChanged();
                                break;
                        }

                    }
                }).show();
    }

    private void initRecycleView(int flag,
                                 List<NewsBean> datas,
                                 RecyclerView.LayoutManager layoutManager) {
        MainPagesAdapter.layoutFlag = flag;

        mMainPagesAdapter = new MainPagesAdapter(MainActivity.this, datas);
        mRecyclerView.setAdapter(mMainPagesAdapter);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//默认动画
        mRecyclerView.setHasFixedSize(true);//效率最高

        mMainPagesAdapter.setOnItemClickListener(MainActivity.this);
        mMainPagesAdapter.setOnItemLongClickListener(MainActivity.this);
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
