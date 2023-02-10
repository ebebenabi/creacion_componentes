package com.example.creacion_componentes;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.creacion_componentes.TablasController.columnas;

public class HelloController implements Initializable {

    @FXML public BbddController refBbddController;
    //@FXML public TablasController refTablasController;
    @FXML public Pane panelBbdd;
    @FXML public Pane panelTablas;
    @FXML public Button btnVerTabla;
    @FXML public Button btnVerBbdd;
    @FXML public Button btnConectar;
    @FXML public Button btnCargar;

    public ArrayList<String> columnas;

    Connection con = null;
    static String t;

    public ArrayList<String> tablas = new ArrayList<>();

    public void vaciarArray() {
        for (int i = 0; i < 4; i++) {
            tablas.add(null);
        }
    }
    public void verInicio() {
        panelBbdd.setVisible(true);
        panelTablas.setVisible(false);
    }

    public void verTabla() {
        panelTablas.setVisible(true);
        panelBbdd.setVisible(false);
    }

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
        //vaciarArray();
        refBbddController.cbTabla.getItems().clear();
        while (res.next()) {
            refBbddController.cbTabla.getItems().add(res.getString(3));
        }
    }

    @FXML
    public void onCargarButtonClick() throws SQLException {
        t = (String) refBbddController.cbTabla.getValue();
        columnas(con, t);
        mapas(con, t);
    }

    public static void mapas(Connection con, String nCol) {
        //HashMap<String, String> map = new HashMap<>();
        //map.put("columna", "dato");

        /*ResultSet result = null;
        try {
            PreparedStatement st = con.prepareStatement("SELECT * FROM usuarios");
            result = st.executeQuery();

            while (result.next()) {
                System.out.println(result.getString("id_user"));
                id = result.getInt("id_user");
                nombre = result.getString("nombre");
                usuario = result.getString("usuario");
                contrasena = result.getString("contraseña");
                cargo = result.getString("cargo");
                fecha_alta = result.getString("fecha_alta");
                fecha_baja = result.getString("fecha_baja");

                HospitalUsers h = new HospitalUsers(id,nombre,usuario,contrasena,cargo,fecha_alta,fecha_baja);
                hospital.add(h);
            }

            tvDatos.setItems(hospital);

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }*/
    }

    @FXML
    public void onVerTablaButtonClick() {
        verTabla();
    }

    @FXML
    public void onVerInicioButtonClick() {
        verInicio();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        verInicio();
    }
}