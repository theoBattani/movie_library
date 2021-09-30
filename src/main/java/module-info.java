module fr.theo {
    requires java.sql;
    requires javafx.fxml;
    requires javafx.media;
    requires transitive javafx.controls;

    opens fr.theo.util to javafx.base;
    opens fr.theo.control to javafx.fxml;
    exports fr.theo.data.table;
    exports fr.theo.util;
    exports fr.theo;
}