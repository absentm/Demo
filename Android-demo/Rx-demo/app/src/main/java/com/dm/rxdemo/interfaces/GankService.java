package com.dm.rxdemo.interfaces;

import com.dm.rxdemo.beans.HistoryBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * GankService
 * Created by dm on 16-12-9.
 */

public interface GankService {
    @GET("content/{number}/{page}")
    Call<HistoryBean> getHistoryData(@Path("number") String number,
                                     @Path("page") String page);
}
