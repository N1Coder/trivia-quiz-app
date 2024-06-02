package com.main.triviaquizapp.store;

import com.main.triviaquizapp.model.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DataStore {
    private static List<Score> topScores = new ArrayList<>();

    public static List<Score> getTopScores() {
        return new ArrayList<>(topScores);
    }

    public static void addHighScore(String playerName, Score score) {
        score.setPlayerName(playerName);
        topScores.add(score);
        Collections.sort(topScores, Comparator.comparingInt(Score::getScore).reversed());

        if (topScores.size() > 5) {
            topScores = topScores.subList(0, 5);
        }
    }

    public static boolean isHighScore(Score score) {
        if (topScores.size() < 5) {
            return true;
        }
        int lowestHighScore = topScores.get(topScores.size() - 1).getScore();
        return score.getScore() > lowestHighScore;
    }
}
