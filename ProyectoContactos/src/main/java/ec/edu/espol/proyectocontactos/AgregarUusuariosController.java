/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectocontactos;

import Modelo.Contacto;
import ec.edu.espol.proyectocontactos.ContactosController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Yumi
 */
public class AgregarUusuariosController implements Initializable {
     @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtTipoContacto;
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    @FXML
    void AgregaUsuario(ActionEvent e){
        ContactosController contactosController = new ContactosController();
        Contacto con = new Contacto(txtNombre.getText(),txtApellido.getText(),txtTipoContacto.getText());
        contactosController.contactList.add(con);
        contactosController.actualizarListView();
        
        Alert Guardado = new Alert(Alert.AlertType.INFORMATION);
            Guardado.setTitle("Guardado");
            Guardado.setContentText("Su contacto se a guardado");
            Guardado.showAndWait();
        
    }
    
}
