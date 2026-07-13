package org.example.progettoesameastrojava.GameEngine.Entities;

import javafx.scene.image.Image;
import org.example.progettoesameastrojava.GameEngine.AssetManager;
import org.example.progettoesameastrojava.GameEngine.GameLoop;

public class Player {
    private double x, y;
    private int lives;
    private int score;
    private Image image;

    private boolean isMoving = false;
    private int dx = 0;
    private int dy = 0;
    private double speed = 8.0;

    public Player(double startX, double startY, Image image) {
        this.x = startX;
        this.y = startY;
        this.image = AssetManager.getImage("NavicellaUp"); // Immagine di default
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

    public void startMoving(int newDx, int newDy) {
        if (!isMoving) {
            this.dx = newDx;
            this.dy = newDy;
            this.isMoving = true;

            // Cambia immagine in base alla direzione
            if (dx == 1) this.image = AssetManager.getImage("NavicellaRight");
            else if (dx == -1) this.image = AssetManager.getImage("NavicellaLeft");
            else if (dy == 1) this.image = AssetManager.getImage("NavicellaDown");
            else if (dy == -1) this.image = AssetManager.getImage("NavicellaUp");
        }
    }

    // Metodo per aggiornare la posizione ad ogni frame
    public void update(int[][] map, int tileSize) {
        if (!isMoving) return;

        // Calcoliamo la posizione futura che avremmo al prossimo passo
        double nextX = x + (dx * speed);
        double nextY = y + (dy * speed);

        int nextCol = (int) ((nextX + (dx > 0 ? tileSize - 1 : 0)) / tileSize);
        int nextRow = (int) ((nextY + (dy > 0 ? tileSize - 1 : 0)) / tileSize);

        if (map[nextRow][nextCol] == 1) {
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

    private boolean checkCollision(double nextX, double nextY, int[][] map, int tileSize) {
        int col = (int) (nextX / tileSize);
        int row = (int) (nextY / tileSize);

        if (row < 0 || row >= map.length || col < 0 || col >= map[0].length) return true;

        return map[row][col] == 1;
    }




}