package org.example.progettoesameastrojava.schermatevisive;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.example.progettoesameastrojava.GameEngine.GameLoop;
import org.example.progettoesameastrojava.gestionegenerale.SceneManager;

import java.util.Optional;

public class GameScreen {
    //attributi
    private BorderPane rootLayout;
    private Canvas gameCanvas;
    private Label lblScore;
    private Label lblLives;
    private GameLoop gl;
    private SceneManager sm;
    //metodi
    public GameScreen(SceneManager sm) {
        rootLayout = new BorderPane();
        gameCanvas = new Canvas(800, 600);
        lblScore = new Label("Punteggio: 0");
        lblLives = new Label("Vite: 3");
        this.sm=sm;



        HBox topBar = new HBox(20);
        topBar.setAlignment(Pos.CENTER);
        topBar.getChildren().addAll(lblScore, lblLives);
        topBar.setStyle("-fx-padding: 10; -fx-background-color: lightgray;");

        StackPane centerPane = new StackPane();
        centerPane.getChildren().add(gameCanvas);
        StackPane.setAlignment(gameCanvas, Pos.CENTER);

        rootLayout.setTop(topBar);
        rootLayout.setCenter(centerPane);

    }
    public BorderPane getLayout(){
        return rootLayout;
    }
    public Canvas getCanvas(){
        return gameCanvas;
    }
    public void updateHUD(int score, int lives){
        this.lblScore.setText(String.valueOf(score));
        this.lblLives.setText(String.valueOf(lives));
    }


    private void richiediConfermaRitornoMenu() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Torna al Menu");
        alert.setHeaderText("Vuoi davvero tornare al menu principale?");
        alert.setContentText("La partita verrà interrotta.");

        Optional<ButtonType> risultato = alert.showAndWait();

        if (risultato.isPresent() && risultato.get() == ButtonType.OK) {
            //sm.glstop();
            sm.switchToMenu();
        }
    }

    public void onEscapePressed() {
        richiediConfermaRitornoMenu();
    }

}
