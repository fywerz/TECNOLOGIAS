/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import buscaminas.BuscaMinasApp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author jonathan
 */
public class FXMLMenuPrincipalController implements Initializable, FXMLControlable {
    private BuscaMinasApp mainApp;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void setMainApp(BuscaMinasApp mainApp) {
        this.mainApp = mainApp;
    }
    
}
