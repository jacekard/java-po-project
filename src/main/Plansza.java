package main;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;

public class Plansza extends JComponent {

    public JPanel panel;
    public JFrame frame;

    private int sprite_width = 18;
    private int sprite_height = 18;

    public Plansza(JFrame frame, JPanel panel) {
        this.panel = panel;
        this.frame = frame;
    }

    public void drawSprite(Sprite sprite, int x, int y) {
        ImageIcon icon = new ImageIcon(sprite.img);
        JLabel label = new JLabel(icon);
        label.setBounds(sprite_width * x, sprite_height * y,sprite_width,sprite_height);
        //JOptionPane.showMessageDialog(null, label);
        panel.add(label);
        frame.repaint();
//        frame.invalidate();
//        frame.validate();
    }
//
//    public void paint(Graphics g) {
//        //g.drawRect(10, 10, PLANSZA_WIDTH, PLANSZA_HEIGHT);
//        g.setColor(Color.WHITE);
//        g.fill3DRect(10, 10, PLANSZA_WIDTH, PLANSZA_HEIGHT, true);
//    }

}
