package com.main.triviaquizapp.controller;

import com.main.triviaquizapp.store.DataStore;
import com.main.triviaquizapp.model.Score;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class InputPlayerController {

    @FXML
    private TextField playerNameTextField;

    @FXML
    private Button btnMenuStart;

    private Score score;

    @FXML
    public void initialize() {

    }

    public void setScore(Score score) {
        this.score = score;
    }

    @FXML
    private void submitNickname(ActionEvent event) {
        String playerName = playerNameTextField.getText().trim();

        if (playerName.isEmpty()) {
            showAlert("Validation Error", "Input Your NickName!!!", AlertType.ERROR);
            return;
        }

        if (playerName.length() < 3) {
            showAlert("Validation Error", "Nicknames must be at least 3 characters long", AlertType.ERROR);
            return;
        }

        if (!playerName.matches("[a-zA-Z0-9_ ]+")) {
            showAlert("Validation Error", "Nicknames can only contain letters, numbers, spaces, and underscore (_)", AlertType.ERROR);
            return;
        }

        if (DataStore.isHighScore(score)) {
            DataStore.addHighScore(playerName, score);
        }

        switchToMenuView(event);
    }

    private void showAlert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    private void switchToMenuView(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/main/triviaquizapp/menu-view.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load menu-view.fxml", AlertType.ERROR);
        }
    }
}
