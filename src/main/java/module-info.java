module com.example.trabalho_dss {
    requires javafx.controls;
    requires javafx.fxml;


    opens sgcree.controllers to javafx.fxml;
    exports sgcree.controllers;
    exports sgcree;
    opens sgcree to javafx.fxml;
}