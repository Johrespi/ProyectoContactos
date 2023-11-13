/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectocontactos;

import Modelo.CircularNodeList;
import Modelo.DoubleCircleLinkedList;
import Modelo.Usuario;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author johan
 */
public class ContactosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private ListView<String> listView;
    
    private Usuario usuario;
    
    private DoubleCircleLinkedList<String> contactList = new DoubleCircleLinkedList<>();

    public void initialize(URL url, ResourceBundle rb) {
        
        contactList.add("Contacto 1" );
        contactList.add("Contacto 2" );
        contactList.add("Contacto 3" );
        contactList.add("Contacto 4" );
        contactList.add("Contacto 5" );
        contactList.add("Contacto 6" ); 
        contactList.add("Contacto 7" );
        contactList.add("Contacto 1" );
        contactList.add("Contacto 2" );
        contactList.add("Contacto 3" );
        contactList.add("Contacto 4" );
        contactList.add("Contacto 5" );
        contactList.add("Contacto 6" ); 
        contactList.add("Contacto 7" );
        contactList.add("Contacto 1" );
        contactList.add("Contacto 2" );
        contactList.add("Contacto 3" );
        contactList.add("Contacto 4" );
        contactList.add("Contacto 5" );
        contactList.add("Contacto 6" ); 
        contactList.add("Contacto 7" );
        
        System.out.println(contactList.toString());
        actualizarListView();
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
        
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator<String> iterator = contactList.iterator();

        while (iterator.hasNext()) {
            String contacto = iterator.next();
            arrayList.add(contacto);
        }
        
        ObservableList<String> contactArray = FXCollections.observableArrayList(arrayList);
        listView.setItems(contactArray);
    }
    
}
