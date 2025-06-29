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

        System.out.println("Enter relevant number to select type of game:");

        System.out.println("1 - Euro\n2 - American style" +
                    "\n3 - Area Control\n4 - Abstract\n5 - Worker Placement" +
                    "\n6 - DeckBuilder\n7 - Coop\n8 - Legacy\n9 - Party" +
                    "\n 10 - Roll and X\n11 - Dexterity\n12 - TrickTaker\n13 - Other");
            String userInput = scanner.nextLine().trim();
            GameStyle gameStyle = null;

            switch (userInput) {
                case "1":
                    gameStyle = GameStyle.EUROGAME;
                    break;
                case "2":
                    gameStyle = GameStyle.AMERICAN_STYLE;
                    break;
                case "3":
                    gameStyle = GameStyle.AREA_CONTROL;
                    break;
                case "4":
                    gameStyle = GameStyle.ABSTRACT;
                    break;
                case "5":
                    gameStyle = GameStyle.WORKER_PLACEMENT;
                    break;
                case "6":
                    gameStyle = GameStyle.DECK_BUILDING;
                    break;
                case "7":
                    gameStyle = GameStyle.COOPERATIVE;
                    break;
                case "8":
                    gameStyle = GameStyle.LEGACY;
                    break;
                case "9":
                    gameStyle = GameStyle.PARTY;
                    break;
                case "10":
                    gameStyle = GameStyle.ROLL_AND_X;
                    break;
                case "11":
                    gameStyle = GameStyle.DEXTERITY;
                    break;
                case "12":
                    gameStyle = GameStyle.TRICKTAKING;
                    break;
                case "13":
                    gameStyle = GameStyle.NON_SPECIFIED;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    StartUI();
                    return;
            }

            game.setGameStyle(gameStyle);


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
        }else{
            System.out.println("Deletion cancelled. Returning to main menu");
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
