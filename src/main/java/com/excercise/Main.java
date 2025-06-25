package com.excercise;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {

        try {
            var connection= DriverManager.getConnection("jdbc:h2:./boardgame ;AUTO_SERVER=TRUE");
            String createTableSql="create table if not exists BOARDGAME (id bigint auto_increment primary key, name varchar, gameType varchar, playerCount integer,  playTimePerPlayer integer, timesPlayed integer)";// creating a  table BOARDGAME query
            var statement=connection.createStatement();
            statement.execute(createTableSql);
//            String insertQuery="insert into BOARDGAME (name) values('learn sql') ";
//            statement.execute(insertQuery);

            //repeating with prepared statements
            String insertStatement="insert into BOARDGAME (name varchar, gameType varchar, playerCount integer,  playTimePerPlayer integer, timesPlayed integer) values (?,?,?,?,?) ";
            var preparedStatement=connection.prepareStatement(insertStatement);
            //TODO: insert human input
            preparedStatement.setString(1, "learn spring");
            preparedStatement.execute();

            //updating
            String updateCommand="update BOARDGAME set name = ? where name ='learn sql'"; //UPDATE TO HOW MANY TIMES PLAYED
            preparedStatement=connection.prepareStatement(updateCommand);
            preparedStatement.setString(1,"Learn Jakarta EE");
            preparedStatement.execute();

            //deleting
            String deleteQuery="delete from BOARDGAME where name = 'Chess'"; //DELETE GAME FROM LIBRARY
            statement.execute(deleteQuery);

            //finding in database
            String selectAllQuery="select * from BOARDGAME";
            var resultSet=statement.executeQuery(selectAllQuery);// NB - excecuteQuery vs just excecute
            while(resultSet.next()){
                System.out.println("To Do Item: " + resultSet.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}