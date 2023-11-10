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
import java.util.ArrayList;
import java.util.LinkedList;
import javafx.scene.image.Image;

/**
 *
 * @author johan
 */
public class Contacto implements Serializable {

    private String nombre;
    private String apellido;
    private String tipoContacto; //nombre de la empresa
    private ArrayList<String> direcciones;
    private ArrayList<String> emails;
    private ArrayList<String> numerosTelefono;
    private ArrayList<String> identificadoresRedesSociales;
    private LinkedList<Image> fotos; //podria ser
    private ArrayList<String> fechasInteres;

    public Contacto(String nombre, String apellido, String tipoContacto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoContacto = tipoContacto;
        this.direcciones = new ArrayList<>();
        this.emails = new ArrayList<>();
        this.numerosTelefono = new ArrayList<>();
        this.identificadoresRedesSociales = new ArrayList<>();
        this.fotos = new LinkedList<>();
        this.fechasInteres = new ArrayList<>();
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

    public ArrayList<String> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(ArrayList<String> direcciones) {
        this.direcciones = direcciones;
    }

    public String getTipoContacto() {
        return tipoContacto;
    }

    public void setTipoContacto(String tipoContacto) {
        this.tipoContacto = tipoContacto;
    }

    public ArrayList<String> getEmails() {
        return emails;
    }

    public void addEmail(String email) {
        this.emails.add(email);
    }

//    public void removeEmail(String email) {
//        this.emails.remove(email);
//    }
    public ArrayList<String> getNumerosTelefono() {
        return numerosTelefono;
    }

    public void addNumeroTelefono(String numeroTelefono) {
        this.numerosTelefono.add(numeroTelefono);
    }

    public void removeNumeroTelefono(String numeroTelefono) {
        this.numerosTelefono.remove(numeroTelefono);
    }

    public ArrayList<String> getIdentificadoresRedesSociales() {
        return identificadoresRedesSociales;
    }

    public void addIdentificadorRedesSociales(String identificadorRedesSociales) {
        this.identificadoresRedesSociales.add(identificadorRedesSociales);
    }

    public void removeIdentificadorRedesSociales(String identificadorRedesSociales) {
        this.identificadoresRedesSociales.remove(identificadorRedesSociales);
    }

    public LinkedList<Image> getFotos() {
        return fotos;
    }

    public void addFoto(Image foto) {
        this.fotos.add(foto);
    }

    public void removeFoto(Image foto) {
        this.fotos.remove(foto);
    }

    public ArrayList<String> getFechasInteres() {
        return fechasInteres;
    }

    public void addFechaInter(String fechaInteres) {
        this.fechasInteres.add(fechaInteres);
    }

    public static void saveListToFileSerContactos(LinkedList<Contacto> contactos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Contactos.ser"))) {
            oos.writeObject(contactos);
        } catch (Exception e) {

        }
    }

    public static LinkedList<Contacto> readListFromFileSerContactos() {
        LinkedList<Contacto> contactos = new LinkedList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Contactos.ser"))) {
            contactos = (LinkedList<Contacto>) ois.readObject();
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
