package com.dm.rxdemo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dm.rxdemo.R;
import com.dm.rxdemo.beans.ItemBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * FragmentAdapter
 * Created by dm on 16-12-4.
 */

public class FragmentAdapter
        extends RecyclerView.Adapter<FragmentAdapter.ItemViewHolder>
        implements View.OnClickListener, View.OnLongClickListener {

    private Context context;
    private List<ItemBean> mDatas;

    public FragmentAdapter(Context context, List<ItemBean> datas) {
        this.context = context;
        mDatas = datas;
    }

    // 设置item点击事件的回调函数
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    // 设置item点击事件的回调函数
    private OnRecyclerViewItemLongClickListener mOnItemLongClickListener = null;

    // define interface
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, ItemBean data);
    }

    // define interface
    public interface OnRecyclerViewItemLongClickListener {
        void onItemLongClick(View view, ItemBean data);
    }

    public void setOnItemClickListener(FragmentAdapter.OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void setOnItemLongClickListener(FragmentAdapter.OnRecyclerViewItemLongClickListener listener) {
        this.mOnItemLongClickListener = listener;
    }

    @Override
    public FragmentAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fg_item, parent, false);
        FragmentAdapter.ItemViewHolder itemViewHolder = new FragmentAdapter.ItemViewHolder(view);

        //将创建的View注册点击事件
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(FragmentAdapter.ItemViewHolder holder, int position) {
//        Glide.with(context)
//                .load(mDatas.get(position).getItemDrawable())
//                .error(R.drawable.bg)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .crossFade()
//                .centerCrop()
//                .into(holder.itemImageImv);
        holder.itemImageImv.setImageDrawable(mDatas.get(position).getItemDrawable());
        holder.itemTitleTv.setText(mDatas.get(position).getItemTitle());
        holder.itemTimeTv.setText(mDatas.get(position).getItemTime());

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
            mOnItemClickListener.onItemClick(view, (ItemBean) view.getTag());
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if (mOnItemLongClickListener != null) {
            mOnItemLongClickListener.onItemLongClick(view, (ItemBean) view.getTag());
        }

        return false;
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imv_fg_item)
        CircleImageView itemImageImv;

        @BindView(R.id.tv_fg_item_title)
        TextView itemTitleTv;

        @BindView(R.id.tv_fg_item_time)
        TextView itemTimeTv;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
