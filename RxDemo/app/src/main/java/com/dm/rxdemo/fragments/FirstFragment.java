package com.dm.rxdemo.fragments;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.dm.rxdemo.R;
import com.dm.rxdemo.adapters.FragmentAdapter;
import com.dm.rxdemo.beans.ItemBean;
import com.github.lzyzsd.randomcolor.RandomColor;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * FirstFragment
 * Created by dm on 16-12-4.
 */
public class FirstFragment extends Fragment
        implements FragmentAdapter.OnRecyclerViewItemClickListener,
        FragmentAdapter.OnRecyclerViewItemLongClickListener,
        OnRefreshListener, OnLoadMoreListener {

    @BindView(R.id.fg_swipeToLoadLayout)
    SwipeToLoadLayout mSwipeToLoadLayout;

    @BindView(R.id.swipe_target)
    RecyclerView mRecyclerView;

    @BindDrawable(R.drawable.bg)
    Drawable mDrawable;

    private List<ItemBean> mDatas = new ArrayList<>();
    private FragmentAdapter mFragmentAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_bind, container, false);
        ButterKnife.bind(this, view);

        initView();

        // 自动加载
        autoRefresh();

        return view;
    }

    private void initView() {
        mDatas = getDatas();

        mFragmentAdapter = new FragmentAdapter(getActivity(), mDatas);

        mRecyclerView.setAdapter(mFragmentAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);

        mFragmentAdapter.setOnItemClickListener(this);
        mFragmentAdapter.setOnItemLongClickListener(this);

        mSwipeToLoadLayout.setOnRefreshListener(this);
        mSwipeToLoadLayout.setOnLoadMoreListener(this);
    }

    private List<ItemBean> getDatas() {
        List<ItemBean> itemBeanList = new ArrayList<>();

        itemBeanList.add(new ItemBean(getRandomIcon(), "Hehe1", "13:57"));
        itemBeanList.add(new ItemBean(getRandomIcon(), "Hehe2", "12:46"));
        itemBeanList.add(new ItemBean(getRandomIcon(), "Hehe3", "14:33"));
        itemBeanList.add(new ItemBean(getRandomIcon(), "Hehe4", "16:11"));
        itemBeanList.add(new ItemBean(getRandomIcon(), "Hehe5", "18:10"));
        itemBeanList.add(new ItemBean(getRandomIcon(), "Hehe6", "19:51"));
        itemBeanList.add(new ItemBean(getRandomIcon(), "Hehe7", "10:13"));
        itemBeanList.add(new ItemBean(getRandomIcon(), "Hehe8", "12:21"));
        itemBeanList.add(new ItemBean(getRandomIcon(), "Hehe9", "15:41"));
        itemBeanList.add(new ItemBean(getRandomIcon(), "Hehe10", "11:26"));
        itemBeanList.add(new ItemBean(getRandomIcon(), "Hehe11", "12:28"));
        itemBeanList.add(new ItemBean(getRandomIcon(), "Hehe12", "19:29"));
        itemBeanList.add(new ItemBean(getRandomIcon(), "Hehe13", "18:31"));
        itemBeanList.add(new ItemBean(getRandomIcon(), "Hehe14", "17:25"));
        itemBeanList.add(new ItemBean(getRandomIcon(), "Hehe15", "15:34"));
        itemBeanList.add(new ItemBean(getRandomIcon(), "Hehe16", "22:58"));
        itemBeanList.add(new ItemBean(getRandomIcon(), "Hehe17", "13:24"));
        itemBeanList.add(new ItemBean(getRandomIcon(), "Hehe18", "22:36"));
        itemBeanList.add(new ItemBean(getRandomIcon(), "Hehe18", "22:36"));
        itemBeanList.add(new ItemBean(getRandomIcon(), "Hehe18", "22:36"));
        itemBeanList.add(new ItemBean(getRandomIcon(), "Hehe18", "22:36"));
        itemBeanList.add(new ItemBean(getRandomIcon(), "Hehe18", "22:36"));
        itemBeanList.add(new ItemBean(getRandomIcon(), "Hehe18", "22:36"));
        itemBeanList.add(new ItemBean(getRandomIcon(), "Hehe18", "22:36"));
        itemBeanList.add(new ItemBean(getRandomIcon(), "Hehe18", "22:36"));
        itemBeanList.add(new ItemBean(getRandomIcon(), "Hehe18", "22:36"));
        itemBeanList.add(new ItemBean(getRandomIcon(), "Hehe18", "22:36"));
        itemBeanList.add(new ItemBean(getRandomIcon(), "Hehe18", "22:36"));
        itemBeanList.add(new ItemBean(getRandomIcon(), "Hehe18", "22:36"));
        itemBeanList.add(new ItemBean(getRandomIcon(), "Hehe18", "22:36"));
        itemBeanList.add(new ItemBean(getRandomIcon(), "Hehe18", "22:36"));
        itemBeanList.add(new ItemBean(getRandomIcon(), "Hehe18", "22:36"));
        itemBeanList.add(new ItemBean(getRandomIcon(), "Hehe18", "22:36"));
        itemBeanList.add(new ItemBean(getRandomIcon(), "Hehe18", "22:36"));
        itemBeanList.add(new ItemBean(getRandomIcon(), "Hehe18", "22:36"));

        return itemBeanList;
    }


    @Override
    public void onItemClick(View view, ItemBean data) {
        Toast.makeText(getActivity(),
                data.getItemTitle(),
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onItemLongClick(View view, ItemBean data) {
        Toast.makeText(getActivity(),
                data.getItemTime(),
                Toast.LENGTH_SHORT).show();
    }

    private Drawable getRandomIcon() {
        RandomColor randomColor = new RandomColor();
        int color = randomColor.randomColor();

        return new IconicsDrawable(getActivity())
                .icon(FontAwesome.Icon.faw_caret_square_o_up)
                .color(color)
                .sizeDp(48);
    }

    @Override
    public void onLoadMore() {
        mSwipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeToLoadLayout.setLoadingMore(false);
                mDatas.add(new ItemBean(getRandomIcon(), "onLoadMore!", "14:00"));
                mFragmentAdapter.notifyDataSetChanged();
            }
        }, 1000);
    }

    @Override
    public void onRefresh() {
        mSwipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeToLoadLayout.setRefreshing(false);
                mDatas.add(new ItemBean(getRandomIcon(), "onRefresh!", "14:00"));
                mFragmentAdapter.notifyDataSetChanged();
            }
        }, 1000);

    }

    private void autoRefresh() {
        mSwipeToLoadLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeToLoadLayout.setRefreshing(true);
            }
        });
    }
}
