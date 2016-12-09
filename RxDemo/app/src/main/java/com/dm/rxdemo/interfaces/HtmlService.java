package com.dm.rxdemo.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * HtmlService
 * Created by dm on 16-12-9.
 */

public interface HtmlService {
    @GET("/")
    Call<String> getHtml();
}
