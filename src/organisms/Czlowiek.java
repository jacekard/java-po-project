package organisms;

import main.Sprite;
import main.Swiat;
import main.point;

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

    public void rozmnazanie() {}

    public void akcja() {

        if(swiat.getTarczaAlzura() == true)
        System.out.println("tarcza ON");
        else
        System.out.println("tarcza OFF");


        if (--skillEnabled < 0)
            swiat.setTarczaAlzura(false);

        swiat.ifKeyWasPressed = false;

        if (swiat.world[pos.y][pos.x] != null
                && swiat.world[pos.y][pos.x] != this) {
            if (!swiat.getTarczaAlzura())
                swiat.world[pos.y][pos.x].kolizja(this);
            //DLA PROSTEJ DEMONSTRACJI UMIEJETNOSCI CZLOWIEKA instanceof Roslina (domyslnie Zwierze)
            else if (swiat.world[pos.y][pos.x] instanceof Roslina) {
                swiat.world[pos.y][pos.x].reallocate();
                swiat.world[old_pos.y][old_pos.x] = null;
                swiat.world[pos.y][pos.x] = this;
            } else {
                swiat.world[old_pos.y][old_pos.x] = null;
                swiat.world[pos.y][pos.x] = this;
            }

        } else {
            swiat.world[old_pos.y][old_pos.x] = null;
            swiat.world[pos.y][pos.x] = this;
        }

        grow();
        coolDown--;
    }

    public void skill() {
        if (!swiat.getTarczaAlzura() && coolDown <= 0) {
            coolDown = 11;
            skillEnabled = 5;
            swiat.setTarczaAlzura(true);
            skillEnabled--;
        }
    }

    public boolean czyOdbilAtak(Organizm atakujacy) {
        if (swiat.getTarczaAlzura()) {
            atakujacy.reallocate();
            swiat.komentuj(this.rodzaj + " uzywa Tarczy Alzura! + ");
            return true;
        }
        else return false;
    }
}
