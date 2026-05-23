module org.example.progettoesameastrojava {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jfr;


    exports org.example.progettoesameastrojava.GameEngine;
    opens org.example.progettoesameastrojava.GameEngine to javafx.fxml;
    exports org.example.progettoesameastrojava.gestionegenerale;
    opens org.example.progettoesameastrojava.gestionegenerale to javafx.fxml;
    exports org.example.progettoesameastrojava.schermatevisive;
    opens org.example.progettoesameastrojava.schermatevisive to javafx.fxml;
}