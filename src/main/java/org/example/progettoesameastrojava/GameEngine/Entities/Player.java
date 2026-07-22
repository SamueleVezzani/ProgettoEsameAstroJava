package org.example.progettoesameastrojava.GameEngine.Entities;

import javafx.scene.image.Image;
import org.example.progettoesameastrojava.GameEngine.AssetManager;

public class Player {
    private double x, y;
    private double startX, startY;
    private int lives;
    private int score;
    private Image image;

    private boolean isMoving = false;
    private int dx = 0;
    private int dy = 0;

    // --- GESTIONE VELOCITA E BOOST ---
    private final double BASE_SPEED = 6.0;
    private double currentSpeed = BASE_SPEED;

    private boolean needsHUDUpdate = false;

    private Image[] frames;
    private int currentFrameIndex = 0;

    private long lastFrameTime = 0;
    private final long frameDuration = 200_000_000;

    private boolean isGameOver = false;
    private boolean hasReachedPortal = false;

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

    // --- GETTER E SETTER ---
    public double getX() { return x; }
    public double getY() { return y; }
    public int getLives() { return lives; }
    public int getScore() { return score; }
    public Image getImage() {
        if (frames != null && frames.length > 0) {
            return frames[currentFrameIndex];
        }
        return image;
    }
    public boolean isGameOver(){ return isGameOver; }
    public boolean hasReachedPortal(){ return hasReachedPortal; }

    public void setLives(int lives) { this.lives = lives; }
    public void setScore(int score) { this.score = score; }
    public void setX(double x){ this.x = x; }
    public void setY(double y){ this.y = y; }
    public void setStartX(double startX){ this.startX = startX; }
    public void setStartY(double startY){ this.startY = startY; }

    public void startMoving(int newDx, int newDy) {
        if (!isMoving) {
            this.currentSpeed = BASE_SPEED; // Reset della velocità al movimento manuale coi tasti
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

    // --- METODO PER I TRAMPOLINI ---
    private void deflect(int newDx, int newDy) {
        this.isMoving = false; // Forza lo sblocco per permettere il nuovo startMoving
        startMoving(newDx, newDy);
        this.currentSpeed = 10.0; // Applica il Boost di velocità x2
    }

    public void update(int[][] map, int tileSize, long now) {

        // --- 1. GESTIONE ANIMAZIONI ---
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

        // --- 2. PRE-CALCOLO DELLA POSIZIONE SUCCESSIVA ---
        double nextX = x + (dx * currentSpeed);
        double nextY = y + (dy * currentSpeed);

        int nextCol = (int) ((nextX + (dx > 0 ? tileSize - 1 : 0)) / tileSize);
        int nextRow = (int) ((nextY + (dy > 0 ? tileSize - 1 : 0)) / tileSize);

        // Controlli di sicurezza per i bordi della mappa
        if (nextRow < 0 || nextRow >= map.length || nextCol < 0 || nextCol >= map[0].length) {
            stop();
            return;
        }

        int nextTile = map[nextRow][nextCol];

        // --- 3. CONTROLLO PREVENTIVO DEI MURI E OSTACOLI ---
        if (nextTile == 3) {
            isMoving = false;
            dx = 0;
            dy = 0;
            this.currentSpeed = BASE_SPEED;
            System.out.println("Sei morto");
            LoseALife();
            return;
        }
        else if (nextTile == 1) {
            isMoving = false;
            dx = 0;
            dy = 0;
            this.currentSpeed = BASE_SPEED;
            x = Math.round(x / tileSize) * tileSize;
            y = Math.round(y / tileSize) * tileSize;
            return;
        }

        // --- 4. LOGICA TRAMPOLINI (Controllo sulla cella d'arrivo) ---
        if (nextTile >= 7 && nextTile <= 10) {
            // Centriamo la navicella esattamente sulla casella del trampolino prima di rimbalzare
            x = nextCol * tileSize;
            y = nextRow * tileSize;

            // Trampolino Bottom-Left (7)
            if (nextTile == 7) {
                if (dx == -1) { deflect(0, -1); }
                else if (dy == 1) { deflect(1, 0); }
                else { stop(); return; }
            }
            // Trampolino Bottom-Right (8)
            else if (nextTile == 8) {
                if (dx == 1) { deflect(0, -1); }
                else if (dy == 1) { deflect(-1, 0); }
                else { stop(); return; }
            }
            // Trampolino Top-Left (9)
            else if (nextTile == 9) {
                if (dx == -1) { deflect(0, 1); }
                else if (dy == -1) { deflect(1, 0); }
                else { stop(); return; }
            }
            // Trampolino Top-Right (10)
            else if (nextTile == 10) {
                if (dx == 1) { deflect(0, 1); }
                else if (dy == -1) { deflect(-1, 0); }
                else { stop(); return; }
            }
            return; // Interrompiamo il frame per applicare subito il deflessione
        }

        // --- 5. RACCOLTA OGGETTI E AGGIORNAMENTO POSIZIONE FINALE ---
        if (nextTile == 4 && !hasReachedPortal) {
            System.out.println("fine livello");
            x = nextX;
            y = nextY;
            hasReachedPortal = true;
            return;
        }
        else if (nextTile == 5) {
            this.score += 10;
            needsHUDUpdate = true;
            map[nextRow][nextCol] = 0;
            x = nextX;
            y = nextY;
        }
        else if (nextTile == 6) {
            this.score += 500;
            needsHUDUpdate = true;
            map[nextRow][nextCol] = 0;
            x = nextX;
            y = nextY;
        }
        else {
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

    public void stop(){
        this.currentSpeed = BASE_SPEED; // Reset velocità
        this.isMoving = false;
        this.dx = 0;
        this.dy = 0;

        this.image = AssetManager.getImage("NavicellaUp");
        this.frames = new Image[]{ this.image, AssetManager.getImage("NavicellaUpHighFlame") };
        this.currentFrameIndex = 0;
    }

    public void resetToStart() {
        this.currentSpeed = BASE_SPEED; // Reset velocità
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
    public void notReachedPortal(){hasReachedPortal = false;}
}