import main.*;

import java.awt.*;
import java.io.IOException;

public class project {

    public static void main(String []args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new MyFrame();
            }
        });
    }



    //TODO:
    //1. Obsluga myszy (funkcja respawnowania wybranych zwierzat)
    //2. Obsluga poruszania czlowiekiem
    //3. Dodanie reszty klas


}
