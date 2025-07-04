package com.excercise.services;

import com.excercise.entities.Boardgame;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.SneakyThrows;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoardGameFromCSV {

    Scanner scanner = new Scanner(System.in);

@SneakyThrows
    public static void boardgamesBGG (String file) { // reference -> https://opencsv.sourceforge.net/#quick_start
        ArrayList<Boardgame> boardgamesArrayList = new ArrayList<>();

        try {
        List<Boardgame> boardgames = new CsvToBeanBuilder(new FileReader(file))
                .withType(Boardgame.class).build().parse();
        for (Boardgame x:boardgames){
            BoardGameRepository.create(x);
        }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
        }
