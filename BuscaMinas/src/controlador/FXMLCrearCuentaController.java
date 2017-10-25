/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import buscaminas.BuscaMinasApp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import modelo.Dialogo;

/**
 * FXML Controller class
 *
 * @author jonathan
 */
public class FXMLCrearCuentaController implements Initializable, FXMLControlable {

    @FXML
    private TextField tfdContrasena;
    @FXML
    private TextField tfdCorreo;
    @FXML
    private DatePicker dprNacimiento;
    @FXML
    private ComboBox<String> cbxGenero;
    @FXML
    private TextField tfdJugador;
    @FXML
    private ListView<String> lvwMenu;
    
    private BuscaMinasApp mainApp;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lvwMenu.setOnMouseClicked(value -> {
            String opcion = lvwMenu.getSelectionModel().getSelectedItem();
            switch (opcion) {
                case "Iniciar Sesión":
                    boolean confirmacion = Dialogo.confirmacion("¿Está seguro que desea cancelar el registro?");
                    if (confirmacion) mainApp.intercambiarEscena(BuscaMinasApp.ESCENA_INICIAR_SESION);
                break;
            }
        });
    }    

    @Override
    public void setMainApp(BuscaMinasApp mainApp) {
        this.mainApp = mainApp;
    }
    
}
