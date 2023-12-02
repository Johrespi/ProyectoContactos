/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author johan
 */
public class Relacion implements Serializable{
    
    private String tipoRelacion;
    private Contacto contacto;

    public Relacion(String tipoRelacion, Contacto contacto) {
        this.tipoRelacion = tipoRelacion;
        this.contacto = contacto;
    }

    public String getTipoRelacion() {
        return tipoRelacion;
    }

    public void setTipoRelacion(String tipoRelacion) {
        this.tipoRelacion = tipoRelacion;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }



    @Override
    public String toString() {
        return tipoRelacion + ": " + contacto.getNombre() + " " + contacto.getApellido();
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Relacion other = (Relacion) obj;
        if (!Objects.equals(this.tipoRelacion, other.tipoRelacion)) {
            return false;
        }
        return Objects.equals(this.contacto, other.contacto);
    }
    
    
    
}
