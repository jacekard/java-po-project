package main;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    public JFrame frame;

    public MyFrame(int WIDTH, int HEIGHT) {
        super("Wirtualny Swiat -- Jacek Ardanowski 165178");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        Swiat swiat = new Swiat(WIDTH, HEIGHT);
        JPanel panel = new MyPanel(swiat, WIDTH, HEIGHT);
        add(panel);
        pack();
        setVisible(true);
    }


}
