package com.dbtechprojects.gamedealsjava.ui.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dbtechprojects.gamedealsjava.models.Game;
import com.dbtechprojects.gamedealsjava.repository.RepositoryImpl;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SavedViewModel extends ViewModel {

    // setup repository
    private final RepositoryImpl repository = RepositoryImpl.getInstance();

    // disposable
    private  final CompositeDisposable disposable = new CompositeDisposable();

    //LiveData to be observed in Fragment
    private final MutableLiveData<List<Game>> _gamesList = new MutableLiveData<List<Game>>();
    public LiveData<List<Game>> gamesList = _gamesList;

    public SavedViewModel(){
        getGames();
    }

    public void getGames() {
        Disposable subscription = repository.getSavedGames()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(games -> Log.d("ViewModel", "found games " + games));

        disposable.add(subscription);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}