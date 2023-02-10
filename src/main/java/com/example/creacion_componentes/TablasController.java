package com.example.creacion_componentes;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import java.sql.*;
import java.util.ArrayList;

public class TablasController {
    //@FXML public static TableView tabla;
    @FXML private TableView tv;
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
                System.out.println(colName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("NÚMERO DE COLUMNAS: " + col);
        return col;
    }

}
