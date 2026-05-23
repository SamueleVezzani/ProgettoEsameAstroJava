package org.example.progettoesameastrojava.gestionegenerale;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import org.example.progettoesameastrojava.GameEngine.AssetManager;

public class Launcher extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        AssetManager.loadAssets();
        SceneManager sm=new SceneManager(stage);
        sm.switchToMenu();
    }

    public static void main(String[] args){
        launch(args);
    }
}
