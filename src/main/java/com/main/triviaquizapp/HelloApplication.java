package com.main.triviaquizapp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setStyle("-fx-background-color: yellow;");

        HBox scoreBox = new HBox(10);
        Label scoreLabel = new Label("Score:");
        scoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 48));
        scoreLabel.setTextFill(Color.BLACK);
        Label scoreValue = new Label("0");
        scoreValue.setFont(Font.font("Arial", FontWeight.BOLD, 48));
        scoreValue.setTextFill(Color.BLACK);
        scoreBox.getChildren().addAll(scoreLabel, scoreValue);
        scoreBox.setAlignment(Pos.TOP_LEFT);

        Label nameLabel = new Label("Player Name:");
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        TextField nameInput = new TextField();
        nameInput.setFont(Font.font("Arial", 24));

        Button submitButton = new Button("Submit");
        submitButton.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        submitButton.setStyle("-fx-background-color: #FF69B4; -fx-text-fill: white;");
        submitButton.setOnAction(e -> {
            System.out.println("Player Name: " + nameInput.getText());
            int currentScore = Integer.parseInt(scoreValue.getText());
            scoreValue.setText(String.valueOf(currentScore + 1));
        });

        VBox inputLayout = new VBox(10);
        inputLayout.getChildren().addAll(nameLabel, nameInput, submitButton);
        inputLayout.setAlignment(Pos.CENTER);

        mainLayout.getChildren().addAll(scoreBox, inputLayout);

        Scene scene = new Scene(mainLayout, 600, 400);
        primaryStage.setTitle("Input Player Name");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
