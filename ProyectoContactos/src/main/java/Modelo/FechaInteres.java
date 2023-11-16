/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author johan
 */
public class FechaInteres implements Serializable{
    
    private String fecha;
    private String tipoFechaInteres;

    public FechaInteres(String fecha, String tipoFechaInteres) {
        this.fecha = fecha;
        this.tipoFechaInteres = tipoFechaInteres;
    }
    
}
