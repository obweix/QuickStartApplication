package com.example.quickstartapplication.ui.knowledge;

import static com.example.quickstartapplication.ui.knowledge.KnowledgeFragment.LINK_KEY;
import static com.example.quickstartapplication.ui.knowledge.KnowledgeFragment.TITLE_KEY;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickstartapplication.R;
import com.example.quickstartapplication.databinding.ItemArticlesBinding;
import com.example.quickstartapplication.network.bean.Articles;
import com.example.quickstartapplication.network.bean.FilteredArticles;
import com.example.quickstartapplication.utils.LogUtil;
import com.example.quickstartapplication.utils.Utils;
import com.google.android.flexbox.FlexboxLayout;

import java.util.List;

public class NavigateDataAdapter extends ListAdapter<FilteredArticles, NavigateDataAdapter.NavigateDataViewHolder>{
    private static final String TAG = NavigateDataAdapter.class.getSimpleName();

    protected NavigateDataAdapter() {
        super(new DiffUtil.ItemCallback<FilteredArticles>() {
            @Override
            public boolean areItemsTheSame(@NonNull FilteredArticles oldItem, @NonNull FilteredArticles newItem) {
                return false;
            }

            @Override
            public boolean areContentsTheSame(@NonNull FilteredArticles oldItem, @NonNull FilteredArticles newItem) {
                return false;
            }
        });

    }


    @NonNull
    @Override
    public NavigateDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemArticlesBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.item_articles,
                        parent, false);

        return new NavigateDataViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NavigateDataViewHolder holder, int position) {
        holder.binding.tvTitle.setText(getItem(position).mTitle);
        final List<Articles> articlesList = getItem(position).mArticles;
        for (int i = 0; i < articlesList.size(); i++) {
            TextView textView = new TextView(holder.itemView.getContext());
            textView.setText(articlesList.get(i).getTitle());
            textView.setId(i);
            textView.setBackgroundResource(R.drawable.bg_item);
            FlexboxLayout.LayoutParams layoutParams = new FlexboxLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            int marginValue = Utils.dip2px(holder.itemView.getContext(),16);
            layoutParams.setMargins(marginValue,0,marginValue,marginValue/2);
            textView.setLayoutParams(layoutParams);
            textView.setOnClickListener(v -> {
                Articles articles = articlesList.get(v.getId());
                Bundle bundle = new Bundle();
                bundle.putString(LINK_KEY, articles.getLink());
                bundle.putString(TITLE_KEY,articles.getTitle());
                Navigation.findNavController(v).navigate(R.id.navgation_home_details,bundle);
            } );

            holder.binding.flexContainer.addView(textView);

        }
    }



    static class NavigateDataViewHolder extends RecyclerView.ViewHolder {
        public ItemArticlesBinding binding;

        public NavigateDataViewHolder(@NonNull ItemArticlesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
