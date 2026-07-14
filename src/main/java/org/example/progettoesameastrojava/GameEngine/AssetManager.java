package org.example.progettoesameastrojava.GameEngine;

import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Objects;

public class AssetManager {
    //attributi
    private static HashMap<String, Image> sprites = new HashMap<>();

    public static void loadAssets(){
        //utilizzare
        //loadImage("<nome sprite>","/resources/images/<sprite.png>");
        loadImage("NavicellaUp", "/images/navicellaUp.png");
        loadImage("NavicellaDown", "/images/navicellaDown.png");
        loadImage("NavicellaLeft", "/images/navicellaLeft.png");
        loadImage("NavicellaRight", "/images/navicellaRight.png");
        loadImage("MuroMappa","/images/MuroSingolo.png");
        loadImage("MuroOstacolo", "/images/MuroOstacolo.png");
    }

    private static void loadImage(String key, String filePath) {
        try {
            InputStream is = AssetManager.class.getResourceAsStream(filePath);
            if (is == null) {
                System.err.println("Immagine non trovata: " + filePath);
                return;
            }

            // Aggiungiamo i parametri 32, 32 per il ridimensionamento
            // Il costruttore è: Image(InputStream, width, height, preserveRatio, smooth)
            Image img = new Image(is, 32, 32, true, true);

            sprites.put(key, img);
            System.out.println("Caricata e ridimensionata: " + key);
        } catch (Exception e) {
            System.err.println("Errore nel caricamento di " + key + ": " + e.getMessage());
        }
    }


    public static Image getImage(String key){
        return sprites.get(key);
    }


}
