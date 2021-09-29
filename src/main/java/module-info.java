module fr.theo {
    requires java.sql;
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    opens fr.theo.data to javafx.base;
    opens fr.theo.control to javafx.fxml;
    exports fr.theo;
}