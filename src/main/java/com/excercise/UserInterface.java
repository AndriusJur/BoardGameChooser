package com.excercise;

import lombok.SneakyThrows;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class UserInterface {



        public static void StartUI () throws Exception {
        String commands = "\n1 - Add a game to collection \n2 - Registrate a play of a game\n3 - choose a random game to play\n4- List all games in collection\n5 - Statstics of a selected game\n6- delete a single game from library\n7-delete all games from library\n0-exits app";
        System.out.println(commands);
        Scanner scanner = new Scanner(System.in);
        String cmd = scanner.nextLine();
        switch (cmd) {
            case "1":
                addGame();
                break;
            case "2":
                addPlay();
                break;
            case "3":
                chooseRandomGame();
                break;
            case "4":
                listAllGames();
                break;
            case "5":
                statsOfSelectedGame();
                break;
            case "6":
                removeGame();
                break;
            case "7":
                deleteAllGames();
                break;
            case "0":
                break;
        }
    }

        @SneakyThrows
        public static void addGame () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter title of game:");// check for uniqueness!!
        String cmd = scanner.nextLine().toLowerCase().trim();

        if (isGameInLibrary(cmd)) {
            System.out.println("Game already exists.");
            return;
        }

        Boardgame game = new Boardgame();
        game.setName(cmd);
        System.out.println("Enter type of game:");
        game.setGameType(scanner.nextLine().toLowerCase().trim());
        System.out.println("Enter best suited player count ( a single number):");
        game.setPlayerCount(Integer.valueOf(scanner.nextLine()));
        System.out.println("playTimePerPlayer,in minutes:");
        game.setPlayTimePerPlayer(Integer.valueOf(scanner.nextLine()));
        BoardGameRepository.create(game);
        System.out.println("Game entered into database succesfully. Returning to main menu.");
        StartUI();
    }

        @SneakyThrows
        public static void addPlay () throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the title of the game");// TODO: implement "or a part of it"
        String cmd = scanner.nextLine();
        if (!isGameInLibrary(cmd)) {
            System.out.println("Game not found. Returning to main menu");
            StartUI();
        }
        Boardgame boardgame = new Boardgame(cmd);
        BoardGameRepository.update(boardgame);
        System.out.println("Update succesful. Returning to main menu.");
        StartUI();

        }

        @SneakyThrows
        public static void chooseRandomGame () {
        List<Boardgame> boardgames = BoardGameRepository.findAll();
        if (boardgames.isEmpty()) {
            System.out.println("The list is empty. Probably should buy some games");
            return;
        }
        Random rnd = new Random();
        Boardgame randomBoard = boardgames.get(rnd.nextInt(boardgames.size()));
        System.out.println("Your random boardgame is:" + randomBoard.getName() + ".\nReturning to main menu.");
            StartUI();
        }

        public static void listAllGames () throws Exception {
        List<Boardgame> boardgames = BoardGameRepository.findAll();
        if (boardgames.isEmpty()) {
            System.out.println("The list is empty. Probably should buy some games. Returning to main menu");
            return;
        }
        for (Boardgame x : boardgames) {
            System.out.println(x.getName());
        }
        System.out.println("To return to main menu, enter MAIN below, to exit - press any button");
        Scanner scanner = new Scanner(System.in);
        if (scanner.nextLine().toLowerCase().trim().equals("main")) {
            StartUI();
        }
    }

        @SneakyThrows
        public static void statsOfSelectedGame () throws Exception {
        System.out.println("Enter a game title:");
        Scanner scanner = new Scanner(System.in);

        String title = scanner.nextLine().trim();
        Boardgame game = BoardGameRepository.findOne(title);
        if (game == null) {
            System.out.println("Game not found");
            return;
        }


        System.out.println("Statistics of selected game: " + game);
        // TOOD:ask if to terminate app or return
    }
        @SneakyThrows
        public static void removeGame () {
        System.out.println("Enter a game title:");
        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();
        if (!isGameInLibrary(title)) {
            System.out.println("Game not found.");
            StartUI();
        }
        BoardGameRepository.deleteOne(title);
        StartUI();
    }

        @SneakyThrows
        public static void deleteAllGames () {
        System.out.println("Are you sure you want to delete your whole library?\nInput yes/no to confirm:");
        Scanner scanner = new Scanner(System.in);
        String cmd = scanner.nextLine().trim().toLowerCase();
        if (cmd.contains("yes")) {
            BoardGameRepository.deleteAll();
            System.out.println("Deleting complete. Database empty.\nReturning to initial menu");
            StartUI();
        }
    }

        @SneakyThrows
        public static boolean isGameInLibrary (String name){
        List<Boardgame> boardgames = BoardGameRepository.findAll();
        return boardgames.stream().
                map(Boardgame::getName)
                .anyMatch(gameName -> gameName.contains(name));

    }

    }
