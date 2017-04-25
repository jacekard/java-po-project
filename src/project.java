import main.*;

import java.awt.*;
import java.io.IOException;

public class project {

    public static void main(String []args) {

//        Screen screen = new Screen();
//        screen.initializeGame();

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new MyFrame();
            }
        });
    }

}
