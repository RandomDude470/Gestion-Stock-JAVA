module com.example.gestionstock {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires org.postgresql.jdbc;

    opens com.example.gestionstock to javafx.fxml;
    opens com.db.utils to javafx.base;
    exports com.db.utils;
    exports com.example.gestionstock;
}