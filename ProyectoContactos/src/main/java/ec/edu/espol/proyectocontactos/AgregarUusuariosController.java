/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectocontactos;

import Modelo.ArrayList;
import Modelo.Contacto;
import Modelo.DoubleCircleLinkedList;
import Modelo.Usuario;
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

    private ContactosController contactosController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    void AgregaUsuario(ActionEvent e) {
        Contacto con = new Contacto(txtNombre.getText(), txtApellido.getText(), txtTipoContacto.getText());
        Usuario u = contactosController.usuario;
        contactosController.usuario.getContactos().add(con);
        ArrayList<Usuario> AllUsers = Usuario.readListFromFileSerUsuarios();

        System.out.println("///////////////////");
        System.out.println(AllUsers);
        System.out.println("///////////////////");

        for (Usuario user : AllUsers) {
            if (u.equals(user)) {
                user.getContactos().add(con);

                System.out.println(user.getContactos());
                Usuario.saveListToFileSerUsuarios(AllUsers);

            }
        }

        contactosController.actualizarListView();

        Alert Guardado = new Alert(Alert.AlertType.INFORMATION);
        Guardado.setTitle("Guardado");
        Guardado.setContentText("Su contacto se a guardado");
        Guardado.showAndWait();

    }

    public void setContactosController(ContactosController contactosController) {
        this.contactosController = contactosController;
    }

}
