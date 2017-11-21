package controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author EricK
 */
public class FXMLMenuController implements Initializable {

  /**
   * Initializes the controller class.
   */
  @FXML
  Button btnSalir;
  @FXML
  Button btnJugar;
  @FXML
  Button btnMejores;
  @FXML
  Button btnOpciones;
  @Override
  public void initialize(URL url, ResourceBundle rb) {
  btnSalir.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
    inicioSesion();
    }
  });
  }  
  
  
  public void inicioSesion(){
    Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();

        try {
          FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/pantallas/FXMLInicioSesion.fxml"));
          Parent root1 = (Parent) fxmlLoader.load();
          stage.setScene(new Scene(root1));
          stage.show();
        } catch (Exception e) {
        }
      
  }
}
