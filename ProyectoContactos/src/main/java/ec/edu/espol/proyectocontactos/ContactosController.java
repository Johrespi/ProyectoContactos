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


/**
 * FXML Controller class
 *
 * @author johan
 */
public class ContactosController implements Initializable {
    private Usuario usuario;
    @FXML
    private Button  BtnAgregar;
    
    @FXML
    private AnchorPane ContactosRoot;
    
    @FXML
    private ListView<String> ListaContacto;
    
    /**
     * Initializes the controller class.
     */

   public  ArrayList<String> Contactos = new ArrayList<>();
   public static ObservableList<Contacto> observable = FXCollections.observableArrayList();
   
   protected DoubleCircleLinkedList<Contacto> contactList = new DoubleCircleLinkedList<>();

    public void initialize(URL url, ResourceBundle rb) {
        
        contactList.add(new Contacto("Raul","Leon","Amigo"));
        contactList.add(new Contacto("Johan","Ramirez","Amigo"));
        contactList.add(new Contacto("Michelle","Arreaga","Amigo"));
        actualizarListView();

    }
    
    @FXML
    void AgregarContacto(ActionEvent event) {
      
        try{
        Parent root = FXMLLoader.load(getClass().getResource("AgregarUusuarios.fxml"));
        Stage st = new Stage();
        Scene sc= new Scene(root);
        st.setScene(sc);
        st.show();
        
        
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

     @FXML
    public void navigateNext() {
        if (!contactList.isEmpty()) {
            contactList.moveToNext();
            actualizarListView();
        }

    }

    @FXML
    public void navigatePrevious() {
        if (!contactList.isEmpty()) {
            contactList.moveToPrevious();
            actualizarListView();
        }
    }

    public void actualizarListView() {
        Iterator<Contacto> iterator = contactList.iterator();
        System.out.println(contactList);
        while (iterator.hasNext()) {
            Contacto contacto = iterator.next();
            Contactos.add(contacto.getNombre() + " " + contacto.getApellido());
        }
        ObservableList<String> contactArray = FXCollections.observableArrayList(Contactos);
        System.out.println("DoubleList: "+ contactList.toString());
        System.out.println("ArrayList: "+ Contactos.toString());
        ListaContacto.setItems(contactArray);
    }
}
