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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import modelo.Dialogo;

/**
 * FXML Controller class
 *
 * @author jonathan
 */
public class FXMLMultijugadorController implements Initializable, FXMLControlable {

    @FXML
    private ListView<String> lvwMenu;

    @FXML
    private TableView<?> tvwJugadoresConectados;

    @FXML
    private ImageView ivwFotoJugador;
    @FXML
    private Label lblJugador;
    @FXML
    private Button btnIniciarPartida;
    private BuscaMinasApp mainApp;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lvwMenu.setOnMouseClicked(value -> {
            String opcion = lvwMenu.getSelectionModel().getSelectedItem();
            switch (opcion) {
                case "Cerrar Sesión":
                    boolean confirmacion = Dialogo.confirmacion("¿Está seguro que desea salir de BuscaMinaApp?");
                    if (confirmacion) {
                        mainApp.intercambiarEscena(BuscaMinasApp.ESCENA_INICIAR_SESION);
                    }
                    break;
            }
        });
        
        btnIniciarPartida.setOnAction(value -> {
            mainApp.intercambiarEscena(BuscaMinasApp.ESCENA_INICIAR_JUEGO);
        });
        
    }

    @Override
    public void setMainApp(BuscaMinasApp mainApp) {
        this.mainApp = mainApp;
    }

}
