package organisms;

import main.Sprite;
import main.Swiat;
import main.Util;
import main.point;

public class Zolw extends Zwierze {

    public Zolw(Swiat swiat) {
        super(4, 4, null, 0, "ZOLW", swiat);
        allocate();
        sprite = new Sprite(super.setProperSprite(this.rodzaj));
    }

    private Zolw(Swiat swiat, int x, int y) {
        super(4, 4, null, 0, "ZOLW", swiat);
        sprite = new Sprite(super.setProperSprite(this.rodzaj));
        this.pos.x = x;
        this.pos.y = y;
        allocate();

    }

    public boolean czyOdbilAtak(Organizm other) {
        if (other.getSila() < 5) {
            other.setPosx(other.getOldPosx());
            other.setPosy(other.getOldPosy());
            swiat.komentuj(" + ZOLW odbija atak! +");
            return true;
        } else return false;
    }

    public void rozmnazanie() {
        Organizm child = new Zolw(swiat, pos.x, pos.y);
        swiat.lista.add(child);
        swiat.sortujInicjatywa();
        swiat.komentuj("Urodzil sie maly powolny zolw!");
    }

    public point ruch() {
        point tmp = new point();
        if (Util.los(1, 4) == 2) {

            int kierunek_ruchu = Util.los(0, 3);
            if (kierunek_ruchu == 0)
                tmp.y -= 1;
            else if (kierunek_ruchu == 1)
                tmp.y += 1;
            else if (kierunek_ruchu == 2)
                tmp.x -= 1;
            else if (kierunek_ruchu == 3)
                tmp.x += 1;
        }
        return tmp;
    }
}
