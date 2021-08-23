package com.dbtechprojects.gamedealsjava.ui.adapters;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.dbtechprojects.gamedealsjava.R;
import com.dbtechprojects.gamedealsjava.databinding.SavedGameItemBinding;
import com.dbtechprojects.gamedealsjava.models.Game;
import com.dbtechprojects.gamedealsjava.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class SavedGameListAdapter extends RecyclerView.Adapter<SavedGameListAdapter.RVViewHolder> {
    private List<Game> dataSet = new ArrayList<>();
    private final onClickListener clickListener;

    public SavedGameListAdapter(
            onClickListener clickListener
    ) {
        this.clickListener = clickListener;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setDataSet(List<Game> value) {
        dataSet = value;
        notifyDataSetChanged();
    }

    public void removeItemAtPosition(int position){
        dataSet.remove(position);
        notifyDataSetChanged();
    }
    public int getListSize(){
        return dataSet.size();
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
        public SavedGameItemBinding binding;

        RVViewHolder(@NonNull SavedGameItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public static RVViewHolder getViewHolder(ViewGroup parent) {
            SavedGameItemBinding binding = SavedGameItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

            return new RVViewHolder(binding);
        }

        @SuppressLint("SetTextI18n")
        public void bind(Game currentItem, onClickListener clickListener) {
            binding.GameItemPrice.setText("$" + currentItem.cheapest);
            binding.GameItemTitle.setText(currentItem.external);
            binding.GameItemDelete.setOnClickListener(v -> clickListener.onClick(currentItem, getAdapterPosition()));

            // load image
            ImageLoader
            .getSharedInstance(itemView.getContext())
            .load(currentItem.thumb).placeholder(R.drawable.ic_baseline_search_placeholder)
            .into(binding.SavedRowGameThumbnail);
        }

    }

    public interface onClickListener{
        void onClick(Game game, int position);
    }
}

