package com.example.computer_assembly;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssemblyController {

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
    private ImageView assemblyMotherboardImageView;
    @FXML
    private ImageView point1;
    @FXML
    private ImageView point2;
    @FXML
    private ImageView point3;
    @FXML
    private ImageView point4;
    @FXML
    private ImageView point5;
    @FXML
    private Button endButton;
    @FXML
    private Label timerLabel;

    private double mouseOffsetX;
    private double mouseOffsetY;

    private double startX;
    private double startY;

    private boolean isCPUSet = false;
    private boolean isRAMSet = false;
    private boolean isBlokSet = false;
    private boolean isHardDiskSet = false;
    private boolean isClipartSet = false;

    private int isCheckComponents = 0;

    private int correctComponents = 0;

    private int mistakeNum = 0;

    private Timeline timeline;
    private int timeSeconds = 300; // 5 minutes

    @FXML
    public void initialize() {
        initMap();

        makeDraggable(assemblyClipartImageView);
        makeDraggable(assemblyCPUImageView);
        makeDraggable(assemblyRAMImageView);
        makeDraggable(assemblyBlokImageView);
        makeDraggable(assemblyHardDiskImageView);

        startTimer();
    }

    private void makeDraggable(ImageView imageView) {
        imageView.setOnMousePressed(this::onDragStart);
        imageView.setOnMouseDragged(this::onDrag);
        imageView.setOnMouseReleased(event -> onDragEnd(event, imageView));
    }

    private void onDragStart(MouseEvent event) {
        ImageView imageView = (ImageView) event.getSource();
        Bounds parentBounds = imageView.getParent().getLayoutBounds();
        mouseOffsetX = event.getSceneX() - imageView.getX();
        mouseOffsetY = event.getSceneY() - imageView.getY();
        imageView.setOpacity(0.5);
        imageView.toFront(); // Переместить изображение на передний план
    }

    private void onDrag(MouseEvent event) {
        ImageView imageView = (ImageView) event.getSource();
        imageView.setX(event.getSceneX() - mouseOffsetX);
        imageView.setY(event.getSceneY() - mouseOffsetY);
        //System.out.println("Dragging " + imageView.getId() + " to: X=" + imageView.getX() + ", Y=" + imageView.getY());
    }

    private HashMap<ImageView, ImageView> pairs;

    private void initMap() {
        pairs = new HashMap<>();

        pairs.put(assemblyCPUImageView, point1);
        pairs.put(assemblyBlokImageView, point2);
        pairs.put(assemblyRAMImageView, point3);
        pairs.put(assemblyHardDiskImageView, point4);
        pairs.put(assemblyClipartImageView, point5);
    }

    private boolean isCorrect(ImageView imageView, double mouseX, double mouseY) {
        double startX = pairs.get(imageView).getLayoutX();
        double startY = pairs.get(imageView).getLayoutY();
        return mouseX >= startX && mouseX <= startX + 46 && mouseY >= startY && mouseY <= startY + 46;
    }

    private void onDragEnd(MouseEvent event, ImageView imageView) {
        imageView.setOpacity(1.0);
        double mouseX = event.getSceneX();
        double mouseY = event.getSceneY();
        if (isCorrect(imageView, mouseX, mouseY)) {
            System.out.println("YOU PUT "+imageView.getId()+"Correctly");
            imageView.setVisible(false);
            pairs.get(imageView).setVisible(false);
            isCheckComponents++;
            correctComponents++;
        }else{
            isErrorOutputCom(imageView, mouseX, mouseY);
        }

        if(isCheckComponents == 5)
            checkAssembly();
    }

    private boolean isUnCorrect(ImageView imageView, double mouseOffsetX, double mouseOffsetY, ImageView pointIndex){
        double startX = pointIndex.getLayoutX();
        double startY = pointIndex.getLayoutY();
        return mouseOffsetX >= startX && mouseOffsetX <= startX + 46 && mouseOffsetY >= startY && mouseOffsetY <= startY + 46;
    }


    private void isErrorOutputCom(ImageView imageView, double mouseX, double mouseY) {
        imageView.setOpacity(1.0);

        List<ImageView> pointList = new ArrayList<>();
        pointList.add(point1);
        pointList.add(point2);
        pointList.add(point3);
        pointList.add(point4);
        pointList.add(point5);

        for (ImageView view : pointList) {
            if (isUnCorrect(imageView, mouseX, mouseY, view)) {
                mistakeNum++;
            }
        }
    }

    private void checkAssembly() {
            showResult();
    }
    private void showResult() {

        int result = correctComponents - mistakeNum;
        if(result < 0 ){
            result = 0;
        }
        // Показать диалог с результатами
        showResultDialog(result, mistakeNum);
    }

    private void showResultDialog(int correct, int incorrect) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Результаты сборки");
        alert.setHeaderText(null);
        alert.setContentText("Правильно размещено: " + correct + "\nНеправильно размещено: " + incorrect + "\nВаша оценка: " + correct);

        alert.showAndWait();

        Platform.runLater(() -> {
            try {
                HelloApplication instructionApp = new HelloApplication();
                instructionApp.start(new Stage());
                Stage stage = (Stage) endButton.getScene().getWindow();
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

    private void startTimer() {
        timerLabel.setText("Время: " + (timeSeconds));
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            timeSeconds--;
            timerLabel.setText( "Время: " + formatTime(timeSeconds));
            if (timeSeconds <= 0) {
                timeline.stop();
                showResult();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    @FXML
    private void onEndMainClick() {
        timeline.stop();
        showResult();
    }
}

