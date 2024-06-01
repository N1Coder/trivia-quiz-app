package com.main.triviaquizapp.model;

public class Score {
    private int correctAnswers;
    private int score;
    private Time time = new Time();
    private Player playerLeaderboard = new PlayerLeaderboard();

    // Constructor for predefined score and time
    public Score(int score, Time time) {
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
        this.time.setTimeMinutes(minutes);
        this.time.setTimeSeconds(seconds);

        this.time.setTime(String.format("%02d:%02d", time.getTimeMinutes(), time.getTimeSeconds()));
    }

    public int getScore() {
        return score;
    }

    public Time getTime() {
        return time;
    }

    public String getPlayerName() {
        return playerLeaderboard.getName();
    }

    public void setPlayerName(String playerName) {
        playerLeaderboard.setName(playerName);
    }

    public void printScore() {
        System.out.println("Current Score: " + getScore());
        if (correctAnswers >= 10) {
            System.out.println("Time taken for 10 correct answers: " + time.getTimeMinutes() + " minutes and " + time.getTimeSeconds() + " seconds.");
        }
    }

    public int getTimeMinutes() {
        return time.getTimeMinutes();
    }

    public int getTimeSeconds() {
        return time.getTimeSeconds();
    }

    public boolean isCompleted() {
        return correctAnswers >= 10;
    }
}