package com.excercise;

import lombok.SneakyThrows;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class UserInterface {

    public static void StartUI() throws Exception {
        System.out.println("Hi! This is a boardgame management app.\n These are the possible commands. To SELECT - insert a valid number");
         String commands="1 - Add a game to collection \n2 - Registrate a play of a game\n3 - choose a random game to play\n4- List all games in collection\n5 - Statstics of a selected game\n6- delete a single game from library\n7-delete all games from library\n0-exits app";
        System.out.println(commands);
         Scanner scanner= new Scanner(System.in);
         String cmd=scanner.nextLine();
         switch (cmd){
             case "1":AddGame();
             break;
             case "2":AddPlay();
             break;
             case "3":ChooseRandomGame();
             break;
             case "4":ListAllGames();
             break;
             case "5":StatsOfSelectedGame();
             break;
             case "6":RemoveGame();
             break;
             case "7":DeleteAllGames();
             break;
             case "0":break;



         }

    }
    @SneakyThrows
    public static void AddGame() {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Enter title of game:");// check for uniqueness!!
        String title=scanner.nextLine().toLowerCase().trim();
        IsGameInLibrary(title);

        Boardgame game=new Boardgame();
        game.setName(title);
        System.out.println("Enter type of game:");
        game.setGameType(title);
        System.out.println("Enter player count:");
        game.setPlayerCount(Integer.valueOf(title));
        System.out.println("playTimePerPlayer:");
        game.setPlayTimePerPlayer(Integer.valueOf(title));
        BoardGameRepository.create(game);
        System.out.println("Game entered into database succesfully. Returning to main menu.");
        StartUI();
    }

    @SneakyThrows
    public static void AddPlay() throws Exception {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Enter the title of the game");// TODO: implement "or a part of it"
        String cmd=scanner.nextLine();
        IsGameInLibrary(cmd);
        Boardgame boardgame=new Boardgame(cmd);
        BoardGameRepository.update(boardgame);
        System.out.println("Update succesful. Returning to main menu.");
        StartUI();
    }
    @SneakyThrows
    public static String ChooseRandomGame (){
        List<Boardgame> boardgames = BoardGameRepository.findAll();
        if(boardgames.isEmpty()){
            return "The list is empty. Probably should buy some games";
        }
        Random rnd=new Random();
        Boardgame randomBoard=boardgames.get(rnd.nextInt(boardgames.size()));
        return "Your random boardgame is:" + randomBoard.getName()+".\nReturning to main menu.";


    }

    public static void  ListAllGames() throws Exception {
        List<Boardgame> boardgames = BoardGameRepository.findAll();
        if(boardgames.isEmpty()){
            System.out.println("The list is empty. Probably should buy some games");
        }

        for (Boardgame x : boardgames) {
            System.out.println(x.getName());
        }
    }
    @SneakyThrows
    public static void StatsOfSelectedGame() throws Exception {
        System.out.println("Enter a game title:");
        Scanner scanner= new Scanner(System.in);
        String title=scanner.nextLine().trim();
        IsGameInLibrary(title);

        List<Boardgame> boardgames = BoardGameRepository.findAll();//TODO: fix for select one (probalbly with boardgames list in array)
        for (Boardgame x : boardgames) {
            if(x.getName().equalsIgnoreCase(title)){
                System.out.println(x.toString());
            }else {
                System.out.println("Game not found");
            }
        }
    }

    @SneakyThrows
    public static void  RemoveGame(){
        System.out.println("Enter a game title:");
        Scanner scanner= new Scanner(System.in);
        String title=scanner.nextLine();
        IsGameInLibrary(title);
        BoardGameRepository.deleteOne(title);



    }

    public static void DeleteAllGames (){

    }

    @SneakyThrows
    public static boolean IsGameInLibrary(String name){
        List<Boardgame> boardgames = BoardGameRepository.findAll();
        if(boardgames.isEmpty()){
            System.out.println("The list is empty. Probably should buy some games");
            System.out.println("Returning to initial menu");
            StartUI();
        }
        return boardgames.stream().
                map(Boardgame::getName)
                .anyMatch(gameName->gameName.contains(name));

    }

}
