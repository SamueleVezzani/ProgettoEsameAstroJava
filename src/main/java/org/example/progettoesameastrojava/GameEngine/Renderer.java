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

        double targetX = player.getX() - (canvas.getWidth() / 2);
        double targetY = player.getY() - (canvas.getHeight() / 2);

        cameraX += (targetX - cameraX) * lerpFactor;
        cameraY += (targetY - cameraY) * lerpFactor;

        double mapWidth = map[0].length * tileSize;
        double mapHeight = map.length * tileSize;

        cameraX = Math.max(0, Math.min(cameraX, mapWidth - canvas.getWidth()));

        cameraY = Math.max(0, Math.min(cameraY, mapHeight - canvas.getHeight()));

        renderMap(map, tileSize, cameraX, cameraY);
        renderPlayer(player, cameraX, cameraY, tileSize);
    }

    private void renderPlayer(Player player, double cameraX, double cameraY, int tileSize) {

        double drawX = player.getX() - cameraX;
        double drawY = player.getY() - cameraY;

        gc.drawImage(player.getImage(), drawX, drawY);
    }

    private void renderMap(int[][] map, int tileSize, double cameraX, double cameraY) {
        int viewWidth = (int) Math.ceil(canvas.getWidth() / tileSize) + 1;
        int viewHeight = (int) Math.ceil(canvas.getHeight() / tileSize) + 1;

        int startCol = (int) (cameraX / tileSize);
        int startRow = (int) (cameraY / tileSize);

        for (int row = Math.max(0, startRow); row < Math.min(startRow + viewHeight, map.length); row++) {
            for (int col = Math.max(0, startCol); col < Math.min(startCol + viewWidth, map[0].length); col++) {


                double drawX = (col * tileSize) - cameraX;
                double drawY = (row * tileSize) - cameraY;

                if (map[row][col] == 1) {
                    gc.drawImage(AssetManager.getImage("MuroMappa"), drawX, drawY);
                }
            }
        }
    }
}