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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.quickstartapplication.databinding.FragmentHomeBinding;
import com.example.quickstartapplication.model.HomeModel;
import com.example.quickstartapplication.network.bean.Datas;
import com.example.quickstartapplication.network.bean.JsonRootBean;

import java.util.List;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

public class HomeFragment extends Fragment {
    private static final String TAG = HomeFragment.class.getSimpleName();

    private FragmentHomeBinding mBinding;
    
    private DatasAdapter mDatasAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mDatasAdapter = new DatasAdapter((data)->{
            Log.d(TAG, "onCreateView: item click,datas = " + data.toString());
        });

        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        mBinding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = mBinding.getRoot();

        final TextView textView = mBinding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        mBinding.rvHome.setAdapter(mDatasAdapter);

        subscribeToMode(homeViewModel);

        return root;
    }

    private void subscribeToMode(final HomeViewModel model){
       if(model.getDatas(0).getValue() == null ){
           model.requestDatas(0);
       }

        model.getDatas(0).observe(this, new Observer<List<Datas>>() {
            @Override
            public void onChanged(List<Datas> datas) {
                if(datas != null){
                    mDatasAdapter.submitList(datas);
                }else{
                }
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onDestroyView() {
        mBinding = null;
        mDatasAdapter = null;
        super.onDestroyView();
    }
}