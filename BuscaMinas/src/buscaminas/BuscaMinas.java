/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author jonathan
 */
public class BuscaMinas extends Application {
    private Stage escenario;
    private Parent escenaPrincipal;
    
    static final String ESCENA_INICIO = "vista/FXMLInicio.fxml";
    static final String ESCENA_INICIAR_JUEGO = "vista/FXMLIniciarJuego.fxml";
    
    @Override
    public void start(Stage stage) throws IOException {
        escenaPrincipal =  FXMLLoader.load(getClass().getResource(ESCENA_INICIAR_JUEGO));
        Scene scene = new Scene(escenaPrincipal);
        stage.setScene(scene);
        stage.show();
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
    
}
