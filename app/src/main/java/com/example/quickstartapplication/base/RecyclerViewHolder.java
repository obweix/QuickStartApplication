package com.example.quickstartapplication.base;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    public ViewDataBinding binding;

    public RecyclerViewHolder(ViewDataBinding binding){
        super(binding.getRoot());
        this.binding = binding;
    }
}
