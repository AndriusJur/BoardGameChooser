package com.excercise.services;

import com.excercise.entities.Boardgame;
import com.excercise.entities.Player;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data


@Entity
@Table(name = "PLAYER_BOARDGAME_PLAYS")
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
    }

    public PlayerBoardgamePlays(Player player, Boardgame bg) {

    }
}
