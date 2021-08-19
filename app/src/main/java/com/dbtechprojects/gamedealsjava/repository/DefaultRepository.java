package com.dbtechprojects.gamedealsjava.repository;

import com.dbtechprojects.gamedealsjava.models.Game;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface DefaultRepository {

    Single<List<Game>> getGameList(String query);
    Completable saveGame(Game game);
}
