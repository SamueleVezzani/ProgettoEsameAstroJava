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
    private final double zoom = 1.2;

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

        double visibleWidth = canvas.getWidth() / zoom;
        double visibleHeight = canvas.getHeight() / zoom;

        double targetX = player.getX() - (visibleWidth / 2);
        double targetY = player.getY() - (visibleHeight / 2);

        cameraX += (targetX - cameraX) * lerpFactor;
        cameraY += (targetY - cameraY) * lerpFactor;

        double mapWidth = map[0].length * tileSize;
        double mapHeight = map.length * tileSize;

        double maxCameraX = Math.max(0, mapWidth - visibleWidth);
        double maxCameraY = Math.max(0, mapHeight - visibleHeight);

        cameraX = Math.max(0, Math.min(cameraX, maxCameraX));
        cameraY = Math.max(0, Math.min(cameraY, maxCameraY));

        renderMap(map, tileSize, cameraX, cameraY);
        renderPlayer(player, cameraX, cameraY, tileSize);
    }
    private void renderPlayer(Player player, double cameraX, double cameraY, int tileSize) {
        double scaledTileSize = tileSize * zoom;

        double drawX = (player.getX() - cameraX) * zoom;
        double drawY = (player.getY() - cameraY) * zoom;

        gc.drawImage(player.getImage(), drawX, drawY, scaledTileSize, scaledTileSize);
    }

    private void renderMap(int[][] map, int tileSize, double cameraX, double cameraY) {
        // 1. Definiamo le dimensioni "effettive" di un tile dopo lo zoom
        double scaledTileSize = tileSize * zoom;

        // 2. Calcoliamo quanto spazio è visibile (in tile) tenendo conto dello zoom
        // Se zoommiamo, vediamo meno tile, quindi dividiamo per lo zoom
        int viewWidth = (int) Math.ceil(canvas.getWidth() / scaledTileSize) + 1;
        int viewHeight = (int) Math.ceil(canvas.getHeight() / scaledTileSize) + 1;

        // 3. Coordinate di partenza (pixel mondo / dimensione tile)
        int startCol = (int) (cameraX / tileSize);
        int startRow = (int) (cameraY / tileSize);

        // 4. Ciclo di rendering
        for (int row = Math.max(0, startRow); row < Math.min(startRow + viewHeight + 1, map.length); row++) {
            for (int col = Math.max(0, startCol); col < Math.min(startCol + viewWidth + 1, map[0].length); col++) {

                // Calcolo posizione: (Posizione Mondo - Camera) * Zoom
                double drawX = (col * tileSize - cameraX) * zoom;
                double drawY = (row * tileSize - cameraY) * zoom;

                if (map[row][col] == 1) {
                    gc.drawImage(AssetManager.getImage("MuroMappa"), drawX, drawY, scaledTileSize, scaledTileSize);
                }
            }
        }
    }
}