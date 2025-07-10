package com.excercise.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name ="PLAYER")

public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

    private String name;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL ) // one player ->many boardgames.
    private List<PlayerBoardgamePlays> PlayerBoardgamePlays= new ArrayList<>();


    private int calculatedTotalPlays;

    public Player(String name) {
        this.name = name;
    }




}
