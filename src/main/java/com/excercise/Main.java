package com.excercise;


import lombok.SneakyThrows;

public class Main {
    @SneakyThrows
    public static void main(String[] args)  {
            BoardGameRepository.createDatabase();
            UserInterface ui= new UserInterface();
            ui.StartUI();
//            Boardgame boardgame = new Boardgame(0, "make coffee", "Strategy", 2, 30, 0);
//            BoardGameRepository.create(boardgame);
//            BoardGameRepository.deleteAll();
    }
}

