module project_gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    opens project_gui to javafx.fxml;

    exports project_gui;

    opens model to javafx.fxml;

    exports model;
}
