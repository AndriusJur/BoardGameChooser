package com.excercise.entities;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

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

    @OneToMany(mappedBy = "boardgame", cascade = CascadeType.ALL)
    private List<PlayerBoardgamePlays> playerBoardgamePlays = new ArrayList<>();


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


    //constructor made for tests
    public Boardgame(int anInt, String string, GameStyle gameStyle, int anInt1, int anInt2, int anInt3) {


    }
}
