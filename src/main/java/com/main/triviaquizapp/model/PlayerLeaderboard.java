package com.main.triviaquizapp.model;

import com.main.triviaquizapp.entity.Player;

public class PlayerLeaderboard extends Player {
    private int positionOnLeaderboard;

    public int getPositionOnLeaderboard() {
        return positionOnLeaderboard;
    }

    public void setPositionOnLeaderboard(int positionOnLeaderboard) {
        this.positionOnLeaderboard = positionOnLeaderboard;
    }
}
