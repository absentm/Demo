package com.dm.rxdemo.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import com.dm.rxdemo.R;
import com.dm.rxdemo.beans.HistoryBean;
import com.dm.rxdemo.interfaces.GankService;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * JsonGetAty
 * url: http://gank.io/api/history/content/2/1
 * Created by dm on 16-12-9.
 */
public class JsonGetAty extends BaseBarWithBackActivity {
    @BindView(R.id.json_tv)
    TextView mJsonTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_json);
        ButterKnife.bind(this);
        setTitle("JSON GET");

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://gank.io/api/history/")
                .build();

        GankService gankService = retrofit.create(GankService.class);
        Call<HistoryBean> call = gankService.getHistoryData("2", "1");

        call.enqueue(new Callback<HistoryBean>() {
            @Override
            public void onResponse(Call<HistoryBean> call, Response<HistoryBean> response) {
                Logger.d(response.body().toString());
                HistoryBean historyBean = response.body();

                if (historyBean == null) return;
                mJsonTv.setText(String.format("%s", historyBean.isError() + "\n\n"));
                for (HistoryBean.ResultsBean resultsBean : historyBean.getResults()) {
                    mJsonTv.append(resultsBean.getTitle() + "\n\n");
                }

            }

            @Override
            public void onFailure(Call<HistoryBean> call, Throwable t) {
                Toast.makeText(JsonGetAty.this,
                        t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}
