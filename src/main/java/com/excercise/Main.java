package com.excercise;


import com.excercise.services.BoardGameRepository;
import com.excercise.services.PlayerRepository;
import com.excercise.services.UserInterface;
import lombok.SneakyThrows;

public class Main {
    @SneakyThrows
    public static void main(String[] args)  {
            BoardGameRepository.createDatabase();
            PlayerRepository.createDatabase();
            UserInterface.StartUI();
    }
}

