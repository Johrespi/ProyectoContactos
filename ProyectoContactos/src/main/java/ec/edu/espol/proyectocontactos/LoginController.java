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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author johan
 */
public class LoginController implements Initializable {

    @FXML
    private TextField userField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button signInButton;
    @FXML
    private Button signUpButton;
    @FXML
    private ImageView imageViewLogin;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private final String[] tiposUsuarios = {"Persona natural", "Administrador"};

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    //Metodo cuando el usuario accede al sistema
    @FXML
    private void signIn(ActionEvent event) throws IOException {
        //Leer todos los usuarios registrados en el sistema
        ArrayList<Usuario> usuarios = Usuario.readListFromFileSerUsuarios();
        System.out.println(usuarios);
        //Validar Login
        Usuario usuarioAVerificar = new Usuario(userField.getText(), passwordField.getText(), null);
        if (userField.getText().isBlank() || passwordField.getText().isBlank() || userField.getText().contains(" ")
                || passwordField.getText().contains(" ")) {

            Alert campoVacio = new Alert(Alert.AlertType.WARNING);
            campoVacio.setTitle("Advertencia");
            campoVacio.setContentText("No se admiten espacios vacíos");
            campoVacio.showAndWait();

        } else if (usuarios.contains(usuarioAVerificar)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Contactos.fxml"));
            Parent root = loader.load();
            ContactosController controller = loader.getController();
            for (Usuario u : usuarios) {
                if (u.equals(usuarioAVerificar)) {
                    controller.setLoginController(this);
                    controller.setUsuario(u);
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setTitle("Welcome!");
                    stage.setScene(scene);
                    stage.show();
                    System.out.println(u); 
                    System.out.println("HOLASAASASSASA");

//Para verificar el tipo de usuario por pantalla
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AgregarUusuarios.fxml"));
//                    Parent root = loader.load();
//
//                    // Obtener el controlador y setearlo
//                    AgregarUusuariosController agregarUusuariosController = loader.getController();
//                    agregarUusuariosController.setContactosController(this);
//
//                    Stage st = new Stage();
//                    Scene sc = new Scene(root);
//                    st.setScene(sc);
//                    st.show();
                }
            }

        } else {
            Alert alertaUsuarioNoEncontrado = new Alert(Alert.AlertType.INFORMATION);
            alertaUsuarioNoEncontrado.setTitle("Usuario no encontrado");
            alertaUsuarioNoEncontrado.setContentText("Su usuario o contraseña son incorrectos.");
            alertaUsuarioNoEncontrado.showAndWait();
        }
    }

    //Metodo para logearse
    @FXML
    private void signUp(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle("Sign Up");
        stage.setScene(scene);
        stage.show();
    }



}
