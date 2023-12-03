/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author johan
 */
public class FechaInteres implements Serializable{
    
    private String fecha;
    private String tipoFechaInteres;
    private static final long serialVersionUID = 5400558121L;

    public FechaInteres(String fecha, String tipoFechaInteres) {
        this.fecha = fecha;
        this.tipoFechaInteres = tipoFechaInteres;
    }

    @Override
    public String toString() {
        return tipoFechaInteres + ": " + fecha;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final FechaInteres other = (FechaInteres) obj;
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        return Objects.equals(this.tipoFechaInteres, other.tipoFechaInteres);
    }
    
    
}
