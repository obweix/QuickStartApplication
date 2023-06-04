package com.example.quickstartapplication.network;

import android.os.Handler;

import com.example.quickstartapplication.utils.Singleton;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://www.wanandroid.com/";
    private Retrofit mRetrofit;


    private RetrofitClient(){};


    private static final Singleton<RetrofitClient> INSTANCE = new Singleton<RetrofitClient>() {
        @Override
        protected RetrofitClient create() {
            return new RetrofitClient();
        }
    };

    public static RetrofitClient getInstance(){
        return INSTANCE.get();
    }


    public <T> T getService(Class<T> tClass){
        return getRetrofit().create(tClass);
    }

    private Retrofit getRetrofit(){
        if (null == mRetrofit){
            mRetrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
        }

        return mRetrofit;
    }

}
