package com.main.triviaquizapp.model;

import java.time.Duration;
import java.time.Instant;

public class Score {
    private int correctAnswers;
    private Instant firstAnswerTime;
    private Instant lastAnswerTime;
    private int timeMinutes;
    private int timeSeconds;
    private int score;
    private String time;

    // Constructor for predefined score and time
    public Score(int score, String time) {
        this.score = score;
        this.time = time;
    }

    // Default constructor for tracking correct answers and time
    public Score() {
        this.correctAnswers = 0;
        this.firstAnswerTime = null;
        this.lastAnswerTime = null;
    }

    public void addCorrectAnswer(Option option) {
        if (option.getCorrect()) {
            correctAnswers++;
            lastAnswerTime = Instant.now();
            if (correctAnswers == 1) {
                firstAnswerTime = lastAnswerTime;
            }
            if (correctAnswers == 10) {
                // Calculate total time taken for 10 correct answers
                Duration duration = Duration.between(firstAnswerTime, lastAnswerTime);
                timeMinutes = (int) duration.toMinutes();
                timeSeconds = (int) (duration.getSeconds() % 60);
                time = String.format("%02d:%02d", timeMinutes, timeSeconds);
                score = calculateScore(duration);
            }
        }
    }

    public int calculateScore(Duration duration) {
        int totalSeconds = (int) duration.getSeconds();
        int timeBonus = Math.max(0, 300 - totalSeconds);
        int baseScore = correctAnswers * 50;
        score = baseScore + timeBonus;
        return score;
    }

    public int getScore() {
        return score;
    }

    public String getTime() {
        return time;
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
package com.main.triviaquizapp.model;

import java.time.Duration;
import java.time.Instant;

public class Score {
    private int correctAnswers;
    private Instant firstAnswerTime;
    private Instant lastAnswerTime;
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

    // Default constructor for tracking correct answers and time
    public Score() {
        this.correctAnswers = 0;
        this.firstAnswerTime = null;
        this.lastAnswerTime = null;
    }

    public void addCorrectAnswer(Option option) {
        if (option.getCorrect()) {
            correctAnswers++;
            lastAnswerTime = Instant.now();
            if (correctAnswers == 1) {
                firstAnswerTime = lastAnswerTime;
            }
            if (correctAnswers == 10) {
                Duration duration = Duration.between(firstAnswerTime, lastAnswerTime);
                timeMinutes = (int) duration.toMinutes();
                timeSeconds = (int) (duration.getSeconds() % 60);
                time = String.format("%02d:%02d", timeMinutes, timeSeconds);
                score = calculateScore(duration);
            }
        }
    }

    public int calculateScore(Duration duration) {
        int totalSeconds = (int) duration.getSeconds();
        int timeBonus = Math.max(0, 300 - totalSeconds);
        int baseScore = correctAnswers * 50;
        score = baseScore + timeBonus;
        return score;
    }

    public int getScore() {
        return score;
    }

    public String getTime() {
        return time;
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

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
