package com.dbtechprojects.gamedealsjava.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.dbtechprojects.gamedealsjava.R;
import com.dbtechprojects.gamedealsjava.databinding.GameItemBinding;
import com.dbtechprojects.gamedealsjava.models.Game;
import com.dbtechprojects.gamedealsjava.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.RVViewHolder> {
    private List<Game> dataSet = new ArrayList<>();
    private final onClickListener clickListener;

    public GameListAdapter(
            onClickListener clickListener
    ) {
        this.clickListener = clickListener;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setDataSet(List<Game> value) {
        dataSet = value;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return RVViewHolder.getViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RVViewHolder holder, int position) {
        holder.bind(dataSet.get(position), clickListener);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class RVViewHolder extends RecyclerView.ViewHolder {
        public GameItemBinding binding;

        RVViewHolder(@NonNull GameItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public static RVViewHolder getViewHolder(ViewGroup parent) {
            GameItemBinding binding = GameItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

            return new RVViewHolder(binding);
        }

        @SuppressLint("SetTextI18n")
        public void bind(Game currentItem, onClickListener clickListener) {
            binding.GameItemPrice.setText(currentItem.cheapest);
            binding.GameItemTitle.setText(currentItem.external);
            binding.GameItemArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onClick(currentItem);
                }
            });

            // load image
            ImageLoader
            .getSharedInstance(itemView.getContext())
            .load(currentItem.thumb).placeholder(R.drawable.ic_baseline_search_placeholder)
            .into(binding.SavedRowGameThumbnail);
        }

    }

    public interface onClickListener{
        void onClick(Game game);
    }
}

