/*
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
//        Image pointImage = new Image(getClass().getResource("/point.png").toExternalForm());
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

   */
/* @FXML
    private void onEndMainClick() {
        // Логика для завершения сборки
        System.out.println("Сборка завершена");
        // Вы можете также остановить таймер, если нужно
        timeline.stop();
        showResults();
    }*//*

}
*/

package com.example.computer_assembly;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

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

    private Timeline timeline;
    private int timeSeconds = 300; // 5 minutes

    @FXML
    public void initialize() {
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

    private void onDragEnd(MouseEvent event, ImageView imageView) {
        imageView.setOpacity(1.0);
       // System.out.println(assemblyCPUImageView + "\n X " + imageView.getX() + " Y " + imageView.getY());

        if (imageView.equals(assemblyCPUImageView) && !isCPUSet ) {
            startX = imageView.getX() + 118;
            startY = imageView.getY() + 298;
           // layoutX="771.0" layoutY="324.0">
            if(startX <= 771 &&  771 >= startX -46 && startY <= 324 && 324 >= startY - 46) {
                isCPUSet = true;
                imageView.setVisible(false);
                point1.setVisible(false);
                isCheckComponents++;
                System.out.println(isCPUSet + " isCPUSet");
            }else{
                isErrorOutputCom(imageView);
            }
        } else if (imageView.equals(assemblyBlokImageView) && !isBlokSet) {
            // layoutX="378.0" layoutY="370.0"
            startX = imageView.getX() + 984;
            startY = imageView.getY() + 116;


            if (startX <= 378 && 378  >= startX - 46 && startY <= 370  && 370 >= startY - 46) {
                isBlokSet = true;
                imageView.setVisible(false);
                point2.setVisible(false);
                isCheckComponents++;
                System.out.println(isBlokSet + " isBlokSet");

            }else{
                isErrorOutputCom(imageView);
            }
        } else if (imageView.equals( assemblyRAMImageView) && !isRAMSet) {
            // layoutX="725.0" layoutY="465.0"

            startX = imageView.getX() + 65;
            startY = imageView.getY() + 488;
            if (startX <= 725 && 725 >= startX - 46 && startY <= 465 && 465 >= startY - 46) {
                isRAMSet = true;
                imageView.setVisible(false);
                point3.setVisible(false);
                isCheckComponents++;
                System.out.println(isRAMSet + " isRAMSet");
            }else{
                isErrorOutputCom(imageView);
            }
        } else if (imageView.equals(assemblyHardDiskImageView) && !isHardDiskSet) {
            // layoutX="605.0" layoutY="190.0"
          // layoutX="964.0" layoutY="298.0"
            startX = imageView.getX() + 964;
            startY = imageView.getY() + 298;
            if (startX <= 605 && 605 >=  startX - 46  && startY <= 190 && 190 >= startY - 46) {
                isHardDiskSet = true;
                imageView.setVisible(false);
                point4.setVisible(false);
                isCheckComponents++;
                System.out.println(isHardDiskSet + " isHardDiskSet");
            }else{
                isErrorOutputCom(imageView);
            }
        } else if (imageView.equals(assemblyClipartImageView) && !isClipartSet) {
            // layoutX="448.0" layoutY="262.0"
            //layoutX="65.0" layoutY="91.0"
            startX = imageView.getX() + 65;
            startY = imageView.getY() + 91;
            if (startX <= 448 && 448 >= startX - 46 && startY <= 262 &&  262 >= startY - 46) {
                isClipartSet = true;
                imageView.setVisible(false);
                point5.setVisible(false);
                isCheckComponents++;
                System.out.println(isClipartSet + " isClipartSet");
            }else{
                isErrorOutputCom(imageView);
            }
        }
        if(isCheckComponents == 5)
            checkAssembly();
    }

    private void isErrorOutputCom(ImageView imageView) {
        if (startX <= 771 && 771 >= startX - 46 && startY <= 324 && 324 >= startY - 46) {
            imageView.setVisible(false);
            point1.setVisible(false);
            isCheckComponents++;
            System.out.println("point1" + imageView.getId());
        } else if (startX <= 378 && 378  >= startX - 46 && startY <= 370  && 370 >= startY - 46) {
            imageView.setVisible(false);
            point2.setVisible(false);
            isCheckComponents++;
        } else if (startX <= 725 && 725 >= startX - 46 && startY <= 465 && 465 >= startY - 46) {
            imageView.setVisible(false);
            point3.setVisible(false);
            isCheckComponents++;
        } else if (startX <= 605 && 605 >=  startX - 46  && startY <= 190 && 190 >= startY - 46) {
            imageView.setVisible(false);
            point4.setVisible(false);
            isCheckComponents++;
        } else if (startX <= 448 && 448 >= startX - 46 && startY <= 262 &&  262 >= startY - 46) {
            imageView.setVisible(false);
            point5.setVisible(false);
            isCheckComponents++;
        }
    }

    private void checkAssembly() {
            showResult();
    }
    private void showResult() {
        // Рассчитайте оценку в зависимости от правильно размещенных компонентов
        int correctComponents = 0;
        if (isCPUSet) correctComponents++;
        if (isRAMSet) correctComponents++;
        if (isBlokSet) correctComponents++;
        if (isHardDiskSet) correctComponents++;
        if (isClipartSet) correctComponents++;

        int result = correctComponents;

        // Показать диалог с результатами
        showResultDialog(result, 5 - result);
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

       /* timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            timeSeconds--;
            timerLabel.setText("Время: " + timeSeconds);
            if (timeSeconds <= 0) {
                timeline.stop();
                // Обработка завершения времени
                System.out.println("Время истекло!");
                // Example: showTimeUpDialog();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();*/
    }

    @FXML
    private void onEndMainClick() {
        timeline.stop();
        showResult();
    }
}

