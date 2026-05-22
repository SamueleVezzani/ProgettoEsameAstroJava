package org.example.progettoesameastrojava.GameEngine;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

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
}
