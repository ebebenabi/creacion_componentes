package com.example.creacion_componentes;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TablasController {
    @FXML public CeldasController refCeldasController;
    @FXML TableView<List<String>> tv;

    int col = 0;
    String tabActual;

    public ArrayList<String> tablas = new ArrayList<>();
    public ArrayList<String> columnas = new ArrayList<>();

    public void vaciarArray() {
        for (int i = 0; i < 4; i++) {
            tablas.add(null);
        }
    }
    public int columnas(Connection con, String table) {
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.createStatement();
            String sql = "SELECT * FROM " + table + ";";
            rs = stmt.executeQuery(sql);

            ResultSetMetaData rsmd = rs.getMetaData();

            int colCount = rsmd.getColumnCount();
            col = colCount;

            for (int i = 1; i <= colCount; i++){
                String colName = rsmd.getColumnName(i);
                columnas.add(colName);
                System.out.println(colName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("NÚMERO DE COLUMNAS: " + col);
        return col;
    }

    public void mapas(Connection con) {
        tv.setItems(FXCollections.emptyObservableList());
        refCeldasController.vBox_celdas.getChildren().clear();
        ArrayList<ArrayList<String>> maps = new ArrayList<>();

        String value;
        ResultSet result = null;
        try {
            PreparedStatement st = con.prepareStatement("SELECT * FROM " + tabActual);
            result = st.executeQuery();
            int aux = 0;
            while (result.next()) {
                ArrayList<String> map = new ArrayList<>();
                aux++;
                try {
                    for (int i = 0; i < columnas.size(); i++) {
                        value = result.getString(columnas.get(i));
                        map.add(value);
                        System.out.println(value);
                    }
                    maps.add(map);
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < columnas.size(); i++) {
            TableColumn<List<String>, String> col = new TableColumn<>(columnas.get(i));
            int finalI1 = i;
            col.setCellValueFactory(listStringCellDataFeatures -> new ReadOnlyStringWrapper(listStringCellDataFeatures.getValue().get(finalI1)));
            tv.getColumns().add(col);
        }

        ObservableList<List<String>> data = FXCollections.observableArrayList();
        data.addAll(maps);
        tv.setItems(data);

        refCeldasController.generarCb(con, columnas, tv, tabActual);
    }

}
