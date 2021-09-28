module fr.theo {
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    opens fr.theo to javafx.fxml;
    exports fr.theo;
}