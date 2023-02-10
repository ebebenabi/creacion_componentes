package com.example.creacion_componentes;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import java.sql.*;
import static com.example.creacion_componentes.HelloController.t;


public class TablasController {
    //@FXML public static TableView tabla;
    @FXML private TableView tv;
    static int col = 0;
    String table = t;

    public static int columnas(Connection con, String table) {
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
