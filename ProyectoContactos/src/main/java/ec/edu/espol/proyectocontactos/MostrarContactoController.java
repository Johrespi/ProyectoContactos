/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectocontactos;

import Modelo.ArrayList;
import Modelo.Contacto;
import Modelo.Direccion;
import Modelo.DoubleCircleLinkedList;
import Modelo.Email;
import Modelo.FechaInteres;
import Modelo.Relacion;
import Modelo.Telefono;
import Modelo.Usuario;
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
    @FXML
    private TextField relacionFieldd;

    private ContactosController contactosController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void agregarInformacionContacto(Contacto contacto, Object o) {
        ArrayList<Usuario> usuarios = Usuario.readListFromFileSerUsuarios();
        for (Usuario u : usuarios) {
            if (contactosController.usuario.equals(u)) {
                for (Contacto c : u.getContactos()) {
                    if (c.equals(contacto)) {
                        if (o instanceof Direccion) {
                            c.getDirecciones().addLast((Direccion) o);
                        } else if (o instanceof Email) {
                            c.getEmails().addLast((Email) o);
                        } else if (o instanceof Relacion) {
                            c.getContactosRelacionados().addLast((Relacion) o);
                        } else if (o instanceof Telefono) {
                            c.getNumerosTelefono().addLast((Telefono) o);
                        } else if (o instanceof redSocial) {
                            c.getRedesSociales().addLast((redSocial) o);
                        } else if (o instanceof FechaInteres) {
                            c.getFechasInteres().addLast((FechaInteres) o);
                        }

                        break;

                    }
                }
            }
        }
        Usuario.saveListToFileSerUsuarios(usuarios);
    }

    private void removerInformacionContacto(Contacto contacto, Object o) {
        ArrayList<Usuario> usuarios = Usuario.readListFromFileSerUsuarios();
        for (Usuario u : usuarios) {
            if (contactosController.usuario.equals(u)) {
                for (Contacto c : u.getContactos()) {
                    if (c.equals(contacto)) {
                        if (o instanceof Direccion) {
                            int index = c.getDirecciones().indexOf((Direccion) o);
                            c.getDirecciones().remove(index);
                        } else if (o instanceof Email) {
                            int index = c.getEmails().indexOf((Email) o);
                            c.getEmails().remove(index);
                        } else if (o instanceof Relacion) {
                            int index = c.getContactosRelacionados().indexOf((Relacion) o);
                            c.getContactosRelacionados().remove(index);
                        } else if (o instanceof Telefono) {
                            int index = c.getNumerosTelefono().indexOf((Telefono) o);
                            c.getNumerosTelefono().remove(index);
                        } else if (o instanceof redSocial) {
                            int index = c.getRedesSociales().indexOf((redSocial) o);
                            c.getRedesSociales().remove(index);
                        } else if (o instanceof FechaInteres) {
                            int index = c.getFechasInteres().indexOf((FechaInteres) o);
                            c.getFechasInteres().remove(index);
                        }

                        break;

                    }
                }
            }
        }
        Usuario.saveListToFileSerUsuarios(usuarios);
    }

    @FXML
    private void addDireccion(ActionEvent event) {
        if (!direccionField.getText().isBlank() && !etDireccionField.getText().isBlank()) {
            Direccion direccion = new Direccion(etDireccionField.getText(), direccionField.getText());
            agregarInformacionContacto(contactosController.contacto, direccion);
            initializeContacto();
        } else if ((direccionField.getText().isBlank() && !etDireccionField.getText().isBlank()) || (!direccionField.getText().isBlank() && etDireccionField.getText().isBlank())) {
            AlertaCampos();
        }
    }

    @FXML
    private void addEmail(ActionEvent event) {
        if (!emailField.getText().isBlank() && !etEmailField.getText().isBlank()) {
            Email email = new Email(etEmailField.getText(), emailField.getText());
            agregarInformacionContacto(contactosController.contacto, email);
            initializeContacto();

        } else if ((emailField.getText().isBlank() && !etEmailField.getText().isBlank()) || (!emailField.getText().isBlank() && etEmailField.getText().isBlank())) {
            AlertaCampos();
        }
    }

    @FXML
    private void addTelefono(ActionEvent event) {
        if (!telefonoField.getText().isBlank() && !etTelefonoField.getText().isBlank()) {
            Telefono telefono = new Telefono(etTelefonoField.getText(), telefonoField.getText());
            agregarInformacionContacto(contactosController.contacto, telefono);
            initializeContacto();
        } else if ((telefonoField.getText().isBlank() && !etTelefonoField.getText().isBlank()) || (!telefonoField.getText().isBlank() && etTelefonoField.getText().isBlank())) {
            AlertaCampos();
        }
    }

    @FXML
    private void addRed(ActionEvent event) {
        if (!etRedField.getText().isBlank() && !redField.getText().isBlank()) {
            redSocial redSocial = new redSocial(etRedField.getText(), redField.getText());
            agregarInformacionContacto(contactosController.contacto, redSocial);
            initializeContacto();

        } else if ((etRedField.getText().isBlank() && !redField.getText().isBlank()) || (!etRedField.getText().isBlank() && redField.getText().isBlank())) {
            AlertaCampos();
        }
    }

    @FXML
    private void addFecha(ActionEvent event) {
        if (fechaField.getValue() != null && !etFechaFIeld.getText().isBlank()) {
            LocalDate myDate = fechaField.getValue();
            FechaInteres fechaInteres = new FechaInteres(myDate.toString(), etFechaFIeld.getText());
            agregarInformacionContacto(contactosController.contacto, fechaInteres);
            initializeContacto();

        } else if ((fechaField.getValue() == null && !etFechaFIeld.getText().isBlank()) || (fechaField.getValue() != null && etFechaFIeld.getText().isBlank())) {
            AlertaCampos();
        }
    }

    @FXML
    private void addRelacion(ActionEvent event) {
        if (!etRelacionField.getText().isBlank() && !relacionFieldd.getText().isBlank()) {
            Relacion relacion = new Relacion(etRelacionField.getText(), relacionFieldd.getText());
            agregarInformacionContacto(contactosController.contacto, relacion);
            initializeContacto();
        } else if ((etRelacionField.getText().isBlank() && !relacionFieldd.getText().isBlank()) || (!etRelacionField.getText().isBlank() && relacionFieldd.getText().isBlank())) {
            AlertaCampos();
        }
    }

    @FXML
    private void removeTelefono(ActionEvent event) {
        Telefono selectedTelefono = telefonosList.getSelectionModel().getSelectedItem();
        removerInformacionContacto(contactosController.contacto, selectedTelefono);
        initializeContacto();
    }

    @FXML
    private void removeEmail(ActionEvent event) {
        Email selectedEmail = emailsList.getSelectionModel().getSelectedItem();
        removerInformacionContacto(contactosController.contacto, selectedEmail);
        initializeContacto();
    }

    @FXML
    private void removeDireccion(ActionEvent event) {
        Direccion selectedDireccion = direccionesList.getSelectionModel().getSelectedItem();
        removerInformacionContacto(contactosController.contacto, selectedDireccion);
        initializeContacto();
    }

    @FXML
    private void removeRed(ActionEvent event) {
        redSocial selectedRed = redesSocialesList.getSelectionModel().getSelectedItem();
        removerInformacionContacto(contactosController.contacto, selectedRed);
        initializeContacto();
    }

    @FXML
    private void removeFecha(ActionEvent event) {
        FechaInteres selectedFecha = fechasList.getSelectionModel().getSelectedItem();
        removerInformacionContacto(contactosController.contacto, selectedFecha);
        initializeContacto();
    }

    @FXML
    private void removeRelacion(ActionEvent event) {
        Relacion selectedRelacion = relacionesList.getSelectionModel().getSelectedItem();
        removerInformacionContacto(contactosController.contacto, selectedRelacion);
        initializeContacto();
    }

    public void setContactosController(ContactosController contactosController) {
        this.contactosController = contactosController;
        initializeContacto();
    }

    private void initializeContacto() {
        if(contactosController.contacto.getTipoContacto().equals("Empresa")){
            nombreBtn.setDisable(true);
            apellidoBtn.setDisable(true);
        } else{
            empresaBtn.setDisable(true);
        }
        ArrayList<Usuario> usuarios = Usuario.readListFromFileSerUsuarios();
        for (Usuario u : usuarios) {
            if (u.equals(contactosController.usuario)) {
                for (Contacto c : u.getContactos()) {
                    if (c.equals(contactosController.contacto)) {

                        if (c.getApellido().isBlank()) {
                            empresaField.setText(c.getNombre());
                        } else {
                            nombreField.setText(c.getNombre());
                            apellidoField.setText(c.getApellido());
                        }
                        if (!c.getNumerosTelefono().isEmpty()) {
                            telefonosList.setItems(FXCollections.observableArrayList(c.getNumerosTelefono()));
                        }
                        if (!c.getEmails().isEmpty()) {
                            emailsList.setItems(FXCollections.observableArrayList(c.getEmails()));
                        }
                        if (!c.getDirecciones().isEmpty()) {
                            direccionesList.setItems(FXCollections.observableArrayList(c.getDirecciones()));
                        }
                        if (!c.getContactosRelacionados().isEmpty()) {
                            relacionesList.setItems(FXCollections.observableArrayList(c.getContactosRelacionados()));
                        }
                        if (!c.getFechasInteres().isEmpty()) {
                            fechasList.setItems(FXCollections.observableArrayList(c.getFechasInteres()));
                        }
                        if (!c.getRedesSociales().isEmpty()) {
                            redesSocialesList.setItems(FXCollections.observableArrayList(c.getRedesSociales()));
                        }

                    }
                }
            }
        }

    }

    @FXML
    private void cambiarNombre(ActionEvent event) {

        ArrayList<Usuario> usuarios = Usuario.readListFromFileSerUsuarios();
        for (Usuario u : usuarios) {
            if (contactosController.usuario.equals(u)) {
                for (Contacto c : u.getContactos()) {
                    if (c.equals(contactosController.contacto)) {
                        if (!nombreField.getText().isBlank() && !c.getTipoContacto().equals("Empresa")) {
                            c.setNombre(nombreField.getText());
                            AlertaAtributoCambiado();
                            contactosController.contacto.setNombre(nombreField.getText());
                            contactosController.actualizarListView();
                        }

                    }
                }
            }
        }
        Usuario.saveListToFileSerUsuarios(usuarios);
    }

    @FXML
    private void cambiarApellido(ActionEvent event) {
        ArrayList<Usuario> usuarios = Usuario.readListFromFileSerUsuarios();
        for (Usuario u : usuarios) {
            if (contactosController.usuario.equals(u)) {
                for (Contacto c : u.getContactos()) {
                    if (c.equals(contactosController.contacto)) {
                        if (!apellidoField.getText().isBlank() && !c.getTipoContacto().equals("Empresa")) {
                            c.setApellido(apellidoField.getText());
                            AlertaAtributoCambiado();
                            contactosController.contacto.setApellido(apellidoField.getText());
                            contactosController.actualizarListView();

                        }

                    }
                }
            }
        }
        Usuario.saveListToFileSerUsuarios(usuarios);
    }

    @FXML
    private void cambiarEmpresa(ActionEvent event) {
        ArrayList<Usuario> usuarios = Usuario.readListFromFileSerUsuarios();
        for (Usuario u : usuarios) {
            if (contactosController.usuario.equals(u)) {
                for (Contacto c : u.getContactos()) {
                    if (c.equals(contactosController.contacto)) {
                        if (c.getTipoContacto().equals("Empresa")) {
                            c.setNombre(empresaField.getText());
                            AlertaAtributoCambiado();
                            contactosController.contacto.setNombre(empresaField.getText());
                            contactosController.actualizarListView();

                        }

                    }
                }
            }
        }
        Usuario.saveListToFileSerUsuarios(usuarios);
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

    public void AlertaAtributoCambiado() {
        Alert alertaCampos = new Alert(Alert.AlertType.CONFIRMATION);
        alertaCampos.setContentText("Se ha cambiado la información");
        alertaCampos.setTitle("Cambio exitoso");
        alertaCampos.showAndWait();
    }

}
