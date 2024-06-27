package com.example.computer_assembly;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class InstructionController {

    @FXML
    private Button startMainButton;

    @FXML
    public void initialize() {

    }

    @FXML
    protected void onBackMainClick() {

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
