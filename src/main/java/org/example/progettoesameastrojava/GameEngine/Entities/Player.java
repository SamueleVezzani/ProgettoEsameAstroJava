package org.example.progettoesameastrojava.GameEngine.Entities;

import javafx.scene.image.Image;
import org.example.progettoesameastrojava.GameEngine.AssetManager;
import org.example.progettoesameastrojava.GameEngine.GameLoop;

public class Player {
    private double x, y;
    private int lives;
    private int score;
    private Image image;

    public Player(double startX, double startY, Image image) {
        this.x = startX;
        this.y = startY;
        this.image = image;
        this.lives = 3;
        this.score = 0;
        this.image = AssetManager.getImage("NavicellaUp");
    }

    //getter
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }

    public Image getImage() {
        return (image != null) ? image : AssetManager.getImage("NavicellaUp.png");
    }

    //setter
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void moveUp(){
        this.image = AssetManager.getImage("NavicellaUp");
        this.y -= GameLoop.step;
    }
    public void moveDown(){
        this.image = AssetManager.getImage("NavicellaDown");
        this.y += GameLoop.step;

    }
    public void moveRight(){
        this.image = AssetManager.getImage("NavicellaRight");
        this.x += GameLoop.step;
    }
    public void moveLeft(){
        this.image = AssetManager.getImage("NavicellaLeft");
        this.x -= GameLoop.step;
    }




}