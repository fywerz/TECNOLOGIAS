package controladores;

import controller.JugadorJpaController;
import entidades.Jugador;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logica.Encriptar;

/**
 * FXML Controller class
 *
 * @author EricK
 */
public class FXMLInicioSesionController implements Initializable {
//Inicializacion de variables con FXML
  @FXML
  Button btnIniciar;
  @FXML
  Button btnCrear;
  @FXML
  TextField txfUsuario;
  @FXML
  PasswordField psfContrasena;
  JugadorJpaController jugadorjpa = new JugadorJpaController();//Variable de controlador JPA
  List<Jugador> jugadores = jugadorjpa.findJugadorEntities(); //Lista para guardar entidades de la base de datos
  Encriptar encript = new Encriptar(); //Variable para encriptacion
  Alerta alerta = new Alerta(); //Variable para la creacion de alertas

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    /**
     * Acciones a ejecutar cuando sea pulsado el boton Iniciar (Iniciar sesion) dependiendo el resultado del metodo
     * verificar
     */
    btnIniciar.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        //switch dependiendo de la verificacion
        switch (verificar()) {
          case 0:
            menu(); //0 es para inicio de sesion correcto 
            break;
          case 1:
            //alerta de informacion de contraseña incorrecta
            alerta.alertaInfo("Acceso denegado", "Contraseña incorrecta", "Tu contraseña es incorrecta, vuelve a intentar");
            break;
          case 2:
            //alerta de usuario no encontrado
            alerta.alertaInfo("Acceso denegado", "Usuario no encontrado", "El usuario con el que intentas ingresar no existe");
        }

      }
      /**
       * Metodo para la invocacion del menu
       */
      private void menu() {
        Stage stage = (Stage) btnIniciar.getScene().getWindow();
        stage.close();

        try {
          FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pantallas/FXMLMenu.fxml"));
          Parent root1 = (Parent) fxmlLoader.load();
          stage.setScene(new Scene(root1));
          stage.show();
        } catch (Exception e) {
        }
      }
    });
  }
  /**
   * Metodo para verificar los datos introducidos en los campos de jugador y contraseña
   * @return regresa un int que indica la comprobacion de los campos
   */
  public int verificar() {
    jugadorjpa.findJugadorEntities(); //Actualizacion de entidades en la base de datos
    //for para comprobar uno a uno los datos de las entidades
    for (int i = 0; i < jugadores.size(); i++) {
      String nombre = jugadores.get(i).getNombreJugador(); //variable auxiliar para el nombre del jugador
      if (nombre.equals(txfUsuario.getText())) {
        String contrasena = jugadores.get(i).getContrasena(); //variable auxiliar para la contraseña
        String contrasenaUsuario = encript.convertirSHA256(psfContrasena.getText()); //contraseña introducida hasheada
        if (contrasena.equals(contrasenaUsuario)) {
          return 0; //0 para nombre y contraseña correctas
        } else {
          return 1; //1 para contraseña incorrecta
        }
      }

    }
    return 2; //2 si el nombre de jugador no es encontrado
  }

}
