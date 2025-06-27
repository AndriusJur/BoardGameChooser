package com.excercise;


import lombok.SneakyThrows;

public class Main {
    @SneakyThrows
    public static void main(String[] args)  {
            BoardGameRepository.createDatabase();
            UserInterface.StartUI();
    }
}

