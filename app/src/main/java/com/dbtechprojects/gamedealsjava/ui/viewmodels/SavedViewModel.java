package com.dbtechprojects.gamedealsjava.ui.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dbtechprojects.gamedealsjava.models.Game;
import com.dbtechprojects.gamedealsjava.repository.RepositoryImpl;
import com.dbtechprojects.gamedealsjava.utils.SingleLiveEvent;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SavedViewModel extends ViewModel {

    // setup repository
    private final RepositoryImpl repository = RepositoryImpl.getInstance();

    // disposable
    private final CompositeDisposable disposable = new CompositeDisposable();

    //LiveData to be observed in Fragment
    private final MutableLiveData<List<Game>> _gamesList = new MutableLiveData<>();
    public LiveData<List<Game>> gamesList = _gamesList;

    public SingleLiveEvent<String> dbMessages = new SingleLiveEvent<>();

    public SavedViewModel() {
        getGames();
    }

    public void getGames() {
        Disposable subscription = repository.getSavedGames()
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

    public void deleteGame(Game game) {
        Log.d("SavedViewModel", "delete " + game.external);

        Disposable deleteGame = repository.deleteGame(game)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnComplete(() -> dbMessages.postValue("Game deleted"))
                .doOnError(throwable -> dbMessages.postValue("Error deleting game"))
                .subscribe();

        disposable.add(deleteGame);

    }
}