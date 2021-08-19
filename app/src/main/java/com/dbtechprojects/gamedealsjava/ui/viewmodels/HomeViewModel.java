package com.dbtechprojects.gamedealsjava.ui.viewmodels;

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


public class HomeViewModel extends ViewModel {

    // setup repository
    private final RepositoryImpl repository = RepositoryImpl.getInstance();

    // disposable
    private  final CompositeDisposable disposable = new CompositeDisposable();

    //LiveData to be observed in Fragment
    private final MutableLiveData<List<Game>> _gamesList = new MutableLiveData<List<Game>>();
    public LiveData<List<Game>> gamesList = _gamesList;

    public void getGames(String query) {
        Disposable subscription = repository.getGameList(query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(_gamesList::postValue);

        disposable.add(subscription);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}
