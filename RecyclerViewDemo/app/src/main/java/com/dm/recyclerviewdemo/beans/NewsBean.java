package com.dm.recyclerviewdemo.beans;

import java.io.Serializable;

/**
 * NewsBean
 * Created by dm on 16-11-17.
 */
public class NewsBean implements Serializable {
    private String ctime;
    private String title;
    private String description;
    private String picUrl;
    private String url;

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
}
