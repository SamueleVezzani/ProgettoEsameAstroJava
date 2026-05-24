package org.example.progettoesameastrojava.GameEngine;


import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
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
    public static final double step = 32.0;

    public GameLoop(GameScreen gm, Scene scena, SceneManager sm){
        this.gm=gm;
        this.sm = sm;
        this.activeKeys = new HashSet<>();
        Canvas canvas = gm.getCanvas();
        this.renderer = new Renderer(canvas);
        player = new Player(0,0, AssetManager.getImage("NavicellaUp.png"));


        scena.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            switch (code) {
                case W: case UP:
                    player.moveUp();
                    if (player.getY() < 0) player.setY(0);
                    break;
                case S: case DOWN:
                    player.moveDown();
                    double maxY = gm.getCanvas().getHeight() - player.getImage().getHeight();
                    if (player.getY() > maxY) player.setY(maxY);
                    break;
                case A: case LEFT:
                    player.moveLeft();
                    if (player.getX() < 0) player.setX(0);
                    break;
                case D: case RIGHT:
                    player.moveRight();
                    double maxX = gm.getCanvas().getWidth() - player.getImage().getWidth();
                    if (player.getX() > maxX) player.setX(maxX);
                    break;
                case ESCAPE:
                    sm.switchToMenu();
                    break;
            }
        });
    }

    @Override
    public void handle(long currentNanoTime) {
        renderer.render(player);
    }
}
