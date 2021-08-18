package com.dbtechprojects.gamedealsjava.repository;

import com.dbtechprojects.gamedealsjava.models.Game;

import java.util.List;

public interface DefaultRepository {

    List<Game> getGames();
    void saveGame(Game game);
}
