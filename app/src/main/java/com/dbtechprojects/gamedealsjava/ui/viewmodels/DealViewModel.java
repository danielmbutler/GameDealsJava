package com.dbtechprojects.gamedealsjava.ui.viewmodels;
import androidx.lifecycle.ViewModel;
import com.dbtechprojects.gamedealsjava.models.Game;
import com.dbtechprojects.gamedealsjava.repository.RepositoryImpl;
import com.dbtechprojects.gamedealsjava.utils.SingleLiveEvent;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DealViewModel extends ViewModel {

    // setup repository
    private final RepositoryImpl repository = RepositoryImpl.getInstance();

    // disposable
    private  final CompositeDisposable disposable = new CompositeDisposable();
    // liveData (only emitted once)
    public SingleLiveEvent<String> dbMessages = new SingleLiveEvent<>();


    public void saveGame(Game game) {
       Disposable saveGame = repository.saveGame(game)
               .observeOn(AndroidSchedulers.mainThread())
               .subscribeOn(Schedulers.io())
               .doOnComplete(() -> dbMessages.postValue("Game saved"))
               .doOnError(throwable -> {
                   dbMessages.postValue("Error saving game");
               })
               .subscribe();

       disposable.add(saveGame);

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}

