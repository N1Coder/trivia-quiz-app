package com.main.triviaquizapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TriviaQuizApp {

    public static int calculateTotalPoints(int numberOfQuestions, int difficulty, int remainingTime) {
        int PointPerJawaban = 50;
        int timeBonusPerSecond = 5;

        // Menghitung poin berdasarkan kesulitan
        int difficultyMultiplier = getDifficultyMultiplier(difficulty);

        // Menghitung total poin dari jawaban benar
        int totalPoints = numberOfQuestions + PointPerJawaban + difficultyMultiplier;

        // Menghitung bonus poin dari waktu tersisa
        int timeBonus = remainingTime * timeBonusPerSecond;

        // Menambahkan bonus poin dari waktu tersisa ke total poin
        totalPoints += timeBonus;

        return totalPoints;
    }

    private static int getDifficultyMultiplier(int difficulty) {
        switch (difficulty) {
            case 1:
                return 1; // easy
            case 2:
                return 2; // medium
            case 3:
                return 3; // hard
            }
        }


}
