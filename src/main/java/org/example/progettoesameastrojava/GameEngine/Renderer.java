package org.example.progettoesameastrojava.GameEngine;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Renderer {
    //attributi
    private GraphicsContext gc;
    private final Canvas canvas;
    //metodi
    public Renderer(Canvas canvas){
        gc = canvas.getGraphicsContext2D();
        this.canvas = canvas;
    }
    public void render(double playerX, double playerY){
        gc.clearRect(0,0,canvas.getWidth(), canvas.getHeight());
    }
    public void renderMap(int[][] map, int tileSize) {
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                int tileType = map[row][col];
                //dentro le virgolette mettere il nome delle chiavi delle sprite per muri
                String key = switch (tileType) {
                    case 0 -> "";
                    case 1 -> "";
                    case 2 -> "";
                    case 3 -> "";
                    case 4 -> "";
                    case 5 -> "";
                    default -> "";
                };
                Image img = AssetManager.getImage(key);
                gc.drawImage(img, col * tileSize, row * tileSize);
            }
        }
    }
