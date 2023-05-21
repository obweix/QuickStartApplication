package com.example.quickstartapplication.ui.home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.LoadState;
import androidx.paging.LoadStateAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickstartapplication.R;
import com.example.quickstartapplication.databinding.ItemLoadStateBinding;
import com.example.quickstartapplication.network.bean.Datas;

public class DatasLoadStateAdapter extends LoadStateAdapter<DatasLoadStateAdapter.LoadStateViewHolder> {
    private static final String TAG = DatasLoadStateAdapter.class.getSimpleName();

    // Define Retry Callback
    private View.OnClickListener mRetryCallback;


    public DatasLoadStateAdapter(View.OnClickListener retryCallback) {
        // Init Retry Callback
        mRetryCallback = retryCallback;
    }

    @Override
    public void onBindViewHolder(@NonNull LoadStateViewHolder holder, @NonNull LoadState loadState) {
        // Call Bind Method to bind visibility of views
        holder.bind(loadState);
    }

    @NonNull
    @Override
    public LoadStateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, @NonNull LoadState loadState) {
        ItemLoadStateBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.item_load_state,
                        parent, false);

        return new LoadStateViewHolder(binding,mRetryCallback);
    }




    static class LoadStateViewHolder extends RecyclerView.ViewHolder{
        public ItemLoadStateBinding binding;

        public LoadStateViewHolder(@NonNull ItemLoadStateBinding binding,View.OnClickListener retryCallback) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.retryButton.setOnClickListener(retryCallback);

        }

        public void bind(LoadState loadState) {
            // Check load state

            if (loadState instanceof LoadState.Error) {
                // Get the error
                LoadState.Error loadStateError = (LoadState.Error) loadState;
                // Set text of Error message
                binding.errorMsg.setText(loadStateError.getError().getLocalizedMessage());
            }
            // set visibility of widgets based on LoadState
            binding.progressBar.setVisibility(loadState instanceof LoadState.Loading
                    ? View.VISIBLE : View.GONE);

            binding.errorMsg.setVisibility(loadState instanceof LoadState.Error
                    ? View.VISIBLE : View.GONE);

            binding.retryButton.setVisibility(loadState instanceof LoadState.Error
                    ? View.VISIBLE : View.GONE);
        }
    }
}
