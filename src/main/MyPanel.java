package main;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.*;
import java.util.Arrays;

import javax.swing.*;

public class MyPanel extends JPanel implements MouseListener {

    private Swiat swiat;
    private Sprite empty_sprite;
    private JFrame frame;

    private final int POLE_WIDTH = 30;
    private final int POLE_HEIGHT = 30;
    private final int button_width = 80;
    private final int button_height = 30;
    private final int offset = 10;
    private final int sprite_size = 20;
    private int mouseX;
    private int mouseY;
    private String organism_choice = "TRAWA";

    private final Font font;

    private int plansza_width;
    private int plansza_height;
    private JTextArea Message;

    public MyPanel(Swiat swiat, JFrame frame, int width, int height) {
        this.swiat = swiat;
        this.frame = frame;
        this.plansza_width = width * sprite_size;
        this.plansza_height = height * sprite_size;

        empty_sprite = new Sprite(true);

        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        setPreferredSize(new Dimension(300 + plansza_width, 200 + plansza_height));
        setLayout(null);
        addMouseListener(this);
        setFocusable(true);
        font = new Font(Font.SANS_SERIF, 3, 18);

        String[] dialog_choice = {"Antylopa", "Lis", "Owca", "Wilk", "Zolw", "Trawa",
                "Mlecz", "Guarana", "Barszcz"};
        JComboBox comboBox = new JComboBox(dialog_choice);
        comboBox.setBounds(3 * button_width, plansza_height + 2 * offset + button_height, 150, 30);
        comboBox.setSelectedIndex(0);
        comboBox.setFocusable(false);
        add(comboBox);
        comboBox.addActionListener(e -> {
            organism_choice = (String) comboBox.getSelectedItem();
        });

        JButton save_button = new JButton("save");
        JButton load_button = new JButton("load");
        JButton next_turn = new JButton("nowa tura");
        JButton tarcza_alzura = new JButton("tarcza alzura");


        save_button.setBounds(offset, plansza_height + offset, button_width, button_height);
        load_button.setBounds(button_width + 2 * offset, plansza_height + offset, button_width, button_height);
        next_turn.setBounds(2 * button_width + 3 * offset, plansza_height + offset, button_width + 2 * offset, button_height);
        tarcza_alzura.setBounds(offset, plansza_height + 2 * offset + button_height, 2 * button_width, button_height);


        save_button.setFocusable(false);
        load_button.setFocusable(false);
        next_turn.setFocusable(false);
        tarcza_alzura.setFocusable(false);


        add(save_button);
        add(load_button);
        add(next_turn);
        add(tarcza_alzura);


        next_turn.addActionListener(e -> {
            if (!swiat.getCzyKoniec()) {
                swiat.wykonajTure();
                for (int i = 0; i < swiat.getKomunikaty().size(); i++)
                    System.out.println(swiat.getKomunikaty().get(i));
                swiat.getKomunikaty().clear();
            } else {
                System.out.println("Koniec symulacji!");
                setVisible(false);
                frame.setVisible(false);
                this.frame = null;
            }
        });
        save_button.addActionListener(e -> {
            swiat.save();
        });
        load_button.addActionListener(e -> {
            swiat.load();
        });
        tarcza_alzura.addActionListener(e -> {
            swiat.player.skill();
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
                            (sprite_size) * x,
                            (sprite_size) * y,
                            this);
                else
                    g.drawImage(
                            swiat.world[y][x].getSprite().img,
                            (sprite_size) * x,
                            (sprite_size) * y,
                            this);
            }
        }

        repaint();
    }


    public void mousePressed(MouseEvent e) {
        mouseX = (e.getX()) / sprite_size;
        mouseY = (e.getY()) / sprite_size;

        if (swiat.world[mouseY][mouseX] == null)
            swiat.addingNewOrganism(swiat, organism_choice, mouseX, mouseY);

        repaint();
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }
}