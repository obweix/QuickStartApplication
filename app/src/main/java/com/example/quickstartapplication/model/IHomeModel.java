package com.example.quickstartapplication.model;

import com.example.quickstartapplication.network.bean.JsonRootBean;

import io.reactivex.rxjava3.core.Flowable;

public interface IHomeModel {
    Flowable<JsonRootBean> getHomeList(int pageIndex);
}
