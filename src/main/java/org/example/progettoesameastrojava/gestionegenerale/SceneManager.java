package org.example.progettoesameastrojava.gestionegenerale;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.progettoesameastrojava.GameEngine.GameLoop;
import org.example.progettoesameastrojava.schermatevisive.GameScreen;
import org.example.progettoesameastrojava.schermatevisive.MenuScreen;

public class SceneManager {
    private Stage mainStage;
    private GameLoop gl;

    public SceneManager(Stage stage) {
        this.mainStage = stage;

        // Listener registrato UNA SOLA VOLTA all'avvio dell'applicazione
        this.mainStage.maximizedProperty().addListener((obs, wasMaximized, isNowMaximized) -> {
            if (!isNowMaximized) {
                mainStage.setWidth(800);
                mainStage.setHeight(500);
                mainStage.centerOnScreen();
            }
        });
    }

    public void switchToMenu() {
        if (gl != null) {
            gl.stop();
        }
        MenuScreen ms = new MenuScreen(this);
        VBox menuLayout = ms.getLayout();
        Scene menuScene = new Scene(menuLayout, 800, 500);

        mainStage.setScene(menuScene);
        mainStage.setTitle("Menu Principale");
        mainStage.show();
    }

    public void switchToGame() {
        if (gl != null) {
            gl.stop();
        }
        GameScreen gs = new GameScreen(this);
        BorderPane gameLayout = gs.getLayout();
        Scene gameScene = new Scene(gameLayout, 800, 500);

        gl = new GameLoop(gs, gameScene, this);
        gl.start();

        mainStage.setScene(gameScene);
        mainStage.setTitle("AstroJava");
        mainStage.show();
    }

    public void glstop() {
        if (gl != null) {
            gl.stop();
        }
    }
}