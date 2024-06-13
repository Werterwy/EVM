package com.example.computer_assembly;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AssemblyController {


    @FXML
    private ImageView assemblyImageView;

    @FXML
    private Button startMainButton;

    @FXML
    public void initialize() {
        // Load the instruction image
        Image instructionImage = new Image(getClass().getResource("/motherboard.png").toExternalForm());
        if (instructionImage != null) {
            assemblyImageView.setImage(instructionImage);
        } else {
            System.err.println("Ошибка: Не удалось загрузить изображение instruction.png");
        }
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
}
