package com.dbtechprojects.gamedealsjava.repository;

import static com.dbtechprojects.gamedealsjava.utils.Constants.BASE_URL;
import static com.dbtechprojects.gamedealsjava.utils.Constants.SEARCH_URL;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.dbtechprojects.gamedealsjava.api.ApiClient;
import com.dbtechprojects.gamedealsjava.models.Game;
import com.dbtechprojects.gamedealsjava.models.Mappers;
import com.dbtechprojects.gamedealsjava.utils.MyApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;


// singleton design pattern https://www.journaldev.com/1377/java-singleton-design-pattern-best-practices-examples

public class RepositoryImpl implements DefaultRepository {

    // singleton declaration
    private static final RepositoryImpl instance = new RepositoryImpl();

    //private constructor to avoid client applications to use constructor
    private RepositoryImpl() {
    }

    public static RepositoryImpl getInstance() {
        return instance;
    }


    @Override
    public List<Game> getGames(String query) {
        Log.d("repository", "getGames method called");
        String requestUrl = BASE_URL + SEARCH_URL + query;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                requestUrl,

                response -> {
                    Log.d("repository", response.toString());
                },
                error -> {
                    Log.d("repository", error.getMessage());
                }
        );

        // Access the RequestQueue through singleton class.


        return null;
    }


    // get a list of games from API using background thread
    public Single<List<Game>> getGameList(String query) {
        return Single.create(new SingleOnSubscribe<List<Game>>() {
            @Override
            public void subscribe(@NonNull SingleEmitter<List<Game>> emitter) throws Exception {
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
                        }
                );
                // enqueue request
                ApiClient.getInstance(MyApplication.getAppContext()).addToRequestQueue(jsonArrayRequest);
            }
        });
    }

    @Override
    public void saveGame(Game game) {

    }
}
