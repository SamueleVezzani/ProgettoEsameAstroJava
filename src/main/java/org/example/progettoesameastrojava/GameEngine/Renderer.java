package org.example.progettoesameastrojava.GameEngine;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.example.progettoesameastrojava.GameEngine.Entities.Player;

public class Renderer {
    //attributi
    private GraphicsContext gc;
    private final Canvas canvas;
    private double cameraX = 0;
    private double cameraY = 0;
    private final double lerpFactor = 0.1;
    private final double zoom = 1.5;

    public Renderer(Canvas canvas) {
        gc = canvas.getGraphicsContext2D();
        this.canvas = canvas;
    }

    private int[] getCameraOffset(int[][] map, Player player, int tileSize, int viewWidth, int viewHeight ) {

        int playerCol = (int) (player.getX() / tileSize);
        int playerRow = (int) (player.getY() / tileSize);

        int startCol = Math.max(0, Math.min(playerCol - (viewWidth / 2), map[0].length - viewWidth));
        int startRow = Math.max(0, Math.min(playerRow - (viewHeight / 2), map.length - viewHeight));

        return new int[]{startCol, startRow};
    }

    public void render(Player player, int[][] map, int tileSize) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        double canvasWidth = this.canvas.getWidth();
        double canvasHeight = this.canvas.getHeight();

        // Sfondo scalato sul Canvas
        gc.drawImage(AssetManager.getImage("SfondoSpazio"), 0, 0, canvasWidth, canvasHeight);

        double visibleWidth = canvasWidth / zoom;
        double visibleHeight = canvasHeight / zoom;

        double targetX = player.getX() - (visibleWidth / 2.0);
        double targetY = player.getY() - (visibleHeight / 2.0);

        cameraX += (targetX - cameraX) * lerpFactor;
        cameraY += (targetY - cameraY) * lerpFactor;

        double mapWidth = map[0].length * tileSize;
        double mapHeight = map.length * tileSize;

        // Se la finestra è più grande della mappa, calcola l'offset per centrarla
        double offsetX = 0;
        double offsetY = 0;

        if (mapWidth * zoom < canvasWidth) {
            offsetX = (canvasWidth - (mapWidth * zoom)) / 2.0;
            cameraX = 0;
        } else {
            double maxCameraX = mapWidth - visibleWidth;
            cameraX = Math.max(0, Math.min(cameraX, maxCameraX));
        }

        if (mapHeight * zoom < canvasHeight) {
            offsetY = (canvasHeight - (mapHeight * zoom)) / 2.0;
            cameraY = 0;
        } else {
            double maxCameraY = mapHeight - visibleHeight;
            cameraY = Math.max(0, Math.min(cameraY, maxCameraY));
        }

        renderMap(map, tileSize, cameraX, cameraY, offsetX, offsetY);
        renderPlayer(player, cameraX, cameraY, tileSize, offsetX, offsetY);
    }

    private void renderPlayer(Player player, double cameraX, double cameraY, int tileSize, double offsetX, double offsetY) {
        double scaledTileSize = tileSize * zoom;

        double drawX = ((player.getX() - cameraX) * zoom) + offsetX;
        double drawY = ((player.getY() - cameraY) * zoom) + offsetY;

        gc.drawImage(player.getImage(), drawX, drawY, scaledTileSize, scaledTileSize);
    }

    private void renderMap(int[][] map, int tileSize, double cameraX, double cameraY, double offsetX, double offsetY) {
        double scaledTileSize = tileSize * zoom;

        int viewWidth = (int) Math.ceil(canvas.getWidth() / scaledTileSize) + 1;
        int viewHeight = (int) Math.ceil(canvas.getHeight() / scaledTileSize) + 1;

        int startCol = (int) (cameraX / tileSize);
        int startRow = (int) (cameraY / tileSize);

        for (int row = Math.max(0, startRow); row < Math.min(startRow + viewHeight + 1, map.length); row++) {
            for (int col = Math.max(0, startCol); col < Math.min(startCol + viewWidth + 1, map[0].length); col++) {

                double drawX = ((col * tileSize - cameraX) * zoom) + offsetX;
                double drawY = ((row * tileSize - cameraY) * zoom) + offsetY;

                if (map[row][col] == 1) {
                    gc.drawImage(AssetManager.getImage("MuroMappa"), drawX, drawY, scaledTileSize, scaledTileSize);
                }
                if (map[row][col] == 3) {
                    gc.drawImage(AssetManager.getImage("MuroOstacolo"), drawX, drawY, scaledTileSize, scaledTileSize);
                }
                if(map[row][col] == 4) {
                    gc.drawImage(AssetManager.getImage("PortaleFineLivello"), drawX, drawY, scaledTileSize, scaledTileSize);
                }
                if(map[row][col] == 5){
                    gc.drawImage(AssetManager.getImage("Stellina"), drawX, drawY, scaledTileSize, scaledTileSize);
                }
                if(map[row][col] == 6){
                    gc.drawImage(AssetManager.getImage("Stella"), drawX + 8, drawY + 8, scaledTileSize - 16, scaledTileSize - 16);
                }
            }
        }
    }
}