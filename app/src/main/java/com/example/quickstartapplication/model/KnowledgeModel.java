package com.example.quickstartapplication.model;

import com.example.quickstartapplication.network.RetrofitClient;
import com.example.quickstartapplication.network.bean.NavigateJsonRootBean;
import com.example.quickstartapplication.network.service.KnowledgeService;

import io.reactivex.rxjava3.core.Flowable;

public class KnowledgeModel implements IKnowledgeModel{
    @Override
    public Flowable<NavigateJsonRootBean> getNavigateData() {
        return RetrofitClient.getInstance().getService(KnowledgeService.class).getNavigateData();
    }
}
