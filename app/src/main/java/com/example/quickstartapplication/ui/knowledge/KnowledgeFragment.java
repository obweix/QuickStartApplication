package com.example.quickstartapplication.ui.knowledge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.quickstartapplication.databinding.FragmentKnowledgeBinding;

public class KnowledgeFragment extends Fragment {
    private FragmentKnowledgeBinding mFragmentKnowledgeBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        KnowledgeViewModel knowledgeViewModel = new ViewModelProvider(this).get(KnowledgeViewModel.class);

        mFragmentKnowledgeBinding = FragmentKnowledgeBinding.inflate(inflater,container,false);

        return mFragmentKnowledgeBinding.getRoot();

    }
}
