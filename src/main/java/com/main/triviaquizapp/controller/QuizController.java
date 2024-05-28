package com.main.triviaquizapp.controller;

import com.main.triviaquizapp.model.DataStore;
import com.main.triviaquizapp.model.Option;
import com.main.triviaquizapp.model.Question;
import com.main.triviaquizapp.model.Quiz;
import com.main.triviaquizapp.model.Score;
import com.main.triviaquizapp.utils.jdm.QuestionJDM;
import com.main.triviaquizapp.utils.music.Music;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class QuizController {
    private Quiz quizQuestions;
    private Random random;
    private Question currentQuestion;
    private Set<Question> displayedQuestions;
    private List<Option> displayedOptions;
    private String numberOfQuestion = "01";
    private int elapsedSecond = 0;
    private int timeMinutes;
    private int timeSeconds;
    private Score score;
    private Instant quizStartTime;

    Music sound = new Music();

    public QuizController() throws Exception {
        quizQuestions = new QuestionJDM().getQuizData();
        random = new Random();
        displayedQuestions = new HashSet<>();
        score = new Score();
        shuffleQuestions();
    }

    private void shuffleQuestions() throws Exception {
        List<Question> shuffledQuestions = new ArrayList<>(quizQuestions.getQuestions());
        Collections.shuffle(shuffledQuestions);
        currentQuestion = shuffledQuestions.stream()
                .filter(q -> !displayedQuestions.contains(q))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No more questions to display"));
        displayedQuestions.add(currentQuestion);
    }

    public String getQuestionText() {
        return currentQuestion.getQuestionText();
    }

    public List<Option> getOptions() {
        return currentQuestion.getOptions();
    }

    private boolean isCorrectOption(Option option) {
        return option.getCorrect() == Boolean.TRUE;
    }

    public void correctAnswer(ActionEvent actionEvent, Option selectedOption) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if (isCorrectOption(selectedOption)) {
            System.out.println("Jawaban benar!");
            sound.playMusic("correct.wav",  1f);

            score.addCorrectAnswer(selectedOption);

            if (score.isCompleted()) {
                System.out.println("Kuis selesai!");
                Duration totalDuration = Duration.between(quizStartTime, Instant.now());
                int finalScore = score.calculateScore(totalDuration); // Update and get the final score
                System.out.println("Skor akhir: " + finalScore);
                System.out.println("Waktu yang diambil: " + score.getTimeMinutes() + " menit dan " + score.getTimeSeconds() + " detik.");
                stopTimer();
                return;
            }

            score.addCorrectAnswer(selectedOption);

            if (score.isCompleted()) {
                System.out.println("Kuis selesai!");
                Duration totalDuration = Duration.between(quizStartTime, Instant.now());
                int finalScore = score.calculateScore(totalDuration); // Update and get the final score
                System.out.println("Skor akhir: " + finalScore);
                System.out.println("Waktu yang diambil: " + score.getTimeMinutes() + " menit dan " + score.getTimeSeconds() + " detik.");
                stopTimer();

                // Periksa apakah skor tinggi
                if (DataStore.isHighScore(score)) {
                    showInputNicknameScreen(actionEvent); // Arahkan ke layar input nama
                } else {
                    switchToMenuView(actionEvent);
                }

                return;
            }

            displayedOptions.remove(selectedOption);
            try {
                shuffleQuestions();
                numberOfQuestion = increaseAndPad(numberOfQuestion);
                initialize();
            } catch (Exception e) {
                System.out.println("No more questions to display");
            }
        } else {
            System.out.println("Salah");
            sound.playMusic("wrong.wav", 1f);
        }
    }

    public static String increaseAndPad(String numberOfQuestion) {
        int increasedNumber = Integer.parseInt(numberOfQuestion) + 1;
        return String.format("%02d", increasedNumber);
    }

    public void showQuiz() {
        this.question.setText(getQuestionText());
        List<Option> options = getOptions();

        displayedOptions = new ArrayList<>(options);
        Collections.shuffle(displayedOptions);

        for (int i = 0; i < options.size(); i++) {
            Option option = displayedOptions.get(i);
            Button button = getButtonByIndex(i);
            button.setText(option.getAnswer());
            button.setOnMouseEntered(event -> {
                try {
                    sound.playMusic("hoverButtonSound.wav", 1f);
                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                    e.printStackTrace();
                }
            });
            button.setOnAction(event -> {
                try {
                    correctAnswer(event, option);
                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    Timer quizTimer;
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            elapsedSecond++;
            timeSeconds = elapsedSecond % 60;
            timeMinutes = (elapsedSecond / 60) % 60;
            second.setText(String.format("%02d", timeSeconds));
            minutes.setText(String.format("%02d", timeMinutes));
        }
    };

    public void startTimer() {
        quizTimer = new Timer();
        quizTimer.scheduleAtFixedRate(task, 1000, 1000);
    }

    public void stopTimer() {
        quizTimer.cancel();
    }

    @FXML
    Text question, number, second, minutes;
    @FXML
    Button btnOption1, btnOption2, btnOption3, btnOption4;

    @FXML
    private void initialize() throws Exception {
        quizStartTime = Instant.now(); // Start the quiz time
        showQuiz();
        number.setText(numberOfQuestion);
        startTimer();
    }

    private Button getButtonByIndex(int index) {
        switch (index) {
            case 0:
                return btnOption1;
            case 1:
                return btnOption2;
            case 2:
                return btnOption3;
            case 3:
                return btnOption4;
            default:
                throw new IllegalArgumentException("Invalid button index: " + index);
        }
    }

    private void showInputNicknameScreen(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/main/triviaquizapp/input-nickname-view.fxml"));
            Parent root = loader.load();
            InputPlayerController inputPlayerController = loader.getController();
            inputPlayerController.setScore(score);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void switchToMenuView(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/main/triviaquizapp/menu-view.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

