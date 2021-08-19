package com.dbtechprojects.gamedealsjava.ui.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dbtechprojects.gamedealsjava.R;
import com.dbtechprojects.gamedealsjava.databinding.ActivityGameDealBinding;
import com.dbtechprojects.gamedealsjava.models.Game;
import com.dbtechprojects.gamedealsjava.utils.ImageLoader;

public class DealFragment extends Fragment {

    private ActivityGameDealBinding binding;
    private Game game;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ActivityGameDealBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        game = DealFragmentArgs.fromBundle(getArguments()).getGame();
        setupClicks();
        setupView(game);

    }

    private void setupClicks() {
        binding.GameDealGetDealButton.setOnClickListener(v -> browseDeal());
    }

    private void setupView(Game game) {
        binding.GameDealTitle.setText(game.external);
        binding.GameDealPriceTextView.setText(game.cheapest);
        // load image
        ImageLoader
                .getSharedInstance(requireContext())
                .load(game.thumb).placeholder(R.drawable.ic_baseline_search_placeholder)
                .into(binding.GameDealimageView);
    }

    private void browseDeal() {
        String deal = game.cheapestDealID;
        Intent browserIntent = new Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.cheapshark.com/redirect?dealID=" + deal)
        );
        startActivity(browserIntent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
