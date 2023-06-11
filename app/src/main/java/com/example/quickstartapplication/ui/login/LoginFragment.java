package com.example.quickstartapplication.ui.login;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.quickstartapplication.databinding.FragmentLoginBinding;
import com.example.quickstartapplication.ui.base.BaseFragment;
import com.example.quickstartapplication.utils.LogUtil;

public class LoginFragment extends BaseFragment {
    FragmentLoginBinding mFragmentLoginBinding;
    LoginViewModel mLoginViewModel;


    public LoginFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mLoginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        mFragmentLoginBinding = FragmentLoginBinding.inflate(inflater,container,false);
        mFragmentLoginBinding.setUser(new User());
        mFragmentLoginBinding.setCallback(user->{
            LogUtil.log("user = "+ user.toString());
        });
        mFragmentLoginBinding.setLifecycleOwner(this);

        return mFragmentLoginBinding.getRoot();
    }
}
