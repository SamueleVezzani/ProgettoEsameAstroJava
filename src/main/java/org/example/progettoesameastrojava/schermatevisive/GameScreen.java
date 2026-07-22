package org.example.progettoesameastrojava.schermatevisive;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
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
    private VBox victoryPanel;

    private StackPane root;
    private VBox gameOverPanel;

    public GameScreen(SceneManager sm) {
        this.sm = sm;

        rootLayout = new BorderPane();
        gameCanvas = new Canvas(800, 500);
        lblScore = new Label("Punteggio: 0");
        lblLives = new Label("Vite: 3");

        initLayout();
    }

    private void initLayout() {
        HBox topBar = new HBox(20);
        topBar.setAlignment(Pos.CENTER);
        topBar.getChildren().addAll(lblScore, lblLives);
        topBar.setStyle("-fx-padding: 10; -fx-background-color: lightgray;");

        // --- Pannello Game Over ---
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

        // --- Pannello di Vittoria (Nuovo) ---
        victoryPanel = new VBox(20);
        victoryPanel.setAlignment(Pos.CENTER);
        victoryPanel.setStyle("-fx-background-color: rgba(0, 0, 0, 0.75);");

        Label lblVictory = new Label("HAI VINTO!");
        lblVictory.setStyle("-fx-font-size: 40px; -fx-text-fill: gold; -fx-font-weight: bold;");

        Label lblCompliments = new Label("Hai completato tutti i livelli!");
        lblCompliments.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");

        Button btnMenuVictory = new Button("Torna al Menu");
        btnMenuVictory.setStyle("-fx-font-size: 16px; -fx-padding: 10 20 10 20;");
        btnMenuVictory.setOnAction(e -> {
            sm.switchToMenu();
        });

        victoryPanel.getChildren().addAll(lblVictory, lblCompliments, btnMenuVictory);
        victoryPanel.setVisible(false);

        // --- Soluzione al problema del ridimensionamento del Canvas ---
        Pane canvasContainer = new Pane(gameCanvas);
        canvasContainer.setMinSize(0, 0);

        gameCanvas.widthProperty().bind(canvasContainer.widthProperty());
        gameCanvas.heightProperty().bind(canvasContainer.heightProperty());

        StackPane centerPane = new StackPane();
        centerPane.getChildren().addAll(canvasContainer, gameOverPanel, victoryPanel);

        rootLayout.setTop(topBar);
        rootLayout.setCenter(centerPane);
    }

    public void showGameOver(GameLoop gl) {
        gl.stop();
        gameOverPanel.setVisible(true);
    }

    public void showVictory(GameLoop gl) {
        gl.stop(); // Ferma il gioco
        victoryPanel.setVisible(true); // Mostra la schermata di vittoria
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
