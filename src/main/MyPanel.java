package main;

import java.awt.*;
import java.awt.geom.*;

import javax.swing.*;

public class MyPanel extends JPanel {

    private Swiat swiat;
    private Sprite empty_sprite;

    private final int PLANSZA_WIDTH = 400;
    private final int PLANSZA_HEIGHT = 400;
    private final int POLE_WIDTH = 30;
    private final int POLE_HEIGHT = 30;
    private final int button_width = 80;
    private final int button_height = 30;
    private final int offset = 10;

    private final int sprite_width = 18;
    private final int sprite_height = 18;


    public MyPanel(Swiat swiat) {
        this.swiat = swiat;
        empty_sprite = new Sprite(true);
        setPreferredSize(new Dimension(800, 800));

        JButton save_button = new JButton("save");
        save_button.setBounds(offset, PLANSZA_HEIGHT + button_height, button_width, button_height);
        add(save_button);
        JButton load_button = new JButton("load");
        load_button.setBounds(2 * offset + button_width, PLANSZA_HEIGHT + button_height, button_width, button_height);
        add(load_button);
        JButton next_turn = new JButton("nowa tura");
        next_turn.setBounds(3 * offset + 2 * button_width, PLANSZA_HEIGHT + button_height, 2 * offset + button_width, button_height);
        add(next_turn);

        next_turn.addActionListener(e -> {
            swiat.wykonajTure();
            System.out.println(swiat.lista.size());
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int y = 0; y < swiat.HEIGHT; y++) {
            for (int x = 0; x < swiat.WIDTH; x++) {
                if (swiat.world[y][x] == null) {
                    g.drawImage(empty_sprite.img, sprite_width * x, sprite_height * y, this);
                }
                else {
                    g.drawImage(swiat.world[y][x].getSprite().img,
                            sprite_width * x, sprite_height * y, this);
                   // int ilosc = swiat.lista.size();
                }
            }
        }

        repaint();
    }
}