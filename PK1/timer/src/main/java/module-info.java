module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    opens com to javafx.fxml;
    exports com;
}