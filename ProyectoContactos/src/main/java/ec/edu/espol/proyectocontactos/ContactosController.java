/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectocontactos;

import Modelo.Contacto;

import Modelo.CircularNodeList;

import Modelo.DoubleCircleLinkedList;
import Modelo.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import javafx.fxml.FXML;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author johan
 */
public class ContactosController implements Initializable {

    Usuario usuario;
    @FXML
    private Button BtnAgregar;

    @FXML
    private AnchorPane ContactosRoot;

    @FXML
    private ListView<String> ListaContacto;
    
    private LoginController loginController;

    /**
     * Initializes the controller class.
     */
    public ArrayList<String> Contactos = new ArrayList<>();
    public static ObservableList<Contacto> observable = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle rb) {

//        contactList.add(new Contacto("Raul", "Leon", "Amigo"));
//        contactList.add(new Contacto("Johan", "Ramirez", "Amigo"));
//        contactList.add(new Contacto("Michelle", "Arreaga", "Amigo"));
//        contactList.add(new Contacto("Michelle123", "Arreaga", "Amigo"));
//        actualizarListView(); Cuando dejamos este metodo aqui, el programa por alguna razon se cae xd

    }

    @FXML
    void AgregarContacto(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AgregarUusuarios.fxml"));
            Parent root = loader.load();

            // Obtener el controlador y setearlo
            AgregarUusuariosController agregarUusuariosController = loader.getController();
            agregarUusuariosController.setContactosController(this);

            Stage st = new Stage();
            Scene sc = new Scene(root);
            st.setScene(sc);
            st.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    @FXML
    public void navigateNext() {
        if (!usuario.getContactos().isEmpty()) {
            usuario.getContactos().moveToNext();
            actualizarListView();
        }

    }

    @FXML
    public void navigatePrevious() {
        if (!usuario.getContactos().isEmpty()) {
            usuario.getContactos().moveToPrevious();
            actualizarListView();
        }
    }

    public void actualizarListView() {
        Contactos.clear();
        DoubleCircleLinkedList contactosDelUsuario = usuario.getContactos();
        System.out.println("===============");
        System.out.println(usuario);
        System.out.println("===============");
        if (!contactosDelUsuario.isEmpty()) {
            Iterator<Contacto> iterator = contactosDelUsuario.iterator();
            System.out.println(contactosDelUsuario);
            while (iterator.hasNext()) {
                Contacto contacto = iterator.next();
                Contactos.add(contacto.getNombre() + " " + contacto.getApellido());
            }
            ObservableList<String> contactArray = FXCollections.observableArrayList(Contactos);
            System.out.println("DoubleList: " + contactosDelUsuario.toString());
            System.out.println("ArrayList: " + Contactos.toString());
            ListaContacto.setItems(contactArray);
        } else {
            Alert noContacts = new Alert(Alert.AlertType.WARNING);
            noContacts.setTitle("Advertencia");
            noContacts.setContentText("Su lista de contactos esta vacía");
            noContacts.showAndWait();
        }
    }
}
