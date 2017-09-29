/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author jonathan
 */
public class FXMLInicioController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ListView<String> tvwMenu;

    @FXML
    private ImageView imwPortada;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Platform.runLater(() -> {
           
        });
    }    
    
}
