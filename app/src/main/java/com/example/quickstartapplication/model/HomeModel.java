package com.example.quickstartapplication.model;

import com.example.quickstartapplication.network.RetrofitClient;
import com.example.quickstartapplication.network.bean.JsonRootBean;
import com.example.quickstartapplication.network.service.HomeService;

import io.reactivex.rxjava3.core.Flowable;

public class HomeModel implements IHomeModel {

    @Override
    public Flowable<JsonRootBean> getHomeList(int pageIndex) {
        return RetrofitClient.getInstance().getService(HomeService.class).getHomeList(pageIndex);
    }
}
