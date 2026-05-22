package org.example.progettoesameastrojava.GameEngine;

import javafx.scene.image.Image;
import java.util.HashMap;
import java.util.Objects;

public class AssetManager {
    //attributi
    private static HashMap <String, Image> sprites;
    //metodi
    public static void loadAssets(){
        //utilizzare
        //loadImage("<nome sprite>","/resources/images/<sprite.png>");

    }
    private static void loadImage(String key, String path) {
        try {
            Image img = new Image(Objects.requireNonNull(AssetManager.class.getResourceAsStream(path)));
            sprites.put(key, img);
        } catch (Exception e) {
            System.err.println("Impossibile caricare l'immagine: " + path);
        }
    }


    public static Image getImage(String key){
        return sprites.get(key);
    }


}
