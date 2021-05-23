package PaooGame.Audio;

import javax.sound.sampled.*;
import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Audio extends JFrame {
    Clip clip;
    String audioName;
    private boolean audioOn;
    private boolean musicWasStopped; // Variable that tells me if the music was stopped in the last frame

    public boolean getAudioOn(){return audioOn;}

    public Audio(){
        audioName = "GameMusic";
    }

    public Audio(String songName){

        audioName = songName;
    }

    public void startMusic(){
        String fileName = audioName;
        String filePath = "";

        Connection connection = null;
        Statement stmt = null;

        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:game.db");
            connection.setAutoCommit(true);
            stmt = connection.createStatement();

            stmt.executeUpdate("UPDATE Settings set Value = 1 where Name = 'Sound';");

            ResultSet rs = stmt.executeQuery("SELECT * FROM Files where Name = '" + fileName + "'");

            while(rs.next()){
                filePath = rs.getString("Path");
            }

            audioOn = true;
            musicWasStopped = false;

            rs.close();
            stmt.close();
            connection.close();

        }catch ( Exception e ) {
            System.out.println("Eroare la database");
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }

        try {
            // Open an audio input stream.
            URL url = this.getClass().getClassLoader().getResource(filePath);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
            clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        }catch(Exception e) {
            e.printStackTrace();
        }
    }


    public void update(){
        Connection connection;
        Statement stmt;
        int isMusicOn = 1;

        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:game.db");
            connection.setAutoCommit(true);
            stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM Settings where Name = 'Sound'");

            while(rs.next()){
                isMusicOn = rs.getInt("Value");
            }

            rs.close();
            stmt.close();
            connection.close();

            if(isMusicOn != 0){
                audioOn = true;
            }
            else
            {
                audioOn = false;
                musicWasStopped = true;
            }

            // daca s-a oprit in acest frame muzica si nu era oprita inainte
            if(audioOn == false && musicWasStopped == false) {
                stopMusic();
            }
            if(audioOn == true && musicWasStopped == true){
                musicWasStopped = false;
                //pornim muzica
                startMusic();
            }



        }catch ( Exception e ) {
            System.out.println("Eroare la database");
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }


    public void stopMusic(){
        Connection connection = null;
        Statement stmt = null;

        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:game.db");
            connection.setAutoCommit(true);
            stmt = connection.createStatement();

            stmt.executeUpdate("UPDATE Settings set Value = 0 where Name = 'Sound';");

            clip.stop();
            audioOn = false;

            stmt.close();
            connection.close();
        }catch ( Exception e ) {
            System.out.println("Eroare la oprire muzica database");
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }

    }
}