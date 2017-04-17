import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Screen extends JPanel{

    GridBagConstraints gbc = new GridBagConstraints();

    public Screen() {
        setLayout(null);
    }

    public static void windowApplication() {
        int button_width = 80;
        int button_height = 30;
        int offset = 10;

        JFrame frame = new JFrame();
        Plansza plansza = new Plansza();
        frame.setTitle("Wirtualny Swiat -- Jacek Ardanowski 165178");
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(plansza);
        frame.setVisible(true);


        JPanel panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);

        JButton save_button = new JButton("save");
        save_button.setBounds(offset,plansza.PLANSZA_HEIGHT+button_height,button_width,button_height);
        panel.add(save_button);
        JButton load_button = new JButton("load");
        load_button.setBounds(2*offset+button_width,plansza.PLANSZA_HEIGHT+button_height,button_width,button_height);
        panel.add(load_button);


    }
}
