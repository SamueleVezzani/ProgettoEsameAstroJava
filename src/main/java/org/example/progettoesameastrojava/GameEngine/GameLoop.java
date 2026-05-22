package org.example.progettoesameastrojava.GameEngine;


import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import org.example.progettoesameastrojava.schermatevisive.GameScreen;

import static jdk.jfr.internal.consumer.EventLog.update;

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

    public GameLoop(GameScreen gm){
        this.gm=gm;
        Canvas canvas = gm.getCanvas();
        //this.renderer = new Renderer(canvas);

        this.playerx = 350;
        this.playery = 450;
        this.vel = 5.0;

        this.isUp = false;
        this.isDown = false;
        this.isLeft = false;
        this.isRight = false;
    }

    @Override
    public void handle(long currentNanoTime) {
        update();
        //renderer.render(playerX, playerY);
    }
}
