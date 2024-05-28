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
    private Button btnMenuStart, btnMenuLeaderBoard, btnMenuExit;

    @FXML
    private void toQuiz(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/main/triviaquizapp/play-view.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setResizable(false);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            sound.playMusic("btnMenuOnClick.wav", 1f);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load play-view.fxml or play button click sound");
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
            System.err.println("Unsupported audio file format for play button click sound");
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            System.err.println("Audio line unavailable for play button click sound");
        }
    }

    @FXML
    private void toLeaderBoard(ActionEvent event) {
        try {
            // Cetak path yang akan diakses untuk memastikan path benar
            System.out.println("Loading Highscore-view.fxml from path: " +
                    Objects.requireNonNull(getClass().getResource("/com/main/triviaquizapp/Highscore-view.fxml")));

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/main/triviaquizapp/Highscore-view.fxml")));
            Stage stage = new Stage();
            stage.setResizable(false);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            sound.playMusic("btnMenuOnClick.wav", 1f);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to load Highscore-view.fxml or leaderboard button click sound");
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
            System.err.println("Unsupported audio file format for leaderboard button click sound");
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            System.err.println("Audio line unavailable for leaderboard button click sound");
        }
    }

    @FXML
    private void exitApplication(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void setUpButton(Button button) {
        button.setOnMouseEntered(event -> {
            try {
                sound.playMusic("hoverButtonSound.wav", 1f);
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
                System.err.println("Unsupported audio file format for hover button sound");
            } catch (LineUnavailableException e) {
                e.printStackTrace();
                System.err.println("Audio line unavailable for hover button sound");
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Failed to load hover button sound");
            }
        });
    }

    @FXML
    private void initialize() {
        setUpButton(btnMenuStart);
        setUpButton(btnMenuLeaderBoard);
        setUpButton(btnMenuExit);

        btnMenuStart.setOnAction(event -> {
            try {
                toQuiz(event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        btnMenuLeaderBoard.setOnAction(event -> {
            try {
                toLeaderBoard(event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        btnMenuExit.setOnAction(event -> {
            exitApplication(event);
        });
    }
}
