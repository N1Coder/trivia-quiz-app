package com.main.triviaquizapp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DataStore {
    private static List<Score> topScores = new ArrayList<>();

    public static List<Score> getTopScores() {
        return new ArrayList<>(topScores); // Return a copy to prevent modification of original list
    }

    public static void addHighScore(String playerName, Score score) {
        score.setPlayerName(playerName);  // Set player name here as well for redundancy
        topScores.add(score);
        // Sort the scores in descending order
        Collections.sort(topScores, Comparator.comparingInt(Score::getScore).reversed());
        // Ensure the list has at most 10 scores
        if (topScores.size() > 10) {
            topScores = topScores.subList(0, 10);
        }
    }

    public static boolean isHighScore(Score score) {
        if (topScores.size() < 10) {
            return true;
        }
        int lowestHighScore = topScores.get(topScores.size() - 1).getScore();
        return score.getScore() > lowestHighScore;
    }
}
