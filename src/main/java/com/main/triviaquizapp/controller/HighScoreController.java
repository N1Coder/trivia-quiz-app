package com.main.triviaquizapp.controller;

import com.main.triviaquizapp.model.DataStore;
import com.main.triviaquizapp.model.Score;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class HighScoreController {
    @FXML
    private GridPane highScoreGridPane;
    @FXML
    private Button btnExitLeaderboard;

    @FXML
    public void initialize() {
        updateHighScoreGridPane();
    }

    private void addHighScoreRow(GridPane gridPane, String playerName, Score score, int rowIndex) {
        Text rankText = new Text(String.valueOf(rowIndex + 1));
        TextFlow rankFlow = new TextFlow(rankText);

        Text nameText = new Text(playerName);
        TextFlow nameFlow = new TextFlow(nameText);

        Text timeText = new Text(score.getTime());
        TextFlow timeFlow = new TextFlow(timeText);

        Text scoreText = new Text(String.valueOf(score.getScore()));
        TextFlow scoreFlow = new TextFlow(scoreText);

        gridPane.add(rankFlow, 0, rowIndex);
        gridPane.add(nameFlow, 1, rowIndex);
        gridPane.add(timeFlow, 2, rowIndex);
        gridPane.add(scoreFlow, 3, rowIndex);
    }

    private void updateHighScoreGridPane() {
        List<Score> topScores = DataStore.getTopScores();
        highScoreGridPane.getChildren().clear();
        for (int i = 0; i < topScores.size(); i++) {
            Score score = topScores.get(i);
            addHighScoreRow(highScoreGridPane, score.getPlayerName(), score, i);
        }
    }

    @FXML
    private void exitToMainMenu(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/main/triviaquizapp/menu-view.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
