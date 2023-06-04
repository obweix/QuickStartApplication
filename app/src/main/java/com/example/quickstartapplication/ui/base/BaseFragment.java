package com.example.quickstartapplication.ui.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import com.example.quickstartapplication.R;


public abstract class BaseFragment extends Fragment {


    public void navigate(View view, @IdRes int from, @IdRes int to, @Nullable Bundle args){
        NavOptions navOptions = new NavOptions.Builder()
                .setEnterAnim(R.anim.slide_in_right)
                .setPopEnterAnim(R.anim.slide_in_left)
                .setExitAnim(R.anim.slide_out_left)
                .setPopExitAnim(R.anim.slide_out_right)
                .setPopUpTo(from,false)
                .build();

            Navigation.findNavController(view).navigate(to,args,navOptions);

    }
}
