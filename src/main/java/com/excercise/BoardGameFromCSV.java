package com.excercise;

import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BoardGameFromCSV {

    Scanner scanner = new Scanner(System.in);

@SneakyThrows
    public static void boardgamesBGG (String file) {
        ArrayList<Boardgame> boardgamesArrayList = new ArrayList<>();

// PARTS (columns) NEEDED FROM BGG CSV FILE - [] marks column number in CSV file, number after it is zero-indexed :
// A[1]0 - name, P[16]15 - comment(style), AI[35]34 - count, AD[30]29 -playtime,
// D[4]3 - times played

    try {
        Files.lines(Paths.get(file))

                /// //////////////////////////////////Debugging
//                .forEach(line -> {
//                    //debugging columns
//                    String[] parts = line.split("\t");
//                    System.out.println("Split into " + parts.length + " parts: " + Arrays.toString(parts));
//                                    });
//    } catch (Exception e) {
//        System.err.println("ERROR:");
//        e.printStackTrace();
//    }
    //////////////////////////////////


                    .skip(1)
                    .filter(line -> !line.isEmpty())
                    .map(row -> row.split(","))
                    .map(parts -> {
                        //checking if gamestyle exists as an enum, adding not specified if not found.
                        GameStyle gameStyle =null;

                        try {
                            gameStyle = GameStyle.valueOf(parts[15].trim().toUpperCase());
                        } catch (IllegalArgumentException e) {
                            gameStyle = GameStyle.NON_SPECIFIED;
                        }

                        return new Boardgame(parts[0],
                                gameStyle,
                                Integer.valueOf(parts[34]),
                                Integer.valueOf(parts[29]),
                                Integer.valueOf(parts[3]));
                    })
                    .forEach(boardgamesArrayList::add);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Boardgame boardgame : boardgamesArrayList) {
            try {
                BoardGameRepository.create(boardgame);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
        }
