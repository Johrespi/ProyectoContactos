/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectocontactos;

import Modelo.ArrayList;
import Modelo.Contacto;
import Modelo.Direccion;
import Modelo.DoubleCircleLinkedLists;
import Modelo.Email;
import Modelo.Telefono;
import Modelo.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ListIterator;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.Utilitario;

/**
 * FXML Controller class
 *
 * @author ALVARADO
 */
public class ContactosPrincipalController implements Initializable {
    @FXML
    private RadioButton optionAtr;
    @FXML
    private ToggleGroup ordenes;
    @FXML
    private RadioButton optionTipo;
    @FXML
    private RadioButton optionApellido;
    @FXML
    private TextField txtBusqueda;
    @FXML
    private Button btnAnterior;
    @FXML
    private VBox vbContactos;
    @FXML
    private Button btnSiguiente;
    @FXML
    private MenuItem itmCuenta;        
    @FXML
    private Button btnBuscar;
    @FXML
    private ComboBox<String> cbParametros;
    @FXML
    private Button btnLimpiar;
    
    private int indUltimo;
    private int numPaginas;
    public Contacto contacto;
    private ListIterator<Contacto> iteratorContactos;
    private ListIterator<Contacto> iteratorFiltrados;    
    private DoubleCircleLinkedLists<Contacto> listaFiltrada;
    public Usuario usuario;
    private boolean isFiltrado;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        indUltimo = 0;
        this.numPaginas = 4;       
        llenarParametros();
        isFiltrado = false;
    }    

    public void setUsuario(Usuario u) {
        this.usuario = u;
        this.itmCuenta.setText("Cuenta: "+u.getNombreUsuario());        
        confOptionButton();
        if (!u.getContactos().isEmpty()){
            iteratorContactos = u.getContactos().listIterator();
            llenarContatos(null);
        }
            
    }
    
    private void llenarParametros(){
        this.cbParametros.getItems().addAll("Nombre", "Apellido", "Tipo", "Numero", "Email", "Direccion");
    }
    
    public Usuario getUsuario() {
        return this.usuario;
    }

    public void actualizarIteratorContactos(){
        actualizarArchContactos();
        iteratorContactos = this.usuario.getContactos().listIterator();
        llenarContatos(null);
    }
    
    @FXML
    private void agregarContacto(ActionEvent event) {
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

        } catch (IOException e) {
        }

    }

    @FXML
    private void cerrarSesion(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root = loader.load();            
            Stage st = new Stage();
            st.setTitle("Inicio");
            Scene sc = new Scene(root);
            st.setScene(sc);
            cerrarVentana();
            st.show();            
        } catch (IOException e) {
        }
    }
    
    private void cerrarVentana(){
        Stage ventanaActual = (Stage) vbContactos.getScene().getWindow();
        ventanaActual.close();
    }

    @FXML
    private void limpiarFiltros(ActionEvent event) {        
        ordenes.getToggles().forEach(toggle -> ((RadioButton) toggle).setSelected(false));        
        this.cbParametros.setValue(null);        
        this.txtBusqueda.setText("");
        isFiltrado = false;
        ordenNombre();
    }
    
    public void llenarContatos(String modo){
        vbContactos.getChildren().clear();
        int cont = 0;                             
        
        while (cont<numPaginas &&  this.iteratorContactos.hasNext()){            
            if(cont<this.usuario.getContactos().size()){                                
                if ("back".equals(modo))                    
                    this.vbContactos.getChildren().add(cajaContacto(iteratorContactos.previous()));   
                else if ("next".equals(modo) || modo==null)
                    this.vbContactos.getChildren().add(cajaContacto(iteratorContactos.next()));                                                  
            }else
                break;
            cont++;
        }                
    }
    
    private HBox cajaContacto(Contacto contacto){
        HBox hbContacto = new HBox(5);       
      
        String nombres = contacto.getApellido() + " " + contacto.getNombre();
        Label lblApellido = new Label(nombres);
        
        
        hbContacto.getChildren().addAll(lblApellido, botonesEdicion(contacto));
                
        HBox.setHgrow(hbContacto, Priority.ALWAYS);
        VBox.setVgrow(hbContacto, Priority.ALWAYS);
        hbContacto.setId("hbox-contacto");
        return hbContacto;
    }

    private VBox botonesEdicion(Contacto contacto){                
        Button btnEliminar = new Button ("x");
        Button btnEditar = new Button ("Mostrar");
        
        btnEliminar.setOnAction(e->{
            if(mostrarDialogoConfirmacion()){
                this.usuario.getContactos().remove(indiceContacto(contacto));                
                actualizarIteratorContactos();                  
            }            
        });
        
        btnEditar.setOnAction(eh->editarContacto(contacto));
        VBox vbBotones = new VBox(5);
        vbBotones.getChildren().addAll(btnEliminar,btnEditar);
        return vbBotones;
        
    }
    
    private int indiceContacto(Contacto contacto){
        for (int i = 0; i < usuario.getContactos().size(); i++) {
            if(usuario.getContactos().get(i).equals(contacto))
                return i;
        }
        return -1;
    }
    
    @FXML
    private void actualizarCantidadPagina(ActionEvent event) {
        actualizarIteratorContactos();
        MenuItem menuItem = (MenuItem) event.getSource();
        String cant = menuItem.getText().trim();
        numPaginas = Integer.parseInt(cant);
        llenarContatos(null);
    }

    @FXML
    private void contactosAnterior(ActionEvent event) {    
        if (!this.usuario.getContactos().isEmpty() && !isFiltrado)
            llenarContatos("back");
        else if (!this.usuario.getContactos().isEmpty() && isFiltrado)
            llenarContatosFiltrados("back");
    }

    @FXML
    private void contactosNext(ActionEvent event) {
        if (!this.usuario.getContactos().isEmpty() && !isFiltrado)
            llenarContatos("next");
        else if (!this.usuario.getContactos().isEmpty() && isFiltrado)
            llenarContatosFiltrados("next");
    }
    
    private void actualizarArchContactos(){
        ArrayList<Usuario> AllUsers = Usuario.readListFromFileSerUsuarios();
        int i = AllUsers.indexOf(getUsuario());
        AllUsers.set(i, usuario);                
        this.usuario.setContactos(AllUsers.get(i).getContactos());
        Usuario.saveListToFileSerUsuarios(AllUsers);        
    }
    
    private boolean mostrarDialogoConfirmacion() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText("¿Está seguro de que desea eliminar este contacto?");
        alert.setContentText("Esta acción no se puede deshacer.");

        ButtonType botonConfirmar = new ButtonType("Sí", ButtonBar.ButtonData.OK_DONE);
        ButtonType botonCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(botonConfirmar, botonCancelar);

        Optional<ButtonType> resultado = alert.showAndWait();

        return resultado.isPresent() && resultado.get() == botonConfirmar;
    }
    
    private void editarContacto(Contacto contacto){
        try {            
            this.contacto = contacto;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MostrarContacto.fxml"));
            Parent root = loader.load();            
            MostrarContactoController mostrarContactoController = loader.getController();
            mostrarContactoController.setContactosController(this);

            Stage st = new Stage();
            st.setTitle("Editar contacto");
            Scene sc = new Scene(root);
            st.setScene(sc);
            st.show();            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }
    
    private void confOptionButton(){
        this.optionApellido.setOnAction(e->{ if (!this.usuario.getContactos().isEmpty()) ordenApellido();});
        this.optionTipo.setOnAction(e->{ if (!this.usuario.getContactos().isEmpty()) ordenTipo();});
        this.optionAtr.setOnAction(e->{ if (!this.usuario.getContactos().isEmpty()) ordenAtributos();});
        
    }
    
    private PriorityQueue<Contacto> ordenApellido(){                
        PriorityQueue<Contacto> cola = null;                
        if(this.optionApellido.isSelected()){
            cola = new PriorityQueue<>((c1, c2) -> {
                String apellido1 = c1.getApellido();
                String apellido2 = c2.getApellido();
                if (apellido1 == null && apellido2 == null) {
                    return 0;
                } else if (apellido1 == null) {
                    return 1;
                } else if (apellido2 == null) {
                    return -1;
                }
                return apellido1.compareTo(apellido2);
            });

        }            

        if (cola!=null && !isFiltrado)
            ordenarLista(cola);
        else if(cola!=null && isFiltrado) 
            actualizarIteratorFiltrado(cola);
        return cola;

    }  
    
    private PriorityQueue<Contacto> ordenTipo(){        
        PriorityQueue<Contacto> cola = null;   
        if(this.optionTipo.isSelected()){
            cola = new PriorityQueue<>((c1, c2) -> {
                    String tipo1 = c1.getTipoContacto();
                    String tipo2 = c2.getTipoContacto();
                    if (tipo1 == null && tipo2 == null) {
                        return 0;
                    } else if (tipo1 == null) {
                        return 1;
                    } else if (tipo2 == null) {
                        return -1;
                    }
                    return tipo1.compareTo(tipo2);
                });
        }
        if (cola!=null && !isFiltrado)
            ordenarLista(cola);
        else if(cola!=null && isFiltrado) 
            actualizarIteratorFiltrado(cola);
        return cola;
    }
    
    private PriorityQueue<Contacto> ordenAtributos(){        
        PriorityQueue<Contacto> cola = null;   
        if(this.optionAtr.isSelected()){
            cola = new PriorityQueue<>((c1, c2) -> {
                int atributosNoNulos1 = contarAtributosNoNulos(c1);
                int atributosNoNulos2 = contarAtributosNoNulos(c2);

                return Integer.compare(atributosNoNulos1, atributosNoNulos2);
            });
        }
        if (cola!=null && !isFiltrado)
            ordenarLista(cola);
        else if(cola!=null && isFiltrado) 
            actualizarIteratorFiltrado(cola);
        return cola;
    }
    
    public int contarAtributosNoNulos(Contacto contacto) {
        int contador = 0;

        if (contacto.getNombre() != null && !contacto.getNombre().isEmpty()) 
            contador++;
        if (contacto.getApellido() != null && !contacto.getApellido().isEmpty()) 
            contador++;
        if (contacto.getTipoContacto() != null && !contacto.getTipoContacto().isEmpty()) 
            contador++;
        if (contacto.getDirecciones() != null && !contacto.getDirecciones().isEmpty()) 
            contador++;
        if (contacto.getEmails() != null && !contacto.getEmails().isEmpty()) 
            contador++;        
        if (contacto.getNumerosTelefono() != null && !contacto.getNumerosTelefono().isEmpty())
            contador++;     
        if (contacto.getRedesSociales() != null && !contacto.getRedesSociales().isEmpty())
            contador++;      
        if (contacto.getContactosRelacionados() != null && !contacto.getContactosRelacionados().isEmpty()) 
            contador++;
        if (contacto.getFotos() != null && !contacto.getFotos().isEmpty())
            contador++;        
        if (contacto.getFechasInteres() != null && !contacto.getFechasInteres().isEmpty())
            contador++;        
        return contador;
    }
    
    private DoubleCircleLinkedLists<Contacto> llenarListaPriority(PriorityQueue<Contacto> cola){
        DoubleCircleLinkedLists<Contacto> contactosOrdenados = new DoubleCircleLinkedLists<>();
        for(Contacto c: this.usuario.getContactos()){
            cola.offer(c);            
        }
        while(!cola.isEmpty())
            contactosOrdenados.addFirst(cola.poll());
        return contactosOrdenados;
    }
    
    private void ordenarLista(PriorityQueue<Contacto> cola){
        DoubleCircleLinkedLists<Contacto> contactosOrdenados = new DoubleCircleLinkedLists<>();
        for(Contacto c: this.usuario.getContactos()){
            cola.offer(c);            
        }
        while(!cola.isEmpty())
            contactosOrdenados.addFirst(cola.poll());
                           
        this.usuario.getContactos().clear();
        this.usuario.setContactos(contactosOrdenados);
        actualizarIteratorContactos();    
        this.llenarContatos(null);
    }
       
    private void ordenNombre(){                
        PriorityQueue<Contacto> cola = null;                
        cola = new PriorityQueue<>((c1, c2) -> {
                String nombre1 = c1.getNombre();
                String nombre2 = c2.getNombre();
                if (nombre1 == null && nombre2 == null) {
                    return 0;
                } else if (nombre1 == null) {
                    return 1;
                } else if (nombre2 == null) {
                    return -1;
                }
                return nombre1.compareTo(nombre2);
            });         

        if (cola!=null)
            ordenarLista(cola);

    }

    private boolean validarParametros(){
        if (this.cbParametros.getValue()==null)
            Utilitario.mensajeAlertaError("Parametros no seleccionados", "Debe elegir el parametro por el cual desea realizar la busqueda.");
        
        return this.cbParametros.getValue()==null;
    }
    
    
    @FXML
    private void filtrar(ActionEvent event) {
        listaFiltrada = new DoubleCircleLinkedLists<>();
        DoubleCircleLinkedLists<Contacto> contactos = new DoubleCircleLinkedLists<>();
        if(!this.txtBusqueda.getText().isBlank() && !validarParametros()){            
            if(ordenes.getSelectedToggle() == null) contactos = this.usuario.getContactos();
            else{
                if(optionAtr.isSelected()) contactos = llenarListaPriority(ordenAtributos());
                if(optionTipo.isSelected())contactos = llenarListaPriority(ordenTipo());
                if(optionApellido.isSelected()) contactos = llenarListaPriority(ordenApellido());
            }                            
            for(Contacto c: contactos){
                if("Nombre".equals(this.cbParametros.getValue()))
                    agregarItemFiltrado(c.getNombre(), c);                        
                if("Apellido".equals(this.cbParametros.getValue()))
                    agregarItemFiltrado(c.getApellido(), c);  
                if("Tipo".equals(this.cbParametros.getValue()))
                    agregarItemFiltrado(c.getTipoContacto(), c);
                if("Numero".equals(this.cbParametros.getValue())){
                    for(Telefono t: c.getNumerosTelefono())
                        agregarItemFiltrado(t.getTelefono(), c);
                }                        
                if("Email".equals(this.cbParametros.getValue()))
                    for(Email e: c.getEmails())
                        agregarItemFiltrado(e.getEmail(), c);
                if("Direccion".equals(this.cbParametros.getValue()))
                    for(Direccion d: c.getDirecciones())
                        agregarItemFiltrado(d.getDireccion(), c);
            }     
            System.out.println(listaFiltrada);
            isFiltrado = true;
            this.iteratorFiltrados = listaFiltrada.listIterator();
            llenarContatosFiltrados(null);   
         
        }        
        
    }
    
    private void actualizarIteratorFiltrado(PriorityQueue<Contacto> cola){
        DoubleCircleLinkedLists<Contacto> contactosOrdenados = new DoubleCircleLinkedLists<>();
        for(Contacto c: this.listaFiltrada){
            cola.offer(c);            
        }
        while(!cola.isEmpty())
            contactosOrdenados.addFirst(cola.poll());
        
        this.iteratorFiltrados = listaFiltrada.listIterator();
    }
   
    
    
    private void agregarItemFiltrado(String valor, Contacto contacto){
        if(valor.trim().toLowerCase().contains(this.txtBusqueda.getText().trim()))
            listaFiltrada.addFirst(contacto);
    }   
    
    public void llenarContatosFiltrados(String modo){        
        vbContactos.getChildren().clear();
        int cont = 0;                             
        
        while (cont<numPaginas &&  this.iteratorFiltrados.hasNext() && cont<this.listaFiltrada.size() ){            
            if(cont<this.usuario.getContactos().size()){                                
                if ("back".equals(modo))                    
                    this.vbContactos.getChildren().add(cajaContacto(iteratorFiltrados.previous()));   
                else if ("next".equals(modo) || modo==null)
                    this.vbContactos.getChildren().add(cajaContacto(iteratorFiltrados.next()));                                                  
            }else
                break;
            cont++;
        }                
    }
       
    
}