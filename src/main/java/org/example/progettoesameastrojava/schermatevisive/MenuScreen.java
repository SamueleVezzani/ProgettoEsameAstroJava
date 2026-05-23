package org.example.progettoesameastrojava.schermatevisive;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import org.example.progettoesameastrojava.gestionegenerale.SceneManager;
import java.util.Optional;

public class MenuScreen {
    private VBox rootLayout = new VBox(20);

    private void richiediConfermaUscita() {
        // 1. Crea la finestrina di tipo CONFIRMATION
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Uscita dal Gioco");
        alert.setHeaderText("Stai per uscire dal gioco.");
        alert.setContentText("Sei sicuro di voler uscire davvero?");

        // 2. Mostra la finestra e aspetta che l'utente clicchi un pulsante
        Optional<ButtonType> risultato = alert.showAndWait();

        // 3. Se l'utente clicca su OK, chiudi l'applicazione
        if (risultato.isPresent() && risultato.get() == ButtonType.OK) {
            System.exit(0);
        }
    }
    public MenuScreen(SceneManager sm){
        Button btnStart=new Button("GIOCA");
        Button btnOptions=new Button(" opzioni di olli ");
        Button btnExit=new Button("ESCI");

        rootLayout.setAlignment(Pos.CENTER);
        rootLayout.getChildren().addAll(btnStart,btnOptions,btnExit);

        btnStart.setOnAction(e->sm.switchToGame());

        btnOptions.setOnAction(e->sm.switchToMenu());

        btnExit.setOnAction(e->richiediConfermaUscita());

    }

    public VBox getLayout(){
        return rootLayout;
    }
}
