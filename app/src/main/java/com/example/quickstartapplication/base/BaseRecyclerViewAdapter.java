package com.example.quickstartapplication.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {
    private List<T> mData = new ArrayList<>();

    private OnItemClickListener mClickListener;
    private OnItemLongClickListener mLongClickListener;




    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          ViewDataBinding viewDataBinding = DataBindingUtil
                  .inflate(LayoutInflater.from(parent.getContext()), getItemLayoutId(viewType),
                          parent, false);
        RecyclerViewHolder holder = new RecyclerViewHolder(viewDataBinding);
        if (mClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onItemClick(holder.itemView,holder.getLayoutPosition());
                }
            });
        }
        if (mLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mLongClickListener.onItemLongClick(holder.itemView, holder.getLayoutPosition());
                    return true;
                }
            });
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        bindViewHolder(holder,position,mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    abstract public int getItemLayoutId(int viewType);

    abstract public void bindViewHolder(@NonNull RecyclerViewHolder holder, int position, @NonNull T item);


    public interface OnItemClickListener {
        void onItemClick(View itemView, int pos);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View itemView, int pos);
    }
}
