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
        loadImage("Navicella", "/images/navicella.png");
    }

    private static void loadImage(String key, String filePath) {
        try {
            // Il percorso deve essere assoluto all'interno delle risorse, es. "/images/Navicella.png"
            InputStream is = AssetManager.class.getResourceAsStream(filePath);
            if (is == null) {
                System.err.println("Immagine non trovata: " + filePath);
                return;
            }
            Image img = new Image(is);
            sprites.put(key, img);
            System.out.println("Caricata: " + key);
        } catch (Exception e) {
            System.err.println("Errore nel caricamento di " + key + ": " + e.getMessage());
        }
    }


    public static Image getImage(String key){
        return sprites.get(key);
    }


}
