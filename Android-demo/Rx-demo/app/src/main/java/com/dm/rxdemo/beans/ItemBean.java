package com.dm.rxdemo.beans;

import android.graphics.drawable.Drawable;

/**
 * ItemBean
 * Created by dm on 16-12-4.
 */
public class ItemBean {
    private Drawable itemDrawable;
    private String itemTitle;
    private String itemTime;

    public ItemBean() {
    }

    public ItemBean(Drawable itemDrawable, String itemTitle, String itemTime) {
        this.itemDrawable = itemDrawable;
        this.itemTitle = itemTitle;
        this.itemTime = itemTime;
    }

    public Drawable getItemDrawable() {
        return itemDrawable;
    }

    public void setItemDrawable(Drawable itemDrawable) {
        this.itemDrawable = itemDrawable;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemTime() {
        return itemTime;
    }

    public void setItemTime(String itemTime) {
        this.itemTime = itemTime;
    }
}
