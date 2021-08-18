package com.dbtechprojects.gamedealsjava.repository;

import com.dbtechprojects.gamedealsjava.models.Game;

import java.util.List;

// singleton design pattern https://www.journaldev.com/1377/java-singleton-design-pattern-best-practices-examples

public class RepositoryImpl implements DefaultRepository{

    // singleton declaration
    private static final RepositoryImpl instance = new RepositoryImpl();

    //private constructor to avoid client applications to use constructor
    private RepositoryImpl(){}

    public static RepositoryImpl getInstance(){
        return instance;
    }


    @Override
    public List<Game> getGames() {
        return null;
    }

    @Override
    public void saveGame(Game game) {

    }
}
