package com.example.creacion_componentes;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML public BbddController refBbddController;
    @FXML public TablasController refTablasController;
    @FXML public Pane panelBbdd;
    @FXML public Pane panelTablas;
    @FXML public Button btnVerTabla;
    @FXML public Button btnVerBbdd;
    @FXML public Button btnConectar;
    @FXML public Button btnCargar;


    Connection con = null;

    public void setCon(String url) {
        Connection con = null;
        try{
            con = DriverManager.getConnection(url);
            System.out.println("Conectado.");
        } catch (Exception ex) {
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            System.out.println("Error en la conexión.");
        }

        this.con = con;
    }

    @FXML
    public void onConectarButtonClick() throws SQLException {
        setCon(refBbddController.txtBbdd.getText());
        DatabaseMetaData meta = con.getMetaData();
        ResultSet res = meta.getTables(null, "PUBLIC", null, new String[]{"TABLE"});
        refTablasController.vaciarArray();
        refBbddController.cbTabla.getItems().clear();
        String aux;
        while (res.next()) {
            aux = res.getString(3);
            refBbddController.cbTabla.getItems().add(aux);
            refTablasController.tablas.add(aux);
        }
    }

    @FXML
    public void onCargarButtonClick() {
        refTablasController.tabActual = (String) refBbddController.cbTabla.getValue();
        refTablasController.columnas(con, refTablasController.tabActual);
        refTablasController.mapas(con);
    }

    @FXML
    public void onVerTablaButtonClick() {
        panelTablas.setVisible(true);
        panelBbdd.setVisible(false);
    }

    @FXML
    public void onVerInicioButtonClick() {
        panelBbdd.setVisible(true);
        panelTablas.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        panelBbdd.setVisible(true);
        panelTablas.setVisible(false);
    }
}