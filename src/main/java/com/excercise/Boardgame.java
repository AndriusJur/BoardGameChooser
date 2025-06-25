package com.excercise;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class Boardgame {  // TODO: implement enum -  game type!
    int id;
    String name;
    String gameType;
    int playerCount;
    int playTimePerPlayer;
    int timesPlayed;


    public Boardgame(String name) {
        this.name=name;
    }
    public Boardgame(int id, String name){

        this.id=id;
        this.name=name;
    }
}
