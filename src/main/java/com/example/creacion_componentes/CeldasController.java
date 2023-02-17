package com.example.creacion_componentes;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class CeldasController {
    @FXML VBox vBox_celdas;
    public void generarCb(ArrayList<String> columnas, TableView<List<String>> tv) {
        for (String columna : columnas) {
            CheckBox c = new CheckBox(columna);
            c.setOnAction(e -> {
                if (c.isSelected()) {
                    TableColumn col = buscarColumna(columna, tv);
                    if (col != null) {
                        tv.getColumns().remove(col);
                    }
                } else {
                    //Añadir columna
                }
            });
            vBox_celdas.getChildren().add(c);
        }
    }

    private TableColumn buscarColumna(String nombreColumna, TableView tv) {
        for (Object col : tv.getColumns()) {
            if (((TableColumn)col).getText().equals(nombreColumna)) {
                return (TableColumn)col;
            }
        }
        return null;
    }


}
