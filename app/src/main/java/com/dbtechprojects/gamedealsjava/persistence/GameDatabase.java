package com.dbtechprojects.gamedealsjava.persistence;

import static com.dbtechprojects.gamedealsjava.persistence.GameDatabaseTable.SQL_CREATE_ENTRIES;
import static com.dbtechprojects.gamedealsjava.persistence.GameDatabaseTable.SQL_DELETE_ENTRIES;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dbtechprojects.gamedealsjava.models.Game;

//singleton for SQLite
//https://www.androiddesignpatterns.com/2012/05/correctly-managing-your-sqlite-database.html

public class GameDatabase extends SQLiteOpenHelper {

    private static GameDatabase sInstance;

    private static final String DATABASE_NAME = "gamesDatabase";
    private static final String DATABASE_TABLE = "table_name";
    private static final int DATABASE_VERSION = 3;

    public static synchronized GameDatabase getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new GameDatabase(context.getApplicationContext());
        }
        return sInstance;
    }

    /**
     * Constructor should be private to prevent direct instantiation.
     * make call to static method "getInstance()" instead.
     */
    private GameDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // delete data
        db.execSQL(SQL_DELETE_ENTRIES);

        // recreate table
        onCreate(db);
    }
}
