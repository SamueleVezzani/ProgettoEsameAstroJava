package org.example.progettoesameastrojava.schermatevisive;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import org.example.progettoesameastrojava.GameEngine.AssetManager;
import org.example.progettoesameastrojava.gestionegenerale.SceneManager;

import java.util.Optional;

public class MenuScreen {
    private StackPane rootLayout;

    private void richiediConfermaUscita() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Uscita dal Gioco");
        alert.setHeaderText("Stai per uscire dal gioco.");
        alert.setContentText("Sei sicuro di voler uscire davvero?");

        Optional<ButtonType> risultato = alert.showAndWait();

        if (risultato.isPresent() && risultato.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    public MenuScreen(SceneManager sm){
        rootLayout = new StackPane();

        // --- 1. GESTIONE SFONDO RESPONSIVE ---
        ImageView background = new ImageView(AssetManager.getImage("SfondoMenu"));

        // Colleghiamo le dimensioni dell'immagine a quelle dello StackPane in tempo reale
        background.fitWidthProperty().bind(rootLayout.widthProperty());
        background.fitHeightProperty().bind(rootLayout.heightProperty());

        // Questo assicura che l'immagine si deformi/adatti per riempire tutto lo spazio senza lasciare bordi neri
        background.setPreserveRatio(false);

        // --- 2. GESTIONE PULSANTI ---
        Button btnStart = new Button("GIOCA");
        Button btnExit = new Button("ESCI");

        btnStart.setStyle("-fx-font-size: 20px; -fx-padding: 10 30;");
        btnExit.setStyle("-fx-font-size: 20px; -fx-padding: 10 30;");

        VBox buttonContainer = new VBox(20);
        buttonContainer.setAlignment(Pos.CENTER);

        // IMPORTANTE: Diciamo al VBox di non espandersi all'infinito, ma di rimanere
        // grande solo quanto basta per contenere i pulsanti. Questo li tiene bloccati al centro!
        buttonContainer.setMaxSize(VBox.USE_PREF_SIZE, VBox.USE_PREF_SIZE);

        buttonContainer.getChildren().addAll(btnStart, btnExit);

        btnStart.setOnAction(e -> sm.switchToGame());
        btnExit.setOnAction(e -> richiediConfermaUscita());

        // Aggiungiamo prima lo sfondo (che starà dietro) e poi il VBox (davanti)
        rootLayout.getChildren().addAll(background, buttonContainer);
    }

    public StackPane getLayout(){
        return rootLayout;
    }
}