package com.main.triviaquizapp.controller;

import com.main.triviaquizapp.model.DataStore;
import com.main.triviaquizapp.model.Score;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class HighScoreController {
    @FXML
    private GridPane highScoreGridPane;
    @FXML
    private Button btnExitLeaderboard;

    @FXML
    public void initialize() {
        updateHighScoreGridPane();
    }

    private void addHighScoreRow(GridPane gridPane, String playerName, Score score, int rowIndex) {
        Text rankText = createText(String.valueOf(rowIndex + 1));
        VBox vBoxTopLeaderboard = null;

        if (String.valueOf(rowIndex + 1).equals("1")) {
            SVGPath crownSVG = createSVGPath("m20.092 14.326l.193-1.894c.103-1.011.17-1.678.117-2.099h.02c.871 0 1.578-.746 1.578-1.666C22 7.747 21.293 7 20.421 7s-1.579.746-1.579 1.667c0 .416.145.797.384 1.089c-.343.223-.792.695-1.468 1.405c-.52.547-.78.82-1.07.863a.835.835 0 0 1-.473-.07c-.268-.124-.447-.462-.804-1.139L13.527 7.25c-.22-.417-.405-.766-.572-1.047c.683-.368 1.15-1.117 1.15-1.98C14.105 2.994 13.163 2 12 2s-2.105.995-2.105 2.222c0 .864.467 1.613 1.15 1.98c-.167.282-.351.631-.572 1.048L8.59 10.816c-.358.676-.537 1.014-.805 1.139a.835.835 0 0 1-.473.07c-.29-.043-.55-.317-1.07-.864c-.676-.71-1.125-1.182-1.468-1.405c.24-.292.384-.673.384-1.09C5.158 7.747 4.45 7 3.578 7C2.708 7 2 7.746 2 8.667c0 .92.707 1.666 1.579 1.666h.019c-.054.42.014 1.088.117 2.099l.193 1.894c.107 1.051.196 2.051.306 2.952h15.572c.11-.9.199-1.901.306-2.952M10.855 22h2.29c2.985 0 4.478 0 5.474-.94c.434-.412.71-1.152.908-2.116H4.473c.198.964.473 1.704.908 2.115C6.377 22 7.87 22 10.855 22");
            vBoxTopLeaderboard = new VBox(crownSVG, rankText);
            vBoxTopLeaderboard.setAlignment(Pos.CENTER);
        }

        TextFlow rankTextFlow = creaTextFlowForRank(rowIndex + 1 == 1 ? vBoxTopLeaderboard : rankText, rowIndex + 1 == 1 ? 10 : 17);

        // nickname
        Text nameText = createText(playerName);
        TextFlow nameFlow = creaTextFlowWithBorder(nameText);

        // time
        Text timeText = createText(score.getTime().getTime());
        TextFlow timeFlow = creaTextFlowWithBorder(timeText);

        // score
        Text scoreText = createText(String.valueOf(score.getScore()));
        TextFlow scoreFlow = creaTextFlowWithBorder(scoreText);

        gridPane.add(rankTextFlow, 0, rowIndex);
        gridPane.add(nameFlow, 1, rowIndex);
        gridPane.add(timeFlow, 2, rowIndex);
        gridPane.add(scoreFlow, 3, rowIndex);
    }

    private static Text createText(String inputText) {
        Text text = new Text(inputText);
        text.setFont(Font.font("Jersey 10", 20));

        return text;
    }

    private static TextFlow creaTextFlowWithBorder(Node node) {
        TextFlow nameFlow = new TextFlow(node);
        nameFlow.setPadding(new Insets(17, 0, 0, 20));
        nameFlow.setStyle("-fx-border-width: 0 0 0 5; -fx-border-color: black;");

        return nameFlow;
    }

    private static TextFlow creaTextFlowForRank(Node node, int paddingTop) {
        TextFlow rankTextFlow = new TextFlow(node);
        rankTextFlow.setTextAlignment(TextAlignment.valueOf("CENTER"));
        rankTextFlow.setPadding(new Insets(paddingTop, 0, 0, 0));
        rankTextFlow.setStyle("-fx-background-color: #fff; -fx-background-radius: 500;");

        return rankTextFlow;
    }

    private static SVGPath createSVGPath(String path) {
        SVGPath svg = new SVGPath();
        svg.setContent(path);
        svg.setFill(Paint.valueOf("#ffdd00"));

        return svg;
    }


    private void updateHighScoreGridPane() {
        List<Score> topScores = DataStore.getTopScores();
        highScoreGridPane.getChildren().clear();
        for (int rank = 0; rank < topScores.size(); rank++) {
            Score score = topScores.get(rank);
            addHighScoreRow(highScoreGridPane, score.getPlayerName(), score, rank);
        }
    }

    @FXML
    private void exitToMainMenu(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/main/triviaquizapp/menu-view.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
