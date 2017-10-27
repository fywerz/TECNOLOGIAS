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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import modelo.Cuenta;
import modelo.Dialogo;
import modelo.ICuenta;

/**
 * FXML Controller class
 *
 * @author jonathan
 */
public class FXMLIniciarSesionController implements Initializable, FXMLControlable {

    @FXML
    private TextField tfdUsuario;
    @FXML
    private PasswordField tfdContrasena;
    @FXML
    private ListView<String> lvwMenu;
    @FXML
    private Button btnIniciarSesion;

    private BuscaMinasApp mainApp;
    private ICuenta stub;

    private String generarHash(String contrasena) {
        MessageDigest sha256;
        try {
            sha256 = MessageDigest.getInstance("SHA-256");
            byte[] hash = sha256.digest(contrasena.getBytes());
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                stringBuilder.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }

    }

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
            btnIniciarSesion.setOnAction(value -> {
            String usuario = tfdUsuario.getText();
            String contrasena = tfdContrasena.getText();
            String password = generarHash(contrasena);

            if (password != null) {
                try {
                    Cuenta cuenta = stub.autenticar(new Cuenta(usuario, password));
                    if (cuenta != null) {
                        Dialogo.informacion("Bienvendo: " + cuenta.getUsuario());

                        mainApp.intercambiarEscena(BuscaMinasApp.ESCENA_MULTIJUGADOR);
                    } else if (cuenta == null) {
                        Dialogo.advertencia("El usuario o contraseña no han sido indentificados");
                    }
                } catch (RemoteException ex) {
                    System.err.println(ex.getMessage());
                    ex.printStackTrace();
                }

            }

        });
            
        
        lvwMenu.setOnMouseClicked(value -> {
            String opcion = lvwMenu.getSelectionModel().getSelectedItem();
            switch (opcion) {
                case "Crear Cuenta":
                    mainApp.intercambiarEscena(BuscaMinasApp.ESCENA_CREAR_CUENTA);
                    break;
            }
        });

        

    }

    @Override
    public void setMainApp(BuscaMinasApp mainApp) {
        this.mainApp = mainApp;
    }

}
