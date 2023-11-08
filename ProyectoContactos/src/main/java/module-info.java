module ec.edu.espol.proyectocontactos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens ec.edu.espol.proyectocontactos to javafx.fxml;
    exports ec.edu.espol.proyectocontactos;
}
