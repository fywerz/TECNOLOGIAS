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
  Alerta alerta = new Alerta();

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    btnIniciar.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        switch (verificar()) {
          case 0:
            menu();
            break;
          case 1:
            alerta.alertaInfo("Acceso denegado", "Contraseña incorrecta", "Tu contraseña es incorrecta, vuelve a intentar");
            break;
          case 2:
            alerta.alertaInfo("Acceso denegado", "Usuario no encontrado", "El usuario con el que intentas ingresar no existe");
        }

      }

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

  public int verificar() {
    jugadorjpa.findJugadorEntities();
    for (int i = 0; i < jugadores.size(); i++) {
      String nombre = jugadores.get(i).getNombreJugador();
      if (nombre.equals(txfUsuario.getText())) {
        String contrasena = jugadores.get(i).getContrasena();
        String contrasenaUsuario = encript.convertirSHA256(psfContrasena.getText());
        if (contrasena.equals(contrasenaUsuario)) {
          return 0;
        } else {
          return 1;
        }
      }

    }
    return 2;
  }

}
