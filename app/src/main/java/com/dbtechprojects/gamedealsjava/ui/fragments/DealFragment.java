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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.dbtechprojects.gamedealsjava.R;
import com.dbtechprojects.gamedealsjava.databinding.ActivityGameDealBinding;
import com.dbtechprojects.gamedealsjava.models.Game;
import com.dbtechprojects.gamedealsjava.ui.viewmodels.DealViewModel;
import com.dbtechprojects.gamedealsjava.utils.ImageLoader;
import com.dbtechprojects.gamedealsjava.utils.ViewUtils;

public class DealFragment extends Fragment {

    private ActivityGameDealBinding binding;
    private DealViewModel viewModel;
    private Game game;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(DealViewModel.class);
    }

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
        initObservers();

    }

    private void initObservers() {
        viewModel.dbMessages.observe(getViewLifecycleOwner(), message -> {
            if (message != null  && !message.isEmpty()){
                ViewUtils.showSnackBar(binding.getRoot(), requireActivity(), message);
            }
        });
    }

    private void setupClicks() {
        binding.GameDealGetDealButton.setOnClickListener(v -> browseDeal());
        binding.GameDealSaveDealButton.setOnClickListener(v -> viewModel.saveGame(game));
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
