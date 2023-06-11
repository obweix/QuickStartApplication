package com.example.quickstartapplication.ui.login;

import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;


public class User extends BaseObservable {

    private String mUserName;
    private String mPassword;

    @Bindable
    public String getUserName(){
        return mUserName;
    }

    public void setUserName(String  userName){
        Log.d("test", "setUserName: ");
        mUserName = userName;
        notifyPropertyChanged(BR.userName);
    }

    public void setPassword(String password){
        mPassword = password;
        Log.d("test", "setPassword: ");
        notifyPropertyChanged(BR.password);
    }

    @Bindable
    public String getPassword(){
        return mPassword;
    }

    public interface OnItemClickListener {
        void onItemClick(User item);
    }

    @Override
    public String toString() {
        return "User{" +
                "mUserName='" + mUserName + '\'' +
                ", mPassword='" + mPassword + '\'' +
                '}';
    }
}
