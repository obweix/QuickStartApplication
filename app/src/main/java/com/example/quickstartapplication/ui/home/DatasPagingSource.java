package com.example.quickstartapplication.ui.home;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingSource;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.RxPagingSource;

import com.example.quickstartapplication.model.IHomeModel;
import com.example.quickstartapplication.network.bean.Data;
import com.example.quickstartapplication.network.bean.Datas;
import com.example.quickstartapplication.network.bean.JsonRootBean;
import com.example.quickstartapplication.network.service.HomeService;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DatasPagingSource extends RxPagingSource<Integer, Datas> {
    private static final String TAG = DatasPagingSource.class.getSimpleName();
    IHomeModel mHomeModel;

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    DatasPagingSource(IHomeModel homeModel) {
        mHomeModel = homeModel;
    }

    @Nullable
    @Override
    public Integer getRefreshKey(@NonNull PagingState<Integer, Datas> pagingState) {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        Integer anchorPosition = pagingState.getAnchorPosition();
        if (anchorPosition == null) {
            return null;
        }

        LoadResult.Page<Integer, Datas> anchorPage = pagingState.closestPageToPosition(anchorPosition);
        if (anchorPage == null) {
            return null;
        }

        Integer prevKey = anchorPage.getPrevKey();
        if (prevKey != null) {
            return prevKey + 1;
        }

        Integer nextKey = anchorPage.getNextKey();
        if (nextKey != null) {
            return nextKey - 1;
        }

        return null;
    }


    @NonNull
    @Override
    public Single<LoadResult<Integer, Datas>> loadSingle(@NonNull LoadParams<Integer> loadParams) {

        // If page number is already there then init page variable with it otherwise we are loading fist page
        int page = loadParams.getKey() != null ? loadParams.getKey() : 0;

        return  mHomeModel.getHomeList(page)
                .subscribeOn(Schedulers.io())
                .map(this::toLoadResult)
                .first(new LoadResult.Error<>(new Throwable("this is an error")))
                .onErrorReturn(LoadResult.Error::new);
    }


    private LoadResult<Integer,Datas> toLoadResult(JsonRootBean response){
        return new LoadResult.Page<>(response.getData().getDatas(),
                null, // Only paging forward.
                response.getData().getCurPage() + 1,
                LoadResult.Page.COUNT_UNDEFINED,
                LoadResult.Page.COUNT_UNDEFINED);
    }
}
