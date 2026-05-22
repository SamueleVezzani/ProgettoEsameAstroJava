package org.example.progettoesameastrojava.gestionegenerale;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.progettoesameastrojava.GameEngine.GameLoop;
import org.example.progettoesameastrojava.schermatevisive.GameScreen;
import org.example.progettoesameastrojava.schermatevisive.MenuScreen;

public class SceneManager {
    private Stage mainStage;

    public SceneManager(Stage stage){
        mainStage=stage;
    }

    public void switchToMenu() {
        MenuScreen ms = new MenuScreen(this);
        VBox menuLayout= ms.getLayout();
        Scene menuScene = new Scene(menuLayout, 800, 500);
        mainStage.setScene(menuScene);
        mainStage.setTitle("Menu Principale");
        mainStage.show();
    }

    public void switchToGame(){
        GameScreen gs = new GameScreen();
        BorderPane gameLayout = gs.getLayout();
        Scene gameScene = new Scene(gameLayout,801,800);
        mainStage.setScene(gameScene);
        mainStage.setTitle("AstroJava");
        mainStage.show();
    }

    public void switchToGameOver(){

    }
}
