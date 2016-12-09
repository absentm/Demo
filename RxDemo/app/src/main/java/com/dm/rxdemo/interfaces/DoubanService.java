package com.dm.rxdemo.interfaces;

import com.dm.rxdemo.beans.BookBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * DoubanService
 * url: https://api.douban.com/v2/book/search?q="小王子"
 * Created by dm on 16-12-9.
 */

public interface DoubanService {
    @GET("book/search")
    Call<BookBean> getBookData(@Query("q") String name);
}
