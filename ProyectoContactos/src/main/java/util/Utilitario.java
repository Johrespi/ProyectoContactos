/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

/**
 *
 * @author MICHI ALVARADO
 */
public class Utilitario {
    
    public static void mensajeAlertaConfirmacion(String title, String mensaje){
        Alert ConfirmacionAdmin = new Alert(Alert.AlertType.CONFIRMATION);
        ConfirmacionAdmin.setTitle(title);
        ConfirmacionAdmin.setContentText(mensaje);
        ConfirmacionAdmin.showAndWait();
    }
    
    public static void mensajeAlertaAdvertencia(String title, String mensaje){
        Alert ConfirmacionAdmin = new Alert(Alert.AlertType.WARNING);
        ConfirmacionAdmin.setTitle(title);
        ConfirmacionAdmin.setContentText(mensaje);
        ConfirmacionAdmin.showAndWait();
    }
    
    public static void mensajeAlertaError(String title, String mensaje){
        Alert ConfirmacionAdmin = new Alert(Alert.AlertType.ERROR);
        ConfirmacionAdmin.setTitle(title);
        ConfirmacionAdmin.setContentText(mensaje);
        ConfirmacionAdmin.showAndWait();
    }
    
    public static void mensajeAlertaInfo(String title, String mensaje){
        Alert ConfirmacionAdmin = new Alert(Alert.AlertType.INFORMATION);
        ConfirmacionAdmin.setTitle(title);
        ConfirmacionAdmin.setContentText(mensaje);
        ConfirmacionAdmin.showAndWait();
    }
    
    public static void soloNumerosYLetras(TextField textField) {        
        TextFormatter<String> textFormatter = new TextFormatter<>(change -> {
            String nuevoTexto = change.getControlNewText();
            if (nuevoTexto.matches("[a-zA-Z0-9]*")) {
                return change;
            } else {
                return null;
            }
        });
        
        textField.setTextFormatter(textFormatter);
    }
}
