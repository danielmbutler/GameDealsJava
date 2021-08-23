package com.dbtechprojects.gamedealsjava.models;

import java.io.Serializable;


public class Game implements Serializable {

    public int id;

    public String saved;

    public String cheapest;

    public String timestamp;

    public String cheapestDealID;

    public String external;

    public String gameID;

    public String internalName;

    public String steamAppID;

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
        String saved,
        int id
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
        this.id = id;

    }


}
