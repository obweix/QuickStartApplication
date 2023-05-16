package com.example.quickstartapplication.ui.home;

import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickstartapplication.R;
import com.example.quickstartapplication.base.BaseRecyclerViewAdapter;
import com.example.quickstartapplication.databinding.ItemHomeListBinding;
import com.example.quickstartapplication.network.bean.Data;
import com.example.quickstartapplication.network.bean.Datas;

import java.util.Random;

public class DatasAdapter extends ListAdapter<Datas, DatasAdapter.DatasViewHolder> {
    private static final String TAG = DatasAdapter.class.getSimpleName();
    private OnItemClickListener mClickListener;
    private OnItemLongClickListener mLongClickListener;

    protected DatasAdapter(OnItemClickListener onItemClickListener) {
        super(new DiffUtil.ItemCallback<Datas>() {
            @Override
            public boolean areItemsTheSame(@NonNull Datas oldItem, @NonNull Datas newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Datas oldItem, @NonNull Datas newItem) {
                return oldItem.getId() == newItem.getId()
                        && TextUtils.equals(oldItem.getTitle(),newItem.getTitle());
            }
        });

        this.mClickListener = onItemClickListener;
    }



    @NonNull
    @Override
    public DatasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHomeListBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.item_home_list,
                        parent, false);

        binding.setCallback(mClickListener);

        return new DatasViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DatasViewHolder holder, int position) {
        holder.binding.setData(getItem(position));
        holder.binding.executePendingBindings();
        holder.binding.llContainer.setBackgroundColor(getRandomColor());
    }

    static class DatasViewHolder extends RecyclerView.ViewHolder{
        public ItemHomeListBinding binding;

        public DatasViewHolder(@NonNull ItemHomeListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            if(null != binding.getData()){
                String title = binding.getData().getTitle();
                Log.d(TAG, "DatasViewHolder: title = " + title);
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Datas item);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View itemView, int pos);
    }

    public int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(200, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}
