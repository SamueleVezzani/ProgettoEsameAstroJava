module org.example.progettoesameastrojava {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.progettoesameastrojava to javafx.fxml;
    exports org.example.progettoesameastrojava;
}