/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectocontactos;

import Modelo.Contacto;
import Modelo.Direccion;
import Modelo.Email;
import Modelo.FechaInteres;
import Modelo.Relacion;
import Modelo.Telefono;
import Modelo.redSocial;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author johan
 */
public class MostrarContactoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ListView<redSocial> redesSocialesList;
    @FXML
    private ListView<FechaInteres> fechasList;
    @FXML
    private ListView<Relacion> relacionesList;
    @FXML
    private ListView<Email> emailsList;
    @FXML
    private ListView<Telefono> telefonosList;
    @FXML
    private ListView<Direccion> direccionesList;
    @FXML
    private Button DireccionBtn;
    @FXML
    private TextField etDireccionField;
    @FXML
    private Button EmailBtn;
    @FXML
    private TextField etEmailField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField direccionField;
    @FXML
    private TextField etTelefonoField;
    @FXML
    private TextField telefonoField;
    @FXML
    private Button TelefonoBtn;
    @FXML
    private TextField etRedField;
    @FXML
    private TextField redField;
    @FXML
    private Button RedBtn;
    @FXML
    private TextField etFechaFIeld;
    @FXML
    private Button FechaBtn;
    @FXML
    private TextField etRelacionField;
    @FXML
    private Button RelacionBtn;
    @FXML
    private Label nombreLabel;
    @FXML
    private Label ApellidoLabel;
    @FXML
    private Label empresaLabel;
    @FXML
    private DatePicker fechaField;
    @FXML
    private Label relacionField;
    @FXML
    private Button removeTelefonoBtn;
    @FXML
    private Button removeEmailBtn;
    @FXML
    private Button removeDireccionBtn;
    @FXML
    private Button removeRedBtn;
    @FXML
    private Button removeFechaBtn;
    @FXML
    private Button removeRelacionBtn;
    @FXML
    private TextField nombreField;
    @FXML
    private TextField apellidoField;
    @FXML
    private TextField empresaField;
    @FXML
    private Button nombreBtn;
    @FXML
    private Button apellidoBtn;
    @FXML
    private Button empresaBtn;

    private ContactosController contactosController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void addDireccion(ActionEvent event) {
        if (!direccionField.getText().isBlank() && !etDireccionField.getText().isBlank()) {

            Direccion direccion = new Direccion(etDireccionField.getText(), direccionField.getText());
            contactosController.contacto.getDirecciones().addLast(direccion);
            AlertaAdd();
        } else if ((direccionField.getText().isBlank() && !etDireccionField.getText().isBlank()) || (!direccionField.getText().isBlank() && etDireccionField.getText().isBlank())) {
            AlertaCampos();
        }
    }

    @FXML
    private void addEmail(ActionEvent event) {
        if (!emailField.getText().isBlank() && !etEmailField.getText().isBlank()) {
            Email email = new Email(etEmailField.getText(), emailField.getText());
            contactosController.contacto.getEmails().addLast(email);
            AlertaAdd();
        } else if ((emailField.getText().isBlank() && !etEmailField.getText().isBlank()) || (!emailField.getText().isBlank() && etEmailField.getText().isBlank())) {
            AlertaCampos();
        }
    }

    @FXML
    private void addTelefono(ActionEvent event) {
        if (!telefonoField.getText().isBlank() && !etTelefonoField.getText().isBlank()) {
            Telefono telefono = new Telefono(etTelefonoField.getText(), telefonoField.getText());
            contactosController.contacto.getNumerosTelefono().addLast(telefono);
            AlertaAdd();
        } else if ((telefonoField.getText().isBlank() && !etTelefonoField.getText().isBlank()) || (!telefonoField.getText().isBlank() && etTelefonoField.getText().isBlank())) {
            AlertaCampos();
        }
    }

    @FXML
    private void addRed(ActionEvent event) {
        if (!etRedField.getText().isBlank() && !redField.getText().isBlank()) {
            redSocial redSocial = new redSocial(etRedField.getText(), redField.getText());
            contactosController.contacto.getRedesSociales().addLast(redSocial);
            AlertaAdd();
        } else if ((etRedField.getText().isBlank() && !redField.getText().isBlank()) || (!etRedField.getText().isBlank() && redField.getText().isBlank())) {
            AlertaCampos();
        }
    }

    @FXML
    private void addFecha(ActionEvent event) {
        if (fechaField.getValue() != null && !etFechaFIeld.getText().isBlank()) {
            LocalDate myDate = fechaField.getValue();
            FechaInteres fechaInteres = new FechaInteres(myDate.toString(), etFechaFIeld.getText());
            contactosController.contacto.getFechasInteres().addLast(fechaInteres);
            AlertaAdd();
        } else if ((fechaField.getValue() == null && !etFechaFIeld.getText().isBlank()) || (fechaField.getValue() != null && etFechaFIeld.getText().isBlank())) {
            AlertaCampos();
        }
    }

    @FXML
    private void addRelacion(ActionEvent event) {
        if (!etRelacionField.getText().isBlank() && !relacionField.getText().isBlank()) {
            Relacion relacion = new Relacion(etRelacionField.getText(), relacionField.getText());
            contactosController.contacto.getContactosRelacionados().addLast(relacion);
            AlertaAdd();
        } else if ((etRelacionField.getText().isBlank() && !relacionField.getText().isBlank()) || (!etRelacionField.getText().isBlank() && relacionField.getText().isBlank())) {
            AlertaCampos();
        }
    }

    @FXML
    private void removeTelefono(ActionEvent event) {
    }

    @FXML
    private void removeEmail(ActionEvent event) {
    }

    @FXML
    private void removeDireccion(ActionEvent event) {
    }

    @FXML
    private void removeRed(ActionEvent event) {
    }

    @FXML
    private void removeFecha(ActionEvent event) {
    }

    @FXML
    private void removeRelacion(ActionEvent event) {
    }

    public void setContactosController(ContactosController contactosController) {
        this.contactosController = contactosController;
        initializeContacto();
    }

    private void initializeContacto() {
        if (contactosController.contacto.getApellido() == null) {
            empresaField.setText(contactosController.contacto.getNombre());
        } else {
            nombreField.setText(contactosController.contacto.getNombre());
            apellidoField.setText(contactosController.contacto.getApellido());
        }

        telefonosList.setItems(FXCollections.observableArrayList(contactosController.contacto.getNumerosTelefono()));
        emailsList.setItems(FXCollections.observableArrayList(contactosController.contacto.getEmails()));
        direccionesList.setItems(FXCollections.observableArrayList(contactosController.contacto.getDirecciones()));
        relacionesList.setItems(FXCollections.observableArrayList(contactosController.contacto.getContactosRelacionados()));
        fechasList.setItems(FXCollections.observableArrayList(contactosController.contacto.getFechasInteres()));
        redesSocialesList.setItems(FXCollections.observableArrayList(contactosController.contacto.getRedesSociales()));
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
