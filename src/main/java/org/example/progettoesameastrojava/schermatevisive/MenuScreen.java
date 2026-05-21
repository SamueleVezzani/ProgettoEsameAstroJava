package org.example.progettoesameastrojava.schermatevisive;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import org.example.progettoesameastrojava.gestionegenerale.SceneManager;


public class MenuScreen {
    //attributi
    private VBox rootLayout = new VBox(20);
    private Button btnStart;

    public MenuScreen(SceneManager sm){
        Button btnStart=new Button("GIOCA");
        Button btnOptions=new Button("OPZIONI");
        Button btnExit=new Button("ESCI");

        rootLayout.setAlignment(Pos.CENTER);
        rootLayout.getChildren().addAll(btnStart,btnOptions,btnExit);

        btnStart.setOnAction(e->sm.switchToGame());

        btnOptions.setOnAction(e->sm.switchToMenu());

        btnExit.setOnAction(e->sm.switchToGameOver());

    }

    public VBox getLayout(){
        return rootLayout;
    }
}
