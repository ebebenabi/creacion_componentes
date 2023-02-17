package com.example.creacion_componentes;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CeldasController {
    @FXML VBox vBox_celdas;
    public void generarCb(Connection con, ArrayList<String> columnas, TableView<List<String>> tv, String tabActual) {
        for (String columna : columnas) {
            CheckBox c = new CheckBox(columna);
            c.setOnAction(e -> {
                if (c.isSelected()) {
                    seleccionado(c, tv);
                } else {
                    deseleccionado(con, c, tabActual, tv);
                }
            });
            vBox_celdas.getChildren().add(c);
        }
    }

    private void seleccionado(CheckBox c, TableView<List<String>> tv) {
        String nombreColumna = c.getText(); // Nombre de la columna que buscas
        TableColumn<?, ?> columnaBuscada = null;
        for (TableColumn<?, ?> col : tv.getColumns()) {
            if (col.getText().equals(nombreColumna)) {
                columnaBuscada = col;
                break;
            }
        }
        if (columnaBuscada != null) {
            columnaBuscada.setVisible(false);
        }
    }

    private void deseleccionado(Connection con, CheckBox c, String tabActual, TableView<List<String>> tv) {
        String nombreColumna = c.getText(); // Nombre de la columna que buscas
        TableColumn<?, ?> columnaBuscada = null;
        for (TableColumn<?, ?> col : tv.getColumns()) {
            if (col.getText().equals(nombreColumna)) {
                columnaBuscada = col;
                break;
            }
        }
        if (columnaBuscada != null) {
            columnaBuscada.setVisible(true);
        }
    }
}
