package com.dm.rxdemo.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.dm.rxdemo.R;
import com.dm.rxdemo.beans.BookBean;
import com.dm.rxdemo.interfaces.CustomInterceptor;
import com.dm.rxdemo.interfaces.DoubanService;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * GetWithParamAty
 * url: https://api.douban.com/v2/book/search?q="小王子"
 * Created by dm on 16-12-9.
 */
public class GetWithParamAty extends BaseBarWithBackActivity {
    @BindView(R.id.param_tv)
    TextView mParamTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_get_with_param);
        ButterKnife.bind(this);

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                .addInterceptor(new CustomInterceptor())
                .connectTimeout(1000, TimeUnit.MILLISECONDS);

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.douban.com/v2/")
                .build();

        DoubanService doubanService = retrofit.create(DoubanService.class);
        Call<BookBean> call = doubanService.getBookData("小王子");
        call.enqueue(new Callback<BookBean>() {
            @Override
            public void onResponse(Call<BookBean> call, Response<BookBean> response) {
                BookBean bookBean = response.body();
                if (bookBean == null) return;

                for (BookBean.BooksBean booksBean : bookBean.getBooks()) {
                    mParamTv.setText("Title: " + booksBean.getTitle() + "\n"
                            + "SubTitle: " + booksBean.getSubtitle() + "\n"
                            + "Price: " + booksBean.getPrice() + "\n"
                            + "Publisher: " + booksBean.getPublisher() + "\n"
                            + "Pub-Date: " + booksBean.getPubdate() + "\n\n");
                }
            }

            @Override
            public void onFailure(Call<BookBean> call, Throwable t) {

            }
        });
    }
}
