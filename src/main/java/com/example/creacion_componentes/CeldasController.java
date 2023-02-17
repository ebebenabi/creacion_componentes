package com.example.creacion_componentes;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class CeldasController {
    @FXML VBox vBox_celdas;
    public void generarCb(ArrayList<String> columnas) {
        for (String columna : columnas) {
            CheckBox c = new CheckBox(columna);
            c.setOnAction(e -> {
                if (c.isSelected()) {
                    System.out.println("El CheckBox " + columna + " ha sido seleccionado.");
                } else {
                    System.out.println("El CheckBox " + columna + " ha sido deseleccionado.");
                }
            });
            vBox_celdas.getChildren().add(c);
        }
    }
}
