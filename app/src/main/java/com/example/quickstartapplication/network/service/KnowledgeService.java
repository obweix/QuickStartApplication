package com.example.quickstartapplication.network.service;

import com.example.quickstartapplication.network.bean.NavigateJsonRootBean;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;

public interface KnowledgeService {
    @GET("navi/json")
    Flowable<NavigateJsonRootBean> getNavigateData();
}
