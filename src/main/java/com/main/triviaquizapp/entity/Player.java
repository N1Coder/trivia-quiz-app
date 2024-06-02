package com.main.triviaquizapp.entity;

import com.main.triviaquizapp.model.Time;

public abstract class Player {
    private String name;
    private Time timeToFinish;
    private int playerScore;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getTimeToFinish() {
        return timeToFinish;
    }

    public void setTimeToFinish(Time timeToFinish) {
        this.timeToFinish = timeToFinish;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }
}
