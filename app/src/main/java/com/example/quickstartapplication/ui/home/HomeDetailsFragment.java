package com.example.quickstartapplication.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.quickstartapplication.databinding.FragmentHomeDetailsBinding;

public class HomeDetailsFragment extends Fragment {
    FragmentHomeDetailsBinding mBinding;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentHomeDetailsBinding.inflate(inflater,container,false);
        String url = getArguments().getString(HomeFragment.LINK_KEY);
        String title = getArguments().getString(HomeFragment.TITLE_KEY);
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
