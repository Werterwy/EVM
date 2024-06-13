package com.example.computer_assembly;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button startAssemblyButton;
    @FXML
    private Button startDisassemblyButton;
    @FXML
    private Button instructionsButton;
    @FXML
    private ImageView instructionImageView;
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


    @FXML
    protected void onStartAssemblyClick() {
        System.out.println("Начать сборку clicked");

        // Close current application and start InstructionApplication
        Platform.runLater(() -> {
            try {
                AssemblyApplication assemblyApp = new AssemblyApplication();
                assemblyApp.start(new Stage());
                Stage stage = (Stage) startAssemblyButton.getScene().getWindow();
                stage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    protected void onStartDisassemblyClick() {
        System.out.println("Начать разборку clicked");

        // Close current application and start InstructionApplication
        Platform.runLater(() -> {
            try {
                DisassemblyApplication disasemblyApp = new DisassemblyApplication();
                disasemblyApp.start(new Stage());
                Stage stage = (Stage) startDisassemblyButton.getScene().getWindow();
                stage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    @FXML
    protected void onInstructionsClick() {
        System.out.println("Инструкция clicked");

        // Close current application and start InstructionApplication
        Platform.runLater(() -> {
            try {
                InstructionApplication instructionApp = new InstructionApplication();
                instructionApp.start(new Stage());
                Stage stage = (Stage) instructionsButton.getScene().getWindow();
                stage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


}