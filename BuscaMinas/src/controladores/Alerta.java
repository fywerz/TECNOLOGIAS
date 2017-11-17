package controladores;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author EricK
 */
public class Alerta {


public void alertaInfo(String titulo, String cabeza, String mensaje){
  Alert alert = new Alert(AlertType.INFORMATION);
alert.setTitle(titulo);
alert.setHeaderText(cabeza);
alert.setContentText(mensaje);

alert.showAndWait();
}  
}
