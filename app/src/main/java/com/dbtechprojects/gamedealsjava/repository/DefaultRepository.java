package com.dbtechprojects.gamedealsjava.repository;

import com.dbtechprojects.gamedealsjava.models.Game;

import java.util.List;

public interface DefaultRepository {

    List<Game> getGames(String query);
    void saveGame(Game game);
}
