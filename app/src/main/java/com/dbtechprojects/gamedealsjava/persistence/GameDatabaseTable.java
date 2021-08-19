package com.dbtechprojects.gamedealsjava.persistence;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.dbtechprojects.gamedealsjava.models.Game;

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
    }

    // DB Helper Methods

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TableColumns.TABLE_NAME + " (" +
                    TableColumns._ID + " INTEGER PRIMARY KEY," +
                    TableColumns.COLUMN_NAME_TITLE + " TEXT," +
                    TableColumns.COLUMN_NAME_Image + " TEXT," +
                    TableColumns.COLUMN_NAME_InternalName + " TEXT," +
                    TableColumns.COLUMN_NAME_CheapestDealId + " TEXT," +
                    TableColumns.COLUMN_NAME_Cheapest + " TEXT," +
                    TableColumns.COLUMN_NAME_SteamAppId + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TableColumns.TABLE_NAME;

    // store game in DB
    public static void SaveGame(Game game, SQLiteDatabase db){
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(TableColumns.COLUMN_NAME_TITLE, game.external);
        values.put(TableColumns.COLUMN_NAME_Image, game.thumb);
        values.put(TableColumns.COLUMN_NAME_InternalName, game.internalName);
        values.put(TableColumns.COLUMN_NAME_CheapestDealId, game.cheapestDealID);
        values.put(TableColumns.COLUMN_NAME_Cheapest, game.cheapest);
        values.put(TableColumns.COLUMN_NAME_SteamAppId, game.steamAppID);

        // Insert the new row, returning the primary key value of the new row
         db.insert(TableColumns.TABLE_NAME, null, values);
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
