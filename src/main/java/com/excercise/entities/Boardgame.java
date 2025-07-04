package com.excercise.entities;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class Boardgame {
   public int id;

    @CsvBindByName(column = "objectname")
    public  String name;

    @CsvBindByName(column = "comment")
    public GameStyle gameStyle;

    @CsvBindByName(column = "bggbestplayers")
    public int playerCount;

    @CsvBindByName(column = "playingtime")
    public  int playTime;

    @CsvBindByName(column = "numplays")
    public int timesPlayed;


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
