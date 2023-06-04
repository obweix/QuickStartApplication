package com.example.quickstartapplication.ui.knowledge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.quickstartapplication.databinding.FragmentHomeDetailsBinding;
import com.example.quickstartapplication.ui.home.HomeFragment;

public class KnowledgeDetailsFragment extends Fragment {
    FragmentHomeDetailsBinding mBinding;

    KnowledgeDetailsFragment(){

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding = FragmentHomeDetailsBinding.inflate(inflater,container,false);
        String url = getArguments().getString(KnowledgeFragment.LINK_KEY);
        String title = getArguments().getString(KnowledgeFragment.TITLE_KEY);
        mBinding.webview.loadUrl(url);
        mBinding.tvHomeDetailsTitle.setText(title);
        mBinding.pbLoading.setMax(100);

        mBinding.webview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mBinding.pbLoading.setProgress(newProgress);

                if(newProgress == 100){
                    mBinding.pbLoading.setVisibility(View.GONE);
                }
            }
        });

        return mBinding.getRoot();
    }


}
