package com.dbtechprojects.gamedealsjava.ui.viewmodels;

import androidx.lifecycle.ViewModel;

import com.dbtechprojects.gamedealsjava.repository.RepositoryImpl;

public class HomeViewModel extends ViewModel {

    // setup repository
    private final RepositoryImpl repository = RepositoryImpl.getInstance();


}
