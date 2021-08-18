package com.dbtechprojects.gamedealsjava.repository;

import static com.dbtechprojects.gamedealsjava.utils.Constants.BASE_URL;
import static com.dbtechprojects.gamedealsjava.utils.Constants.SEARCH_URL;

import android.app.Application;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.dbtechprojects.gamedealsjava.api.ApiClient;
import com.dbtechprojects.gamedealsjava.models.Game;
import com.dbtechprojects.gamedealsjava.utils.MyApplication;

import org.json.JSONArray;

import java.util.List;


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
        ApiClient.getInstance(MyApplication.getAppContext()).addToRequestQueue(jsonArrayRequest);

        return null;
    }

    @Override
    public void saveGame(Game game) {

    }
}
