package com.example.quickstartapplication.ui.home;

import androidx.annotation.NonNull;

import com.example.quickstartapplication.R;
import com.example.quickstartapplication.base.BaseRecyclerViewAdapter;
import com.example.quickstartapplication.base.RecyclerViewHolder;
import com.example.quickstartapplication.network.bean.Datas;

public class DatasListAdapter extends BaseRecyclerViewAdapter<Datas> {

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.item_home_list;
    }

    @Override
    public void bindViewHolder(@NonNull RecyclerViewHolder holder, int position, @NonNull Datas item) {
    }
}
