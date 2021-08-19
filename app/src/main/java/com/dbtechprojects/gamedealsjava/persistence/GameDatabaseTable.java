package com.dbtechprojects.gamedealsjava.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import com.dbtechprojects.gamedealsjava.models.Game;

import java.util.ArrayList;

public class GameDatabaseTable {

    private GameDatabaseTable() {}


    /* Inner class that defines the table contents */
    public static class TableColumns implements BaseColumns {
        public static final String TABLE_NAME = "savedGames";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_Image = "thumb";
        public static final String COLUMN_NAME_InternalName = "internalName";
        public static final String COLUMN_NAME_CheapestDealId = "cheapestDealID";
        public static final String COLUMN_NAME_Cheapest = "cheapest";
        public static final String COLUMN_NAME_SteamAppId = "steamAppID";
        public static final String COLUMN_NAME_GameId = "gameID";
    }

    // DB Helper Methods

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TableColumns.TABLE_NAME + " (" +
                    TableColumns._ID + " INTEGER PRIMARY KEY," +
                    TableColumns.COLUMN_NAME_TITLE + " TEXT," +
                    TableColumns.COLUMN_NAME_Image + " TEXT," +
                    TableColumns.COLUMN_NAME_InternalName + " TEXT," +
                    TableColumns.COLUMN_NAME_CheapestDealId + " TEXT," +
                    TableColumns.COLUMN_NAME_GameId + " TEXT," +
                    TableColumns.COLUMN_NAME_Cheapest + " TEXT," +
                    TableColumns.COLUMN_NAME_SteamAppId + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TableColumns.TABLE_NAME;

    // store game in DB
    public static void SaveGame(Game game, SQLiteDatabase db){
        // Create a new map of values, where column names are the keys
        Log.d("Games Database", "saving game : " + game);
        ContentValues values = new ContentValues();
        values.put(TableColumns.COLUMN_NAME_TITLE, game.external);
        values.put(TableColumns.COLUMN_NAME_Image, game.thumb);
        values.put(TableColumns.COLUMN_NAME_InternalName, game.internalName);
        values.put(TableColumns.COLUMN_NAME_CheapestDealId, game.cheapestDealID);
        values.put(TableColumns.COLUMN_NAME_Cheapest, game.cheapest);
        values.put(TableColumns.COLUMN_NAME_SteamAppId, game.steamAppID);
        values.put(TableColumns.COLUMN_NAME_GameId, game.gameID);

        // Insert the new row, returning the primary key value of the new row
         long res = db.insert(TableColumns.TABLE_NAME, null, values);
         Log.d("Games Database", "saved game : " + res);
    }

    // retrieve all saved games
    public static ArrayList<Game> getSavedGames(SQLiteDatabase db){
        Log.d("Games Database", "get Saved games called");
        ArrayList<Game> gameList = new ArrayList<Game>();
        Cursor res = db.rawQuery( "select * from "+TableColumns.TABLE_NAME, null );
        res.moveToFirst();
        while(!res.isAfterLast()) {
            Game game = new Game(
                    (res.getString(res.getColumnIndex(TableColumns.COLUMN_NAME_Image))),
                    (res.getString(res.getColumnIndex(TableColumns.COLUMN_NAME_SteamAppId))),
                    (res.getString(res.getColumnIndex(TableColumns.COLUMN_NAME_InternalName))),
                    (res.getString(res.getColumnIndex(TableColumns.COLUMN_NAME_GameId))),
                    (res.getString(res.getColumnIndex(TableColumns.COLUMN_NAME_TITLE))),
                    (res.getString(res.getColumnIndex(TableColumns.COLUMN_NAME_CheapestDealId))),
                    "",
                    (res.getString(res.getColumnIndex(TableColumns.COLUMN_NAME_Cheapest))),
                    ""
            );
            Log.d("Games Database", "found game" + game);
            gameList.add(game);
            res.moveToNext();
        }
        res.close();
        return gameList;

    }
}

//   String thumb,
//        String steamAppId,
//        String internalName,
//        String gameID,
//        String external,
//        String cheapestDealID,
//        String timestamp,
//        String cheapest,
//        String saved