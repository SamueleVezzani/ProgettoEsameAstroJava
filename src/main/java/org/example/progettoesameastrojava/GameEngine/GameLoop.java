package org.example.progettoesameastrojava.GameEngine;


import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import org.example.progettoesameastrojava.GameEngine.Entities.Player;
import org.example.progettoesameastrojava.gestionegenerale.SceneManager;
import org.example.progettoesameastrojava.schermatevisive.GameScreen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class GameLoop extends AnimationTimer {
    private Renderer renderer;
    private GameScreen gm;
    private int[][] map;
    private int tileSize = 32;
    private SceneManager sm;
    private Set<KeyCode> activeKeys;
    private Player player;
    public static final double step = 32.0;


    public GameLoop(GameScreen gm, Scene scena, SceneManager sm) {
        this.gm = gm;
        this.sm = sm;
        this.activeKeys = new HashSet<>();
        Canvas canvas = gm.getCanvas();
        this.renderer = new Renderer(canvas);

        loadMap();
        int[] startPos = findPlayerStartPosition();
        player = new Player(startPos[1] * tileSize, startPos[0] * tileSize);

        scena.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W: case UP:    player.startMoving(0, -1); break;
                case S: case DOWN:  player.startMoving(0, 1);  break;
                case A: case LEFT:  player.startMoving(-1, 0); break;
                case D: case RIGHT: player.startMoving(1, 0);  break;
                case ESCAPE:        sm.switchToMenu(); break;
            }
        });
    }

    private int[] findPlayerStartPosition() {
        for (int r = 0; r < map.length; r++) {
            for (int c = 0; c < map[r].length; c++) {
                if (map[r][c] == 2) {
                    return new int[]{r, c};
                }
            }
        }
        return new int[]{0, 0};
    }

    private void loadMap() {
        try (var stream = getClass().getResourceAsStream("/maps/map1")) {
            if (stream == null) throw new IOException("File non trovato!");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
                String line = reader.readLine();
                if (line == null) return;

                String[] dimensioni = line.trim().split("\\s+");
                int righe = Integer.parseInt(dimensioni[0]);
                int colonne = Integer.parseInt(dimensioni[1]);

                map = new int[righe][colonne];

                for (int i = 0; i < righe; i++) {
                    line = reader.readLine();
                    if (line == null) break;
                    String[] values = line.trim().split("\\s+");
                    for (int j = 0; j < colonne && j < values.length; j++) {
                        map[i][j] = Integer.parseInt(values[j]);
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Errore nel caricamento mappa: " + e.getMessage());
            map = new int[0][0];
        }
    }


    @Override
    public void handle(long now) {
        player.update(map, tileSize, now);

        if(player.needsHUDUpdate()){
            this.gm.updateHUD(player.getScore(), player.getLives());
        }
        if(player.isGameOver()){
            gm.showGameOver(this);
            return;
        }

        renderer.render(player, map, tileSize);
    }}
