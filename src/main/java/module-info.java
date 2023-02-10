module com.example.creacion_componentes {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.creacion_componentes to javafx.fxml;
    exports com.example.creacion_componentes;
}