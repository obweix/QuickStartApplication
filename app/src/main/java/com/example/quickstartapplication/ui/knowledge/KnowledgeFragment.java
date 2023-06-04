package com.example.quickstartapplication.ui.knowledge;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Placeholder;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.quickstartapplication.R;
import com.example.quickstartapplication.databinding.FragmentKnowledgeBinding;
import com.example.quickstartapplication.databinding.LayoutAppBarBinding;
import com.example.quickstartapplication.network.bean.FilteredArticles;
import com.example.quickstartapplication.ui.base.BaseFragment;

import java.util.List;

public class KnowledgeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    public static final String LINK_KEY = "link_key";
    public static final String TITLE_KEY = "title_key";

    private FragmentKnowledgeBinding mFragmentKnowledgeBinding;
    private NavigateDataAdapter mNavigateDataAdapter;

    KnowledgeViewModel mKnowledgeViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mKnowledgeViewModel = new ViewModelProvider(this).get(KnowledgeViewModel.class);
        mNavigateDataAdapter = new NavigateDataAdapter(articles -> {
               Bundle bundle = new Bundle();
                bundle.putString(LINK_KEY, articles.getLink());
                bundle.putString(TITLE_KEY,articles.getTitle());
                navigate(getView(),R.id.navigation_knowledge,R.id.navgation_home_details,bundle);
        });

        mFragmentKnowledgeBinding = FragmentKnowledgeBinding.inflate(inflater,container,false);

        // because the usage of <merge>
        LayoutAppBarBinding layoutAppBarBinding = LayoutAppBarBinding.bind(mFragmentKnowledgeBinding.getRoot());
        layoutAppBarBinding.tvTitle.setText(getString(R.string.knowledge));

        mFragmentKnowledgeBinding.rvArticles.setAdapter(mNavigateDataAdapter);
        mFragmentKnowledgeBinding.swipeRefreshLayout.setOnRefreshListener(this);

        if(mKnowledgeViewModel.getFilteredArticles().getValue() == null){
          requestArticles();
        }


        mKnowledgeViewModel.getFilteredArticles().observe(this, new Observer<List<FilteredArticles>>() {
            @Override
            public void onChanged(List<FilteredArticles> filteredArticles) {
                if(filteredArticles == null){
                    mFragmentKnowledgeBinding.tvLoadState.setText(R.string.error);
                    mFragmentKnowledgeBinding.tvLoadState.setVisibility(View.VISIBLE);
                    return;
                }
                mNavigateDataAdapter.submitList(filteredArticles);
                mFragmentKnowledgeBinding.tvLoadState.setVisibility(View.GONE);
                mFragmentKnowledgeBinding.swipeRefreshLayout.setRefreshing(false);
            }

        });

        return mFragmentKnowledgeBinding.getRoot();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mKnowledgeViewModel.clearRequest();
    }

    private void requestArticles(){
       mKnowledgeViewModel.requestArticles();
       mFragmentKnowledgeBinding.tvLoadState.setText(R.string.loading);
       mFragmentKnowledgeBinding.tvLoadState.setVisibility(View.VISIBLE);
   }

    @Override
    public void onRefresh() {
        requestArticles();
    }
}
