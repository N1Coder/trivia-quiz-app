package com.main.triviaquizapp.controller;

import com.main.triviaquizapp.utils.music.Music;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Objects;

public class PlayController {
    Music sound = new Music();

    @FXML
    Button btnMenuStart,btnMenuLeaderBoard,btnMenuExit;

    @FXML
    public void toQuiz(ActionEvent event) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/main/triviaquizapp/play-view.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        sound.playMusic("btnMenuOnClick.wav",1f);
    }

    @FXML
    public void setUpButton(Button button){
        button.setOnMouseEntered(event -> {
            try {
                sound.playMusic("hoverButtonSound.wav",1f);
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                e.printStackTrace();
            }
        });
    }


    @FXML
    public void initialize(){
        setUpButton(btnMenuStart);
        setUpButton(btnMenuLeaderBoard);
        setUpButton(btnMenuExit);

    }
}