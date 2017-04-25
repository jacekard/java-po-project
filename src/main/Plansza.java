package main;

import javax.swing.JPanel;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class Plansza extends Applet {

    JPanel panel;

    private int sprite_width = 18;
    private int sprite_height = 18;
    private Sprite pusty_sprite;

    public Plansza(JPanel panel) {
        pusty_sprite = new Sprite(true);
        this.panel = panel;
    }

    public void paint(Graphics g) {
    //public void drawSprite(Sprite sprite, int x, int y) {

//    public void paint(Graphics g) {
//        //g.drawRect(10, 10, PLANSZA_WIDTH, PLANSZA_HEIGHT);
        g.setColor(Color.RED);
        g.fill3DRect(10, 10, 15,15, true);
//        ImageIcon icon = new ImageIcon(sprite.img);
//        icon.setBounds(sprite_width * x, sprite_height * y, sprite_width, sprite_height);
//        //JOptionPane.showMessageDialog(null, label);
//        panel.add(label);
//        //frame.add(panel);
    }

//    public void rysujMape() {
//        for (int y = 0; y < HEIGHT; y++) {
//            for (int x = 0; x < WIDTH; x++) {
//                if (swiat.world[y][x] == null) {
//                    //drawSprite(pusty_sprite, x, y);
//                } else {
//                    //drawSprite(swiat.world[y][x].getSprite(), x, y);
//                    int ilosc = swiat.lista.size();
//                }
//
//                frame.repaint();
//                frame.invalidate();
//                frame.validate();
//            }
//        }
//    }

//    public void wypiszKomunikaty() {
//
//        if (swiat.getKomunikaty().size() == 0)
//            System.out.println("Brak nowych komunikatow");
//
//        for (String str : swiat.getKomunikaty())
//            System.out.println(str);
//
//        swiat.getKomunikaty().clear();
//    }

}



