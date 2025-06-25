package com.excercise;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class BoardGameRepository {

    private static DataSource getDataSource(){
        var dataSource=new HikariDataSource(); //hikari allows to pool connections
        dataSource.setJdbcUrl("jdbc:h2:./boardgame;AUTO_SERVER=TRUE");
        return dataSource;
    }

    public static void create(Boardgame boardgame) throws Exception {
        try (Connection connection=getDataSource().getConnection()){
            String insertStatement="insert into BOARDGAME (id, name, gameType, playerCount, playTimePerPlayer, timesPlayed) values(?, ?, ?, ?, ?, ?)";
            var preparedStatement=connection.prepareStatement(insertStatement);
            preparedStatement.setString(1, boardgame.name); //indexes correspond to question marks in sql
            preparedStatement.setString(2, boardgame.gameType);
            preparedStatement.setInt(3, boardgame.playerCount);
            preparedStatement.setInt(4, boardgame.playTimePerPlayer);
            preparedStatement.setInt(5, boardgame.timesPlayed);
            preparedStatement.execute();
        }
    }

    public static void update(Boardgame boardgame) throws Exception {
        try (Connection connection=getDataSource().getConnection()){
            String insertStatement="update BOARDGAME set timesPlayed=? where name=?";
            var preparedStatement=connection.prepareStatement(insertStatement);
            preparedStatement.setString(1, boardgame.name);
            preparedStatement.setString(2, String.valueOf(boardgame.timesPlayed));//ditto
            preparedStatement.execute();

        }
    }
    // delete
    public static void deleteAll () throws Exception {
        try (Connection connection=getDataSource().getConnection()){
            var statement=connection.createStatement();
            statement.executeUpdate("DELETE FROM BOARDGAME");// NB excecuteUpdate , not just update;

        }
    }
    //retrieve
    public static List<Boardgame> findAll()  throws Exception {
        List<Boardgame> boardgames = new ArrayList<>();
        try (Connection connection=getDataSource().getConnection()){
            var statement=connection.createStatement();
            var resultSet=statement.executeQuery("select * from BOARDGAME");
            while (resultSet.next()){
                Boardgame boardgame =new Boardgame(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6)); //1-6 - columns
                boardgames.add(boardgame);
            }
        }
        return boardgames;
    }
    public static void main(String[] args) {
        try {
            BoardGameRepository.deleteAll();

            Boardgame boardgame =new Boardgame("make coffee");
            BoardGameRepository.create(boardgame);

            Boardgame boardgame2 =new Boardgame(11,"eat some 'za");// wont  change anything
            BoardGameRepository.update(boardgame2);

            List<Boardgame> boardgames = BoardGameRepository.findAll();
            for (Boardgame x: boardgames){
                System.out.println(x.getName());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}