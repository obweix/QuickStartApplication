package com.example.quickstartapplication.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.LoadState;
import androidx.paging.PagingData;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.quickstartapplication.databinding.FragmentHomeBinding;
import com.example.quickstartapplication.model.HomeModel;
import com.example.quickstartapplication.network.bean.Datas;
import com.example.quickstartapplication.network.bean.JsonRootBean;
import com.example.quickstartapplication.utils.LogUtil;

import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

public class HomeFragment extends Fragment {
    private static final String TAG = HomeFragment.class.getSimpleName();

    private FragmentHomeBinding mBinding;

   private DatasAdapter mDatasAdapter;
    private final CompositeDisposable mDisposable = new CompositeDisposable();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        mBinding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = mBinding.getRoot();

        final TextView textView = mBinding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);


        mDatasAdapter = new DatasAdapter((data)->{
            Log.d(TAG, "onCreateView: item click,datas = " + data.toString());
        });

        mDatasAdapter.addLoadStateListener(loadStates -> {
            Log.d(TAG, "onCreateView: " +loadStates.toString());
          if(loadStates.getRefresh() instanceof LoadState.Loading){
              Log.d(TAG, "onCreateView: 1");
             mBinding.tvState.setVisibility(View.VISIBLE);
             mBinding.tvState.setText("loading");
          }else if(loadStates.getRefresh() instanceof  LoadState.Error){
              Log.d(TAG, "onCreateView: 2");
             mBinding.tvState.setVisibility(View.VISIBLE);
             mBinding.tvState.setText("network error");
          }else {
              mBinding.tvState.setVisibility(View.GONE);
          }
            return null;
        });

        mBinding.rvHome.setAdapter(mDatasAdapter
                .withLoadStateFooter(new DatasLoadStateAdapter(view -> mDatasAdapter.retry())));

        subscribeToMode(homeViewModel);

        return root;
    }

    private void subscribeToMode(final HomeViewModel model){
      Disposable disposable = model.mDatasFlowable.subscribe(new Consumer<PagingData<Datas>>() {
           @Override
           public void accept(PagingData<Datas> datasPagingData) throws Throwable {
               mDatasAdapter.submitData(getLifecycle(),datasPagingData);
           }
       });

      mDisposable.add(disposable);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        mBinding = null;
        mDisposable.clear();
        super.onDestroyView();
    }
}