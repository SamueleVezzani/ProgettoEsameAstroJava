module org.example.progettoesameastrojava {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.jfr;


    opens org.example.progettoesameastrojava to javafx.fxml;
    exports org.example.progettoesameastrojava;
    exports org.example.progettoesameastrojava.altro;
    opens org.example.progettoesameastrojava.altro to javafx.fxml;
    exports org.example.progettoesameastrojava.GameEngine;
    opens org.example.progettoesameastrojava.GameEngine to javafx.fxml;
}