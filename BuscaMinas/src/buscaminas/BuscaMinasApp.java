/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package buscaminas;

import controlador.FXMLControlable;
import java.io.IOException;
import java.rmi.registry.Registry;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author jonathan
 */
public class BuscaMinasApp extends Application {
    public static final String ESCENA_INICIAR_SESION = "/vista/FXMLIniciarSesion.fxml";
    public static final String ESCENA_MENU_PRINCIPAL = "/vista/FXMLMenuPrincipal.fxml";
    public static final String ESCENA_CREAR_CUENTA = "/vista/FXMLCrearCuenta.fxml";
    public static final String ESCENA_MULTIJUGADOR = "/vista/FXMLMultijugador.fxml";
    public static final String ESCENA_INICIAR_JUEGO = "/vista/FXMLIniciarJuego.fxml";
    
    private Stage mainStage;
    private Pane mainScene; 
    
    @Override
    public void start(Stage stage) {

        Pane root = new Pane();
        this.mainStage = stage;
        this.mainScene = root;
        this.intercambiarEscena(ESCENA_INICIAR_SESION);

        Scene scene = new Scene(root);       
        stage.setScene(scene);       
        stage.show();
    } 
    
     public void intercambiarEscena(final String view) {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource(view));
        
        try {
            Pane screen = myLoader.load();
            if (mainScene.getChildren().isEmpty()) {                
                mainScene.getChildren().add(screen);
            } else {
                
                mainScene.getChildren().remove(0);
                mainScene.getChildren().add(0, screen);
                this.mainStage.setWidth(655);
                this.mainStage.setHeight(520);
                
            }
            FXMLControlable myScreenControler = ((FXMLControlable) myLoader.getController());
            myScreenControler.setMainApp(this);
        } catch (IOException ex) {
            System.err.println("EXCEPTION 2 APPMAIN" + ex.getMessage());
            
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
    
}
