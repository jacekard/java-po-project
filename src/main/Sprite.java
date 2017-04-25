package main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sprite {

    public BufferedImage img;

    public Sprite(String path) {
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Sprite(boolean czyPusty) {
        try {
            img = ImageIO.read(new File("src/sprites/empty.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
