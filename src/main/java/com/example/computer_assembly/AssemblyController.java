package com.example.computer_assembly;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AssemblyController {

    @FXML
    private Button EndButton;
    @FXML
    private ImageView assemblyImageView;
    @FXML
    private ImageView assemblyClipartImageView;
    @FXML
    private ImageView assemblyCPUImageView;
    @FXML
    private ImageView assemblyRAMImageView;
    @FXML
    private ImageView assemblyBlokImageView;

    @FXML
    private ImageView assemblyHardDiskImageView;
    @FXML
    private ImageView assemblyPoint1ImageView;
    @FXML
    private ImageView assemblyPoint2ImageView;
    @FXML
    private ImageView assemblyPoint3ImageView;
    @FXML
    private ImageView assemblyPoint4ImageView;
    @FXML
    private ImageView assemblyPoint5ImageView;

    @FXML
    private Button startMainButton;

    @FXML
    private Label timerLabel;

    private Timeline timeline;
    private int timeSeconds = 300; // 5 минут = 300 секунд

    @FXML
    public void initialize() {
//        // Load the instruction image
//        Image instructionImage = new Image(getClass().getResource("/motherboard.png").toExternalForm());
//        if (instructionImage != null) {
//            assemblyImageView.setImage(instructionImage);
//        } else {
//            System.err.println("Ошибка: Не удалось загрузить изображение instruction.png");
//        }
//
//        Image clipartimage = new Image(getClass().getResource("/clipart.png").toExternalForm());
//        assemblyClipartImageView.setImage(clipartimage);
//
//        Image cpuImage = new Image(getClass().getResource("/cpu.png").toExternalForm());
//        assemblyCPUImageView.setImage(cpuImage);
//
//        Image ramImage = new Image(getClass().getResource("/ram.png").toExternalForm());
//        assemblyRAMImageView.setImage(ramImage);
//
//        Image blokImage = new Image(getClass().getResource("/blok-pitaniya1.png").toExternalForm());
//        assemblyBlokImageView.setImage(blokImage);
//
//        Image hardDiskImage = new Image(getClass().getResource("/Hard-Disk.png").toExternalForm());
//        assemblyHardDiskImageView.setImage(hardDiskImage);
//
//        Image pointImage = new Image(getClass().getResource("/point1.png").toExternalForm());
//        assemblyPoint1ImageView.setImage(pointImage);
//        assemblyPoint2ImageView.setImage(pointImage);
//        assemblyPoint3ImageView.setImage(pointImage);
//        assemblyPoint4ImageView.setImage(pointImage);
//        assemblyPoint5ImageView.setImage(pointImage);
//
        // Инициализируем таймер

        timerLabel.setText("Время: " + (timeSeconds));
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            timeSeconds--;
            timerLabel.setText( "Время: " + formatTime(timeSeconds));
            if (timeSeconds <= 0) {
                timeline.stop();
                showResults();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();


    }

   @FXML
   protected void onBackMainClick() {
        System.out.println("Начать сборку clicked");

        // Close current application and start InstructionApplication
        Platform.runLater(() -> {
            try {
                HelloApplication mainApp = new HelloApplication();
                mainApp.start(new Stage());
                Stage stage = (Stage) startMainButton.getScene().getWindow();
                stage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    protected void onEndMainClick() {
        System.out.println("Завершить clicked");

        // Close current application and start InstructionApplication
        Platform.runLater(() -> {
            try {
                HelloApplication mainApp = new HelloApplication();
                mainApp.start(new Stage());
                Stage stage = (Stage) EndButton.getScene().getWindow();
                stage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private String formatTime(int seconds) {
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%02d:%02d", minutes, remainingSeconds);
    }

    private void showResults() {
        // Логика для перехода к результатам
        System.out.println("Переход к результатам");
        // Здесь вы можете заменить System.out.println на вызов метода для отображения результатов
    }

   /* @FXML
    private void onEndMainClick() {
        // Логика для завершения сборки
        System.out.println("Сборка завершена");
        // Вы можете также остановить таймер, если нужно
        timeline.stop();
        showResults();
    }*/
}
