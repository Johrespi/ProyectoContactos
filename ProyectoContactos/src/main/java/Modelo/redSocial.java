/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;

/**
 *
 * @author johan
 */
public class redSocial implements Serializable {
    private String tipoRedSocial;
    private String redID;

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
    
    
}
