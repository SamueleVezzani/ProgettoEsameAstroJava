package org.example.progettoesameastrojava.GameEngine;

import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.HashMap;

public class AssetManager {
    private static HashMap<String, Image> sprites = new HashMap<>();

    public static void loadAssets(){
        loadImage("NavicellaUp", "/images/navicellaUp.png");
        loadImage("NavicellaDown", "/images/navicellaDown.png");
        loadImage("NavicellaLeft", "/images/navicellaLeft.png");
        loadImage("NavicellaRight", "/images/navicellaRight.png");
        loadImage("MuroMappa","/images/MuroSingolo.png");
        loadImage("MuroOstacolo", "/images/MuroOstacolo.png");
        loadImage("SfondoSpazio","/images/SfondoSpazio.png");
        loadImage("NavicellaUpHighFlame","/images/NavicellaUpHighFlame.png");
        loadImage("NavicellaRightHighFlame","/images/NavicellaRightHighFlame.png");
        loadImage("NavicellaDownHighFlame","/images/NavicellaDownHighFlame.png");
        loadImage("NavicellaLeftHighFlame","/images/NavicellaLeftHighFlame.png");
        loadImage("PortaleFineLivello","/images/PortaleFineLivello.png");
        loadImage("Stella","/images/Stella.png");
        loadImage("Stellina","/images/Stellina.png");
        loadImage("TrampolinoBottomLeft","/images/TrampolinoBottomLeft.png");
        loadImage("TrampolinoBottomRight","/images/TrampolinoBottomRight.png");
        loadImage("TrampolinoTopLeft","/images/TrampolinoTopLeft.png");
        loadImage("TrampolinoTopRight","/images/TrampolinoTopRight.png");

        loadOriginalImage("SfondoMenu", "/images/SfondoMenu.png");
    }

    private static void loadImage(String key, String filePath) {
        try {
            InputStream is = AssetManager.class.getResourceAsStream(filePath);
            if (is == null) {
                System.err.println("Immagine non trovata: " + filePath);
                return;
            }

            Image img = new Image(is, 32, 32, true, true);

            sprites.put(key, img);
            System.out.println("Caricata e ridimensionata: " + key);
        } catch (Exception e) {
            System.err.println("Errore nel caricamento di " + key + ": " + e.getMessage());
        }
    }

    private static void loadOriginalImage(String key, String filePath) {
        try {
            InputStream is = AssetManager.class.getResourceAsStream(filePath);
            if (is == null) {
                System.err.println("Immagine non trovata: " + filePath);
                return;
            }

            Image img = new Image(is);

            sprites.put(key, img);
            System.out.println("Caricata risoluzione originale: " + key);
        } catch (Exception e) {
            System.err.println("Errore nel caricamento di " + key + ": " + e.getMessage());
        }
    }

    public static Image getImage(String key){
        return sprites.get(key);
    }
}