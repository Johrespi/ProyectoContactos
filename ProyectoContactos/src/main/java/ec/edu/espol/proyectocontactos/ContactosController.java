/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectocontactos;

import Modelo.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author johan
 */
public class ContactosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Usuario usuario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
