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
        Button btnStart=new Button("GIOCA");
        Button btnOptions=new Button(" opzioni");
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
