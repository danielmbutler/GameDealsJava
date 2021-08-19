package com.dbtechprojects.gamedealsjava.ui.viewmodels;
import androidx.lifecycle.ViewModel;
import com.dbtechprojects.gamedealsjava.models.Game;
import com.dbtechprojects.gamedealsjava.repository.RepositoryImpl;
import com.dbtechprojects.gamedealsjava.utils.SingleLiveEvent;
import io.reactivex.Completable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DealViewModel extends ViewModel {

    // setup repository
    private final RepositoryImpl repository = RepositoryImpl.getInstance();

    // disposable
    private Disposable disposable;

    // liveData (only emitted once)
    public SingleLiveEvent<String> dbMessages = new SingleLiveEvent<>();


    public void saveGame(Game game) {
       disposable =  Completable.fromAction(() -> repository
                .saveGame(game))
                .subscribeOn(Schedulers.io())
                .doOnError(throwable -> dbMessages.postValue("Error saving game"))
                .doOnComplete(() -> dbMessages.postValue("Game saved"))
                .subscribe();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}

