/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import buscaminas.BuscaMinasApp;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import modelo.Cuenta;
import modelo.Dialogo;
import modelo.ICuenta;
import modelo.Partida;
import modelo.Perfil;

/**
 * FXML Controller class
 *
 * @author jonathan
 */
public class FXMLCrearCuentaController implements Initializable, FXMLControlable {

    @FXML
    private ListView<String> lvwMenu;
    @FXML
    private TextField tfdContrasena;
    @FXML
    private TextField tfdUsuario;
    @FXML
    private Button btnCrearCuenta;
    @FXML
    private TextField tfdCorreo;
    @FXML
    private DatePicker dprNacimiento;
    @FXML
    private ComboBox<String> cbxGenero;
    private BuscaMinasApp mainApp;
    private ICuenta stub;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            try {
                stub = (ICuenta) mainApp.obtenerRegistro().lookup("rmi://127.0.0.1:1099/" + "cuenta");
            } catch (RemoteException ex) {
                System.err.println(ex.getMessage());
                ex.printStackTrace();
            } catch (NotBoundException ex) {
                System.err.println(ex.getMessage());
                ex.printStackTrace();
            }
        });
        lvwMenu.setOnMouseClicked(value -> {
            String opcion = lvwMenu.getSelectionModel().getSelectedItem();
            switch (opcion) {
                case "Iniciar Sesión":
                    boolean confirmacion = Dialogo.confirmacion("¿Está seguro que desea cancelar el registro?");
                    if (confirmacion) {
                        mainApp.intercambiarEscena(BuscaMinasApp.ESCENA_INICIAR_SESION);
                    }
                    break;
            }
        });

        btnCrearCuenta.setOnAction(value -> {
            String contrasena = tfdContrasena.getText();
            String usuario = tfdUsuario.getText();
            String correo = tfdCorreo.getText();
            Date nacimiento = new Date(dprNacimiento.getValue().toEpochDay());
            String genero = cbxGenero.getValue();
            Perfil perfil = new Perfil(genero, correo, nacimiento);
            Cuenta cuenta = new Cuenta(usuario, contrasena, new Partida(), perfil);
            try {
                //boolean registrado = stub.registrar(cuenta);
                stub.registrar(cuenta);
                //if (registrado) {
                    Dialogo.informacion("El perfil ha sido registrado");
                //} else {
                //    Dialogo.advertencia("Problemas");
                //}
            } catch (RemoteException ex) {
                System.err.println(ex.getMessage());
                ex.printStackTrace();
            }
            
        });
    }

    @Override
    public void setMainApp(BuscaMinasApp mainApp) {
        this.mainApp = mainApp;
    }

}
