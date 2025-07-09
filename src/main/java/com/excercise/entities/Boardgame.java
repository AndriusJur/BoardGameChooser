package com.excercise.entities;

import com.excercise.services.PlayerBoardgamePlays;
import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

@Entity
@Table(name ="BOARDGAME")

public class Boardgame {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;//changed from int to Long


    @CsvBindByName(column = "objectname")
    public  String name;

    @Enumerated(EnumType.STRING)
    @CsvBindByName(column = "comment")
    public GameStyle gameStyle;


    @CsvBindByName(column = "bggbestplayers")
    public int playerCount;


    @CsvBindByName(column = "playingtime")
    public  int playTime;


    @CsvBindByName(column = "numplays") //TODO: implement to work with player class
    public int timesPlayed;

    @OneToMany
    public PlayerBoardgamePlays playerBoardgamePlays;
    public Boardgame(){//hibernate needs empty constructor, could just use @noargsConstructor

    }
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
