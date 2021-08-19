package com.dbtechprojects.gamedealsjava.repository;

import com.dbtechprojects.gamedealsjava.models.Game;

import java.util.List;

import io.reactivex.Single;

public interface DefaultRepository {

    Single<List<Game>> getGameList(String query);
    void saveGame(Game game);
}
