package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyFrame extends JFrame implements KeyListener {
    public JFrame frame;
    public Swiat swiat;
    private final int sprite_size = 20;

    public MyFrame(int WIDTH, int HEIGHT) {
        super("Wirtualny Swiat -- Jacek Ardanowski 165178");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        addKeyListener(this);

        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        swiat = new Swiat(WIDTH, HEIGHT);
        JPanel panel = new MyPanel(swiat, frame, WIDTH, HEIGHT);
        add(panel);
        pack();
        setVisible(true);
    }

    public void keyPressed(KeyEvent e) {
        if (!swiat.ifKeyWasPressed) {
            swiat.ifKeyWasPressed = true;

            swiat.player.old_pos = new point(swiat.player.getPosx(), swiat.player.getPosy());

            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_UP:
                    if(swiat.player.getPosy() - 1 >= 0)
                    swiat.player.setPosy(swiat.player.getPosy() - 1);
                    break;
                case KeyEvent.VK_DOWN:
                    if(swiat.player.getPosy() + 1 < swiat.HEIGHT)
                    swiat.player.setPosy(swiat.player.getPosy() + 1);
                    break;
                case KeyEvent.VK_LEFT:
                    if(swiat.player.getPosx() - 1 >= 0)
                    swiat.player.setPosx(swiat.player.getPosx() - 1);
                    break;
                case KeyEvent.VK_RIGHT:
                    if(swiat.player.getPosx() + 1 < swiat.WIDTH)
                    swiat.player.setPosx(swiat.player.getPosx() + 1);
                    break;
            }

        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }


}
