module HelloWorldFX {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.base;
    requires java.sql;
    requires sqlite.jdbc;
    opens controller;

}