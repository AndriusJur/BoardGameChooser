package com.excercise;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class Boardgame {
    int id;
    String name;
    GameStyle gameStyle;
    int playerCount;
    int playTime;
    int timesPlayed;


    public Boardgame(String name) {
        this.name=name;
    }

    public Boardgame(String name, GameStyle gameStyle, int playerCount, int playTime, int timesPlayed) {
        this.name = name;
        this.gameStyle = gameStyle;
        this.playerCount = playerCount;
        this.playTime = playTime;
        this.timesPlayed = timesPlayed;
    }

}
