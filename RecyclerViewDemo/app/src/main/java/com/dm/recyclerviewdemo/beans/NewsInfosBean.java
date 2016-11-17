package com.dm.recyclerviewdemo.beans;

import java.util.List;

/**
 * NewsInfosBean
 * Created by dm on 16-11-17.
 */

public class NewsInfosBean {
    private int code;
    private String msg;
    private List<NewsBean> newslist;

    public NewsInfosBean() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewsBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewsBean> newslist) {
        this.newslist = newslist;
    }
}
