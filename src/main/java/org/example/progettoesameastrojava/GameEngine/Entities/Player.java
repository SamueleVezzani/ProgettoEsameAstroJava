package org.example.progettoesameastrojava.GameEngine.Entities;

import javafx.scene.image.Image;
import org.example.progettoesameastrojava.GameEngine.AssetManager;

public class Player {
    private double x, y;
    private double startX,startY;
    private int lives;
    private int score;
    private Image image;

    private boolean isMoving = false;
    private int dx = 0;
    private int dy = 0;
    private double speed = 4.0;

    private boolean needsHUDUpdate = false;

    private Image[] frames;
    private int currentFrameIndex = 0;

    private long lastFrameTime = 0;
    private final long frameDuration = 200_000_000;

    private boolean isGameOver = false;

    public Player(double startX, double startY) {
        this.x = startX;
        this.y = startY;
        this.startX = startX;
        this.startY = startY;
        this.image = AssetManager.getImage("NavicellaUp");
        this.frames = new Image[]{ this.image, AssetManager.getImage("NavicellaUpHighFlame") };
        this.lives = 3;
        this.score = 0;

        this.isMoving = false;
        this.dx = 0;
        this.dy = 0;
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
        if (frames != null && frames.length > 0) {
            return frames[currentFrameIndex];
        }
        return image;
    }

    public boolean isGameOver(){ return isGameOver;}
    //setter

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void startMoving(int newDx, int newDy) {
        if (!isMoving) {
            this.dx = newDx;
            this.dy = newDy;
            this.isMoving = true;

            if (dx == 1) {
                this.image = AssetManager.getImage("NavicellaRight");
                this.frames = new Image[]{ this.image, AssetManager.getImage("NavicellaRightHighFlame") };
            } else if (dx == -1) {
                this.image = AssetManager.getImage("NavicellaLeft");
                this.frames = new Image[]{ this.image, AssetManager.getImage("NavicellaLeftHighFlame") };
            } else if (dy == 1) {
                this.image = AssetManager.getImage("NavicellaDown");
                this.frames = new Image[]{ this.image, AssetManager.getImage("NavicellaDownHighFlame") };
            } else if (dy == -1) {
                this.image = AssetManager.getImage("NavicellaUp");
                this.frames = new Image[]{ this.image, AssetManager.getImage("NavicellaUpHighFlame") };
            }

            this.currentFrameIndex = 0;
            this.lastFrameTime = 0;
        }
    }

    public void update(int[][] map, int tileSize, long now) {

        if (isMoving && frames != null && frames.length > 0) {
            if (lastFrameTime == 0) {
                lastFrameTime = now;
            }

            if (now - lastFrameTime >= frameDuration) {
                currentFrameIndex = (currentFrameIndex + 1) % frames.length;
                lastFrameTime = now;
            }
        } else {
            currentFrameIndex = 0;
            lastFrameTime = 0;
        }

        if (!isMoving) return;


        double nextX = x + (dx * speed);
        double nextY = y + (dy * speed);

        int nextCol = (int) ((nextX + (dx > 0 ? tileSize - 1 : 0)) / tileSize);
        int nextRow = (int) ((nextY + (dy > 0 ? tileSize - 1 : 0)) / tileSize);
        if(map[nextRow][nextCol] == 4){
            isMoving = false;
            dx = 0;
            dy = 0;
            System.out.println("fine livello");

            NextLevel();
            return;
        }
        else if(map[nextRow][nextCol] == 3){

            isMoving = false;
            dx = 0;
            dy = 0;

            System.out.println("Sei morto");
            LoseALife();
            return;
        }
        else if (map[nextRow][nextCol] == 1) {
            isMoving = false;
            dx = 0;
            dy = 0;

            x = Math.round(x / tileSize) * tileSize;
            y = Math.round(y / tileSize) * tileSize;
        } else {
            x = nextX;
            y = nextY;
        }
    }
    public void LoseALife(){
        lives--;
        needsHUDUpdate = true;
        System.out.println(this.lives);
        if(this.lives == 0){
            isGameOver = true;
        }
        resetToStart();
    }

    public void NextLevel(){

    }

    public void resetToStart() {
        this.x = this.startX;
        this.y = this.startY;

        this.isMoving = false;
        this.dx = 0;
        this.dy = 0;

        this.image = AssetManager.getImage("NavicellaUp");
        this.frames = new Image[]{ this.image, AssetManager.getImage("NavicellaUpHighFlame") };
        this.currentFrameIndex = 0;
    }

    public boolean needsHUDUpdate() {return needsHUDUpdate;}
    public void setHUDUpdated(){ needsHUDUpdate = false;}
}