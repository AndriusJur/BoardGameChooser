package com.excercise.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data


@Entity
@Table(name = "PLAYER_BOARDGAME_PLAYS")

//helper table, serves as a connection between player and boardgame.
//reflects a game, which players have played it, and how many times a single player has played a single game.

public class PlayerBoardgamePlays {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//generates id for the player-boardgame relationship

    @ManyToOne
    @JoinColumn(name="player_id")//owning side bc contains fkeys
    private Player player;

    @ManyToOne
    @JoinColumn(name="boardgame_id")//same
    private Boardgame boardgame;


    private Integer timesPlayed;//NB: timesPlayed A SINGLE BOARDGAME!!!

    public PlayerBoardgamePlays(Player player) {
        this.player=player;
        this.timesPlayed=0;
    }

    public PlayerBoardgamePlays(Player player, Boardgame bg) {

    }

}
