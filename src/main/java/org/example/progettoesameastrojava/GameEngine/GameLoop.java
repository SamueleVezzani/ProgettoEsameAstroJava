package org.example.progettoesameastrojava.GameEngine;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import org.example.progettoesameastrojava.Entities.GameMap;
import org.example.progettoesameastrojava.Entities.Player;

import static com.sun.scenario.effect.impl.prism.PrEffectHelper.render;
import static jdk.jfr.internal.consumer.EventLog.update;

public class GameLoop extends AnimationTimer{
    private GameMap map;
    private Player player;
    private GraphicsContext graphicsContext;

    public GameLoop(GraphicsContext graphicsContext){
        this.graphicsContext=graphicsContext;
        this.map=new GameMap();
        this.player=new Player();
    }

    @Override
    public void handle(long now) {
        update();
        render();
    }

    private void update(){
        player.update(map);
    }

    private void render() {
        graphicsContext.clearRect(0, 0, 800, 600);
        map.render(graphicsContext);
        player.render(graphicsContext);
    }
}
