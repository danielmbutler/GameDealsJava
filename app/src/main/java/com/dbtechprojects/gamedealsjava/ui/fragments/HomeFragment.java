package com.dbtechprojects.gamedealsjava.ui.fragments;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.dbtechprojects.gamedealsjava.databinding.FragmentGameBinding;
import com.dbtechprojects.gamedealsjava.models.Game;
import com.dbtechprojects.gamedealsjava.ui.adapters.GameListAdapter;
import com.dbtechprojects.gamedealsjava.ui.viewmodels.HomeViewModel;

public class HomeFragment extends Fragment implements GameListAdapter.onClickListener {

    private FragmentGameBinding binding;
    private HomeViewModel viewModel;
    private GameListAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentGameBinding.inflate(inflater);
        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRV();
        initObservers();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initObservers() {
        viewModel.gamesList.observe(getViewLifecycleOwner(), games -> {
            Log.d("HomeFragment", "found games");
            if (adapter != null && !games.isEmpty()){
                adapter.setDataSet(games);
                hidePlaceHolder();
            }
        });
    }

    private void setupRV(){
        adapter = new GameListAdapter(this);
        binding.GamesRecyclerView.setAdapter(adapter);
    }

    private void hidePlaceHolder(){
        binding.GameFragmentPlaceholderImage.setVisibility(View.GONE);
        binding.GameFragmentPlaceholderText.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(Game game) {
        Log.d("HomeFragment", "game click " + game.external);
        // Navigate to gamedeal fragment
        NavHostFragment.findNavController(this)
                .navigate(HomeFragmentDirections.actionHomeFragmentToDealFragment(game));
    }
}
