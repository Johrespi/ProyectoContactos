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
import java.util.Objects;
import java.util.UUID;

/**
 *
 * @author johan
 */
public class Contacto implements Serializable {
    private String id;
    private String nombre;
    private String apellido;
    private String tipoContacto; 
    private ArrayList<Direccion> direcciones;
    private ArrayList<Email> emails;
    private ArrayList<Telefono> numerosTelefono;
    private ArrayList<redSocial> redesSociales;
    private ArrayList<Relacion> ContactosRelacionados;
    private DoubleCircleLinkedList<String> fotos; //podria ser
    //private DoublyCircularLInkedList<String> fotos; //podria ser
    private ArrayList<FechaInteres> fechasInteres;
    private boolean esFavorito;

    private static final long serialVersionUID = 5458121L;
            
    public Contacto(String id, String nombre, String apellido, String tipoContacto, boolean esFavorito) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoContacto = tipoContacto;
        this.esFavorito = esFavorito;
        this.direcciones = new ArrayList<>();
        this.emails = new ArrayList<>();
        this.numerosTelefono = new ArrayList<>();
        this.redesSociales = new ArrayList<>();
        this.fechasInteres = new ArrayList<>();
        this.ContactosRelacionados = new ArrayList<>();
        this.fotos = new DoubleCircleLinkedList();
        //this.fotos = new DoublyCircularLInkedList();
        this.id =  id;
    }
    
    public Contacto(String id) {
        this.nombre = "";
        this.apellido = "";
        this.tipoContacto = "";
        this.esFavorito = false;
        this.direcciones = new ArrayList<>();
        this.emails = new ArrayList<>();
        this.numerosTelefono = new ArrayList<>();
        this.redesSociales = new ArrayList<>();
        this.fechasInteres = new ArrayList<>();
        this.fotos = new DoubleCircleLinkedList();
        this.ContactosRelacionados = new ArrayList<>();
        //this.fotos = new DoublyCircularLInkedList();
        this.id =  id;
    }

    public static String generarIDUnico() {        
        UUID uuid = UUID.randomUUID();                
        String idUnico = uuid.toString().replaceAll("-", "");        
        return idUnico;
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }    
    /*public DoublyCircularLInkedList getFotos() {
        return fotos;
    }*/

    public DoubleCircleLinkedList getFotos() {
        return fotos;
    }
    
    public void setFotos(String fotos) {
        this.fotos.add(fotos);
    }

    /*
    public void setFotos(String fotos) {
        this.fotos.addLast(fotos);
    }*/
    
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

    public ArrayList<Relacion> getContactosRelacionados() {
        return ContactosRelacionados;
    }

    public void setContactosRelacionados(ArrayList<Relacion> ContactosRelacionados) {
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

    public ArrayList<Email> getEmails() {
        return emails;
    }

    public void setEmails(ArrayList<Email> emails) {
        this.emails = emails;
    }

    public ArrayList<Telefono> getNumerosTelefono() {
        return numerosTelefono;
    }

    public void setNumerosTelefono(ArrayList<Telefono> numerosTelefono) {
        this.numerosTelefono = numerosTelefono;
    }

    public ArrayList<redSocial> getRedesSociales() {
        return redesSociales;
    }

    public void setRedesSociales(ArrayList<redSocial> redesSociales) {
        this.redesSociales = redesSociales;
    }

    /*
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
*/
    public static void saveListToFileSerContactos(DoubleCircleLinkedLists<Contacto> contactos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Contactos.ser"))) {
            oos.writeObject(contactos);
        } catch (Exception e) {

        }
    }

    public static DoubleCircleLinkedLists<Contacto> readListFromFileSerContactos() {
        DoubleCircleLinkedLists<Contacto> contactos = new DoubleCircleLinkedLists<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Contactos.ser"))) {
            contactos = (DoubleCircleLinkedLists<Contacto>) ois.readObject();
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        } catch (Exception a) {
        }
        return contactos;

    }

    @Override
    public String toString() {
        return id+ "  "+ nombre + " " + apellido + " "+ this.numerosTelefono;
    }

    public String toStringInformacion() {
        return "Contacto{" + "nombre="
                + nombre + ", apellido="
                + apellido + ", tipoContacto="
                + tipoContacto
                + ", direcciones=" + direcciones + ", emails=" + emails
                + ", numerosTelefono=" + numerosTelefono
                + ", redesSociales=" + redesSociales + ", ContactosRelacionados="
                + ContactosRelacionados + ", fotos=" + fotos
                + ", fechasInteres=" + fechasInteres + ", esFavorito=" + esFavorito + '}';
    }

    public String toStringEmpresa() {
        return "Contact{"
                + "nombre='" + nombre + '\''
                + ", apellido='" + apellido + '\''
                + ", numeroTelefono='" + numerosTelefono + '\''
                + ", email='" + emails + '\''
                + ", direccion='" + direcciones + '\''
                + ", tipoContacto='" + tipoContacto + '\''
                + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contacto other = (Contacto) obj;
        return Objects.equals(this.id, other.id);
    }


}
