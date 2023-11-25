/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javafx.scene.image.Image;
/**
 *
 * @author johan
 */
public class Contacto implements Serializable {

    private String nombre;
    private String apellido;
    private String tipoContacto; //nombre de la empresa
    private ArrayList<Direccion> direcciones;
    private ArrayList<Email> emails;
    private ArrayList<Telefono> numerosTelefono;
    private ArrayList<redSocial> identificadoresRedesSociales;
    private ArrayList<String> ContactosRelacionados;
    private DoubleCircleLinkedList<Image> fotos; //podria ser
    private ArrayList<FechaInteres> fechasInteres;
    private boolean esFavorito;

    public Contacto(String nombre, String apellido, String tipoContacto, boolean esFavorito) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoContacto = tipoContacto;
        this.esFavorito = esFavorito;
        this.direcciones = new ArrayList<>();
        this.emails = new ArrayList<>();
        this.numerosTelefono = new ArrayList<>();
        this.identificadoresRedesSociales = new ArrayList<>();
        this.fotos = new DoubleCircleLinkedList<>();
        this.fechasInteres = new ArrayList<>();
        this.ContactosRelacionados = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public ArrayList<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(ArrayList<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    public ArrayList<String> getContactosRelacionados() {
        return ContactosRelacionados;
    }

    public void setContactosRelacionados(ArrayList<String> ContactosRelacionados) {
        this.ContactosRelacionados = ContactosRelacionados;
    }

    public ArrayList<FechaInteres> getFechasInteres() {
        return fechasInteres;
    }

    public void setFechasInteres(ArrayList<FechaInteres> fechasInteres) {
        this.fechasInteres = fechasInteres;
    }

    public boolean isEsFavorito() {
        return esFavorito;
    }

    public void setEsFavorito(boolean esFavorito) {
        this.esFavorito = esFavorito;
    }



    public String getTipoContacto() {
        return tipoContacto;
    }

    public void setTipoContacto(String tipoContacto) {
        this.tipoContacto = tipoContacto;
    }

    public static void saveListToFileSerContactos(DoubleCircleLinkedList<Contacto> contactos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Contactos.ser"))) {
            oos.writeObject(contactos);
        } catch (Exception e) {

        }
    }

    public static DoubleCircleLinkedList<Contacto> readListFromFileSerContactos() {
        DoubleCircleLinkedList<Contacto> contactos = new DoubleCircleLinkedList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Contactos.ser"))) {
            contactos = (DoubleCircleLinkedList<Contacto>) ois.readObject();
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        } catch (Exception a) {
        }
        return contactos;

    }

    @Override
    public String toString() {
        return "Contact{"
                + "nombre='" + nombre + '\''
                + ", apellido='" + apellido + '\''
                + ", numeroTelefono='" + numerosTelefono + '\''
                + ", email='" + emails + '\''
                + ", direccion='" + direcciones + '\''
                + ", tipoContacto='" + tipoContacto + '\''
                + '}';
    }

}
