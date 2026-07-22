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
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;



import java.util.Optional;

public class GameScreen {
    //attributi
    private BorderPane rootLayout;
    private Canvas gameCanvas;
    private Label lblScore;
    private Label lblLives;
    private GameLoop gl;
    private SceneManager sm;

    private StackPane root;
    private VBox gameOverPanel;

    public GameScreen(SceneManager sm) {
        this.sm = sm;

        rootLayout = new BorderPane();
        gameCanvas = new Canvas();
        lblScore = new Label("Punteggio: 0");
        lblLives = new Label("Vite: 3");

        initLayout();
    }

    private void initLayout() {
        HBox topBar = new HBox(20);
        topBar.setAlignment(Pos.CENTER);
        topBar.getChildren().addAll(lblScore, lblLives);
        topBar.setStyle("-fx-padding: 10; -fx-background-color: lightgray;");

        gameOverPanel = new VBox(20);
        gameOverPanel.setAlignment(Pos.CENTER);
        gameOverPanel.setStyle("-fx-background-color: rgba(0, 0, 0, 0.75);");

        Label lblGameOver = new Label("GAME OVER");
        lblGameOver.setStyle("-fx-font-size: 40px; -fx-text-fill: red; -fx-font-weight: bold;");

        Button btnRetry = new Button("Riprova");
        btnRetry.setStyle("-fx-font-size: 16px; -fx-padding: 10 20 10 20;");
        btnRetry.setOnAction(e -> {
            sm.switchToGame();
        });

        Button btnMenu = new Button("Torna al Menu");
        btnMenu.setStyle("-fx-font-size: 16px; -fx-padding: 10 20 10 20;");
        btnMenu.setOnAction(e -> {
            sm.switchToMenu();
        });

        gameOverPanel.getChildren().addAll(lblGameOver, btnRetry, btnMenu);

        gameOverPanel.setVisible(false);

        StackPane centerPane = new StackPane();

        centerPane.getChildren().addAll(gameCanvas, gameOverPanel);

        centerPane.widthProperty().addListener((obs, oldVal, newVal) -> {
            gameCanvas.setWidth(newVal.doubleValue());
        });

        centerPane.heightProperty().addListener((obs, oldVal, newVal) -> {
            gameCanvas.setHeight(newVal.doubleValue());
        });
        rootLayout.setTop(topBar);
        rootLayout.setCenter(centerPane);
    }

    public void showGameOver(GameLoop gl) {
        gl.stop();
        gameOverPanel.setVisible(true);
    }

    public BorderPane getLayout(){
        return rootLayout;
    }
    public Canvas getCanvas(){
        return gameCanvas;
    }
    public void updateHUD(int score, int lives){
        this.lblScore.setText("Punteggio: "+String.valueOf(score));
        this.lblLives.setText(String.valueOf("Vite: "+lives));
    }


    private void richiediConfermaRitornoMenu() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Torna al Menu");
        alert.setHeaderText("Vuoi davvero tornare al menu principale?");
        alert.setContentText("La partita verrà interrotta.");

        Optional<ButtonType> risultato = alert.showAndWait();

        if (risultato.isPresent() && risultato.get() == ButtonType.OK) {
            sm.glstop();
            sm.switchToMenu();
        }
    }


    public void onEscapePressed() {
        richiediConfermaRitornoMenu();
    }

}
