package org.example.progettoesameastrojava.GameEngine.Entities;

import javafx.scene.image.Image;
import org.example.progettoesameastrojava.GameEngine.AssetManager;

public class Player {
    private double x, y;
    private int lives;
    private int score;
    private Image image;

    public Player(double startX, double startY) {
        this.x = startX;
        this.y = startY;
        this.lives = 3;
        this.score = 0;
        this.image = AssetManager.getImage("player");
    }

    // getter/setter, moveLeft/Right/Up/Down, ecc.
}