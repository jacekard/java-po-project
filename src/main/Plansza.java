package main;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;

public class Plansza extends JComponent {

    public final int PLANSZA_WIDTH = 400;
    public final int PLANSZA_HEIGHT = 400;

    public void paint(Graphics g) {
        //g.drawRect(10, 10, PLANSZA_WIDTH, PLANSZA_HEIGHT);
        g.setColor(Color.WHITE);
        g.fill3DRect(10,10,PLANSZA_WIDTH, PLANSZA_HEIGHT, true);
    }
}
