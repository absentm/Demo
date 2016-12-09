package com.dm.rxdemo.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import com.dm.rxdemo.R;
import com.dm.rxdemo.interfaces.HtmlService;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * BasicGetAty
 * Created by dm on 16-12-9.
 */
public class BasicGetAty extends BaseBarWithBackActivity {
    @BindView(R.id.basic_get_tv)
    TextView mBasicGetTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_basic_get);
        ButterKnife.bind(this);
        setTitle("BASIC_GET_ATY");

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl("https://www.baidu.com")
                .build();

        HtmlService htmlService = retrofit.create(HtmlService.class);
        Call<String> call = htmlService.getHtml();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                mBasicGetTv.setText(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(BasicGetAty.this,
                        t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
