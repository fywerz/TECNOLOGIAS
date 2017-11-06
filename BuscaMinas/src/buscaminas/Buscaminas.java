/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import JPA.controller.JugadorJpaController;
import JPA.entidades.Jugador;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
/**
 *
 * @author EricK
 */
public class Buscaminas extends Application {
  
  @Override
  public void start(Stage primaryStage) throws IOException {
    
  Parent root=FXMLLoader.load(getClass().getResource("/pantallas/FXMLInicioSesion.fxml"));  
    
    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    List<Jugador> jugadores=new ArrayList<>();
    JugadorJpaController jpajugador=new JugadorJpaController();
    jugadores= jpajugador.findJugadorEntities();
    System.out.println(jugadores.get(0).getNombreJugador());
    launch(args);
  }
  
}
