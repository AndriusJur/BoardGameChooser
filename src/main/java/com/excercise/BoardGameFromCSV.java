package com.excercise;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.SneakyThrows;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BoardGameFromCSV {

    Scanner scanner = new Scanner(System.in);

@SneakyThrows
    public static void boardgamesBGG (String file) { // reference -> https://opencsv.sourceforge.net/#quick_start
        ArrayList<Boardgame> boardgamesArrayList = new ArrayList<>();

// PARTS (columns) NEEDED FROM BGG CSV FILE - [] marks column number in CSV file, number after it is zero-indexed :
// A[1]0 - name, P[16]15 - comment(style), AI[35]34 - count, AD[30]29 -playtime,
// D[4]3 - times played
    try {
        List<Boardgame> boardgames = new CsvToBeanBuilder(new FileReader(file))
                .withType(Boardgame.class).build().parse();
        for (Boardgame x:boardgames){
            BoardGameRepository.create(x);
        }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Boardgame boardgame : boardgamesArrayList) {
        }
    }
        }
