package main;

import javax.swing.*;

public class MyFrame extends JFrame {

    public MyFrame() {
        super("Wirtualny Swiat -- Jacek Ardanowski 165178");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        Swiat swiat = new Swiat(20,20);
        JPanel panel = new MyPanel(swiat,20, 20);
        add(panel);
        pack();
        setVisible(true);



    }

}
