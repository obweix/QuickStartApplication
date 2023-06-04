package com.example.quickstartapplication.ui.knowledge;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quickstartapplication.model.IKnowledgeModel;
import com.example.quickstartapplication.model.KnowledgeModel;
import com.example.quickstartapplication.network.bean.FilteredArticles;
import com.example.quickstartapplication.network.bean.NavigateData;
import com.example.quickstartapplication.network.bean.NavigateJsonRootBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class KnowledgeViewModel extends ViewModel {
    private static final String TAG = KnowledgeViewModel.class.getSimpleName();

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    private final MutableLiveData<List<FilteredArticles>> mFilteredArticles;

    IKnowledgeModel mKnowledgeModel = new KnowledgeModel();

    public KnowledgeViewModel(){
        mFilteredArticles = new MutableLiveData<>();
    }

    LiveData<List<FilteredArticles>> getFilteredArticles(){
        return mFilteredArticles;
    }

    public void requestArticles(){
        Disposable disposable = mKnowledgeModel.getNavigateData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NavigateJsonRootBean>() {
                    @Override
                    public void accept(NavigateJsonRootBean navigateJsonRootBean) throws Throwable {
                        List<FilteredArticles> filteredArticlesList = new ArrayList<>();
                        for (NavigateData navData : navigateJsonRootBean.getData()) {
                            FilteredArticles filteredArticles = new FilteredArticles(navData.getName());
                            filteredArticles.mArticles.addAll(navData.getArticles());
                            filteredArticlesList.add(filteredArticles);
                        }
                        mFilteredArticles.setValue(filteredArticlesList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        mFilteredArticles.setValue(null);
                       throwable.printStackTrace();
                    }
                });
    }

    public void clearRequest(){
        mDisposable.clear();
    }
}
