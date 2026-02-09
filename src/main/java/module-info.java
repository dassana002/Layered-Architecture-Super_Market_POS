module com.example.layeredarchitecture {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.sql;
    requires mysql.connector.j;
    requires static lombok;

    opens com.example.layeredarchitecture to javafx.fxml;
    opens com.example.layeredarchitecture.controller to javafx.fxml;
    opens com.example.layeredarchitecture.view.tdm to javafx.base;

    exports com.example.layeredarchitecture;
    exports com.example.layeredarchitecture.controller;
    exports com.example.layeredarchitecture.view.tdm;
}
