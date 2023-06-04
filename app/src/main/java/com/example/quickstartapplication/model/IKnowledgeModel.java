package com.example.quickstartapplication.model;

import com.example.quickstartapplication.network.bean.NavigateJsonRootBean;

import io.reactivex.rxjava3.core.Flowable;

public interface IKnowledgeModel {
   Flowable<NavigateJsonRootBean> getNavigateData();
}
