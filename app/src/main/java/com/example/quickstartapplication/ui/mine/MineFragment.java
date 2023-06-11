package com.example.quickstartapplication.ui.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.quickstartapplication.R;
import com.example.quickstartapplication.databinding.FragmentMineBinding;
import com.example.quickstartapplication.ui.base.BaseFragment;


public class MineFragment extends BaseFragment {

    private FragmentMineBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MineViewModel mineViewModel =
                new ViewModelProvider(this).get(MineViewModel.class);

        binding = FragmentMineBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.ivAvatar.setOnClickListener(view -> {
            navigate(getView(), R.id.navigation_mine,R.id.navigation_login,null);
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}