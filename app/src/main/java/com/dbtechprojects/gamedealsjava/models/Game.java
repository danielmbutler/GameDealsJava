package com.dbtechprojects.gamedealsjava.models;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Game implements Serializable {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "isSaved")
    @SerializedName("isSaved")
    public String saved;

    @ColumnInfo(name = "cheapest")
    @SerializedName("cheapest")
    public String cheapest;

    @ColumnInfo(name = "timestamp")
    @SerializedName("timestamp")
    public String timestamp;

    @ColumnInfo(name = "cheapestDealID")
    @SerializedName("cheapestDealID")
    public String cheapestDealID;

    @ColumnInfo(name = "external")
    @SerializedName("external")
    public String external;

    @ColumnInfo(name = "gameID")
    @SerializedName("gameID")
    public String gameID;

    @ColumnInfo(name = "internalName")
    @SerializedName("internalName")
    public String internalName;

    @ColumnInfo(name = "steamAppID")
    @SerializedName("steamAppID")
    public String steamAppID;

    @ColumnInfo(name = "thumb")
    @SerializedName("thumb")
    public String thumb;

    // Constructor

    public Game(
        String thumb,
        String steamAppId,
        String internalName,
        String gameID,
        String external,
        String cheapestDealID,
        String timestamp,
        String cheapest,
        String saved
    ){
        this.cheapest = cheapest;
        this.saved = saved;
        this.cheapestDealID = cheapestDealID;
        this.gameID = gameID;
        this.external = external;
        this.timestamp = timestamp;
        this.internalName = internalName;
        this.steamAppID = steamAppId;
        this.thumb = thumb;

    }


}
