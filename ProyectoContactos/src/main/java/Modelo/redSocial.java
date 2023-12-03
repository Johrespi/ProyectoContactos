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
public class redSocial implements Serializable {
    private String tipoRedSocial;
    private String redID;
    private static final long serialVersionUID = 15485458121L;

    public redSocial(String tipoRedSocial, String redID) {
        this.tipoRedSocial = tipoRedSocial;
        this.redID = redID;
    }

    public String getTipoRedSocial() {
        return tipoRedSocial;
    }

    public void setTipoRedSocial(String tipoRedSocial) {
        this.tipoRedSocial = tipoRedSocial;
    }

    public String getRedID() {
        return redID;
    }

    public void setRedID(String redID) {
        this.redID = redID;
    }

    @Override
    public String toString() {
        return tipoRedSocial + ": " + redID;
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
        final redSocial other = (redSocial) obj;
        if (!Objects.equals(this.tipoRedSocial, other.tipoRedSocial)) {
            return false;
        }
        return Objects.equals(this.redID, other.redID);
    }
    
    
}
