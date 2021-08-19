package com.dbtechprojects.gamedealsjava.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dbtechprojects.gamedealsjava.databinding.ActivityGameDealBinding;
import com.dbtechprojects.gamedealsjava.databinding.FragmentGameBinding;
import com.dbtechprojects.gamedealsjava.models.Game;

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
        setupView(game);

    }

    private void setupView(Game game){
        binding.GameDealTitle.setText(game.external);
        binding.GameDealPriceTextView.setText(game.cheapest);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
