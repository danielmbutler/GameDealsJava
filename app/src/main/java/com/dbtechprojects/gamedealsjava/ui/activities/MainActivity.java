package com.dbtechprojects.gamedealsjava.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import android.os.Bundle;
import android.view.View;
import com.dbtechprojects.gamedealsjava.R;
import com.dbtechprojects.gamedealsjava.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setupNavigation();
    }

    // setup bottom nav
    private void setupNavigation(){
        BottomNavigationView navigationView = binding.Bottomnavigation;
        //Initialize NavController.
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setOnNavigationItemReselectedListener(item -> {
            // do nothing
            // stops fragment recreation
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}