module com.example.algorithms {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.algorithms to javafx.fxml;
    exports com.example.algorithms;
}