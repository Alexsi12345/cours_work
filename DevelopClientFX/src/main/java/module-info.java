module AirportClientFX {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.google.gson;

    opens program.controllers to javafx.fxml;
    exports program.controllers;
    opens program to javafx.fxml;
    exports program;
    opens program.models to javafx.fxml;
    exports program.models;
}