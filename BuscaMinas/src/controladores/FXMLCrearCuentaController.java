package controladores;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author EricK
 */
public class FXMLCrearCuentaController implements Initializable {

  /**
   * Initializes the controller class.
   */
  @FXML
  TextField txfNombreJugador;
  @FXML
  PasswordField psfContrasena;
  @FXML
  PasswordField psfRepita;
  @FXML
  ChoiceBox chbGenero;
  @FXML
  DatePicker dtpFecha;
  @FXML
  Button btnRegistrarse;
  @FXML
  Button btnRegresar;
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    ArrayList<String> listaGenero=new ArrayList<>();
    listaGenero.add("Masculino");
    listaGenero.add("Femenino");
    ObservableList <String> listaObserv=FXCollections.observableArrayList(listaGenero);
    chbGenero.setItems(listaObserv);
    btnRegistrarse.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        
      }
    });
  }  
  
}
