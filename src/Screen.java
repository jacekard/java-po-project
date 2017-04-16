import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;

public class Screen{
    public static void windowApplication() {
        JFrame frame = new JFrame();
        JButton welcome_button = new JButton("save");
        JButton exit_button = new JButton("load");
        //frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        //button
        frame.getContentPane().add(BorderLayout.WEST, welcome_button);
        frame.getContentPane().add(BorderLayout.EAST, exit_button);

        frame.setVisible(true);
        //to jest galaz deweloperska
    }
}
