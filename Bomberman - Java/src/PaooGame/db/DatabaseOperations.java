package PaooGame.db;

import PaooGame.States.HighscoreState;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

public class DatabaseOperations {
    public static void insertRecordInHighScoreTabels(String tableName, String name, int time){
            Connection connection = null;
            Statement stmt = null;

            try{
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:game.db");
                connection.setAutoCommit(true);
                stmt = connection.createStatement();

                ResultSet rs = stmt.executeQuery("SELECT * from '" + tableName + "' WHERE Name = '" + name + "' ");
                if(rs.next() == false){
                    stmt.executeUpdate("INSERT INTO '" + tableName + "' (Name, Time) VALUES('" + name+ "', " + time+")");
                }
                else
                {
                    int time2 = rs.getInt("Time");
                    stmt.executeUpdate("UPDATE '" + tableName + "' set Time = " + time + "" +
                            "  WHERE Name = '" + name + "' AND " + time + " < " + time2 +";");
                }

                stmt.close();
                connection.close();

            }catch ( Exception e ) {
                System.out.println("Eroare la database");
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            }
    }


    public static void getTabelOrdered(String tableName, String columnName){
        Connection connection = null;
        Statement stmt = null;

        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:game.db");
            connection.setAutoCommit(true);
            stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * from '" + tableName + "'");

            if(tableName.endsWith("1")){
                HighscoreState.clearPlayers();

                while(rs.next()){

                    HighscoreState.addPlayer(new PlayerInfo(rs.getString("Name"), rs.getInt("Time")));
                }

                Collections.sort(HighscoreState.getPlayers());
            }

            if(tableName.endsWith("2")){
                HighscoreState.clearPlayers2();

                while(rs.next()){
                    HighscoreState.addPlayer2(new PlayerInfo(rs.getString("Name"), rs.getInt("Time")));
                }

                Collections.sort(HighscoreState.getPlayers2());
            }


            rs.close();
            stmt.close();
            connection.close();

        }catch ( Exception e ) {
            System.out.println("Eroare la database");
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
}
