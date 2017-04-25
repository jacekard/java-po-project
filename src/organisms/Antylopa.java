package organisms;

import main.Sprite;
import main.Swiat;
import main.Util;
import main.point;

public class Antylopa extends Zwierze {

    public Antylopa(Swiat swiat) {
        super(4, 4, null, 0, "ANTYLOPA", swiat);
        allocate();
        sprite = new Sprite(super.setProperSprite(this.rodzaj));
    }

    private Antylopa(Swiat swiat, int x, int y) {
        super(4, 4, null, 0, "ANTYLOPA", swiat);
        sprite = new Sprite(super.setProperSprite(this.rodzaj));
        this.pos.x = x;
        this.pos.y = y;
        allocate();

    }

    public boolean czyOdbilAtak(Organizm atakujacy) {
        if(Util.los(0, 1) == 1) {
            this.reallocate();
            swiat.komentuj(this.rodzaj + " ucieka przed walka z " + atakujacy.getRodzaj() + "! +");
            return true;
        }
	else return false;
    }

    public point ruch() {
        int kierunek_ruchu = Util.los(0, 3);
        point tmp = new point();
        if (kierunek_ruchu == 0)
            tmp.y-=2;
        else if (kierunek_ruchu == 1)
            tmp.y+=2;
        else if (kierunek_ruchu == 2)
            tmp.x-=2;
        else if (kierunek_ruchu == 3)
            tmp.x+=2;

        return tmp;
    }

    public void rozmnazanie() {
        Organizm child = new Antylopa(swiat, pos.x, pos.y);
        swiat.lista.add(child);
        swiat.sortujInicjatywa();
        swiat.komentuj("Urodzila sie mala szybka antylopa!");
    }
}
