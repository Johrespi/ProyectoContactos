/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectocontactos;

import Modelo.ArrayList;
import Modelo.Contacto;
import Modelo.Direccion;
import Modelo.Email;
import Modelo.Email;
import Modelo.FechaInteres;
import Modelo.Relacion;
import Modelo.Telefono;
import Modelo.Usuario;
import Modelo.redSocial;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author johan
 */
public class AgregarContactosController implements Initializable {

    @FXML
    private Label tituloLabel;
    @FXML
    private Label nombreLabel;
    @FXML
    private Label apellidoLabel;
    @FXML
    private Label empresaLabel;
    @FXML
    private Label actualizadorLabel;
    @FXML
    private Button crearButton;
    @FXML
    private Button addFechaButton;
    @FXML
    private Label tipoFechaLabel;
    @FXML
    private Label fechaLabel;
    @FXML
    private Button addFotoButton;
    @FXML
    private Label emailLabel;
    @FXML
    private Label tipoRelacionLabel;
    @FXML
    private Label nombreRelacionLabel;
    @FXML
    private Button addRelacionButton;
    @FXML
    private Label EtiquetaEmailLabel;
    @FXML
    private Button addEmailButton;
    @FXML
    private Label EtiquetaDireccionLabel;
    @FXML
    private Button addDireccionButton;
    @FXML
    private Label direccionLabel;
    @FXML
    private Label etiquetaTelLabel;
    @FXML
    private Label telefonoLabel;
    @FXML
    private Button addTelefonoButton;
    @FXML
    private Label redSocialLabel;
    @FXML
    private Label idRedSocialLabel;
    @FXML
    private Button addRedSocialButton;
    @FXML
    private DatePicker fechaField;
    @FXML
    private TextField etiquetaEmailField;
    @FXML
    private TextField tipoRelacionField;
    @FXML
    private TextField empresaField;
    @FXML
    private TextField apellidoField;
    @FXML
    private TextField nombreField;
    @FXML
    private TextField tipoFechaField;
    @FXML
    private TextField nombreRelacionField;
    @FXML
    private TextField etiquetaDireccionField;
    @FXML
    private TextField direccionField;
    @FXML
    private TextField etiquetaTelefonoField;
    @FXML
    private TextField telefonoFIeld;
    @FXML
    private TextField etiquetaRedField;
    @FXML
    private TextField usernameRedField;
    @FXML
    private TextField emailField;
    @FXML
    private Label favoritoLabel;
    @FXML
    private ChoiceBox<String> FavoritoBox;

    private ContactosController contactosController;

    Contacto contacto = new Contacto();

    private final String[] esFavorito = {"Sí", "No"};
    @FXML
    private Label tipoContactoLabel;
    @FXML
    private TextField tipoContactoField;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        FavoritoBox.getItems().addAll(esFavorito);

    }

    @FXML
    private void crearContacto(ActionEvent event) throws IOException {
        Usuario u = contactosController.usuario;
        System.out.println(u);
        if ((!nombreField.getText().isBlank() && !apellidoField.getText().isBlank())) {
            contacto.setNombre(nombreField.getText());
            contacto.setApellido(apellidoField.getText());
            contacto.setTipoContacto(tipoContactoField.getText());
            Favorito();
            guardarContacto();
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

        } else if (!empresaField.getText().isBlank()) {
            contacto.setNombre(empresaField.getText());
            contacto.setApellido(null);
            contacto.setTipoContacto("Empresa");
            Favorito();
            guardarContacto();
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }

    public void Favorito() {
        if (FavoritoBox.getValue() == null || FavoritoBox.getValue().equals(esFavorito[1])) {
            contacto.setEsFavorito(false);
        } else {
            contacto.setEsFavorito(true);
        }
    }

    public void guardarContacto() {
        contactosController.usuario.getContactos().add(contacto);
        ArrayList<Usuario> AllUsers = Usuario.readListFromFileSerUsuarios();
        for (Usuario user : AllUsers) {
            if (contactosController.usuario.equals(user)) {
                user.getContactos().add(contacto);
                System.out.println(contacto);
                Usuario.saveListToFileSerUsuarios(AllUsers);

            }
        }
        contactosController.actualizarListView();
        Alert Guardado = new Alert(Alert.AlertType.INFORMATION);
        Guardado.setTitle("Guardado");
        Guardado.setContentText("Su contacto se a guardado");
        Guardado.showAndWait();
    }

    @FXML
    private void addFoto(ActionEvent event) {

    }

    @FXML
    private void addFecha(ActionEvent event) {
        if (fechaField.getValue() != null && !tipoFechaField.getText().isBlank()) {
            LocalDate myDate = fechaField.getValue();
            FechaInteres fechaInteres = new FechaInteres(myDate.toString(), tipoFechaField.getText());
            contacto.getFechasInteres().addLast(fechaInteres);
            AlertaAdd();
        } else if ((fechaField.getValue() == null && !tipoFechaField.getText().isBlank()) || (fechaField.getValue() != null && tipoFechaField.getText().isBlank())) {
            AlertaCampos();
        }
    }

    @FXML
    private void addRelacion(ActionEvent event) {
        if (!tipoRelacionField.getText().isBlank() && !nombreRelacionField.getText().isBlank()) {
            Relacion relacion = new Relacion(tipoRelacionField.getText(), nombreRelacionField.getText());
            contacto.getContactosRelacionados().addLast(relacion);
            AlertaAdd();
        } else if ((tipoRelacionField.getText().isBlank() && !nombreRelacionField.getText().isBlank()) || (!tipoRelacionField.getText().isBlank() && nombreRelacionField.getText().isBlank())) {
            AlertaCampos();
        }
    }

    @FXML
    private void addEmail(ActionEvent event) {
        if (!emailField.getText().isBlank() && !etiquetaEmailField.getText().isBlank()) {
            Email email = new Email(etiquetaEmailField.getText(), emailField.getText());
            contacto.getEmails().addLast(email);
            AlertaAdd();
        } else if ((emailField.getText().isBlank() && !etiquetaEmailField.getText().isBlank()) || (!emailField.getText().isBlank() && etiquetaEmailField.getText().isBlank())) {
            AlertaCampos();
        }
    }

    @FXML
    private void addDireccion(ActionEvent event) {
        if (!direccionField.getText().isBlank() && !etiquetaDireccionField.getText().isBlank()) {
            Direccion direccion = new Direccion(etiquetaDireccionField.getText(), direccionField.getText());
            contacto.getDirecciones().addLast(direccion);
            AlertaAdd();
        } else if ((direccionField.getText().isBlank() && !etiquetaDireccionField.getText().isBlank()) || (!direccionField.getText().isBlank() && etiquetaDireccionField.getText().isBlank())) {
            AlertaCampos();
        }
    }

    @FXML
    private void addTelefono(ActionEvent event) {
        if (!telefonoFIeld.getText().isBlank() && !etiquetaTelefonoField.getText().isBlank()) {
            Telefono telefono = new Telefono(etiquetaTelefonoField.getText(), telefonoFIeld.getText());
            contacto.getNumerosTelefono().addLast(telefono);
            AlertaAdd();
        } else if ((telefonoFIeld.getText().isBlank() && !etiquetaTelefonoField.getText().isBlank()) || (!telefonoFIeld.getText().isBlank() && etiquetaTelefonoField.getText().isBlank())) {
            AlertaCampos();
        }
    }

    @FXML
    private void addRed(ActionEvent event) {
        if (!etiquetaRedField.getText().isBlank() && !usernameRedField.getText().isBlank()) {
            redSocial redSocial = new redSocial(etiquetaRedField.getText(), usernameRedField.getText());
            contacto.getRedesSociales().addLast(redSocial);
            AlertaAdd();
        } else if ((etiquetaRedField.getText().isBlank() && !usernameRedField.getText().isBlank()) || (!etiquetaRedField.getText().isBlank() && usernameRedField.getText().isBlank())) {
            AlertaCampos();
        }
    }

    public void setContactosController(ContactosController contactosController) {
        this.contactosController = contactosController;
    }

    public void AlertaCampos() {
        Alert alertaCampos = new Alert(Alert.AlertType.ERROR);
        alertaCampos.setContentText("Usted no ha llenado los campos obligatorios");
        alertaCampos.setTitle("Campos Obligatorios");
        alertaCampos.showAndWait();
    }

    public void AlertaAdd() {
        Alert alertaCampos = new Alert(Alert.AlertType.CONFIRMATION);
        alertaCampos.setContentText("Se ha agredado la información exitosamente");
        alertaCampos.setTitle("Agregación exitosa");
        alertaCampos.showAndWait();
    }

}
