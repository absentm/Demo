package com.dm.recyclerviewdemo.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dm.recyclerviewdemo.R;
import com.dm.recyclerviewdemo.beans.NewsBean;

import java.util.List;

/**
 * MainPagesAdapter
 * Created by dm on 16-11-17.
 */

public class MainPagesAdapter
        extends RecyclerView.Adapter<MainPagesAdapter.ItemContentHolder>
        implements View.OnClickListener, View.OnLongClickListener {

    private Context context;
    private List<NewsBean> mDatas;

    // 设置item点击事件的回调函数
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    // 设置item点击事件的回调函数
    private OnRecyclerViewItemLongClickListener mOnItemLongClickListener = null;

    public MainPagesAdapter(Context context, List<NewsBean> datas) {
        this.context = context;
        mDatas = datas;
    }

    @Override
    public ItemContentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_news_item, parent, false);
        MainPagesAdapter.ItemContentHolder viewHolder = new MainPagesAdapter.ItemContentHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemContentHolder holder, int position) {
        // 使用Glide图片缓存框架加载图
        Glide.with(context)
                .load(mDatas.get(position).getPicUrl())
                .placeholder(R.drawable.icon)
                .error(R.drawable.icon)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .centerCrop()
                .into(holder.newsItemImv);
        holder.newsItemTitleTv.setText(mDatas.get(position).getTitle());
        holder.newsItemTimeTv.setText(mDatas.get(position).getCtime());

        int textColor = Color.parseColor("#CC000000");
        int textColorChange = Color.parseColor("#40000000");
        if (mDatas.get(position).isSelected()) {
            holder.newsItemTitleTv.setTextColor(textColorChange);
            holder.newsItemTimeTv.setTextColor(textColorChange);
        } else {
            holder.newsItemTitleTv.setTextColor(textColor);
            holder.newsItemTimeTv.setTextColor(textColor);
        }

        // 将数据保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener
                    .onItemClick(view, (NewsBean) view.getTag());
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if (mOnItemLongClickListener != null) {
            mOnItemLongClickListener
                    .onItemLongClick(view, (NewsBean) view.getTag());
        }

        return false;
    }

    // define interface
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, NewsBean data);
    }

    // define interface
    public interface OnRecyclerViewItemLongClickListener {
        void onItemLongClick(View view, NewsBean data);
    }

    public void setOnItemClickListener(MainPagesAdapter.OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void setOnItemLongClickListener(MainPagesAdapter.OnRecyclerViewItemLongClickListener listener) {
        this.mOnItemLongClickListener = listener;
    }

    public static class ItemContentHolder extends RecyclerView.ViewHolder {
        ImageView newsItemImv;
        TextView newsItemTitleTv;
        TextView newsItemTimeTv;

        public ItemContentHolder(View itemView) {
            super(itemView);
            newsItemImv = (ImageView) itemView.findViewById(R.id.main_item_image_imv);
            newsItemTitleTv = (TextView) itemView.findViewById(R.id.main_item_title_tv);
            newsItemTimeTv = (TextView) itemView.findViewById(R.id.main_item_time_tv);
        }
    }
}
