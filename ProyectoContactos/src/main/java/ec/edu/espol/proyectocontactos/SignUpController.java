/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectocontactos;

import Modelo.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        choiceBox.getItems().addAll(tiposUsuarios);

    }

    @FXML
    private void registrar(ActionEvent event) throws IOException {
        //Lista de usuarios
        ArrayList<Usuario> usuariosListaSer = Usuario.readListFromFileSerUsuarios();

        //Lista que actualiza a la lista usuariosListaSer
        ArrayList<Usuario> actualizarLista = Usuario.readListFromFileSerUsuarios();

        if (usuarioField.getText().isBlank() || passwordField.getText().isBlank() || usuarioField.getText().contains(" ")
                || passwordField.getText().contains(" ") || choiceBox.getValue() == null) {

            Alert campoVacio = new Alert(Alert.AlertType.WARNING);
            campoVacio.setTitle("Advertencia");
            campoVacio.setContentText("No se admiten espacios vacíos");
            campoVacio.showAndWait();

        }
        if (usuariosListaSer.isEmpty()) {
            Usuario primerUsuario = new Usuario(usuarioField.getText(), passwordField.getText(), tiposUsuarios[0]);
            //usuariosListaSer.add(primerUsuario);
            actualizarLista.add(primerUsuario);
            Usuario.saveListToFileSerUsuarios(actualizarLista);
            Alert RegistroUsuarioAlert = new Alert(Alert.AlertType.CONFIRMATION);
            RegistroUsuarioAlert.setTitle("Registro exitoso");
            RegistroUsuarioAlert.setContentText("Su cuenta ha sido registrada exitosamente");
            RegistroUsuarioAlert.showAndWait();
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setTitle("Welcome!");
            stage.setScene(scene);
            stage.show();

        } else {

            Usuario u1 = new Usuario(usuarioField.getText(), passwordField.getText(), choiceBox.getValue());
            boolean condicion = usuariosListaSer.contains(u1);
            if (!condicion) {
                if (choiceBox.getValue().equals(tiposUsuarios[1])) {
                    adminPassword.setText("Admin Password");
                    PasswordField pf = new PasswordField();
                    Button bt = new Button("Registrar Administrador");
                    registrarButton.setDisable(true);
                    anchorPane.getChildren().addAll(pf, bt);
                    bt.setLayoutX(350);
                    bt.setLayoutY(250);
                    pf.setLayoutX(195);
                    pf.setLayoutY(250);
                    bt.setOnAction(event1 -> {
                        if (pf.getText().equals(AdminPassword)) {
                            try {
                                actualizarLista.add(u1);
                                //usuariosListaSer = actualizarLista;
                                Usuario.saveListToFileSerUsuarios(actualizarLista);
                                Alert ConfirmacionAdmin = new Alert(Alert.AlertType.CONFIRMATION);
                                ConfirmacionAdmin.setTitle("Registro exitoso");
                                ConfirmacionAdmin.setContentText("Su cuenta ha sido registrada exitosamente, Usted es un Usuario Administrador");
                                ConfirmacionAdmin.showAndWait();
                                Parent root;
                                root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                scene = new Scene(root);
                                stage.setTitle("Welcome!");
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException ex) {
                            }

                        } else {
                            Alert campoVacio = new Alert(Alert.AlertType.ERROR);
                            campoVacio.setTitle("ERROR");
                            campoVacio.setContentText("Contraseña Incorrecta");
                            campoVacio.showAndWait();

                        }
                    });
                } else {
                    actualizarLista.add(u1);
                    //usuariosListaSer = actualizarLista;
                    Usuario.saveListToFileSerUsuarios(actualizarLista);
                    Alert ConfirmacionAdmin = new Alert(Alert.AlertType.CONFIRMATION);
                    ConfirmacionAdmin.setTitle("Registro exitoso");
                    ConfirmacionAdmin.setContentText("Su cuenta ha sido registrada exitosamente");
                    ConfirmacionAdmin.showAndWait();
                    Parent root;
                    root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setTitle("Welcome!");
                    stage.setScene(scene);
                    stage.show();

                }
            } else {
                Alert usuarioExistente = new Alert(Alert.AlertType.INFORMATION);
                usuarioExistente.setTitle("Lo sentimos");
                usuarioExistente.setContentText("Este username ya existe, intente con otro");
                usuarioExistente.showAndWait();
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

}
