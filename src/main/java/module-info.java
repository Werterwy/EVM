module com.example.computer_assembly {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.computer_assembly to javafx.fxml;
    exports com.example.computer_assembly;
}