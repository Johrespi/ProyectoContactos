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
import javafx.scene.input.KeyEvent;
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

    private String[] tiposUsuarios = {"Persona natural", "Administrador"};
    
    // Contraseña por si se quiere crear un Usuario administrador
    private String AdminPassword = "1";

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
//        usuarios.add(u1);
//        Usuario.saveListToFileSerUsuarios(usuarios);

    }

    @FXML
    private void registrar(ActionEvent event) {
        
        // talvez no es necesairo es isEmpty, el programa no se caeria simplemente la lista estuviera vacia, talvez las validaciones de los
        //espaccios en blanco vayan primero, y luego se recorra la lista, mañana hay que probar.
        ArrayList<Usuario> usuariosListaSer = Usuario.readListFromFileSerUsuarios();
        if (usuariosListaSer.isEmpty()) {
            System.out.println("No hay nadie registrado");
            if (usuarioField.getText().isBlank() || passwordField.getText().isBlank() || usuarioField.getText().contains(" ")
                    || passwordField.getText().contains(" ") || choiceBox.getValue() == null) {

                Alert mensaje = new Alert(Alert.AlertType.WARNING);
                mensaje.setTitle("Advertencia");
                mensaje.setContentText("No se admiten espacios vacíos");
                mensaje.showAndWait();

            } else {
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
                        Usuario primerUsuario = new Usuario(usuarioField.getText(), passwordField.getText(), choiceBox.getValue());
                        usuariosListaSer.add(primerUsuario);
                        Usuario.saveListToFileSerUsuarios(usuariosListaSer);
                        Alert mensaje2 = new Alert(Alert.AlertType.CONFIRMATION);
                        mensaje2.setTitle("Registro exitoso");
                        mensaje2.setContentText("Su cuenta ha sido registrada exitosamente, Usted es un Usuario Administrador");
                        mensaje2.showAndWait();
                    }
                    });
                }
            }

        } else {
            for (Usuario u : usuariosListaSer) {
                System.out.println(u.toString());
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

    @FXML
    private void nameKeyTyped(KeyEvent event) {

    }

    @FXML
    private void passwordKeyTyped(KeyEvent event) {
    }

}
