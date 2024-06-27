package com.example.computer_assembly;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Assembly2Application extends Application {

    private int correct;
    private int incorrect;
    private int timeSeconds;

    public Assembly2Application() {
        // Default constructor
    }

    public Assembly2Application(int correct, int incorrect, int timeSeconds) {
        this.correct = correct;
        this.incorrect = incorrect;
        this.timeSeconds = timeSeconds;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("assembly-view2.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);

        // Получить контроллер и передать значения
        Assembly2Controller controller = fxmlLoader.getController();

        controller.setValues(correct, incorrect, timeSeconds);

        stage.setTitle("Computer Assembly Trainer");
        stage.setScene(scene);
        stage.setResizable(false);  // Запретить изменение размера окна
        stage.show();

        controller.startTimer();
    }
}
