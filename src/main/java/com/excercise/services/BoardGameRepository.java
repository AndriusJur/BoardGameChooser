package com.excercise.services;

import com.excercise.entities.Boardgame;
import com.excercise.entities.GameStyle;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardGameRepository {

    private static DataSource getDataSource(){
        var dataSource=new HikariDataSource(); //hikari allows to pool connections
        dataSource.setJdbcUrl("jdbc:h2:./boardgame;AUTO_SERVER=TRUE");
        return dataSource;
    }

    public static void createDatabase() throws SQLException {

        try {
            //creating connection to database
            var connection = DriverManager.getConnection("jdbc:h2:./boardgame;AUTO_SERVER=TRUE");
            String createTableBoardgames = "create table if not exists BOARDGAME (id bigint auto_increment primary key, name varchar, GAMESTYLE  varchar, playerCount integer,  playTime integer, timesPlayed integer)";// creating a  table BOARDGAME query
            var statement = connection.createStatement();
            statement.execute(createTableBoardgames);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        public static void create(Boardgame boardgame) throws Exception {
        try (Connection connection=getDataSource().getConnection()){
            String insertStatement="insert into BOARDGAME (name, GAMESTYLE , playerCount, playTime , timesPlayed) values(?, ?, ?, ?, ?)";
            var preparedStatement=connection.prepareStatement(insertStatement);
            preparedStatement.setString(1, boardgame.name); //indexes correspond to question marks in sql
            preparedStatement.setString(2, boardgame.gameStyle.name());
            preparedStatement.setInt(3, boardgame.playerCount);
            preparedStatement.setInt(4, boardgame.playTime);
            preparedStatement.setInt(5, boardgame.timesPlayed);
            preparedStatement.execute();
        }
    }

    public static void update(Boardgame boardgame) throws Exception {
        try (Connection connection=getDataSource().getConnection()){
            String insertStatement="update BOARDGAME set timesPlayed=? where name=?";
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
            statement.executeUpdate("DELETE FROM BOARDGAME");//

        }
    }
    public static void deleteOne (String gameName) throws Exception {
        try (Connection connection=getDataSource().getConnection()){
            var sql = "DELETE FROM BOARDGAME where name = ?";
            var preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,gameName);
            preparedStatement.executeUpdate();
        }
    }
    public static Boardgame findOne(String name) throws Exception {
        try (Connection connection = getDataSource().getConnection()) {
            var sql = "SELECT * FROM BOARDGAME WHERE name = ?";

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
            var resultSet=statement.executeQuery("select * from BOARDGAME");
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
