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

/**
 *
 * @author johan
 */
public class Usuario implements Serializable {

    private String nombreUsuario;
    private String contraseña;
    private String tipoUsuario; // Usuario o administrador
    private DoublyCircularLInkedList<Contacto> contactos;
    
    private long serialVersionUID = 5874329925320491266L;
            
    public Usuario(String nombreUsuario, String contraseña, String tipoUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.tipoUsuario = tipoUsuario;
        this.contactos = new DoublyCircularLInkedList<>();
    }

    public static ArrayList<Usuario> readListFromFileSerUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Usuarios.ser"))) {
            usuarios = (ArrayList<Usuario>) ois.readObject();
            System.out.println("USUARIOS: "+usuarios);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (Exception a) {
            System.err.println(a.getMessage());
        }
        return usuarios;

    }

    public static void saveListToFileSerUsuarios(ArrayList<Usuario> usuarios) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Usuarios.ser"))) {
            oos.writeObject(usuarios);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.nombreUsuario.toLowerCase());
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
        final Usuario other = (Usuario) obj;
        return Objects.equals(this.nombreUsuario.toLowerCase(), other.nombreUsuario.toLowerCase());
    }


    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public DoublyCircularLInkedList<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(DoublyCircularLInkedList<Contacto> contactos) {
        this.contactos = contactos;
    }

    @Override
    public String toString() {
        return "User{" + "nombreUsuario='" + nombreUsuario + '\'' + ", contraseña='" + contraseña + '\'' + ", tipoUsuario='" + tipoUsuario + '\'' + '}';
    }

}
