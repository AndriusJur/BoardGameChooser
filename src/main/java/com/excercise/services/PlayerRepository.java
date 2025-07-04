package com.excercise.services;

import com.excercise.entities.Boardgame;
import com.excercise.entities.GameStyle;
import com.excercise.entities.Player;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerRepository {

    private static DataSource getDataSource(){
        var dataSource=new HikariDataSource(); //hikari allows to pool connections
        dataSource.setJdbcUrl("jdbc:h2:./player;AUTO_SERVER=TRUE");
        return dataSource;
    }

    public static void createDatabase() throws SQLException {

        try {
            //creating connection to database
            var connection = DriverManager.getConnection("jdbc:h2:./player;AUTO_SERVER=TRUE");
            String createTablePlayers = "create table if not exists PLAYER (id bigint auto_increment primary key, name varchar, boardgame varchar, timesPlayed integer,calculatedTotalPlays integer )";
            var statement = connection.createStatement();
            statement.execute(createTablePlayers);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void create(Player player) throws Exception {
        try (Connection connection=getDataSource().getConnection()){
            String insertStatement="insert into PLAYER (name, boardgame , timesPlayed, calculatedTotalPlays) values(?, ?, ?, ?)";
            var preparedStatement=connection.prepareStatement(insertStatement);
            preparedStatement.setString(1, player.name); //indexes correspond to question marks in sql
            preparedStatement.setString(2, String.valueOf(player.boardgamesAndTimesPlayed));
            preparedStatement.setInt(3, player.playerCount); //TODO:forEach????Manage relations betweeen dbs, more realistically -  migrate to jpa/hibernate
            preparedStatement.setInt(4, player.calculatedTotalPlays);
            preparedStatement.execute();
        }
    }

    public static void update(Boardgame boardgame) throws Exception {
        try (Connection connection=getDataSource().getConnection()){
            String insertStatement="update PLAYER set timesPlayed=? where name=?";
            var preparedStatement=connection.prepareStatement(insertStatement);
            preparedStatement.setInt(1, boardgame.timesPlayed+1);
            preparedStatement.setString(2, boardgame.name);//ditto
            preparedStatement.execute();

        }
    }
    // delete
    public static void deleteAll () throws Exception {
        try (Connection connection=getDataSource().getConnection()){
            var statement=connection.createStatement();
            statement.executeUpdate("DELETE FROM PLAYER");//

        }
    }
    public static void deleteOne (String gameName) throws Exception {
        try (Connection connection=getDataSource().getConnection()){
            var sql = "DELETE FROM PLAYER where name = ?";
            var preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,gameName);
            preparedStatement.executeUpdate();
        }
    }
    public static Boardgame findOne(String name) throws Exception {
        try (Connection connection = getDataSource().getConnection()) {
            var sql = "SELECT * FROM PLAYER WHERE name = ?";

            try (var preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name);

                try (var resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return new Boardgame(resultSet.getInt(1),
                                resultSet.getString(2),
                                GameStyle.valueOf(resultSet.getString(3)),
                                resultSet.getInt(4),
                                resultSet.getInt(5),
                                resultSet.getInt(6));
                    }
                }
            }
        }
        return null; // Return null if no boardgame found
    }

    public static List<Boardgame> findAll()  throws Exception {
        List<Boardgame> boardgames = new ArrayList<>();
        try (Connection connection=getDataSource().getConnection()){
            var statement=connection.createStatement();
            var resultSet=statement.executeQuery("select * from PLAYER");
            while (resultSet.next()){
                Boardgame boardgame =new Boardgame(resultSet.getInt(1),
                        resultSet.getString(2),
                        GameStyle.valueOf(resultSet.getString(3)),
                        resultSet.getInt(4),
                        resultSet.getInt(5),
                        resultSet.getInt(6)); //1-6 - columns
                boardgames.add(boardgame);
            }
        }
        return boardgames;
    }
}
