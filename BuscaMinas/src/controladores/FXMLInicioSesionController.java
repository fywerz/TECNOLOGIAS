package controladores;

import controller.JugadorJpaController;
import entidades.Jugador;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import logica.Encriptar;

/**
 * FXML Controller class
 *
 * @author EricK
 */
public class FXMLInicioSesionController implements Initializable {

  @FXML
  Button btnIniciar;
  @FXML
  Button btnCrear;
  @FXML
  TextField txfUsuario;
  @FXML
  PasswordField psfContrasena;
  JugadorJpaController jugadorjpa = new JugadorJpaController();
  List<Jugador> jugadores = jugadorjpa.findJugadorEntities();
  Encriptar encript = new Encriptar();

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    btnIniciar.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        verificar();
      }
    });
  }

  public void verificar() {
    jugadorjpa.findJugadorEntities();
    for (int i = 0; i < jugadores.size(); i++) {
      String nombre = jugadores.get(i).getNombreJugador();
      if (nombre.equals(txfUsuario.getText())) {
        String contrasena = jugadores.get(i).getContrasena();
        String contrasenaUsuario = encript.convertirSHA256(psfContrasena.getText());
        if (contrasena.equals(contrasenaUsuario)) {
          Alert alert = new Alert(AlertType.INFORMATION);
          alert.setTitle("Titulo");
          alert.setHeaderText("Cabeza");
          alert.setContentText("Contexto");

          alert.showAndWait();
        } else {
          System.out.println("Contraseña mal");
        }
      }

    }
    System.out.println("No usuario");
  }

}
