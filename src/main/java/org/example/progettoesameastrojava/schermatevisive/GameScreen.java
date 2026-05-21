package org.example.progettoesameastrojava.schermatevisive;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class GameScreen {
    //attributi
    private BorderPane rootLayout;
    private Canvas gameCanvas;
    private Label lblScore;
    private Label lblLives;
    //metodi
    public GameScreen(){
        //metto il canvas al centro e creo l'HUD in alto
        BorderPane.setAlignment(gameCanvas, Pos.CENTER);
        HBox barra = new HBox(10);
        barra.getChildren().addAll(lblScore, lblLives);
        BorderPane.setAlignment(barra,Pos.TOP_CENTER);
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

}
