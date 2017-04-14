import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Screen {
    public static void windowApplication() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,300);

        JButton button = new JButton();
        button.setText("Witaj!");
    }
}
