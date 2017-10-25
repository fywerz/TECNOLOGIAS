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
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import modelo.Dialogo;

/**
 * FXML Controller class
 *
 * @author jonathan
 */
public class FXMLIniciarJuegoController implements Initializable, FXMLControlable {
    @FXML
    private ListView<String> lvwMenu;
    @FXML
    private GridPane gpeTablero;
    @FXML
    private Button btnAbandonarPartida;

    
    private BuscaMinasApp mainApp;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnAbandonarPartida.setOnAction(value -> {
            boolean confirmacion = Dialogo.confirmacion("¿Está seguro que desea abandonar la partida?");
                if (confirmacion) {
                    mainApp.intercambiarEscena(BuscaMinasApp.ESCENA_MULTIJUGADOR);
                }
        });
    }    

    @Override
    public void setMainApp(BuscaMinasApp mainApp) {
        this.mainApp = mainApp;
    }
    
}
