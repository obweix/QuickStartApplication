package com.example.quickstartapplication.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quickstartapplication.model.HomeModel;
import com.example.quickstartapplication.network.bean.Datas;
import com.example.quickstartapplication.network.bean.JsonRootBean;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<List<Datas>> mDatas;

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mDatas = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }


    HomeModel homeModel = new HomeModel();

    public LiveData<List<Datas>> getDatas(int pageIndex){
        return mDatas;
    }

   public void requestDatas(int pageIndex) {
        Disposable disposable = homeModel.getHomeList(0).subscribeOn(Schedulers.io())
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