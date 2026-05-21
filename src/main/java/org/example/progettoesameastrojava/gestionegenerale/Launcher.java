package org.example.progettoesameastrojava.gestionegenerale;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.stage.Stage;

public class Launcher extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        SceneManager sm=new SceneManager(stage);
        sm.switchToMenu();
    }
    public static VBox createMenu(SceneManager sm){
        VBox menu=new VBox(20);
        Button btnStart=new Button("inizia");
        Button btnOptions=new Button("opzioni");
        Button btnExit=new Button("esci");

        menu.setAlignment(Pos.CENTER);
        menu.getChildren().addAll(btnStart,btnOptions,btnExit);

        btnStart.setOnAction(e->sm.switchToGame());

        btnOptions.setOnAction(e->sm.switchToMenu());

        btnExit.setOnAction(e->sm.switchToGameOver());

        return menu;
    }

    public static void main(String[] args){
        launch(args);
    }
}
