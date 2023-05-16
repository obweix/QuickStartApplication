package com.example.quickstartapplication.network.service;

import com.example.quickstartapplication.network.bean.JsonRootBean;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HomeService {
    @GET("article/list/{pageIndex}/json")
    Flowable<JsonRootBean> getHomeList(@Path("pageIndex") int pageIndex);
}
