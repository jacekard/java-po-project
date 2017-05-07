package organisms;

import main.Sprite;
import main.Swiat;

public class Jagody extends Roslina {

    public Jagody(Swiat swiat) {
        super(99, 0, null, 0, "JAGODY", swiat);
        sprite = new Sprite(super.setProperSprite(this.rodzaj));
        maxZakresLosowania = 60;
        allocate();
    }

    public Jagody(Swiat swiat, int x, int y) {
        super(99, 0, null, 0, "JAGODY", swiat);
        sprite = new Sprite(super.setProperSprite(this.rodzaj));
        maxZakresLosowania = 60;
        this.pos.x = x;
        this.pos.y = y;
        allocate();
    }

    public void rozmnazanie() {
        Organizm child = new Jagody(swiat, pos.x, pos.y);
        swiat.lista.add(child);
        swiat.sortujInicjatywa();
    }

    public void kolizja(Organizm other) {
        swiat.komentuj(" + " + other.getRodzaj() + " ginie otruty przez " + rodzaj + "! + ");
        swiat.world[other.getOldPosy()][other.getOldPosx()] = null;
        other.die();
        swiat.world[pos.y][pos.x] = null;
        this.die();
    }
}