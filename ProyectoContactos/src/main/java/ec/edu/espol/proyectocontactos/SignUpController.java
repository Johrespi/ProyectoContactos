/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectocontactos;

import Modelo.ArrayList;
import Modelo.Usuario;
import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static util.Utilitario.mensajeAlertaAdvertencia;
import static util.Utilitario.soloNumerosYLetras;
import static util.Utilitario.mensajeAlertaConfirmacion;
import static util.Utilitario.mensajeAlertaError;
import static util.Utilitario.mensajeAlertaInfo;
/**
 * FXML Controller class
 *
 * @author johan
 */
public class SignUpController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField usuarioField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button registrarButton;
    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private Button regresarButton;
    @FXML
    private Label adminPassword;

    //Tipos de usuarios que se pueden registrar
    private final String[] tiposUsuarios = {"Persona natural", "Administrador"};
    // Contraseña por si se quiere crear un Usuario administrador
    private final String AdminPassword = "1";

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private HBox hbAdmin;
    @FXML
    private VBox vbRegistro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        choiceBox.getItems().addAll(tiposUsuarios);
        formatosTextField();
    }
    
      private void formatosTextField(){
        soloNumerosYLetras(this.usuarioField);
    }

    private void mostrarConfigAdmin(ArrayList<Usuario> actualizarLista, Usuario u1){
        PasswordField pf = new PasswordField();        
        Button bt = new Button("Registrar Administrador");
        bt.setId("boton");
        bt.getStyleClass().add((getClass().getResource("/styles/contactosprincipal.css").toString()));        
        registrarButton.setDisable(true);        
        HBox.setHgrow(pf, javafx.scene.layout.Priority.ALWAYS);
        this.hbAdmin.getChildren().addAll(pf);                    
        this.vbRegistro.getChildren().add(1, bt);
        bt.setOnAction(event1 -> {
            if (pf.getText().equals(AdminPassword)) {
                asignarContactosAdmin(u1);
                actualizarLista.addLast(u1);
                //usuariosListaSer = actualizarLista;                
                Usuario.saveListToFileSerUsuarios(actualizarLista);
                mensajeAlertaConfirmacion("Registro exitoso","Su cuenta ha sido registrada exitosamente, Usted es un Usuario Administrador");
                this.regresarButton.fire();
            } else {
                mensajeAlertaError("ERROR","Contraseña Incorrecta");
            }
        });
    }
    
    @FXML
    private void registrar(ActionEvent event) throws IOException {
        //Lista de usuarios
        ArrayList<Usuario> usuariosListaSer = Usuario.readListFromFileSerUsuarios();

        //Lista que actualiza a la lista usuariosListaSer
        ArrayList<Usuario> actualizarLista = Usuario.readListFromFileSerUsuarios();

        if (usuarioField.getText().isBlank() || passwordField.getText().isBlank() || usuarioField.getText().contains(" ")
                || passwordField.getText().contains(" ") || choiceBox.getValue() == null) {
            mensajeAlertaAdvertencia("Advertencia", "No se admiten espacios vacíos");
        }
        else if (usuariosListaSer.isEmpty()) {
            Usuario primerUsuario = new Usuario(usuarioField.getText(), passwordField.getText(), tiposUsuarios[0]);
            //usuariosListaSer.add(primerUsuario);
            actualizarLista.addLast(primerUsuario);
            Usuario.saveListToFileSerUsuarios(actualizarLista);
            mensajeAlertaConfirmacion("Registro exitoso","Su cuenta ha sido registrada exitosamente");
            this.regresarButton.fire();

        } else {
            Usuario u1 = new Usuario(usuarioField.getText(), passwordField.getText(), choiceBox.getValue());
            boolean condicion = usuariosListaSer.contains(u1);
            if (!condicion) {
                if (choiceBox.getValue().equals(tiposUsuarios[1])) {
                    adminPassword.setText("Admin Password");
                    this.adminPassword.setId("labelAdmin");
                    mostrarConfigAdmin(actualizarLista,u1);
                } else {
                    actualizarLista.addLast(u1);
                    //usuariosListaSer = actualizarLista;
                    Usuario.saveListToFileSerUsuarios(actualizarLista);
                    mensajeAlertaConfirmacion("Registro exitoso","Su cuenta ha sido registrada exitosamente");
                    this.regresarButton.fire();
                }
            } else {
                mensajeAlertaInfo("Lo sentimos","Este username ya existe, intente con otro");
            }
        }
    }
    
    
    @FXML
    private void regresar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Welcome!");
        stage.setScene(scene);
        stage.show();
    }
    
    private void asignarContactosAdmin(Usuario userAdmin){
        ArrayList<Usuario> AllUsers = Usuario.readListFromFileSerUsuarios();
        for(Usuario u: AllUsers){
            if(!u.getTipoUsuario().equals("Administrador"))
                userAdmin.getContactos().addAll(u.getContactos());
        }                
    }

}
