package com.excercise.entities;

import com.excercise.services.BoardGameRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name ="PLAYER")

public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   public Long id;

    public String name;
    public HashMap <Boardgame,Integer> boardgamesAndTimesPlayed; // boardgame var char and timesPlayed int in SQL
    public int calculatedTotalPlays;

    public Player(String name) {
        this.name = name;
        this.boardgamesAndTimesPlayed = new HashMap <>();
    }

    public void addPlay(Boardgame boardgame){
        try {
            BoardGameRepository.findOne(boardgame.getName());
        } catch (Exception e) {
            throw new RuntimeException("Game not found");
        }
        if(this.boardgamesAndTimesPlayed.containsKey(boardgame)){ //if boardgame already  exists
            int currCount=boardgamesAndTimesPlayed.get(boardgame);
            boardgamesAndTimesPlayed.put(boardgame,currCount+1);
        }else{
            boardgamesAndTimesPlayed.put(boardgame,1);//initial play
        }
        calculatedTotalPlays++;
    }


}
