package com.dm.recyclerviewdemo.beans;

import java.io.Serializable;

/**
 * NewsBean
 * Created by dm on 16-11-17.
 */
public class NewsBean implements Serializable {
    private String ctime;           // 新闻发布时间
    private String title;           // 新闻标题
    private String description;     // 新闻描述
    private String picUrl;          // 新闻配图
    private String url;             // 新闻链接地址
    private boolean isSelected = false;     // 是否已读

    public NewsBean() {
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
