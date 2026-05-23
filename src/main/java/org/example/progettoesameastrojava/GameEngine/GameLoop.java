package org.example.progettoesameastrojava.GameEngine;


import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import org.example.progettoesameastrojava.GameEngine.Entities.Player;
import org.example.progettoesameastrojava.gestionegenerale.SceneManager;
import org.example.progettoesameastrojava.schermatevisive.GameScreen;

import java.util.HashSet;
import java.util.Set;


public class GameLoop extends AnimationTimer {
    private Renderer renderer;
    private GameScreen gm;
    private boolean running;
    private double playerx;
    private double playery;
    private double vel;
    private int score;
    private int lives;
    private boolean isUp;
    private boolean isDown;
    private boolean isLeft;
    private boolean isRight;
    private int[][] map;
    private int tileSize = 32;
    private SceneManager sm;
    private Set<KeyCode> activeKeys;
    private Player player;


    public GameLoop(GameScreen gm, Scene scena, SceneManager sm){
        this.gm=gm;
        this.sm = sm;
        this.activeKeys = new HashSet<>();
        Canvas canvas = gm.getCanvas();
        this.renderer = new Renderer(canvas);
        player = new Player(100,100);

        this.playerx = 0;
        this.playery = 0;
        this.vel = 5.0;

        this.isUp = false;
        this.isDown = false;
        this.isLeft = false;
        this.isRight = false;

        scena.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            switch(code){/*
                case KeyCode.W ->
                    break;
                case KeyCode.A ->
                    break;
                case KeyCode.S ->
                    break;
                case KeyCode.D ->
                    break;
                */
                case KeyCode.ESCAPE -> sm.switchToMenu();
            }
        });

    }

    @Override
    public void handle(long currentNanoTime) {
        renderer.render(this.playerx, this.playery);
    }
}
