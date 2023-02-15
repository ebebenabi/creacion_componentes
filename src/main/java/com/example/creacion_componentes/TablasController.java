package com.example.creacion_componentes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TablasController {
    //@FXML private TableView tv;
    @FXML private TableView<HashMap<String, String>> tv;
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

    public void mapas(Connection con) throws SQLException {
        List<HashMap<String, String>> maps = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();
        String value;
        ResultSet result = null;
        try {
            PreparedStatement st = con.prepareStatement("SELECT * FROM " + tabActual);
            result = st.executeQuery();
            int aux = 0;
            while (result.next()) {
                aux++;
                try {
                    for (int i = 0; i < columnas.size(); i++) {
                        value = result.getString(columnas.get(i));
                        map.put("col" + i, value);
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
            TableColumn<HashMap<String, String>, Object> col = new TableColumn<>(columnas.get(i));
            col.setCellValueFactory(new MapValueFactory(columnas.get(i)));
            tv.getColumns().add(col);
        }

        ObservableList<HashMap<String, String>> data = FXCollections.observableList(maps);
        tv.setItems(data);
    }

}
