package org.example.progettoesameastrojava.schermatevisive;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import org.example.progettoesameastrojava.gestionegenerale.SceneManager;


public class MenuScreen {
    //attributi
    private VBox rootLayout;
    private Button btnStart;

    public MenuScreen(SceneManager sm){
        //implementazione dei bottoni
        //switchToMenu
        //switchToGame
        //switchToGameOver

    }
    public static VBox createMenu(SceneManager sm){
        VBox menu=new VBox(20);
        Button btnStart=new Button("GIOCA");
        Button btnOptions=new Button("OPZIONI");
        Button btnExit=new Button("ESCI");

        menu.setAlignment(Pos.CENTER);
        menu.getChildren().addAll(btnStart,btnOptions,btnExit);

        btnStart.setOnAction(e->sm.switchToGame());

        btnOptions.setOnAction(e->sm.switchToMenu());

        btnExit.setOnAction(e->sm.switchToGameOver());

        return menu;
    }
    public VBox getLayout(){
        return rootLayout;
    }
}
