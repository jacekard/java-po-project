package organisms;

import main.Sprite;
import main.Swiat;
import main.Util;

public class Mlecz extends Roslina {

    public Mlecz(Swiat swiat) {
        super(0, 0, null, 0, "MLECZ", swiat);
        sprite = new Sprite(super.setProperSprite(this.rodzaj));
        maxZakresLosowania = 60;
        allocate();
    }

    public Mlecz(Swiat swiat, int x, int y) {
        super(0, 0, null, 0, "MLECZ", swiat);
        sprite = new Sprite(super.setProperSprite(this.rodzaj));
        maxZakresLosowania = 60;
        this.pos.x = x;
        this.pos.y = y;
        allocate();
    }

    public void akcja() {
        grow();
        for (int i = 0; i < 3; i++) {
            if (czyRosnie) {
                if (Util.los(1, maxZakresLosowania) == 1)
                    if (rozsiewanie())
                        rozmnazanie();
            }
        }
    }

    public void rozmnazanie() {
        Organizm child = new Mlecz(swiat, pos.x, pos.y);
        swiat.lista.add(child);
        swiat.sortujInicjatywa();
    }
}