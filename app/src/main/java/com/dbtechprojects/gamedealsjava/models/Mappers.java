package com.dbtechprojects.gamedealsjava.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Mappers {

    // loop through Json Array get Game Objects and return gameList
    // if error return empty list
    public static ArrayList<Game> JsonResponseToGameList(JSONArray jsonArray){
        ArrayList<Game> games = new ArrayList<>();
        try{
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonGame = jsonArray.getJSONObject(i);
                Game game = new Game(
                        jsonGame.getString("thumb"),
                        jsonGame.getString("steamAppID"),
                        jsonGame.getString("internalName"),
                        jsonGame.getString("gameID"),
                        jsonGame.getString("external"),
                        jsonGame.getString("cheapestDealID"),
                        "",
                        jsonGame.getString("cheapest"),
                        "",
                        0
                );
                games.add(game);
            }

            return games;

        }catch (JSONException exception){
            exception.printStackTrace();
            return games;
        }
    }
}
