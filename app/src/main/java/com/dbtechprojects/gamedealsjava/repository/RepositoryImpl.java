package com.dbtechprojects.gamedealsjava.repository;

import static com.dbtechprojects.gamedealsjava.utils.Constants.BASE_URL;
import static com.dbtechprojects.gamedealsjava.utils.Constants.SEARCH_URL;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.android.volley.toolbox.JsonArrayRequest;
import com.dbtechprojects.gamedealsjava.api.ApiClient;
import com.dbtechprojects.gamedealsjava.models.Game;
import com.dbtechprojects.gamedealsjava.models.Mappers;
import com.dbtechprojects.gamedealsjava.persistence.GameDatabase;
import com.dbtechprojects.gamedealsjava.persistence.GameDatabaseTable;
import com.dbtechprojects.gamedealsjava.utils.MyApplication;
import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Single;


// singleton design pattern https://www.journaldev.com/1377/java-singleton-design-pattern-best-practices-examples

public class RepositoryImpl implements DefaultRepository {

    // singleton declaration
    private static final RepositoryImpl instance = new RepositoryImpl();

    // db instance
    private static final GameDatabase gameDb = GameDatabase.getInstance(MyApplication.getAppContext());

    //private constructor to avoid client applications to use constructor
    private RepositoryImpl() {
    }

    public static RepositoryImpl getInstance() {
        return instance;
    }


    // get a list of games from API using background thread
    public Single<List<Game>> getGameList(String query) {
        return Single.create(emitter -> {
            // make request with volley
            String requestUrl = BASE_URL + SEARCH_URL + query;

            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                    requestUrl,

                    response -> {
                        Log.d("repository", response.toString());
                        // loop through Json Array get Game Objects and return gameList
                        List<Game> games = Mappers.JsonResponseToGameList(response);
                        emitter.onSuccess(games); // emit games to viewModel
                    },
                    error -> {
                        Log.d("repository", error.getMessage());
                        error.printStackTrace();
                    }
            );
            // enqueue request
            ApiClient.getInstance(MyApplication.getAppContext()).addToRequestQueue(jsonArrayRequest);
        });
    }

    public Completable saveGame(Game game){
        return Completable.create(emitter -> {
            // Gets the data repository in write mode
            SQLiteDatabase db = gameDb.getWritableDatabase();
            GameDatabaseTable.SaveGame(game, db);
            db.close();
        });
    }
}
