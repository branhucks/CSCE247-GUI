module project_gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    opens project_gui to javafx.fxml;

    exports project_gui;

    opens controllers to javafx.fxml;

    exports controllers;

    opens model to javafx.fxml;

    exports model;
}
