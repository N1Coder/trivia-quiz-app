package com.main.triviaquizapp.model;

import java.time.Duration;

public class Score {
    private int correctAnswers;
    private int timeMinutes;
    private int timeSeconds;
    private int score;
    private String time;
    private String playerName;

    // Constructor for predefined score and time
    public Score(int score, String time) {
        this.score = score;
        this.time = time;
    }

    // Default constructor for tracking correct answers
    public Score() {
        this.correctAnswers = 0;
    }

    public void addCorrectAnswer(Option option) {
        if (option.getCorrect()) {
            correctAnswers++;
        }
    }

    public int calculateScore(int totalSeconds) {
        // Base score for 10 correct answers
        int baseScore = correctAnswers * 100;
        // Subtract 5 points for each second taken
        int timePenalty = totalSeconds * 5;
        // Calculate final score
        score = baseScore - timePenalty;
        // Ensure score is not negative
        if (score < 0) {
            score = 0;
        }
        return score;
    }

    public void setTime(int minutes, int seconds) {
        this.timeMinutes = minutes;
        this.timeSeconds = seconds;
        this.time = String.format("%02d:%02d", timeMinutes, timeSeconds);
    }

    public int getScore() {
        return score;
    }

    public String getTime() {
        return time;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void printScore() {
        System.out.println("Current Score: " + getScore());
        if (correctAnswers >= 10) {
            System.out.println("Time taken for 10 correct answers: " + timeMinutes + " minutes and " + timeSeconds + " seconds.");
        }
    }

    public int getTimeMinutes() {
        return timeMinutes;
    }

    public int getTimeSeconds() {
        return timeSeconds;
    }

    public boolean isCompleted() {
        return correctAnswers >= 10;
    }
}