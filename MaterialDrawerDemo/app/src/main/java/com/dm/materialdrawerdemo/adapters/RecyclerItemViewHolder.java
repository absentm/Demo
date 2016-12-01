package com.dm.materialdrawerdemo.adapters;

/**
 * RecyclerItemViewHolder
 * Created by dm on 16-12-1.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dm.materialdrawerdemo.R;

public class RecyclerItemViewHolder extends RecyclerView.ViewHolder {

    private final TextView mItemTextView;

    public RecyclerItemViewHolder(final View parent, TextView itemTextView) {
        super(parent);
        mItemTextView = itemTextView;
    }

    public static RecyclerItemViewHolder newInstance(View parent) {
        TextView itemTextView = (TextView) parent.findViewById(R.id.itemTextView);
        return new RecyclerItemViewHolder(parent, itemTextView);
    }

    public void setItemText(CharSequence text) {
        mItemTextView.setText(text);
    }

}