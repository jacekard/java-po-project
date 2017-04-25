package organisms;

import main.Sprite;
import main.Swiat;

public class Trawa extends Roslina {

    public Trawa(Swiat swiat) {
        super(0, 0, null, 0, "TRAWA", swiat);
        sprite = new Sprite(super.setProperSprite(this.rodzaj));
        maxZakresLosowania = 30;
        allocate();
    }

    private Trawa(Swiat swiat, int x, int y) {
        super(0, 0, null, 0, "TRAWA", swiat);
        sprite = new Sprite(super.setProperSprite(this.rodzaj));
        maxZakresLosowania = 30;
        this.pos.x = x;
        this.pos.y = y;
        allocate();
    }

    public void rozmnazanie() {
        Organizm child = new Trawa(swiat, pos.x, pos.y);
        swiat.lista.add(child);
        swiat.sortujInicjatywa();
    }
}
