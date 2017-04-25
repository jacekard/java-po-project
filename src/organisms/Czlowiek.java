package organisms;

import main.Sprite;
import main.Swiat;

import java.util.Scanner;

public class Czlowiek extends Zwierze {
    private int coolDown;
    private int skillEnabled;

    public Czlowiek(Swiat swiat) {
        super(5, 4, null, 0, "CZLOWIEK", swiat);
        allocate();
        coolDown = 0;
        skillEnabled = 5;
        sprite = new Sprite(super.setProperSprite(this.rodzaj));
//        addKeyListener(this);
        Scanner scanner = new Scanner(System.in);
    }

    public Czlowiek(Swiat swiat, int x, int y) {
        super(5, 4, null, 0, "CZLOWIEK", swiat);
        sprite = new Sprite(super.setProperSprite(this.rodzaj));
        this.pos.x = x;
        this.pos.y = y;
        coolDown = 0;
        skillEnabled = 5;
        allocate();
    }

    public void akcja() {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("");
        old_pos = pos;

        if (--skillEnabled < 0)
            swiat.setTarczaAlzura(false);

//        String userInput = "default";
//        try {
//            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//            userInput = in.readLine();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        switch(userInput) {
//
//            case "w":
//                if (pos.y - 1 > 0)
//                    pos.y--;
//                break;
//            case "s":
//                if (pos.y + 1 <= swiat.HEIGHT - 1)
//                    pos.y++;
//                break;
//            case "a":
//                if (pos.x - 1 > 0)
//                    pos.x--;
//                break;
//            case "d" :
//                if (pos.x + 1 <= swiat.WIDTH - 1)
//                    pos.x++;
//                break;
//            default:
//                break;
//        }
        //Obsluga klawiszy!

        if (swiat.world[pos.y][pos.x] != null
                && swiat.world[pos.y][pos.x] != this)
            swiat.world[pos.y][pos.x].kolizja(this);
	    else {
            swiat.world[old_pos.y][old_pos.x] = null;
            swiat.world[pos.y][pos.x] = this;
        }

        grow();
        coolDown--;
    }

//    public void keyPressed(KeyEvent e) {
//        int keyCode = e.getKeyCode();
//        switch( keyCode ) {
//            case KeyEvent.VK_UP:
//                if (pos.y - 1 > 0)
//                    pos.y--;
//                break;
//            case KeyEvent.VK_DOWN:
//                if (pos.y + 1 <= swiat.HEIGHT - 1)
//                    pos.y++;
//                break;
//            case KeyEvent.VK_LEFT:
//                if (pos.x - 1 > 0)
//                    pos.x--;
//                break;
//            case KeyEvent.VK_RIGHT :
//                if (pos.x + 1 <= swiat.WIDTH - 1)
//                    pos.x++;
//                break;
//        }
//
//        swiat.Rysuj();
//    }
//
//    public void keyTyped(KeyEvent e) {}
//    public void keyReleased(KeyEvent e) {}
//    public void actionPerformed(ActionEvent e) {
//        swiat.frame.repaint();
//    }

    public void rozmnazanie() {
        Organizm child = new Czlowiek(swiat, pos.x, pos.y);
        swiat.lista.add(child);
        swiat.sortujInicjatywa();
        swiat.komentuj("Urodzil sie maly szary wilk!");
    }
}
