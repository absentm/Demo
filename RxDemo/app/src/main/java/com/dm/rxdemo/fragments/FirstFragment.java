package com.dm.rxdemo.fragments;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dm.rxdemo.R;
import com.dm.rxdemo.adapters.FragmentAdapter;
import com.dm.rxdemo.beans.ItemBean;
import com.github.lzyzsd.randomcolor.RandomColor;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

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
        FragmentAdapter.OnRecyclerViewItemLongClickListener {

    @BindView(R.id.fg_rcv)
    PullLoadMoreRecyclerView mRecyclerView;

    @BindDrawable(R.drawable.bg)
    Drawable mDrawable;

    private List<ItemBean> mDatas = new ArrayList<>();
    private FragmentAdapter mFragmentAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_bind, container, false);
        ButterKnife.bind(this, view);

        mDatas = getDatas();
        initView();

        return view;
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

    private void initView() {
        mFragmentAdapter = new FragmentAdapter(getActivity(), mDatas);
        mRecyclerView.setLinearLayout();
        mRecyclerView.setAdapter(mFragmentAdapter);
        mRecyclerView.setColorSchemeResources(android.R.color.holo_red_dark,android.R.color.holo_blue_dark);
        mRecyclerView.setFooterViewText("loading");

        mFragmentAdapter.setOnItemClickListener(this);
        mFragmentAdapter.setOnItemLongClickListener(this);
    }


    @Override
    public void onItemClick(View view, ItemBean data) {

    }

    @Override
    public void onItemLongClick(View view, ItemBean data) {

    }

    private Drawable getRandomIcon() {
        RandomColor randomColor = new RandomColor();
        int color = randomColor.randomColor();

        return new IconicsDrawable(getActivity())
                .icon(FontAwesome.Icon.faw_caret_square_o_up)
                .color(color)
                .sizeDp(48);
    }
}
