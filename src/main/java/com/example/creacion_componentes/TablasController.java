package com.example.creacion_componentes;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TablasController {
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
                columnas.add(colName);
                System.out.println(colName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("NÚMERO DE COLUMNAS: " + col);
        return col;
    }

    public void mapas(Connection con, String nCol) throws SQLException {
        HashMap<String, HashMap<String, String>> mapa = new HashMap<>();
        HashMap<String, String> map = new HashMap<>();
        map.put("columna", "dato");
        String value;
        ResultSet result = null;
        try {
            PreparedStatement st = con.prepareStatement("SELECT * FROM " + tabActual);
            result = st.executeQuery();

            while (result.next()) {
                try {
                    for (int i = 0; i < columnas.size(); i++) {
                        value = result.getString(columnas.get(i));
                        map.put("col" + i, value);
                        System.out.println(value);
                    }
                    mapa.put("registro", map);
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
