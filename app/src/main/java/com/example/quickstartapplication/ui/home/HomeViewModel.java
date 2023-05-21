package com.example.quickstartapplication.ui.home;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.example.quickstartapplication.model.HomeModel;
import com.example.quickstartapplication.model.IHomeModel;
import com.example.quickstartapplication.network.bean.Data;
import com.example.quickstartapplication.network.bean.Datas;
import com.example.quickstartapplication.network.bean.JsonRootBean;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineScope;

public class HomeViewModel extends ViewModel {
    private static final String TAG = HomeViewModel.class.getSimpleName();

    private final MutableLiveData<String> mText;
    private final MutableLiveData<List<Datas>> mDatas;

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    @SuppressLint("CheckResult")
    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mDatas = new MutableLiveData<>();
        init();


    }

    public LiveData<String> getText() {
        return mText;
    }


    Flowable<PagingData<Datas>> mDatasFlowable;
    IHomeModel mHomeModel = new HomeModel();

    private void init(){
        DatasPagingSource datasPagingSource = new DatasPagingSource(mHomeModel);
        Pager<Integer,Datas> pager = new Pager<>(new PagingConfig(20, //  Count of items in one page
                20, //  Number of items to prefetch
                false, // Enable placeholders for data which is not yet loaded
                20, // initialLoadSize - Count of items to be loaded initially
                Integer.MAX_VALUE),// maxSize - Count of total items to be shown in recyclerview);
                ()-> datasPagingSource); // set paging source

        // inti Flowable
        CoroutineScope coroutineScope = ViewModelKt.getViewModelScope(this);
        mDatasFlowable  =  PagingRx.cachedIn(PagingRx.getFlowable(pager), coroutineScope);

    }


    public LiveData<List<Datas>> getDatas(int pageIndex){
        return mDatas;
    }

   public void requestDatas(int pageIndex) {
        Disposable disposable = mHomeModel.getHomeList(0).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<JsonRootBean>() {
                    @Override
                    public void accept(JsonRootBean jsonRootBean) throws Throwable {
                        System.out.println(jsonRootBean.toString());
                        mDatas.setValue(jsonRootBean.getData().getDatas());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        throwable.printStackTrace();
                    }
                });

        mDisposable.add(disposable);
    }


   public void clearRequest(){
        mDisposable.clear();
    }

}