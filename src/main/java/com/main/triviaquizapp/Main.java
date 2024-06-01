package com.main.triviaquizapp;

import com.main.triviaquizapp.utils.music.Music;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javax.sound.sampled.*;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Press Start!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws LineUnavailableException, UnsupportedAudioFileException, IOException {

        Music music = new Music();
        music.playMusic("backgroundMusic.wav",0.2f);
        launch();

    }

}




