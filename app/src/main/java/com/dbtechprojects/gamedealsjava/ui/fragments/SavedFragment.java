package com.dbtechprojects.gamedealsjava.ui.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.dbtechprojects.gamedealsjava.databinding.FragmentSavedDealsBinding;
import com.dbtechprojects.gamedealsjava.models.Game;
import com.dbtechprojects.gamedealsjava.ui.adapters.SavedGameListAdapter;
import com.dbtechprojects.gamedealsjava.ui.viewmodels.SavedViewModel;
import com.dbtechprojects.gamedealsjava.utils.ViewUtils;

public class SavedFragment extends Fragment implements SavedGameListAdapter.onClickListener {

    private FragmentSavedDealsBinding binding;
    private SavedViewModel viewModel;
    private SavedGameListAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(SavedViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSavedDealsBinding.inflate(inflater);
        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
        initObservers();

    }

    private void setupRecyclerView() {
        adapter = new SavedGameListAdapter(this);
        binding.SavedGamesRecyclerView.setAdapter(adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initObservers() {
        viewModel.gamesList.observe(getViewLifecycleOwner(), games -> {
            if (!games.isEmpty() && adapter != null){
                showPlaceholder(false);
                adapter.setDataSet(games);
            }
        });

        viewModel.dbMessages.observe(getViewLifecycleOwner(), message -> {
            if (message != null  && !message.isEmpty()){
                ViewUtils.showSnackBar(binding.getRoot(), requireActivity(), message);
            }
        });
    }

    private void showPlaceholder(boolean shouldShow) {
        if (shouldShow){
            binding.SavedGamesPlaceHolderImage.setVisibility(View.VISIBLE);
            binding.textView.setVisibility(View.VISIBLE);
        } else {
            binding.SavedGamesPlaceHolderImage.setVisibility(View.GONE);
            binding.textView.setVisibility(View.GONE);
        }
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void onClick(Game game, int position) {
        new AlertDialog.Builder(requireContext())
                .setTitle("Delete " + game.external)
                .setMessage("Are you sure you want to delete ?")

                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.removeItemAtPosition(position);
                        viewModel.deleteGame(game);
                        if (adapter.getListSize() == 0 ){
                            showPlaceholder(true);
                        }
                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }
}
