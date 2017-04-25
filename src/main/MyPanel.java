package main;

import java.awt.*;
import java.awt.geom.*;

import javax.swing.*;

public class MyPanel extends JPanel {

    private Swiat swiat;
    private Sprite empty_sprite;
    private JFrame frame;

    private final int POLE_WIDTH = 30;
    private final int POLE_HEIGHT = 30;
    private final int button_width = 80;
    private final int button_height = 30;
    private final int offset = 10;
    private final int sprite_size = 18;

    private final Font font;

    private int plansza_width;
    private int plansza_height;
    private JTextArea Message;

    public MyPanel(Swiat swiat, int width, int height) {
        this.swiat = swiat;
        this.frame = frame;
        this.plansza_width = width * sprite_size;
        this.plansza_height = height * sprite_size;

        empty_sprite = new Sprite(true);

        setPreferredSize(new Dimension(800, 800));
        setLayout(null);
        font = new Font(Font.SANS_SERIF, 3, 18);

        JButton save_button = new JButton("save");
        JButton load_button = new JButton("load");
        JButton next_turn = new JButton("nowa tura");
        save_button.setBounds(offset,plansza_height+offset, button_width, button_height);
        load_button.setBounds(button_width + 2*offset,plansza_height+offset, button_width, button_height);
        next_turn.setBounds(2*button_width + 3*offset,plansza_height+offset, button_width + 2*offset, button_height);
        add(save_button);
        add(load_button);
        add(next_turn);


        next_turn.addActionListener(e -> {
            swiat.wykonajTure();
            for(int i = 0; i < swiat.getKomunikaty().size(); i++)
            System.out.println(swiat.getKomunikaty().get(i));
            swiat.getKomunikaty().clear();
            //System.out.println(swiat.lista.size());
        });
        save_button.addActionListener(e -> {
            swiat.save();
        });
        load_button.addActionListener(e -> {
            swiat.load();
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int y = 0; y < swiat.HEIGHT; y++) {
            for (int x = 0; x < swiat.WIDTH; x++) {
                if (swiat.world[y][x] == null)
                    g.drawImage(
                            empty_sprite.img,
                            sprite_size * x,
                            sprite_size * y,
                            this);
                else
                    g.drawImage(
                            swiat.world[y][x].getSprite().img,
                            sprite_size * x,
                            sprite_size * y,
                            this);
            }
        }
/*
        for( int i = 0; i < swiat.getKomunikaty().size(); i++ ) {
            Message = new JTextArea(swiat.getKomunikaty().get(i));
            Message.setBounds(plansza_width+5*offset, offset + i*button_height, 300, button_height);
            Message.setFont(font);
            add(Message);

        }
*/

        repaint();
    }
}