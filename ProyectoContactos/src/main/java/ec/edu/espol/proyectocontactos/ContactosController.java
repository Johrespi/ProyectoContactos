/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectocontactos;

import Modelo.ArrayList;
import Modelo.Contacto;

import Modelo.CircularNodeList;

import Modelo.DoubleCircleLinkedList;
import Modelo.Usuario;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author johan
 */
public class ContactosController implements Initializable {

    Usuario usuario;

    Contacto contacto;

    @FXML
    private Button BtnAgregar;
    @FXML
    private ListView<String> ListaContacto;
    @FXML
    private Button mostrarInformacionBtn;
    @FXML
    private Button removeBtn;
    @FXML
    private ChoiceBox<String> choiceOrdenar;
    @FXML
    private Button ordenarButton;

    private LoginController loginController;

    /**
     * Initializes the controller class.
     */
    public ArrayList<String> Contactos = new ArrayList<>();

    private String[] ordenamientos = {"Predeterminado", "Favoritos", "Empresas", "Personas Naturales"};

    public void initialize(URL url, ResourceBundle rb) {

        choiceOrdenar.getItems().addAll(ordenamientos);
        mostrarInformacionBtn.setDisable(true); // Para deshabilitar el botón

        ListaContacto.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    mostrarInformacionBtn.setDisable(newValue == null); // Habilita el botón si se selecciona un contacto
                }
        );

        mostrarInformacionBtn.setOnAction(e -> {
            String selectedContact = ListaContacto.getSelectionModel().getSelectedItem();
            if (selectedContact != null) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("MostrarContacto.fxml"));
                    Parent root = loader.load();
                    //MostrarContactoController mostrar = loader.getController();

                    DoubleCircleLinkedList<Contacto> contactosDelUsuario = usuario.getContactos();

                    for (Contacto c : contactosDelUsuario) {
                        if (selectedContact.equals(c.getNombre() + " " + c.getApellido())) {
                            contacto = c;
                            break;
                        }
                    }

                    if (contacto != null) {
                        MostrarContactoController mostrarContactoController = loader.getController();
                        mostrarContactoController.setContactosController(this);
                        mostrarContactoController.setContacto(contacto);
                        Stage st = new Stage();
                        Image icono = new Image("imgs/Login.jpg");
                        st.getIcons().add(icono);
                        st.setTitle("Información contacto");
                        st.setResizable(false);
                        Scene sc = new Scene(root);
                        st.setScene(sc);
                        st.show();
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    @FXML
    void AgregarContacto(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AgregarContactos.fxml"));
            Parent root = loader.load();

            AgregarContactosController agregarContactosController = loader.getController();
            agregarContactosController.setContactosController(this);

            Stage st = new Stage();
            st.setTitle("Agrega tus contactos!");
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

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public void navigateNext() {
        if (!usuario.getContactos().isEmpty()) {
            usuario.getContactos().moveToNext();
            actualizarListView();
        }

    }

    public void navigatePrevious() {
        if (!usuario.getContactos().isEmpty()) {
            usuario.getContactos().moveToPrevious();
            actualizarListView();
        }
    }

    public void actualizarListView() {
        Contactos.clear();
        DoubleCircleLinkedList contactosDelUsuario = usuario.getContactos();
        if (!contactosDelUsuario.isEmpty()) {
            Iterator<Contacto> iterator = contactosDelUsuario.iterator();
            while (iterator.hasNext()) {
                Contacto contacto = iterator.next();
                Contactos.addLast(contacto.getNombre() + " " + contacto.getApellido());
            }
            ObservableList<String> contactArray = FXCollections.observableArrayList(Contactos);
            ListaContacto.setItems(contactArray);
        } else {
            Alert noContacts = new Alert(Alert.AlertType.WARNING);
            noContacts.setTitle("Advertencia");
            noContacts.setContentText("Su lista de contactos esta vacía");
            noContacts.showAndWait();
        }
    }

    public void actualizarListViewAdmin() {
        Contactos.clear();
        ArrayList<Usuario> Usuarios = Usuario.readListFromFileSerUsuarios();
        for (Usuario u : Usuarios) {
            DoubleCircleLinkedList contactosDelUsuario = u.getContactos();
            if (!contactosDelUsuario.isEmpty()) {
                Iterator<Contacto> iterator = contactosDelUsuario.iterator();
                while (iterator.hasNext()) {
                    Contacto contacto = iterator.next();
                    Contactos.addLast(contacto.getNombre() + " " + contacto.getApellido());
                }
                ObservableList<String> contactArray = FXCollections.observableArrayList(Contactos);
                ListaContacto.setItems(contactArray);
            }
        }
    }

    @FXML
    private void removerContacto(ActionEvent event) {
        ArrayList<Usuario> usuarios = Usuario.readListFromFileSerUsuarios();
        String selectedContact = ListaContacto.getSelectionModel().getSelectedItem();
        DoubleCircleLinkedList contactosDelUsuario = usuario.getContactos();
        for (Usuario u : usuarios) {
            if (usuario.equals(u)) {
                for (Contacto c : u.getContactos()) {
                    if (selectedContact.equals(c.getNombre() + " " + c.getApellido())) {
                        int index = u.getContactos().indexOf(c);
                        u.getContactos().remove(index);
                        usuario.getContactos().remove(index);
                        break;

                    }
                }
            }
        }
        actualizarListView();
        Usuario.saveListToFileSerUsuarios(usuarios);
    }

    private void ordenarPorFavorito() {

        ArrayList<Usuario> usuarios = Usuario.readListFromFileSerUsuarios();
        ArrayList<String> contactosAOrdenar = new ArrayList<>();
        for (Usuario u : usuarios) {
            if (usuario.equals(u) && !usuario.getContactos().isEmpty()) {
                DoubleCircleLinkedList<Contacto> contactos = u.getContactos();
                for (Contacto c : contactos) {
                    if (c.isEsFavorito() == true) {
                        contactosAOrdenar.addLast(c.getNombre() + " " + c.getApellido());
                        ObservableList<String> contactArray = FXCollections.observableArrayList(contactosAOrdenar);
                        ListaContacto.setItems(contactArray);
                    }

                }

            }
        }
    }

    private void ordenarPorEmpresa() {
        ArrayList<Usuario> usuarios = Usuario.readListFromFileSerUsuarios();
        ArrayList<String> contactosAOrdenar = new ArrayList<>();
        for (Usuario u : usuarios) {
            if (usuario.equals(u) && !usuario.getContactos().isEmpty()) {
                DoubleCircleLinkedList<Contacto> contactos = u.getContactos();
                for (Contacto c : contactos) {
                    if (c.getTipoContacto().equals("Empresa")) {
                        contactosAOrdenar.addLast(c.getNombre() + " " + c.getApellido());
                        ObservableList<String> contactArray = FXCollections.observableArrayList(contactosAOrdenar);
                        ListaContacto.setItems(contactArray);
                    }

                }

            }
        }
    }

    private void ordenarPorPersonaNatural() {
        ArrayList<Usuario> usuarios = Usuario.readListFromFileSerUsuarios();
        ArrayList<String> contactosAOrdenar = new ArrayList<>();
        for (Usuario u : usuarios) {
            if (usuario.equals(u) && !usuario.getContactos().isEmpty()) {
                DoubleCircleLinkedList<Contacto> contactos = u.getContactos();
                for (Contacto c : contactos) {
                    if (!c.getTipoContacto().equals("Empresa")) {
                        contactosAOrdenar.addLast(c.getNombre() + " " + c.getApellido());
                        ObservableList<String> contactArray = FXCollections.observableArrayList(contactosAOrdenar);
                        ListaContacto.setItems(contactArray);
                    }

                }

            }
        }

    }
    public void desactivarBotones(){
        BtnAgregar.setDisable(true);
        mostrarInformacionBtn.setDisable(true);
        removeBtn.setDisable(true);
        ordenarButton.setDisable(true);
    }

    @FXML
    private void ordenarLista(ActionEvent event) {
        if (choiceOrdenar.getValue() == null) {
        } else if (choiceOrdenar.getValue().equals(ordenamientos[0])) {
            actualizarListView();
        } else if (choiceOrdenar.getValue().equals(ordenamientos[1])) {
            ordenarPorFavorito();
        } else if (choiceOrdenar.getValue().equals(ordenamientos[2])) {
            ordenarPorEmpresa();
        } else if (choiceOrdenar.getValue().equals(ordenamientos[3])) {
            ordenarPorPersonaNatural();
        }
    }

}
